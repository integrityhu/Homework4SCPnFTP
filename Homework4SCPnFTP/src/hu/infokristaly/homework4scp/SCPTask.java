package hu.infokristaly.homework4scp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SCPTask {

    public static void main(String[] args) throws JSchException, SftpException, FileNotFoundException {
        String remoteDirectoryPath=".";
        JSch jsch = new JSch();
        Session session = null;
        session = jsch.getSession("username","host",22);
        session.setPassword("passwd");
        session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        ChannelSftp channel = null;
        channel = (ChannelSftp)session.openChannel("sftp");
        channel.connect();
            File localFile = new File("/tmp/screenshot.jpg");
            //If you want you can change the directory using the following line.
            channel.cd(remoteDirectoryPath);
        channel.put(new FileInputStream(localFile),localFile.getName());
            channel.disconnect();
        session.disconnect();
    }

}
