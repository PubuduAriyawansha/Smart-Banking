package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class NewAccountFormController {
    public AnchorPane root;
    public TextField txtAccNumber;
    public TextField txtAccPassword;
    public TextField txtACCName;
    public Button btnCreate;

    public ArrayList<Customer> temp = new ArrayList<>();
    public Button btnback;
    public TextField txtAmmount;

    public void initialize(){
        //txtAccNumber.setText("C001");
    }

    public void btnCreateOnAction(ActionEvent actionEvent) {
        String id = txtAccNumber.getText();
        String name = txtACCName.getText().strip();
        String ammount = txtAmmount.getText().strip();
        if(name.isEmpty()){
            txtACCName.selectAll();
            txtACCName.requestFocus();
            return;
        } else if(ammount.isEmpty()){
            txtAmmount.selectAll();
            txtAmmount.requestFocus();
            return;
        }
        for (int i = 0; i < ammount.length(); i++) {
            if(!(Character.isDigit(ammount.charAt(i))|ammount.charAt(i)=='.')){
                txtAmmount.selectAll();
                txtAmmount.requestFocus();
                return;
            }
        }
        if (Double.parseDouble(txtAmmount.getText())<500.00) {
            new Alert(Alert.AlertType.ERROR,"Initial ammount should greater than Rs. 1000.00").show();
            txtAmmount.selectAll();
            txtAmmount.requestFocus();
            return;

        }
        ammount=String.format("%.2f",Double.parseDouble(txtAmmount.getText()));
        Customer newCustomer = new Customer(id,name,ammount);
        temp.add(newCustomer);
        new Alert(Alert.AlertType.INFORMATION,"Customer Successfully Added").show();
        newCustomer();
    }

    public void initData(ArrayList<Customer> ar){
        temp=ar;
        newCustomer();
    }


    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        MainFormController controller = fxmlLoader.getController();
        controller.initData(temp);
        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.setTitle("Navigatoin main");
        stage.show();
        Stage loginStage = (Stage) root.getScene().getWindow();
        loginStage.close();
    }
    public void newCustomer(){
        int i= Integer.parseInt(temp.
                get(temp.size()-1).getAccountID().substring(1));
        txtAccNumber.setText(String.format("C%03d",i+1));
        txtACCName.clear();
        txtAmmount.clear();
        txtACCName.requestFocus();
    }
}
