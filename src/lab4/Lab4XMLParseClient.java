package lab4;

import java.io.*;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Lab4XMLParseClient {

    public static void main(String[] args) {
        try {
            Socket socketClient = new Socket("localhost", 7777);

            System.out.println("[CLIENT] - connected to " + socketClient.getRemoteSocketAddress() + "\n");

            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse("src/lab4/input.txt");
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("student");
            int thisChild = 0;

            DataOutputStream dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socketClient.getInputStream());

            while (thisChild < nodeList.getLength()) {
                int j = 1;

                dataOutputStream.writeUTF(nodeList
                        .item(thisChild)
                        .getNodeName() + "_" + (thisChild + 1));
                System.out.println("[SERVER] - " + dataInputStream.readUTF());

                dataOutputStream.writeUTF(nodeList
                        .item(thisChild).getAttributes()
                        .getNamedItem("rollno").getTextContent());
                System.out.println("[SERVER] - rollno: " + dataInputStream.readUTF());

                dataOutputStream.writeUTF(nodeList
                        .item(thisChild).getChildNodes()
                        .item(j).getTextContent());
                System.out.println("[SERVER] - First Name: " + dataInputStream.readUTF());

                dataOutputStream.writeUTF(nodeList
                        .item(thisChild).getChildNodes()
                        .item(j += 2).getTextContent());
                System.out.println("[SERVER] - Last Name: " + dataInputStream.readUTF());

                dataOutputStream.writeUTF(nodeList
                        .item(thisChild).getChildNodes()
                        .item(j += 2).getTextContent());
                System.out.println("[SERVER] - Nickname: " + dataInputStream.readUTF());

                dataOutputStream.writeUTF(nodeList
                        .item(thisChild++).getChildNodes()
                        .item(j += 2).getTextContent());
                System.out.println("[SERVER] - marks: " + dataInputStream.readUTF());
                System.out.println();

            }

        } catch (SAXException | IOException | ParserConfigurationException e1) {
            e1.printStackTrace();
        }
    }

}