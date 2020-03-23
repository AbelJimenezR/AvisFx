package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.CarregaDadesDao;
import sample.usuaris.Usuari;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsuariBuscaController implements Initializable {

    @FXML
    private TextField id, nom, pass, rol, idBusca;
    private Usuari usuari;
    private static int rolOld, idold;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();
        Boolean x = false;
        if (bot.equals("Tornar")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
            stage.setTitle("Avis sense llar");
            stage.setScene(new Scene(arrel));
            stage.show();

        } else if (bot.equals("Editar")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//usuariModifica.fxml"));
            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();
        }
    }

    public void buscaUsuari() {
        int i = Integer.parseInt(idBusca.getText());
        ArrayList<Usuari> au = CarregaDadesDao.getLlistaUsuaris();
        for (Usuari u : au) {
            if (u.getId() == i) {
                usuari = u;
                break;
            }
        }
        if (usuari == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("No s'ha trobat cap usuari");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();
            idBusca.setText("");
        } else {
            if (usuari.getClass().getSimpleName().equals("Usuari")) {
                rol.setText("3");
                rolOld = 3;
            } else if (usuari.getClass().getSimpleName().equals("UsuariGestor")) {
                rol.setText("2");
                rolOld = 2;
            } else {
                rol.setText("1");
                rolOld = 1;
            }

            idold = usuari.getId();
            id.setText(String.valueOf(usuari.getId()));
            nom.setText(usuari.getNom());
            pass.setText(usuari.getPassword());
        }
    }

    public static int getId() {
        return idold;
    }


}
