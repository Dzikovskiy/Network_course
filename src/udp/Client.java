package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket st = new DatagramSocket(12346);
        DatagramPacket dp = null;
        InetAddress loc = InetAddress.getByName("localhost");
        byte[] buf = new byte[100];
        int arr[] = new int[3];
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Enter element "+ i + "]:");
            int k = System.in.read(buf);
            dp = new DatagramPacket(buf, k, loc, 12345);
            st.send(dp);
        }
        dp = new DatagramPacket(buf, 100);
        st.receive(dp);
        System.out.println("The answer is " + new String(dp.getData()));
        st.close();
    }

}
