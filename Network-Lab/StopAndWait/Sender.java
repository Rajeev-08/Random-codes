import java.io.*;
import java.net.*;
import java.util.Scanner;

class Sender {
    public static void main(String args[]) throws Exception {
        Sender sws = new Sender();
        sws.run();
    }

    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames to be sent:");
        int n = sc.nextInt();

        Socket myskt = new Socket("localhost", 9999);
        PrintStream myps = new PrintStream(myskt.getOutputStream());

        for (int i = 0; i <= n;) {
            if (i == n) {
                myps.println("exit");
                break;
            }

            System.out.println("Frame no " + i + " is sent");
            myps.println(i);

            BufferedReader bf = new BufferedReader(new InputStreamReader(myskt.getInputStream()));
            String ack = bf.readLine();

            if (ack != null) {
                System.out.println("Acknowledgement was received from receiver");
                i++;
                Thread.sleep(4000);
            } else {
                myps.println(i);
            }
        }
    }
}
