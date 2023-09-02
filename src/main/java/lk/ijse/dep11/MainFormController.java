package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainFormController {
    public Button btnWithdrawal;
    public Button btnNewAccount;
    public Button btnDeposit;
    public Button btnTransfer;
    public Button btnCheckBalance;
    public Button btnDelete;
    public Button btnExit;

    public ArrayList<Customer> customers = new ArrayList<>();
    public ImageView root;

    public void initialize(){
        Customer C1 = new Customer("C000","admin","1000.00");
        customers.add(C1);
    }

    public void btnNewAccountOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/NewAccountForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        NewAccountFormController controller = fxmlLoader.getController();
        controller.initData(customers);

        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.setTitle("Navigatoin main");
        stage.show();
        Stage loginStage =(Stage)root.getScene().getWindow();
        loginStage.close();
    }

    public void btnDepositOnAction(ActionEvent actionEvent) {
    }

    public void btnWithdrawalOnAction(ActionEvent actionEvent) {
    }

    public void btnTransferOnAction(ActionEvent actionEvent) {
    }

    public void btnCheckBalanceOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
    }
    public void initData(ArrayList<Customer>ar){
        customers=ar;
        System.out.println(customers.size());
    }
}
