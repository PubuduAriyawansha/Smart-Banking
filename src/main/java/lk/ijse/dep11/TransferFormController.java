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

public class TransferFormController {
    public AnchorPane root;
    public ComboBox <String>cmbAccountFrom;
    public Label lblFromName;
    public Label lblFrombalance;
    public ComboBox <String>cmbAccountTo;
    public Label lblToname;
    public Label lblToBalance;
    public TextField txtTransfer;
    public Button btntransfer;
    public Button btnBack;

    ArrayList<Customer> temp = new ArrayList<>();

    public int indexfrom;
    public int indexTo;

    public void initialize(){

        txtTransfer.setDisable(true);
        lblFrombalance.setText("");
        lblFromName.setText("");
        lblToBalance.setText("");
        lblToname.setText("");
        cmbAccountTo.setDisable(true);

        cmbAccountFrom.getSelectionModel().selectedItemProperty().addListener(e->{
            if(cmbAccountFrom.getSelectionModel().getSelectedItem()!=null){
                int index = cmbAccountFrom.getItems().indexOf(cmbAccountFrom.getSelectionModel().getSelectedItem());
                this.indexfrom=index;
                lblFromName.setText(temp.get(index).getName());
                lblFrombalance.setText(temp.get(index).getAmmount());
                cmbAccountTo.setDisable(false);


                cmbAccountTo.getSelectionModel().selectedItemProperty().addListener(e1->{
                    if(cmbAccountTo.getSelectionModel().getSelectedItem()!=null){

                        txtTransfer.setDisable(false);

                        int index1 = cmbAccountTo.getItems().indexOf(cmbAccountTo.getSelectionModel().getSelectedItem());
                        this.indexTo=index1;

                        if(indexfrom==indexTo){
                            new Alert(Alert.AlertType.ERROR,"Same Account selected").show();
                            cmbAccountTo.getSelectionModel().clearSelection();
                            cmbAccountTo.requestFocus();
                            lblToname.setText("");
                            lblToBalance.setText("");
                            return;
                        }
                        lblToname.setText(temp.get(index1).getName());
                        lblToBalance.setText(temp.get(index1).getAmmount());
                    }
                });
            }
        });

    }



    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
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
        transfer();
    }
    public void transfer(){
        ObservableList<String> CustomerToID = cmbAccountTo.getItems();
        for (int i = 0; i < temp.size(); i++) {
            CustomerToID.add(temp.get(i).getAccountID());
        }

        ObservableList<String> CustomerFromID = cmbAccountFrom.getItems();
        for (int i = 0; i < temp.size(); i++) {
            CustomerFromID.add(temp.get(i).getAccountID());
        }
    }

    public void btntransferOnAction(ActionEvent actionEvent) {
        String ammount = txtTransfer.getText().strip();
        if (ammount.isEmpty()) {
            txtTransfer.selectAll();
            txtTransfer.requestFocus();
            return;
        }
        for (int i = 0; i < ammount.length(); i++) {
            if (!(Character.isDigit(ammount.charAt(i)) | ammount.charAt(i) == '.')) {
                txtTransfer.selectAll();
                txtTransfer.requestFocus();
                return;
            }
        }
        if (Double.parseDouble(txtTransfer.getText()) < 500.00) {
            new Alert(Alert.AlertType.ERROR, "Transfer should greater than Rs. 500.00").show();
            txtTransfer.selectAll();
            txtTransfer.requestFocus();
            return;

        }
        double transfer = Double.parseDouble(txtTransfer.getText());
        double initialFrom = Double.parseDouble(lblFrombalance.getText());
        double initialTo = Double.parseDouble(lblToBalance.getText());

        if (initialFrom < transfer) {
            new Alert(Alert.AlertType.ERROR, "Balance is not Enough").show();
            txtTransfer.selectAll();
            txtTransfer.requestFocus();
            return;
        }

        temp.get(indexfrom).setAmmount(String.format("%.2f", initialFrom - transfer));
        temp.get(indexTo).setAmmount(String.format("%.2f", initialTo + transfer));


        new Alert(Alert.AlertType.INFORMATION, "Transfer Successful").show();
        cmbAccountFrom.getSelectionModel().clearSelection();
        cmbAccountTo.getSelectionModel().clearSelection();

        lblToname.setText("");
        lblFromName.setText("");

        lblToBalance.setText("");
        lblFrombalance.setText("");

        txtTransfer.clear();
        txtTransfer.setDisable(true);
        cmbAccountFrom.requestFocus();
        cmbAccountTo.setDisable(true);

    }
}
