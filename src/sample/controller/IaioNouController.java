package sample.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.iaios.IaioDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IaioNouController implements Initializable {
    @FXML
    private static int rolUser;

    @FXML
    private TextField nom, edat;

    @FXML
    private ChoiceBox incapacitat;

    @FXML
    private Label labelOk;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rolUser = MainController.getRol();

        for(int x=1;x<4;x++){
            if(x==1){
                incapacitat.getItems().add(x + "- BAIXA");
            }else if(x==2){
                incapacitat.getItems().add(x + "- ALTA");
            }else{
                incapacitat.getItems().add(x + "- MOLT ALTA");
            }
        }
    }

    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();
        if (bot.equals("Tornar")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
            stage.setTitle("Avis sense llar");
            stage.setScene(new Scene(arrel));
            stage.show();


        } else if (bot.equals("Ok")) {
            String d = incapacitat.getValue().toString();
            String c= d.substring(0,1);

            IaioDao id = new IaioDao();
            if (id.crearIaio(nom.getText(), Integer.parseInt(edat.getText()), Integer.parseInt(c))) {
                nom.setText("");
                edat.setText("");
                incapacitat.setValue(null);
                labelOk.setText("Iaio afegit correctament");
                PauseTransition pT = new PauseTransition(Duration.seconds(1));
                pT.setOnFinished(e -> labelOk.setText(""));
                pT.play();
            }

        }
    }
}


