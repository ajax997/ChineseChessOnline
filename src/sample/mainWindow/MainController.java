package sample.mainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class MainController {


    private double xOffset = 0;
    private double yOffset = 0;
    public void onlineButton() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/sample/mainWindow/login_form.fxml"));
        Stage s = new Stage();
        s.setScene(new Scene(p));
        p.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        p.setOnMouseDragged(event -> {
            s.setX(event.getScreenX() - xOffset);
            s.setY(event.getScreenY() - yOffset);
        });
        s.initStyle(StageStyle.UNDECORATED);
        s.show();
    }




    public void closeGame() {
        System.exit(0);
    }


}
