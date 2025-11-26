package PSP.Tema2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println(InetAddress.getLocalHost()); // MIMAQUINA/192.168.1.34
        System.out.println(InetAddress.getByName(null)); // localhost/127.0.0.1
        System.out.println(InetAddress.getByName("ieschandomonte.edu.es")); // ieschandomonte.edu.es/82.98.160.22
        System.out.println(InetAddress.getByName("google.es")); // google.es/173.194.41.247
        System.out.println(InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, 33, 25 })); // /192.168.33.25

        InetAddress ips[] = InetAddress.getAllByName("google.es");
        System.out.println(Arrays.toString(ips));

        InetAddress chando =  InetAddress.getByName("ieschandomonte.edu.es");
        Socket socket = new Socket(chando, 80);

        
    }

}
