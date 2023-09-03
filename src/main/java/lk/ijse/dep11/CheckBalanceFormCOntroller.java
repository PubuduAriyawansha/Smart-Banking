package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CheckBalanceFormCOntroller {
    public AnchorPane root;
    public TableView <Customer>tblCustomers;
    public Button btnBack;

    ArrayList <Customer> temp = new ArrayList<>();

    public void initialize(){
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("accountID"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ammount"));
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
        tblCustomers.getItems().addAll(temp);
    }
}
