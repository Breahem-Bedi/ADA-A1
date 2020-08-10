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
 * A class that represents a server in a number guessing game where GuessClient
 * objects connect to this GuessServer and try to guess a random integer value
 * between min (incl) and max (excl) The game initiates with a response from the
 * server and ends when the server responds with "Correct guess!"
 *
 * @author Andrew Ensor
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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static final int PORT = 7777; // some unused port number
    //private ArrayList<String> users;
    private ArrayList<Chat> clients;

    public Server() {
        //this.users = new ArrayList<String>();

        this.clients = new ArrayList<Chat>();

    }

    public ArrayList<Chat> getChat() {
        return clients;
    }
//
//    public ArrayList<String> getUsers() {
//        return users;
//    }

    // start the server if not already started and repeatedly listen
    // for client connections until stop requested
    public void startServer() {
        ServerSocket serverSocket = null;
        try {
            ServerClientListener scl = new ServerClientListener();
            scl.start();
            serverSocket = new ServerSocket(PORT);
            System.out.println("Chat Server started at "
                    + InetAddress.getLocalHost() + " on port " + PORT);
        } catch (IOException e) {
            System.err.println("Server can't listen on port: " + e);
            System.exit(-1);
        }
        try {
            while (true) {  // block until the next client requests a connection
                // note that the server socket could set an accept timeout
                Socket socket = serverSocket.accept();
                System.out.println("Connection made with "
                        + socket.getInetAddress());
                // start a chat with this connection, note that a server
                // might typically keep a reference to each chat
                Chat chat = new Chat(socket);
                clients.add(chat);
                //Thread thread = new Thread(chat);
                chat.start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            System.err.println("Can't accept client connection: " + e);
        }
        System.out.println("Server finishing");
    }

    // stops server AFTER the next client connection has been made
    // (since this server socket doesn't timeout on client connections)
    // driver main method to test the class
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    // inner class that represents a single game played across a socket
    public class Chat extends Thread {

        private Socket socket; // socket for client/server communication
//        PrintWriter pw; // output stream to client
//        BufferedReader br; // input stream from client
        private ObjectInputStream input;
        private ObjectOutputStream output;
        private String clientName;
        private Message message;

        // constructor for a guess game to guess value across a socket
        // for client/server communication 
        public Chat(Socket socket) {
            this.socket = socket;
            try {  // create an autoflush output stream for the socket
//                pw = new PrintWriter(socket.getOutputStream(), true);
//                // create a buffered input stream for this socket
//                br = new BufferedReader(new InputStreamReader(
//                        socket.getInputStream()));
//
//                clientName = br.readLine();
                //users.add(clientName);
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());
                Broadcaster br = (Broadcaster) input.readObject();
                clientName = br.toString();
                sendMsg(clientName + " has joined the ChatRoom. \n");
                System.out.println(clientName + " has joined the Chat.");

//                pw.println("Welcome to the Chat " + clientName);
//
//                pw.println(clientName + " has joined the ChatRoom.");
            } catch (Exception e) {
                System.err.println("Server error with chat: " + e);
            }
        }

        @Override
        public void run() {
            //String inputMessage;           
            while (true) {
                try {
                    //inputMessage = br.readLine();
                    message = (Message) input.readObject();
                    if (message instanceof Broadcaster) {
                        System.out.println("broadcast");
                        String message = this.message.getMessage();
                        sendMsg(message);
                    } else {
                        clients.remove(this);
                        System.out.println(clientName + " has disconnected");
                        return;
                    }

                    //broadcaster(clientName, inputMessage); // sends msg to all.
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Message recieved: " + message);
            }

        }

//        public void broadcaster(String client, String message) {
//            for (Chat chat : clients) {
//                if (!chat.getClientName().equals(client)) {
//                    chat.sendMsg(client, message);
//                }
//
//            }
//
//        }
        public String getClientName() {
            return clientName;
        }

        public boolean printMsg(String msg) {
            if (!socket.isConnected()) {
                return false;
            }
            try {
                //pw.println(msg);
                output.writeObject(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        public boolean sendMsg(String msg) {
            //pw.println(name + ": " + msg);
            for (Chat c : clients) {
                c.printMsg(msg);
            }
            return true;
        }
    }

    class ServerClientListener extends Thread {

        @Override
        public void run() {
            try {
                DatagramSocket serverSocket = new DatagramSocket(9999);
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    String c = "";
                    for (int i = 0; i < clients.size(); i++) {
                        c += clients.get(i).getClientName() + "_";
                    }
                    sendData = c.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                }
            } catch (SocketException se) {
                se.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
