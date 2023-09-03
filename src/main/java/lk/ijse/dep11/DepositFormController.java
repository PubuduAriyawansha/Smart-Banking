package lk.ijse.dep11;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DepositFormController {
    public AnchorPane root;
    public ComboBox <String>cmbAccount;
    public Label lblBalance;
    public TextField txtAmmount;
    public Button btnDeposit;
    public Button btnback;

    public ArrayList<Customer> temp = new ArrayList<>();
    public Label lblName;

    public int index;

    public void initialize(){

        txtAmmount.setDisable(true);

        cmbAccount.getSelectionModel().selectedItemProperty().addListener(e->{
            if(cmbAccount.getSelectionModel().getSelectedItem()!=null){
                int index = cmbAccount.getItems().indexOf(cmbAccount.getSelectionModel().getSelectedItem());
                this.index=index;
                lblName.setText(temp.get(index).getName());
                lblBalance.setText(temp.get(index).getAmmount());
                txtAmmount.setDisable(false);
            }
        });
    }

    public void btnDepositOnAction(ActionEvent actionEvent) {
        String ammount = txtAmmount.getText().strip();
        if(ammount.isEmpty()){
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
            new Alert(Alert.AlertType.ERROR,"Deposit should greater than Rs. 500.00").show();
            txtAmmount.selectAll();
            txtAmmount.requestFocus();
            return;

        }
        double deposit = Double.parseDouble(txtAmmount.getText());
        double initial = Double.parseDouble(lblBalance.getText());

        temp.get(index).setAmmount(String.format("%.2f",deposit+initial));
        new Alert(Alert.AlertType.INFORMATION, "Deposit Successful").show();
        cmbAccount.getSelectionModel().clearSelection();
        lblName.setText("");
        lblBalance.setText("");
        txtAmmount.clear();
        txtAmmount.setDisable(true);
        cmbAccount.requestFocus();
    }

    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        MainFormController controller = fxmlLoader.getController();
        controller.initData(temp);
        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.show();
        Stage loginStage = (Stage) root.getScene().getWindow();
        loginStage.close();
    }
    public void initData(ArrayList<Customer> ar){
        temp=ar;
        deposit();
    }
    public void deposit(){
        ObservableList<String> CustomerID = cmbAccount.getItems();
        for (int i = 0; i < temp.size(); i++) {
            CustomerID.add(temp.get(i).getAccountID());
        }


    }
}
