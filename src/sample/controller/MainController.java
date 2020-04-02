package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Utilitat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField nom,password;

    @FXML
    private Button veurePass;

    @FXML
    private PasswordField pass_hidden;

    @FXML
    private static int rol;

    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void comprovaUsuari() throws IOException {
        password=pass_hidden;

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
            pass_hidden.setText("");

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

    @FXML
    private void showPass(ActionEvent event) {
        Button boto = (Button) event.getSource();
        String bot = boto.getText();
        if(bot.equals("Mostra")) {
            pass_hidden.setVisible(false);
            password.setVisible(true);
            password.setText(pass_hidden.getText());
            veurePass.setText("Amaga");
        }else{
            pass_hidden.setVisible(true);
            password.setVisible(false);
            //pass.setText(pass_hidden.getText());
            veurePass.setText("Mostra");

        }
    }

}




