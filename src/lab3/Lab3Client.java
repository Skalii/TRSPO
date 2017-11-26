package lab3;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Lab3Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socketClient = new Socket("localhost", 7777);

            System.out.println("Connecting to " + socketClient.getRemoteSocketAddress() + "\n");

            while (true) {
                OutputStream outToServer = socketClient.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                System.out.print("Message to the server: ");
                String str = scanner.nextLine();

                out.writeUTF(str);
                InputStream inFromServer = socketClient.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);

                System.out.println("Server says: " + in.readUTF());
                //socketClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
