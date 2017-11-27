package lab5;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Lab5XMLParseServer extends Thread {

    private ServerSocket serverSocket;

    public Lab5XMLParseServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        try {
            Socket socketServer = serverSocket.accept();
            System.out.println("[CLIENT] - connected from " + socketServer.getRemoteSocketAddress() + "\n");

            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element elementRoot = null, elementSupercar = null;
            Attr attributeCarCompany;

            Element[] elementsCars = new Element[2];
            Attr[] attributesCars = new Attr[2];
            int indexChildren = 0;

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

                if (message.startsWith("root:")) {

                    elementRoot = document.createElement(message.substring(5, message.indexOf("parent:") - 1));
                    document.appendChild(elementRoot);

                    elementSupercar = document.createElement(message.substring(message.indexOf("parent:") + 7,
                            message.indexOf("attribute_p:") - 1));
                    elementRoot.appendChild(elementSupercar);

                    attributeCarCompany = document.createAttribute(message.substring(message.indexOf("attribute_p:") + 12,
                            message.indexOf("attribute_p_value:") - 1));
                    attributeCarCompany.setValue(message.substring(message.indexOf("attribute_p_value:") + 18));
                    elementSupercar.setAttributeNode(attributeCarCompany);

                } else if (message.startsWith("children:")) {

                    elementsCars[indexChildren] = document.createElement(message.substring(9, message.indexOf("attribute_c:") - 1));
                    attributesCars[indexChildren] = document.createAttribute(message.substring(message.indexOf("attribute_c:") + 12,
                            message.indexOf("attribute_c_value:") - 1));
                    attributesCars[indexChildren].setValue(message.substring(message.indexOf("attribute_c_value:") + 18,
                            message.indexOf("text:") - 1));
                    elementsCars[indexChildren].setAttributeNode(attributesCars[indexChildren]);

                    elementsCars[indexChildren].appendChild(document.createTextNode(message.substring(message.indexOf("text:") + 5)));
                    elementSupercar.appendChild(elementsCars[indexChildren]);

                    indexChildren++;
                } else if (message.equals("finish")) {

                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    DOMSource domSource = new DOMSource(document);
                    transformer.transform(domSource, new StreamResult(new File("src/lab5/cars.xml")));

                    System.out.print("\nXML: ");
                    transformer.transform(domSource, new StreamResult(System.out));

                    dataOutputStream.writeUTF("Компания: " + elementRoot
                            .getChildNodes().item(0)
                            .getAttributes().item(0).getTextContent());

                    for (int i = 0; i < elementsCars.length; i++) {
                        dataOutputStream.writeUTF("Тип машины: " + elementRoot
                                .getChildNodes().item(0)
                                .getChildNodes().item(i)
                                .getAttributes().item(0).getTextContent());
                        dataOutputStream.writeUTF("Машина: " + elementRoot
                                .getChildNodes().item(0)
                                .getChildNodes().item(i).getTextContent());
                    }

                    dataOutputStream.writeUTF("stop");

                } else if (message.equals("close")) {
                    socketServer.close();
                }

            }
        } catch (IOException | InterruptedException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            Thread thread = new Lab5XMLParseServer(7777);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}