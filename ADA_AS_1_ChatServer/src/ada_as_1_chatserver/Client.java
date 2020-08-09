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
   A class that represents a client in a number guessing game
   @see GuessServer.java
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner; // Java 1.5 equivalent of cs1.Keyboard

public class Client
{
   public static final String HOST_NAME = "localhost";
   public static final int HOST_PORT = 7777; // host port number
   private GUI gui;

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }
   private String clientName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

   public Client()
   {
   }
   
   public void startClient()
   {  Socket socket = null;
      Scanner keyboardInput = new Scanner(System.in);
      try
      {  socket = new Socket(HOST_NAME, HOST_PORT);
      }
      catch (IOException e)
      {  System.err.println("Client could not make connection: " + e);
         System.exit(-1);
      }
      PrintWriter pw; // output stream to server
      BufferedReader br; // input stream from server
      try
      {  // create an autoflush output stream for the socket
         pw = new PrintWriter(socket.getOutputStream(), true);
         // create a buffered input stream for this socket
         br = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
         // play the game until value is correctly guessed
         boolean finished = false;
         do
         {  String serverResponse = br.readLine();
            System.out.println(serverResponse);
            if (serverResponse.toLowerCase().indexOf("correct")==0)
               finished = true;
            else
            {  // get user input and sent it to server
               pw.println(keyboardInput.nextLine());
            }
         }
         while (!finished);
         pw.close();
         br.close();
         socket.close();
         }
      catch (IOException e)
      {  System.err.println("Client error with game: " + e);
      }
      
   }
   
   public static void main(String[] args)
   {  Client client = new Client();
      GUI gui = new GUI(client);
      //client.startClient();
      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
       public void run() 
       {
           gui.setVisible(true);
       }
    });
              
                  
   }
       //-----------------------------------------------------------------------------------------
    public void setgui(GUI gui) {
        this.gui = gui;
    }
}

