package lab5;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Lab5XMLCreateClient {

    public static void main(String argv[]) {
        try {
            Socket socketClient = new Socket("localhost", 7777);
            System.out.println("[CLIENT] - connected to " + socketClient.getRemoteSocketAddress() + "\n");

            DataOutputStream dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socketClient.getInputStream());

            dataOutputStream.writeUTF(
                    "root:cars;" +
                            "parent:supercars;" +
                            "attribute_p:company;" +
                            "attribute_p_value:Ferrari");
            Thread.sleep(100);
            dataOutputStream.writeUTF(
                    "children:carname;" +
                            "attribute_c:type;" +
                            "attribute_c_value:formula one;" +
                            "text:Ferrari 101");
            Thread.sleep(100);
            dataOutputStream.writeUTF(
                    "children:carname;" +
                            "attribute_c:type;" +
                            "attribute_c_value:sports;" +
                            "text:Ferrari 102");
            Thread.sleep(100);
            dataOutputStream.writeUTF("finish");

            String message;

            while (!Objects.equals(message = dataInputStream.readUTF(), "stop")) {
                System.out.println(message);
            }

            dataOutputStream.writeUTF("close");
            socketClient.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}