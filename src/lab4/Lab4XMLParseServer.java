package lab4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Lab4XMLParseServer extends Thread {

    private ServerSocket serverSocket;

    public Lab4XMLParseServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        try {
            Socket server = serverSocket.accept();
            System.out.println("[CLIENT] - connected from " + server.getRemoteSocketAddress() + "\n");

            while (true) {
                Thread.sleep(3);
                DataInputStream in = new DataInputStream(server.getInputStream());
                String message;
                try {
                    message = in.readUTF();
                } catch (SocketException e) {
                    break;
                }
                System.out.println("[CLIENT] - message: " + message);
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                System.out.println("[SERVER] - echo message: " + message);
                out.writeUTF(message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            Thread t = new Lab4XMLParseServer(7777);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
