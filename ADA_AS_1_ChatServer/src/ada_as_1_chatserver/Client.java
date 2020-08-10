/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_as_1_chatserver;

/**
 *
 * @author Bedi
 */
/**
 * A class that represents a client in a number guessing game
 *
 * @see GuessServer.java
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner; // Java 1.5 equivalent of cs1.Keyboard
import javax.swing.JOptionPane;

public class Client {

    public String HOST_NAME;
    public static final int HOST_PORT = 7777; // host port number
    private GUI gui;
//   PrintWriter pw; // output stream to server
//   BufferedReader br; // input stream from server
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String clientName;

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void getClientName() {
        Scanner scan = new Scanner(System.in);
        while (clientName == null || clientName.isEmpty()) {
            System.out.println("Please enter your display name: ");
            clientName = scan.nextLine();
        }

    }

    public String getClientNameGUI() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setIPAddress(String address) {
        this.HOST_NAME = address;
    }

    public Client() {
    }

    public void startClient() {
        Socket socket = null;
        //Scanner keyboardInput = new Scanner(System.in);
        try {
            socket = new Socket(HOST_NAME, HOST_PORT);
        } catch (IOException e) {
            System.err.println("Client could not make connection: " + e);
            System.exit(-1);
        }
        try {  // create an autoflush output stream for the socket
            Sender sender = new Sender();
            sender.start();
//         pw = new PrintWriter(socket.getOutputStream(), true);
//         // create a buffered input stream for this socket
//         br = new BufferedReader(new InputStreamReader(
//            socket.getInputStream()));
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            Reciever reciver = new Reciever();
            reciver.start();
            //pw.println(new Broadcaster(clientName));
            output.writeObject(new Broadcaster(clientName));

            // play the game until value is correctly guessed
//         boolean finished = false;
//         do
//         {  String serverResponse = br.readLine();
//            pw.println(serverResponse);
//            if (serverResponse.toLowerCase().indexOf("correct")==0)
//               finished = true;
//            else
//            {  // get user input and sent it to server
//               pw.println(keyboardInput.nextLine());
//              
//            }
//         }
//         while (!finished);
//         pw.close();
//         br.close();
//         socket.close();
        } catch (IOException e) {
            System.err.println("Client error with server: " + e);
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        GUI gui = new GUI(client);
        //client.startClient();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);
            }
        });

    }
    //-----------------------------------------------------------------------------------------

    public void setgui(GUI gui) {
        this.gui = gui;
    }

    public void sendMessage(Message msg) throws IOException {
        try {
//            pw.println(msg);
//            pw.close();     
            output.writeObject(msg);
            output.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class Reciever extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    String msg = (String) input.readObject();
                    gui.append(msg);
                    System.out.println(msg);
                    System.out.print("> ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Sender extends Thread {

        @Override
        public void run() {
            try {
                DatagramSocket clientSocket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName(HOST_NAME);
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];
                while (true) {
                    String out = "";
                    sendData = out.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9999);
                    clientSocket.send(sendPacket);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String rec = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println(rec);
                    gui.updateOnlineClients(rec);
                }
            } catch (UnknownHostException ioe) {
                JOptionPane.showMessageDialog(null, "Server not found!");
                System.exit(0);
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Server not found!");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
