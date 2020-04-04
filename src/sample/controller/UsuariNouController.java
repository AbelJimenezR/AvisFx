package sample.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.usuaris.UsuarisDao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsuariNouController implements Initializable {
    @FXML
    private static int rolUser;

    @FXML
    private TextField nom,pass;

    @FXML
    private PasswordField pass_hidden;

    @FXML
    private Button veurePass;

    @FXML
    private ChoiceBox rol;

    @FXML
    private Label labelOk;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rolUser = MainController.getRol();

        for(int x=1;x<4;x++){
            if(x==1){
                rol.getItems().add(x + "- Usuari Coordinador");
            }else if(x==2){
                rol.getItems().add(x + "- Usuari Gestor");
            }else{
                rol.getItems().add(x + "- Usuari");
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
            if(comprovaDades()) {
                String d = rol.getValue().toString();
                String c = d.substring(0, 1);

                UsuarisDao ud = new UsuarisDao();
                if (ud.crearUsuari(nom.getText(), pass.getText(), Integer.parseInt(c))) {
                    nom.setText("");
                    pass.setText("");
                    pass_hidden.setText("");
                    rol.setValue(null);
                    labelOk.setText("Usuari afegit correctament");
                    PauseTransition pT = new PauseTransition(Duration.seconds(1));
                    pT.setOnFinished(e -> labelOk.setText(""));
                    pT.play();
                }
            }
        }
    }

    private boolean comprovaDades(){
        if(nom.getText().isEmpty()||pass.getText().isEmpty()||rol.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("Falten dades per introduïr");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();

            return false;
        }
        return true;
    }

    @FXML
    private void showPass(ActionEvent event) {
        Button boto = (Button) event.getSource();
        String bot = boto.getText();
        if(bot.equals("Mostra")) {
            pass_hidden.setVisible(false);
            pass.setVisible(true);
            pass.setText(pass_hidden.getText());
            veurePass.setText("Amaga");
        }else{
            pass_hidden.setVisible(true);
            pass.setVisible(false);
            //pass.setText(pass_hidden.getText());
            veurePass.setText("Mostra");

        }
    }

    @FXML
    private void copia(){
        pass.setText(pass_hidden.getText());
    }
}


