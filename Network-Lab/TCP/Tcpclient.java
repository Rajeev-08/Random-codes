import java.io.*;
import java.net.*;

public class Tcpclient extends Thread {
    Thread t1, t2;
    BufferedReader br, buf;
    PrintWriter pout;
    String str, a;

    Socket client;

    Tcpclient() {
        try {
            client = new Socket("127.0.0.1", 5555);
            br = new BufferedReader(new InputStreamReader(System.in));
            buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            pout = new PrintWriter(client.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("error :" + e);
        }
    }

    public void run() {
        while (true) {
            if (Thread.currentThread() == t1) {
                try {
                    str = br.readLine();
                    pout.println(str);
                } catch (Exception e) {
                    System.out.println("error :" + e);
                }
            } else {
                try {
                    a = buf.readLine();
                    System.out.println("from server :" + a);
                } catch (Exception e) {
                    System.out.println("error :" + e);
                }
            }
        }
    }

    public static void main(String args[]) {
        Tcpclient s = new Tcpclient();
        s.t1 = new Thread(s);
        s.t2 = new Thread(s);
        s.t1.start();
        s.t2.start();
    }
}
