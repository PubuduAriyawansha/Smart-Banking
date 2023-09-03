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
        stage.show();
        Stage loginStage =(Stage)root.getScene().getWindow();
        loginStage.close();
    }

    public void btnDepositOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DepositForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        DepositFormController controller = fxmlLoader.getController();
        controller.initData(customers);

        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.show();
        Stage loginStage =(Stage)root.getScene().getWindow();
        loginStage.close();
    }

    public void btnWithdrawalOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/WithdrawalForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        WithdrawalFormController controller = fxmlLoader.getController();
        controller.initData(customers);

        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.show();
        Stage loginStage =(Stage)root.getScene().getWindow();
        loginStage.close();
    }

    public void btnTransferOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TransferForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        TransferFormController controller = fxmlLoader.getController();
        controller.initData(customers);

        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.show();
        Stage loginStage =(Stage)root.getScene().getWindow();
        loginStage.close();
    }

    public void btnCheckBalanceOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CheckBalanceForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        CheckBalanceFormCOntroller controller = fxmlLoader.getController();
        controller.initData(customers);

        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.show();
        Stage loginStage =(Stage)root.getScene().getWindow();
        loginStage.close();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DeleteAccountForm.fxml"));
        AnchorPane MainSceneRoot = fxmlLoader.load();

        DeleteAccountFormController controller = fxmlLoader.getController();
        controller.initData(customers);

        Scene MainScene = new Scene(MainSceneRoot);
        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.show();
        Stage loginStage =(Stage)root.getScene().getWindow();
        loginStage.close();
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
    public void initData(ArrayList<Customer>ar){
        customers=ar;
        System.out.println(customers.size());
    }

}
