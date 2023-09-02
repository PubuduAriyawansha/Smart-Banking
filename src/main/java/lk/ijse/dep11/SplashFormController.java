package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashFormController {
    public Label lblSplash;

    public void initialize(){
        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(500), event ->{
            lblSplash.setText("Application is being initialized...");
        });

        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1500), event -> {
            lblSplash.setText("Setting up Tools...");
        });

        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(2500), event -> {
            lblSplash.setText("Setting up UI...");
        });

        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(3000), event -> {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
                Scene MainScene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(MainScene);
                stage.setTitle("Untitled");
                stage.show();
                stage.centerOnScreen();
                lblSplash.getScene().getWindow().hide();

                FadeTransition fade = new FadeTransition(Duration.millis(500),root);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.playFromStart();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4);
        timeline.playFromStart();
    }


}
