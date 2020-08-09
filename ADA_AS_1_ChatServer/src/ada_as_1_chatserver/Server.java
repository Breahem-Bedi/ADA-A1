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
   A class that represents a server in a number guessing game where
   GuessClient objects connect to this GuessServer and try to guess
   a random integer value between min (incl) and max (excl)
   The game initiates with a response from the server and ends when
   the server responds with "Correct guess!"
   @author Andrew Ensor
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Server
{
    
   public static final int PORT = 7777; // some unused port number
   private ArrayList<String> users;
   private ArrayList<Chat> clients;
   
   public Server()
   { 
        this.users = new ArrayList<String>();
        this.clients = new ArrayList<Chat>();
       
   }
   
    public ArrayList<Chat> getChat() {
        return clients;
    }

    public ArrayList<String> getUsers() {
        return users;
    }
   
   // start the server if not already started and repeatedly listen
   // for client connections until stop requested
   public void startServer()
   {  
      ServerSocket serverSocket = null;
      try
      {  serverSocket = new ServerSocket(PORT);
         System.out.println("Chat Server started at "
            + InetAddress.getLocalHost() + " on port " + PORT);
      }
      catch (IOException e)
      {  System.err.println("Server can't listen on port: " + e);
         System.exit(-1);
      }
      try
      {  while (true)
         {  // block until the next client requests a connection
            // note that the server socket could set an accept timeout
            Socket socket = serverSocket.accept();
            System.out.println("Connection made with "
               + socket.getInetAddress());
            // start a game with this connection, note that a server
            // might typically keep a reference to each game
            Chat chat = new Chat(socket);
            clients.add(chat);
            Thread thread = new Thread(chat);
            thread.start();
         }
         //serverSocket.close();
      }
      catch (IOException e)
      {  System.err.println("Can't accept client connection: " + e);
      }
      System.out.println("Server finishing");
   }
   
   // stops server AFTER the next client connection has been made
   // (since this server socket doesn't timeout on client connections)

   
   // driver main method to test the class
   public static void main(String[] args)
   {  Server server = new Server();
      server.startServer();
   }
   
   // inner class that represents a single game played across a socket
   private class Chat implements Runnable
   {

      private Socket socket; // socket for client/server communication
      PrintWriter pw; // output stream to client
      BufferedReader br; // input stream from client
      String username = "";
      // constructor for a guess game to guess value across a socket
      // for client/server communication 
      public Chat(Socket socket)
      {  
         this.socket = socket;
         try
         {  // create an autoflush output stream for the socket
            pw = new PrintWriter(socket.getOutputStream(), true);
            // create a buffered input stream for this socket
            br = new BufferedReader(new InputStreamReader(
               socket.getInputStream()));
            
            username = br.readLine();
            users.add(username);
            
            pw.println("Welcome to the Chat " + username);

           pw.println(username + " has joined the ChatRoom");

            pw.close();
            br.close();
            System.out.println("Closing connection with "
               + socket.getInetAddress());
            socket.close();
            }
         catch (IOException e)
         {  System.err.println("Server error with game: " + e);
         }
      }
        public void broadcaster(String client, String message) {
        for (Chat chat : clients) {
            if (!chat.getUsername().equals(client)) {
                chat.sendMsg(client, message);
            }

        }
        
    }
                public String getUsername() {
            return username;
        }
                        public void sendMsg(String name, String msg) {
            pw.println(name + ": " + msg);
        }
      

        public void run() {
            String inputMessage;
            try {  // create an autoflush output stream for the socket
                do {
                    inputMessage = br.readLine();

                    if (inputMessage.equals("quit")) {
                        clients.remove(this);
                        users.remove(username);
                        // closes connection
//                        pw.close();
//                        br.close();
//                        System.out.println("Closing connection with "
//                        + socket.getInetAddress());
//                        socket.close();

                        break;
                    }
                    broadcaster(username, inputMessage); // sends msg to all.
                } while (true);

            } catch (IOException e) {
                System.err.println("Server error with chat room: " + e);
            }
        }
   }
}