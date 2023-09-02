package lk.ijse.dep11;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class DepositFormController {
    public AnchorPane root;
    public ComboBox <String>cmbAccount;
    public Label lblBalance;
    public TextField txtAmmount;
    public Button btnDeposit;
    public Button btnback;

    public ArrayList<Customer> temp = new ArrayList<>();

    public void initialize(){
        cmbAccount.getSelectionModel().selectedItemProperty().addListener(e->{
            
        });
    }

    public void btnDepositOnAction(ActionEvent actionEvent) {
    }

    public void btnbackOnAction(ActionEvent actionEvent) {
    }
    public void initData(ArrayList<Customer> ar){
        temp=ar;
    }
    public void deposit(){
        ObservableList<String> CustomerID = cmbAccount.getItems();
        for (int i = 1; i < temp.size(); i++) {
            CustomerID.add(temp.get(i).getAccountID());
        }

    }
}
