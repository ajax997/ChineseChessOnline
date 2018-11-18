package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.logging.LogManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LogManager logger = LogManager.getLogManager();
        logger.reset();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        String image = Main.class.getResource("background.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 600, 686));
        primaryStage.show();
        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
