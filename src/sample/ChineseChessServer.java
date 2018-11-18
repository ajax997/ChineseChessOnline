package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class ChineseChessServer {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Server is Running");
        try {
            while (true) {
                Game game = new Game();
                Game.Player playerX = game.new Player(listener.accept(), 'X');
                Game.Player playerO = game.new Player(listener.accept(), 'O');
                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();
            }
        } finally {
            listener.close();
        }
    }
}

class Game {

    Player currentPlayer;
    class Player extends Thread {
        char mark;
        Player opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;

        // thread handler to initialize stream fields
        Player(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
            try {
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }

        //Accepts notification of who the opponent is.
        void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        //Handles the otherPlayerMoved message.
        public void run() {
            try {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

                // Tell the first player that it is his/her turn.
                if (mark == 'X') {
                    output.println("TURN");
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    opponent.output.println(command);
                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}

