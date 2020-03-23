package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Utilitat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField nom, password;

    @FXML
    private static int rol;

    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void comprovaUsuari() throws IOException {

        if (Utilitat.comprovaUsuari(nom.getText(), password.getText()).equals("UsuariCoordinador")) {
            rol = 1;
            carregaMenu();

        } else if (Utilitat.comprovaUsuari(nom.getText(), password.getText()).equals("UsuariGestor")) {
            rol = 2;
            carregaMenu();


        } else if (Utilitat.comprovaUsuari(nom.getText(), password.getText()).equals("Usuari")) {
            rol = 3;
            carregaMenu();


        } else if (Utilitat.comprovaUsuari(nom.getText(), password.getText()).equals("no")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("Usuari o contrasenya incorrecte");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();
            nom.setText("");
            password.setText("");
            nom.requestFocus();

        }


    }

    public static int getRol() {
        return rol;
    }

    public void carregaMenu() throws IOException {
        Stage stage = (Stage) label.getScene().getWindow(); //this accesses the window.

        Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
        stage.setScene(new Scene(arrel));
        stage.show();

    }


}




