package sample.mainWindow;



import com.oracle.javafx.jmx.json.JSONFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import sample.Controller;
import sample.MD5Library;
import sample.Main;

public class Login_Controller {


    public Pane loginForm;
    public Button loginButton;
    public TextField username;
    public PasswordField password;
    public PasswordField re_password;
    public Label dialogName;

    private boolean isLogin = true;

    public void closeLoginForm(){
        Stage s = (Stage)loginForm.getScene().getWindow();
        s.close();
    }



    public void loginButtonCLicked() {
        HttpPost request;
        if (isLogin) {
            request = new HttpPost("http://192.168.1.19:8080/api/rest/services/login2");
        } else {

            request = new HttpPost("http://192.168.1.19:8080/api/rest/services/registry");
            if(password.getText().equals(re_password.getText()))
            {
                //TODO
            }
        }

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", username.getText());
            jsonObject.put("pass", MD5Library.md5(password.getText()));
            System.out.println(jsonObject.toString());

            HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
            StringEntity params = new StringEntity(jsonObject.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            BufferedReader r = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuilder total = new StringBuilder();

            String line;

            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();

            JSONObject res = new JSONObject(total.toString());
            if(res.getString("mess").equals("successful"))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
                Parent root = loader.load();
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Chinese Chess");
                Controller controller = loader.getController();
                controller.setUser(res.getString("name"));
                String image = Main.class.getResource("background.jpg").toExternalForm();
                root.setStyle("-fx-background-image: url('" + image + "'); " +
                        "-fx-background-position: top center; " +
                        "-fx-background-repeat: stretch;");
                root.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
                primaryStage.resizableProperty().setValue(Boolean.FALSE);
                primaryStage.setScene(new Scene(root, 600, 760));

                primaryStage.show();


            }


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void registerButtonClicked() {
        isLogin = false;
        re_password.setVisible(true);
        dialogName.setText("Đăng Ký");
        loginButton.setText("Đăng Ký");
    }
}
