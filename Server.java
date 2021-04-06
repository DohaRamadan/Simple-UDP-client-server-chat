import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
// author Doha Ramadan
// ID : 20190271

public class Server_20190271 {
    public static void main(String[] args)
    {
        try {
            DatagramSocket serverSocket = new DatagramSocket(6000);
            System.out.println("server is up");

            byte[] requestBytes = new byte[4096];
            byte[] responseBytes = new byte [4096];
            Scanner scan = new Scanner(System.in);
            System.out.println("Server is ready to communicate");
            while (true)
            {

                // create a client packet to receive client's request
                DatagramPacket clientPacket = new DatagramPacket(requestBytes, requestBytes.length);
                // Receive the client's packet into the client packet
                serverSocket.receive(clientPacket);

                InetAddress clientIP = clientPacket.getAddress();
                int clientPort = clientPacket.getPort();
                String request = new String(clientPacket.getData()).trim();
                String input;
                // server sends responses to client
                if(request.equalsIgnoreCase("what's the weather")){
                    input = "the weather is good";
                    responseBytes = input.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes,responseBytes.length,clientIP,clientPort);
                    serverSocket.send(myServerPacket);
                }
                else if(request.equalsIgnoreCase("what's the pc ip")){
                    InetAddress pcIP = InetAddress.getLocalHost();
                    String ip = new String(pcIP.toString());
                    responseBytes = ip.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes,responseBytes.length,clientIP,clientPort);
                    serverSocket.send(myServerPacket);
                }
                else if(request.equalsIgnoreCase("what's the current time")){
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    responseBytes = date.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes,responseBytes.length,clientIP,clientPort);
                    serverSocket.send(myServerPacket);
                }
                else{
                    input = "wrong question";
                    responseBytes = input.getBytes();
                    DatagramPacket myServerPacket = new DatagramPacket(responseBytes,responseBytes.length,clientIP,clientPort);
                    serverSocket.send(myServerPacket);
                }

                requestBytes = new byte[4096];
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();

        }

    }
}

