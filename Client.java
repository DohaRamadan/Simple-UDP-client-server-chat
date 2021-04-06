import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

// author Doha Ramadan
// ID : 20190271

public class Client_20190271
{
    public static void main(String[] args)
    {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println("Client is now active");

            InetAddress serverIP = InetAddress.getByName("localhost");
            int serverPort = 4000;

            byte[] requestBytes;
            byte[] responseBytes = new byte[4096] ;

            Scanner scan = new Scanner(System.in);
            while (true) {

                System.out.println("Please enter your request");

                String input = scan.nextLine();


                if (input.toLowerCase().equals("close"))
                {
                    System.out.println("Connection is closed");
                    clientSocket.close();
                    break;
                }if (input.toLowerCase().equals("exit"))
                {
                    System.out.println("Connection is closed");
                    clientSocket.close();
                    break;
                }


                requestBytes = input.getBytes();

                DatagramPacket myClientPacket = new DatagramPacket(requestBytes, requestBytes.length, serverIP, serverPort);

                clientSocket.send(myClientPacket);

                DatagramPacket serverPacket = new DatagramPacket(responseBytes, responseBytes.length);
                clientSocket.receive(serverPacket);

                String response = new String(serverPacket.getData()).trim();
                System.out.println("The server says : " + response);

                responseBytes = new byte[4096];


            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}