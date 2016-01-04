package hu.infokristaly.homework4scp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPTask {

    public static void main(String[] args) {
        FTPClient ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        String server = "host";
        ftp.configure(config);
        boolean error = false;
        try {
          int reply;
          ftp.connect(server);
          System.out.println("Connected to " + server + ".");
          System.out.print(ftp.getReplyString());

          // After connection attempt, you should check the reply code to verify
          // success.
          reply = ftp.getReplyCode();

          if(!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            System.err.println("FTP server refused connection.");
            System.exit(1);
          }
          if (!ftp.login("user", "password"))
          {
              ftp.logout();
              error = true;              
          }
          ftp.setFileType(FTP.BINARY_FILE_TYPE);
          //ftp.enterLocalActiveMode();
          ftp.enterLocalPassiveMode();
          
          InputStream input;
          File local = new File("/tmp/screenshot.jpg"); 
          input = new FileInputStream(local);
          String remote="screenshot.jpg";
          ftp.storeFile(remote, input);

          remote=".";
          for (FTPFile f : ftp.listFiles(remote)) {
              System.out.println(f.getRawListing());
              System.out.println(f.toFormattedString());
          }

          input.close();

          
          ftp.logout();
        } catch(IOException e) {
          error = true;
          e.printStackTrace();
        } finally {
          if(ftp.isConnected()) {
            try {
              ftp.disconnect();
            } catch(IOException ioe) {
              // do nothing
            }
          }
          System.exit(error ? 1 : 0);
        }
    }

}
