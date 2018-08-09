package loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    LoginModel loginModel = new LoginModel ();

    @FXML
    private Label dbStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;


    @Override
    public void initialize (URL url, ResourceBundle rb) {
        if (this.loginModel.isDataBaseConnected ()){
            this.dbStatus.setText ("Connected To DataBase");
        }else {
            this.dbStatus.setText ("Not Connected To DataBase");
        }

        this.comboBox.setItems (FXCollections.observableArrayList (Option.values ()));
    }
    @FXML
    private  void Login(ActionEvent event){
        try {

            if (this.loginModel.isLogin (this.username.getText (), this.password.getText (), ((Option)this.comboBox.getValue ()).toString())){
                Stage stage = (Stage)this.loginButton.getScene ().getWindow ();
                stage.close ();
                switch (((Option)this.comboBox.getValue ()).toString ()){
                    case "Admin":
                        adminLogin ();
                        break;
                    case "Student":
                        studentLogin ();
                        break;
                }
            }
            else {
                this.loginStatus.setText ("Wrong Credential");
            }

        }catch (Exception localException){

        }
    }

    public void studentLogin(){
        try {
            Stage userStage = new Stage ();
            FXMLLoader studentLoader = new FXMLLoader();
            Pane studentRoot = (Pane) studentLoader.load (getClass ().getResource ("/students/studentFXML.fxml").openStream ());

            StudentsController studentsController = (StudentsController)studentLoader.getController ();

            Scene scene = new Scene (studentRoot);
            userStage.setScene (scene);
            userStage.setTitle ("Student Dashboard");
            userStage.setResizable (false);
            userStage.show ();

        }catch (IOException ex){
            ex.printStackTrace ();
        }
    }

    public void adminLogin(){
        try {
            Stage adminStage = new Stage ();
            FXMLLoader adminLoader = new FXMLLoader ();
            Pane adminRoot = (Pane)adminLoader.load (getClass ().getResource("/admin/admin.fxml").openStream ());
            AdminController adminController = (AdminController)adminLoader.getController ();

            Scene scene = new Scene (adminRoot);
            adminStage.setScene (scene);
            adminStage.setTitle ("Admin DashBoard");
            adminStage.setResizable (false);
            adminStage.show ();
        }catch (IOException ex){
            ex.printStackTrace ();
        }
    }
}
