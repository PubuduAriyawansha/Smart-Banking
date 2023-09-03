package lk.ijse.dep11;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteAccountFormController {
    public AnchorPane root;
    public ComboBox <String>cmbCustomer;
    public Label lblName;
    public Label lblBalance;
    public Button btnDelete;
    public Button btnBack;
    public int index;

    ArrayList <Customer> temp = new ArrayList<>();

    public void initialize(){
        btnDelete.setDisable(true);
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener(e->{
            int index = cmbCustomer.getItems().indexOf(cmbCustomer.getSelectionModel().getSelectedItem());
            System.out.println(index);
            this.index=index;
            lblName.setText(temp.get(index).getName());
            lblBalance.setText(temp.get(index).getAmmount());
            btnDelete.setDisable(false);
        });
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        System.out.println(temp.size());
        temp.remove(index);
        cmbCustomer.getItems().remove(index);
        System.out.println(temp.size());
        new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted").show();
        cmbCustomer.getSelectionModel().clearSelection();
        cmbCustomer.requestFocus();
        lblBalance.setText("");
        lblName.setText("");

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
        load();
    }
    public void load(){
        ObservableList<String> CustomerID = cmbCustomer.getItems();
        for (int i = 0; i < temp.size(); i++) {
            CustomerID.add(temp.get(i).getAccountID());
        }
    }
}
