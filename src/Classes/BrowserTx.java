package Classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Telas.TelaBrowser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import tx.utilitarios.*;
import org.apache.commons.net.ftp.*;
import sun.net.www.http.HttpClient;

/**
 *
 * @author Taffrel Xavier <taffarel_deus@hotmail.com>
 */
public class BrowserTx extends TXBrowser {

    /**
     *
     * @param btnAbrir
     * @param url
     * @param field
     * @throws IOException
     */
    public static void iniciar(JButton btnAbrir, String url, JTextField field) throws IOException {
        TXProperties.addItem("urls", url);
    }

    public static void getUltimaUrl(JTextField field) {
        field.setText(TXProperties.getProp("urls"));
    }

    /**
     * 
     * @throws IOException 
     */
    public static void entrarNoDispositivo() throws IOException {
        try {
            //O Host Ip
            byte[] ipAddr = new byte[]{(byte) 192, (byte) 168, (byte) 129, (byte) 206};
            //
            InetAddress addr = InetAddress.getByAddress(ipAddr);

            //A porta para se conectar
            int port = 3721;

            FTPClient ftp = new FTPClient();
        
            ftp.connect(addr, port); // or "localhost" in your case

            ftp.login("username", "pass");

            //O diretório defult para entrar    
            //ftp.changeWorkingDirectory("Banda");

            //Envia o arquivo eclodir para o celular
            File fe = new File("C:\\Users\\Taffarel\\Desktop\\Alberto.txt");

            FileInputStream fis = new FileInputStream(fe);
            
            ftp.appendFile("eclodir.txt", fis);

            // list the files of the current directory
            FTPFile[] files = ftp.listFiles();

            System.out.println("Listed " + files.length + " files.");

            for (FTPFile file : files) {
                System.out.println(file.getName());
            }

            // lets pretend there is a JPEG image in the present folder that we want to copy to the desktop (on a windows machine)
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE); // don't forget to change to binary mode! or you will have a scrambled image!

            ftp.disconnect();

        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void metodoNome() {

    }
}
