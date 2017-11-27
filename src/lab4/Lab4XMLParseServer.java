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
            Socket socketServer = serverSocket.accept();
            System.out.println("[CLIENT] - connected from " + socketServer.getRemoteSocketAddress() + "\n");

            DataInputStream dataInputStream = new DataInputStream(socketServer.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socketServer.getOutputStream());

            while (true) {
                Thread.sleep(3);
                String message;
                try {
                    message = dataInputStream.readUTF();
                } catch (SocketException e) {
                    break;
                }
                System.out.println("[CLIENT] - message: " + message);
                System.out.println("[SERVER] - echo message: " + message);

                dataOutputStream.writeUTF(message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new Lab4XMLParseServer(7777);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}