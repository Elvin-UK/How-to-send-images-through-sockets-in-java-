
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import static javafx.scene.input.KeyCode.I;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Tcpsendfotosocket {
    public static void main(String[] args)throws Exception{
//         Socket socket = new Socket("localhost", 13085);
//        OutputStream outputStream = socket.getOutputStream();
//
//        BufferedImage image = ImageIO.read(new File("C:\\Users\\User\\Desktop\\photo157.jpg"));
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(image, "jpg", byteArrayOutputStream);
//
//        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//        outputStream.write(size);
//        outputStream.write(byteArrayOutputStream.toByteArray());
//        outputStream.flush();
//        System.out.println("Flushed: " + System.currentTimeMillis());
//
//        Thread.sleep(120000);
//        System.out.println("Closing: " + System.currentTimeMillis());
//        socket.close();
//        
        ServerSocket serverSocket = new ServerSocket(13085);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        System.out.println("Reading: " + System.currentTimeMillis());

        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        byte[] imageAr = new byte[size];
        inputStream.read(imageAr);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
        ImageIO.write(image, "jpg", new File("C:\\Users\\User\\Desktop\\photo005.jpg"));

        serverSocket.close();
        
        
    }
}
