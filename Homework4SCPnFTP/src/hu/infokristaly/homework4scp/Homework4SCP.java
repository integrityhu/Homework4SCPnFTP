package hu.infokristaly.homework4scp;

import java.io.FileNotFoundException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;


public class Homework4SCP {

    /*
     * http://unix.stackexchange.com/questions/136165/java-code-to-copy-files-from-one-linux-machine-to-another-linux-machine
     * http://stackoverflow.com/questions/24071188/com-jcraft-jsch-jschexception-auth-fail-error
     * 
     * http://commons.apache.org/proper/commons-net/apidocs/org/apache/commons/net/ftp/FTPClient.html
     * 
     */
    public static void main(String[] args) throws JSchException, SftpException, FileNotFoundException {
        SCPTask.main(args);
        FTPTask.main(args);
    }

}
