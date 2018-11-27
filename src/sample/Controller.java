package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {


    private int[][] board = new int[][]{
            {5, 4, 3, 2, 1, 2, 3, 4, 5},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 0, 6, 0},
            {7, 0, 7, 0, 7, 0, 7, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {-7, 0, -7, 0, -7, 0, -7, 0, -7},
            {0, -6, 0, 0, 0, 0, 0, -6, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {-5, -4, -3, -2, -1, -2, -3, -4, -5}};


    public ImageView enemy_icon, home_icon;
    public Label whichTurn;
    public AnchorPane main;
    private boolean alreadyClicked = false;
    private ImageView clickedImage = null;
    private ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
    public Circle circle;
    public ImageView r1c1;
    public ImageView r2c1;
    public ImageView r3c1;
    public ImageView r4c1;
    public ImageView r5c1;
    public ImageView r6c1;
    public ImageView r7c1;
    public ImageView r8c1;
    public ImageView r9c1;

    public ImageView r1c2;
    public ImageView r2c2;
    public ImageView r3c2;
    public ImageView r4c2;
    public ImageView r5c2;
    public ImageView r6c2;
    public ImageView r7c2;
    public ImageView r8c2;
    public ImageView r9c2;

    public ImageView r1c3;
    public ImageView r2c3;
    public ImageView r3c3;
    public ImageView r4c3;
    public ImageView r5c3;
    public ImageView r6c3;
    public ImageView r7c3;
    public ImageView r8c3;
    public ImageView r9c3;

    public ImageView r1c4;
    public ImageView r2c4;
    public ImageView r3c4;
    public ImageView r4c4;
    public ImageView r5c4;
    public ImageView r6c4;
    public ImageView r7c4;
    public ImageView r8c4;
    public ImageView r9c4;

    public ImageView r1c5;
    public ImageView r2c5;
    public ImageView r3c5;
    public ImageView r4c5;
    public ImageView r5c5;
    public ImageView r6c5;
    public ImageView r7c5;
    public ImageView r8c5;
    public ImageView r9c5;

    public ImageView r1c6;
    public ImageView r2c6;
    public ImageView r3c6;
    public ImageView r4c6;
    public ImageView r5c6;
    public ImageView r6c6;
    public ImageView r7c6;
    public ImageView r8c6;
    public ImageView r9c6;

    public ImageView r1c7;
    public ImageView r2c7;
    public ImageView r3c7;
    public ImageView r4c7;
    public ImageView r5c7;
    public ImageView r6c7;
    public ImageView r7c7;
    public ImageView r8c7;
    public ImageView r9c7;

    public ImageView r1c8;
    public ImageView r2c8;
    public ImageView r3c8;
    public ImageView r4c8;
    public ImageView r5c8;
    public ImageView r6c8;
    public ImageView r7c8;
    public ImageView r8c8;
    public ImageView r9c8;

    public ImageView r1c9;
    public ImageView r2c9;
    public ImageView r3c9;
    public ImageView r4c9;
    public ImageView r5c9;
    public ImageView r6c9;
    public ImageView r7c9;
    public ImageView r8c9;
    public ImageView r9c9;

    public ImageView r1c10;
    public ImageView r2c10;
    public ImageView r3c10;
    public ImageView r4c10;
    public ImageView r5c10;
    public ImageView r6c10;
    public ImageView r7c10;
    public ImageView r8c10;
    public ImageView r9c10;


    /////////////////////////
    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean turn;

    private void setupConnection() throws IOException {
        socket = new Socket("localhost", PORT);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String user;
    public void setUser(String user)
    {
        this.user = user;
    }
    private void play() throws Exception {
        String response;
        try {
            while (true) {
                response = in.readLine();
                System.out.println(response);
                if(response.startsWith("NAME"))
                {
                    //TODO
                }

                if (response.startsWith("TURN")) {
                    turn = true;
                    setTurn();
                }
                if (response.startsWith("MOVE")) {
                    turn = true;
                    setTurn();
                    response = response.substring(5);
                    int[][] _board = new int[10][9];
                    String[] x = response.split(" ");
                    for (int i = 0; i < 10; i++) {

                        for (int j = 0; j < 9; j++) {
                            _board[i][j] = Integer.parseInt(String.valueOf(x[(i * 9 + j)]));
                        }
                    }
                    drawBoard(_board);

                }

            }
        } finally {
            socket.close();
        }
    }

    private void setTurn()
    {
       Platform.runLater(() -> {
           String s = turn?"YOUR TURN":"OPPOSITE TURN";
           whichTurn.setText(s);

       });

    }


    public void initialize() {


        prepare();
        try {
            setupConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(() -> {
            try {
                play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        //test();

    }

    private Image getIcon(int num) {
        switch (num) {
            case -7:
                return new Image("img/bz.png");
            case -6:
                return new Image("img/bp.png");
            case -5:
                return new Image("img/bj.png");
            case -4:
                return new Image("img/bm.png");
            case -3:
                return new Image("img/bx.png");
            case -2:
                return new Image("img/bs.png");
            case -1:
                return new Image("img/bb.png");
            case 0:
                return null;
            case 1:
                return new Image("img/rb.png");
            case 2:
                return new Image("img/rs.png");
            case 3:
                return new Image("img/rx.png");
            case 4:
                return new Image("img/rm.png");
            case 5:
                return new Image("img/rj.png");
            case 6:
                return new Image("img/rp.png");
            case 7:
                return new Image("img/rz.png");
        }
        return null;
    }

    private void prepare() {
        r1c1.setX(-20);
        r1c1.setY(-20);
        r1c1.setImage(new Image("img/bj.png"));
        r2c1.setX(45);
        r2c1.setY(-20);
        r2c1.setImage(new Image("img/bm.png"));
        r3c1.setX(115);
        r3c1.setY(-20);
        r3c1.setImage(new Image("img/bx.png"));
        r4c1.setX(180);
        r4c1.setY(-20);
        r4c1.setImage(new Image("img/bs.png"));
        r5c1.setX(255);
        r5c1.setY(-20);
        r5c1.setImage(new Image("img/bb.png"));
        r6c1.setX(320);
        r6c1.setY(-20);
        r6c1.setImage(new Image("img/bs.png"));
        r7c1.setX(390);
        r7c1.setY(-20);
        r7c1.setImage(new Image("img/bx.png"));
        r8c1.setX(455);
        r8c1.setY(-20);
        r8c1.setImage(new Image("img/bm.png"));
        r9c1.setX(520);
        r9c1.setY(-20);
        r9c1.setImage(new Image("img/bj.png"));


        r1c2.setX(-20);
        r1c2.setY(50);
        //r1c2.setImage(new Image("img/bb.png"));
        r2c2.setX(45);
        r2c2.setY(50);
        //r2c2.setImage(new Image("img/bb.png"));
        r3c2.setX(115);
        r3c2.setY(50);
        //r3c2.setImage(new Image("img/bb.png"));
        r4c2.setX(180);
        r4c2.setY(50);
        //r4c2.setImage(new Image("img/bb.png"));
        r5c2.setX(255);
        r5c2.setY(50);
        //r5c2.setImage(new Image("img/bb.png"));
        r6c2.setX(320);
        r6c2.setY(50);
        //r6c2.setImage(new Image("img/bb.png"));
        r7c2.setX(390);
        r7c2.setY(50);
        //r7c2.setImage(new Image("img/bb.png"));
        r8c2.setX(455);
        r8c2.setY(50);
        //r8c2.setImage(new Image("img/bb.png"));
        r9c2.setX(520);
        r9c2.setY(50);
        //r9c2.setImage(new Image("img/bb.png"));


        r1c3.setX(-20);
        r1c3.setY(120);
        //r1c3.setImage(new Image("img/bb.png"));
        r2c3.setX(45);
        r2c3.setY(120);
        r2c3.setImage(new Image("img/bp.png"));
        r3c3.setX(115);
        r3c3.setY(120);
        //r3c3.setImage(new Image("img/bb.png"));
        r4c3.setX(180);
        r4c3.setY(120);
        //r4c3.setImage(new Image("img/bb.png"));
        r5c3.setX(255);
        r5c3.setY(120);
        //r5c3.setImage(new Image("img/bb.png"));
        r6c3.setX(320);
        r6c3.setY(120);
        //r6c3.setImage(new Image("img/bb.png"));
        r7c3.setX(390);
        r7c3.setY(120);
        //r7c3.setImage(new Image("img/bb.png"));
        r8c3.setX(455);
        r8c3.setY(120);
        r8c3.setImage(new Image("img/bp.png"));
        r9c3.setX(520);
        r9c3.setY(120);
        //r9c3.setImage(new Image("img/bb.png"));


        r1c4.setX(-20);
        r1c4.setY(190);
        r1c4.setImage(new Image("img/bz.png"));
        r2c4.setX(45);
        r2c4.setY(190);
        //r2c4.setImage(new Image("img/bb.png"));
        r3c4.setX(115);
        r3c4.setY(190);
        r3c4.setImage(new Image("img/bz.png"));
        r4c4.setX(180);
        r4c4.setY(190);
        //r4c4.setImage(new Image("img/bb.png"));
        r5c4.setX(255);
        r5c4.setY(190);
        r5c4.setImage(new Image("img/bz.png"));
        r6c4.setX(320);
        r6c4.setY(190);
        //r6c4.setImage(new Image("img/bb.png"));
        r7c4.setX(390);
        r7c4.setY(190);
        r7c4.setImage(new Image("img/bz.png"));
        r8c4.setX(455);
        r8c4.setY(190);
        //r8c4.setImage(new Image("img/bb.png"));
        r9c4.setX(520);
        r9c4.setY(190);
        r9c4.setImage(new Image("img/bz.png"));


        r1c5.setX(-20);
        r1c5.setY(260);
        //r1c5.setImage(new Image("img/bb.png"));
        r2c5.setX(45);
        r2c5.setY(260);
        //r2c5.setImage(new Image("img/bb.png"));
        r3c5.setX(115);
        r3c5.setY(260);
        //r3c5.setImage(new Image("img/bb.png"));
        r4c5.setX(180);
        r4c5.setY(260);
        //r4c5.setImage(new Image("img/bb.png"));
        r5c5.setX(255);
        r5c5.setY(260);
        //r5c5.setImage(new Image("img/bb.png"));
        r6c5.setX(320);
        r6c5.setY(260);
        //r6c5.setImage(new Image("img/bb.png"));
        r7c5.setX(390);
        r7c5.setY(260);
        //r7c5.setImage(new Image("img/bb.png"));
        r8c5.setX(455);
        r8c5.setY(260);
        //r8c5.setImage(new Image("img/bb.png"));
        r9c5.setX(520);
        r9c5.setY(260);
        //r9c5.setImage(new Image("img/bb.png"));


        r1c6.setX(-20);
        r1c6.setY(330);
        //r1c6.setImage(new Image("img/bb.png"));
        r2c6.setX(45);
        r2c6.setY(330);
        //r2c6.setImage(new Image("img/bb.png"));
        r3c6.setX(115);
        r3c6.setY(330);
        //r3c6.setImage(new Image("img/bb.png"));
        r4c6.setX(180);
        r4c6.setY(330);
        //r4c6.setImage(new Image("img/bb.png"));
        r5c6.setX(255);
        r5c6.setY(330);
        //r5c6.setImage(new Image("img/bb.png"));
        r6c6.setX(320);
        r6c6.setY(330);
        //r6c6.setImage(new Image("img/bb.png"));
        r7c6.setX(390);
        r7c6.setY(330);
        //r7c6.setImage(new Image("img/bb.png"));
        r8c6.setX(455);
        r8c6.setY(330);
        //r8c6.setImage(new Image("img/bb.png"));
        r9c6.setX(520);
        r9c6.setY(330);
        //r9c6.setImage(new Image("img/bb.png"));


        r1c7.setX(-20);
        r1c7.setY(400);
        r1c7.setImage(new Image("img/rz.png"));
        r2c7.setX(45);
        r2c7.setY(400);
        //r2c7.setImage(new Image("img/bb.png"));
        r3c7.setX(115);
        r3c7.setY(400);
        r3c7.setImage(new Image("img/rz.png"));
        r4c7.setX(180);
        r4c7.setY(400);
        //r4c7.setImage(new Image("img/bb.png"));
        r5c7.setX(255);
        r5c7.setY(400);
        r5c7.setImage(new Image("img/rz.png"));
        r6c7.setX(320);
        r6c7.setY(400);
        //r6c7.setImage(new Image("img/bb.png"));
        r7c7.setX(390);
        r7c7.setY(400);
        r7c7.setImage(new Image("img/rz.png"));
        r8c7.setX(455);
        r8c7.setY(400);
        //r8c7.setImage(new Image("img/bb.png"));
        r9c7.setX(520);
        r9c7.setY(400);
        r9c7.setImage(new Image("img/rz.png"));


        r1c8.setX(-20);
        r1c8.setY(470);
        //r1c8.setImage(new Image("img/bb.png"));
        r2c8.setX(45);
        r2c8.setY(470);
        r2c8.setImage(new Image("img/rp.png"));
        r3c8.setX(115);
        r3c8.setY(470);
        //r3c8.setImage(new Image("img/bb.png"));
        r4c8.setX(180);
        r4c8.setY(470);
        //r4c8.setImage(new Image("img/bb.png"));
        r5c8.setX(255);
        r5c8.setY(470);
        //r5c8.setImage(new Image("img/bb.png"));
        r6c8.setX(320);
        r6c8.setY(470);
        //r6c8.setImage(new Image("img/bb.png"));
        r7c8.setX(390);
        r7c8.setY(470);
        //r7c8.setImage(new Image("img/bb.png"));
        r8c8.setX(455);
        r8c8.setY(470);
        r8c8.setImage(new Image("img/rp.png"));
        r9c8.setX(520);
        r9c8.setY(470);
        //r9c8.setImage(new Image("img/bb.png"));


        r1c9.setX(-20);
        r1c9.setY(540);
        //r1c9.setImage(new Image("img/bb.png"));
        r2c9.setX(45);
        r2c9.setY(540);
        //r2c9.setImage(new Image("img/bb.png"));
        r3c9.setX(115);
        r3c9.setY(540);
        //r3c9.setImage(new Image("img/bb.png"));
        r4c9.setX(180);
        r4c9.setY(540);
        //r4c9.setImage(new Image("img/bb.png"));
        r5c9.setX(255);
        r5c9.setY(540);
        //r5c9.setImage(new Image("img/bb.png"));
        r6c9.setX(320);
        r6c9.setY(540);
        //r6c9.setImage(new Image("img/bb.png"));
        r7c9.setX(390);
        r7c9.setY(540);
        //r7c9.setImage(new Image("img/bb.png"));
        r8c9.setX(455);
        r8c9.setY(540);
        //r8c9.setImage(new Image("img/bb.png"));
        r9c9.setX(520);
        r9c9.setY(540);
        //r9c9.setImage(new Image("img/bb.png"));


        r1c10.setX(-20);
        r1c10.setY(610);
        r1c10.setImage(new Image("img/rj.png"));
        r2c10.setX(45);
        r2c10.setY(610);
        r2c10.setImage(new Image("img/rm.png"));
        r3c10.setX(115);
        r3c10.setY(610);
        r3c10.setImage(new Image("img/rx.png"));
        r4c10.setX(180);
        r4c10.setY(610);
        r4c10.setImage(new Image("img/rs.png"));
        r5c10.setX(255);
        r5c10.setY(610);
        r5c10.setImage(new Image("img/rb.png"));
        r6c10.setX(320);
        r6c10.setY(610);
        r6c10.setImage(new Image("img/rs.png"));
        r7c10.setX(390);
        r7c10.setY(610);
        r7c10.setImage(new Image("img/rx.png"));
        r8c10.setX(455);
        r8c10.setY(610);
        r8c10.setImage(new Image("img/rm.png"));
        r9c10.setX(520);
        r9c10.setY(610);
        r9c10.setImage(new Image("img/rj.png"));

        r1c1.setAccessibleText("0c0");
        imageViews.add(r1c1);
        r2c1.setAccessibleText("1c0");
        imageViews.add(r2c1);
        r3c1.setAccessibleText("2c0");
        imageViews.add(r3c1);
        r4c1.setAccessibleText("3c0");
        imageViews.add(r4c1);
        r5c1.setAccessibleText("4c0");
        imageViews.add(r5c1);
        r6c1.setAccessibleText("5c0");
        imageViews.add(r6c1);
        r7c1.setAccessibleText("6c0");
        imageViews.add(r7c1);
        r8c1.setAccessibleText("7c0");
        imageViews.add(r8c1);
        r9c1.setAccessibleText("8c0");
        imageViews.add(r9c1);
        r1c2.setAccessibleText("0c1");
        imageViews.add(r1c2);
        r2c2.setAccessibleText("1c1");
        imageViews.add(r2c2);
        r3c2.setAccessibleText("2c1");
        imageViews.add(r3c2);
        r4c2.setAccessibleText("3c1");
        imageViews.add(r4c2);
        r5c2.setAccessibleText("4c1");
        imageViews.add(r5c2);
        r6c2.setAccessibleText("5c1");
        imageViews.add(r6c2);
        r7c2.setAccessibleText("6c1");
        imageViews.add(r7c2);
        r8c2.setAccessibleText("7c1");
        imageViews.add(r8c2);
        r9c2.setAccessibleText("8c1");
        imageViews.add(r9c2);
        r1c3.setAccessibleText("0c2");
        imageViews.add(r1c3);
        r2c3.setAccessibleText("1c2");
        imageViews.add(r2c3);
        r3c3.setAccessibleText("2c2");
        imageViews.add(r3c3);
        r4c3.setAccessibleText("3c2");
        imageViews.add(r4c3);
        r5c3.setAccessibleText("4c2");
        imageViews.add(r5c3);
        r6c3.setAccessibleText("5c2");
        imageViews.add(r6c3);
        r7c3.setAccessibleText("6c2");
        imageViews.add(r7c3);
        r8c3.setAccessibleText("7c2");
        imageViews.add(r8c3);
        r9c3.setAccessibleText("8c2");
        imageViews.add(r9c3);
        r1c4.setAccessibleText("0c3");
        imageViews.add(r1c4);
        r2c4.setAccessibleText("1c3");
        imageViews.add(r2c4);
        r3c4.setAccessibleText("2c3");
        imageViews.add(r3c4);
        r4c4.setAccessibleText("3c3");
        imageViews.add(r4c4);
        r5c4.setAccessibleText("4c3");
        imageViews.add(r5c4);
        r6c4.setAccessibleText("5c3");
        imageViews.add(r6c4);
        r7c4.setAccessibleText("6c3");
        imageViews.add(r7c4);
        r8c4.setAccessibleText("7c3");
        imageViews.add(r8c4);
        r9c4.setAccessibleText("8c3");
        imageViews.add(r9c4);
        r1c5.setAccessibleText("0c4");
        imageViews.add(r1c5);
        r2c5.setAccessibleText("1c4");
        imageViews.add(r2c5);
        r3c5.setAccessibleText("2c4");
        imageViews.add(r3c5);
        r4c5.setAccessibleText("3c4");
        imageViews.add(r4c5);
        r5c5.setAccessibleText("4c4");
        imageViews.add(r5c5);
        r6c5.setAccessibleText("5c4");
        imageViews.add(r6c5);
        r7c5.setAccessibleText("6c4");
        imageViews.add(r7c5);
        r8c5.setAccessibleText("7c4");
        imageViews.add(r8c5);
        r9c5.setAccessibleText("8c4");
        imageViews.add(r9c5);
        r1c6.setAccessibleText("0c5");
        imageViews.add(r1c6);
        r2c6.setAccessibleText("1c5");
        imageViews.add(r2c6);
        r3c6.setAccessibleText("2c5");
        imageViews.add(r3c6);
        r4c6.setAccessibleText("3c5");
        imageViews.add(r4c6);
        r5c6.setAccessibleText("4c5");
        imageViews.add(r5c6);
        r6c6.setAccessibleText("5c5");
        imageViews.add(r6c6);
        r7c6.setAccessibleText("6c5");
        imageViews.add(r7c6);
        r8c6.setAccessibleText("7c5");
        imageViews.add(r8c6);
        r9c6.setAccessibleText("8c5");
        imageViews.add(r9c6);
        r1c7.setAccessibleText("0c6");
        imageViews.add(r1c7);
        r2c7.setAccessibleText("1c6");
        imageViews.add(r2c7);
        r3c7.setAccessibleText("2c6");
        imageViews.add(r3c7);
        r4c7.setAccessibleText("3c6");
        imageViews.add(r4c7);
        r5c7.setAccessibleText("4c6");
        imageViews.add(r5c7);
        r6c7.setAccessibleText("5c6");
        imageViews.add(r6c7);
        r7c7.setAccessibleText("6c6");
        imageViews.add(r7c7);
        r8c7.setAccessibleText("7c6");
        imageViews.add(r8c7);
        r9c7.setAccessibleText("8c6");
        imageViews.add(r9c7);
        r1c8.setAccessibleText("0c7");
        imageViews.add(r1c8);
        r2c8.setAccessibleText("1c7");
        imageViews.add(r2c8);
        r3c8.setAccessibleText("2c7");
        imageViews.add(r3c8);
        r4c8.setAccessibleText("3c7");
        imageViews.add(r4c8);
        r5c8.setAccessibleText("4c7");
        imageViews.add(r5c8);
        r6c8.setAccessibleText("5c7");
        imageViews.add(r6c8);
        r7c8.setAccessibleText("6c7");
        imageViews.add(r7c8);
        r8c8.setAccessibleText("7c7");
        imageViews.add(r8c8);
        r9c8.setAccessibleText("8c7");
        imageViews.add(r9c8);
        r1c9.setAccessibleText("0c8");
        imageViews.add(r1c9);
        r2c9.setAccessibleText("1c8");
        imageViews.add(r2c9);
        r3c9.setAccessibleText("2c8");
        imageViews.add(r3c9);
        r4c9.setAccessibleText("3c8");
        imageViews.add(r4c9);
        r5c9.setAccessibleText("4c8");
        imageViews.add(r5c9);
        r6c9.setAccessibleText("5c8");
        imageViews.add(r6c9);
        r7c9.setAccessibleText("6c8");
        imageViews.add(r7c9);
        r8c9.setAccessibleText("7c8");
        imageViews.add(r8c9);
        r9c9.setAccessibleText("8c8");
        imageViews.add(r9c9);
        r1c10.setAccessibleText("0c9");
        imageViews.add(r1c10);
        r2c10.setAccessibleText("1c9");
        imageViews.add(r2c10);
        r3c10.setAccessibleText("2c9");
        imageViews.add(r3c10);
        r4c10.setAccessibleText("3c9");
        imageViews.add(r4c10);
        r5c10.setAccessibleText("4c9");
        imageViews.add(r5c10);
        r6c10.setAccessibleText("5c9");
        imageViews.add(r6c10);
        r7c10.setAccessibleText("6c9");
        imageViews.add(r7c10);
        r8c10.setAccessibleText("7c9");
        imageViews.add(r8c10);
        r9c10.setAccessibleText("8c9");
        imageViews.add(r9c10);
    }

    public void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();

        }
    }

    private void sendMove() {
        int[][] _board = reversed(board);
        StringBuilder sendContent = new StringBuilder();
        sendContent.append("MOVE ");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++)
                sendContent.append(String.valueOf(_board[i][j])).append(" ");
        }
        out.println(sendContent.toString());
        turn = false;
        setTurn();
        whichTurn.setText("Opposite Turn");
    }

    private ImageView getImageView(int r, int c) {
        String index = String.valueOf(r) + "c" + String.valueOf(c);
        for (ImageView view : imageViews) {
            if (index.equals(view.getAccessibleText()))
                return view;
        }
        return null;
    }

    private void drawBoard(int[][] _board) {
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 9; j++) {
                getImageView(j, i).setImage(getIcon(_board[i][j]));
            }
        }
        board = reversedNegative(_board);
    }


    private int[][] reversed(int[][] _board) {
        int[][] res = new int[10][9];
        for (int i = 0; i < 10; i++)
            System.arraycopy(_board[i], 0, res[9 - i], 0, 9);
        return res;
    }

    private int[][] reversedNegative(int[][] _board) {
        int[][] res = new int[10][9];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 9; j++) {
                res[i][j] = -_board[i][j];
            }
        return res;
    }


    private boolean validMove(int r, int c, int _r, int _c) {
        int obj = board[r][c];
        switch (obj) {
            case -1:
                return RuleHelper.kingMoveValid(r, c, _r, _c, board);
            case -2:
                return RuleHelper.guardMoveValid(r, c, _r, _c, board);
            case -3:
                return RuleHelper.elephantMoveValid(r, c, _r, _c, board);
            case -4:
                return RuleHelper.horseMoveValid(r, c, _r, _c, board);
            case -5:
                return RuleHelper.carMoveValid(r, _r, c, _c, board);
            case -6:
                return RuleHelper.canonMoveValid(r, c, _r, _c, board);
            case -7:
                return RuleHelper.chotMoveValid(r, c, _r, _c, board);

        }
        return false;
    }


    private int c = 0, r = 0;

    public void mouseClick(MouseEvent event) {
        if (!turn)
            return;

        String text;
        if (!alreadyClicked) {
            System.out.println("Target changed");
            clickedImage = (ImageView) event.getSource();
            text = clickedImage.getAccessibleText();
            r = (int) text.charAt(0) - 48;
            c = (int) text.charAt(2) - 48;

            if (board[c][r] == 0 || board[c][r] > 0)
                return;
            alreadyClicked = true;
            circle.setCenterX(clickedImage.getX() + 48);
            circle.setCenterY(clickedImage.getY() + 46);
            circle.setRadius(30.0f);
            circle.setFill(Color.RED);

        } else {
            print();
            ImageView imageView = (ImageView) event.getSource();
            String c_text = imageView.getAccessibleText();
            int c_r = (int) c_text.charAt(0) - 48;
            int c_c = (int) c_text.charAt(2) - 48;

            if ((board[c_c][c_r] < 0 && board[c][r] < 0) || ((board[c_c][c_r] > 0 && board[c][r] > 0))) {
                clickedImage = (ImageView) event.getSource();
                text = clickedImage.getAccessibleText();
                r = (int) text.charAt(0) - 48;
                c = (int) text.charAt(2) - 48;
                circle.setCenterX(clickedImage.getX() + 48);
                circle.setCenterY(clickedImage.getY() + 46);
                circle.setRadius(28.0f);
                circle.setFill(Color.RED);
                return;
            }

            System.out.println(r);
            System.out.println(c);
            System.out.println(c_r);
            System.out.println(c_c);
            boolean valid = validMove(c, r, c_c, c_r);

            System.out.println(valid);
            if (valid) {
                imageView.setImage(clickedImage.getImage());
                clickedImage.setImage(null);
                alreadyClicked = false;
                board[c_c][c_r] = board[c][r];
                board[c][r] = 0;
                sendMove();
            }
        }
    }
}
