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

public class WithdrawalFormController {
    public AnchorPane root;
    public ComboBox <String>cmbAccount;
    public Label lblName;
    public Label lblBalance;
    public TextField txtWithdraw;
    public Button btnWithdraw;
    public Button btnback;
    public ArrayList<Customer> temp = new ArrayList<>();
    public int index;



    public void initialize(){
        txtWithdraw.setDisable(true);
        cmbAccount.getSelectionModel().selectedItemProperty().addListener(e->{
            int index = cmbAccount.getItems().indexOf(cmbAccount.getSelectionModel().getSelectedItem());
            this.index=index;
            lblName.setText(temp.get(index).getName());
            lblBalance.setText(temp.get(index).getAmmount());
            txtWithdraw.setDisable(false);
        });
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

    public void btnWithdrawOnAction(ActionEvent actionEvent) {

        String ammount = txtWithdraw.getText().strip();
        if(ammount.isEmpty()){
            txtWithdraw.selectAll();
            txtWithdraw.requestFocus();
            return;
        }
        for (int i = 0; i < ammount.length(); i++) {
            if(!(Character.isDigit(ammount.charAt(i))|ammount.charAt(i)=='.')){
                txtWithdraw.selectAll();
                txtWithdraw.requestFocus();
                return;
            }
        }
        if (Double.parseDouble(txtWithdraw.getText())<500.00) {
            new Alert(Alert.AlertType.ERROR,"Deposit should greater than Rs. 500.00").show();
            txtWithdraw.selectAll();
            txtWithdraw.requestFocus();
            return;
        }

        double withdraw = Double.parseDouble(txtWithdraw.getText());
        double initial = Double.parseDouble(lblBalance.getText());

        if(initial<withdraw){
            new Alert(Alert.AlertType.ERROR,"Balance is not Enough").show();
            txtWithdraw.selectAll();
            txtWithdraw.requestFocus();
            return;
        }
        temp.get(index).setAmmount(String.format("%.2f",initial-withdraw));
        new Alert(Alert.AlertType.INFORMATION, "Withdrawal Successful").show();
        cmbAccount.getSelectionModel().clearSelection();
        lblName.setText("");
        lblBalance.setText("");
        txtWithdraw.clear();
        txtWithdraw.setDisable(true);
        cmbAccount.requestFocus();
    }
    public void initData(ArrayList<Customer> ar){
        temp=ar;
        withdraw();
    }
    public void withdraw(){
        ObservableList<String> CustomerID = cmbAccount.getItems();
        for (int i = 0; i < temp.size(); i++) {
            CustomerID.add(temp.get(i).getAccountID());
        }
    }
}
