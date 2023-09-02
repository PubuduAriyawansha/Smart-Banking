package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {

    public TextField txtusername;
    public Button btnlogin;
    public Label lblusername;
    public Label lblpassword;
    public PasswordField txtpassword;
    public AnchorPane root;

    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> password = new ArrayList<>();

    public void initialize(){
        lblusername.setVisible(false);
        lblpassword.setVisible(false);

    }
    public void btnloginOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtusername.getText().strip();
        String password = txtpassword.getText().strip();
        if(username.equals("Pubudu")&&password.equals("Dep11")){
            AnchorPane Mainroot = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            Scene MainScene = new Scene(Mainroot);
            Stage stage = new Stage();
            stage.setScene(MainScene);
            stage.setTitle("Smart Banking");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
            Stage loginStage =(Stage)root.getScene().getWindow();
            loginStage.close();
        } else{

            //get username

            //check empty
            if (username.isEmpty()) {
                lblusername.setText("username can't be empty");
                txtusername.requestFocus();
                txtpassword.clear();
                lblusername.setVisible(true);

            } else if (!username.equals("Pubudu")) {
                lblusername.setText("username does not exist");
                txtusername.selectAll();
                txtusername.requestFocus();
                lblusername.setVisible(true);
            } else{
                lblusername.setVisible(false);
                if (password.isEmpty()) {
                    lblpassword.setText("password can't be empty");
                    lblpassword.setVisible(true);
                } else if (password.length() < 4) {
                    txtpassword.requestFocus();
                    txtpassword.selectAll();
                    lblpassword.setText("invalid password");
                    lblpassword.setVisible(true);
                } else if(!password.equals("Dep11")) {
                    lblpassword.setText("password does not match");
                    txtpassword.requestFocus();
                    txtpassword.selectAll();
                    lblpassword.setVisible(true);
                } else{
                    for (int i = 0; i < password.length(); i++) {
                        if (!(Character.isUpperCase(password.charAt(i)) & Character.isLowerCase(password.charAt(i)) & Character.isDigit(password.charAt(i)))) {
                            lblpassword.setText("password should contain UpperCase LowerCase Digit");
                            lblpassword.setVisible(true);
                        }

                    }
                }
            }
        }
    }
}
