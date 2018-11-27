package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TicTacToeClient{


    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // Constructs the client by connecting to a server, laying out the GUI and registering GUI listeners.
  
    public TicTacToeClient(String serverAddress) throws Exception {

        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    Scanner scanner = new Scanner(System.in);
    private void out()
    {
        while (true) {
            String mess = scanner.nextLine();
            out.println(mess);
        }

    }

   //* The main thread of the client will listen for messages from the server.  
   //The first message will be a "WELCOME" message in which we receive our mark.  
   //Then we go into a loop listening for: 
   //--> "VALID_MOVE", --> "OPPONENT_MOVED", --> "VICTORY", --> "DEFEAT", --> "TIE", --> "OPPONENT_QUIT, --> "MESSAGE" messages, and handling each message appropriately.
   //The "VICTORY","DEFEAT" and "TIE" ask the user whether or not to play another game. 
   //If the answer is no, the loop is exited and the server is sent a "QUIT" message.  If an OPPONENT_QUIT message is recevied then the loop will exit and the server will be sent a "QUIT" message also.
    public void play() throws Exception {
        String response;
        try {
            while (true) {
                response = in.readLine();
                System.out.println(response);
            }
        }
        finally {
            socket.close();
        }
    }

    private boolean wantsToPlayAgain() {
       return false;
    }

    //main
    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            final TicTacToeClient client = new TicTacToeClient(serverAddress);

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        client.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    client.out();
                }
            });
            thread1.start();
            thread2.start();

            if (!client.wantsToPlayAgain()) {
                break;
            }
        }
    }
}