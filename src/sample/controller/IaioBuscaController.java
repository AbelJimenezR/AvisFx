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
import sample.iaios.Iaio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IaioBuscaController implements Initializable {

    @FXML
    private TextField id, nom, edat, incapacitat, idBusca;
    private Iaio iaio;
    private static int idold;


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

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//iaioModifica.fxml"));
            stage.setTitle("Avis");
            stage.setScene(new Scene(arrel));
            stage.show();
        }
    }

    public void buscaIaio() {
        int i = Integer.parseInt(idBusca.getText());
        ArrayList<Iaio> ai = CarregaDadesDao.getLlistaIaios();
        for (Iaio u : ai) {
            if (u.getId() == i) {
                iaio = u;
                break;
            }
        }
        if (iaio == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("No s'ha trobat cap iaio");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();
            idBusca.setText("");
        } else {

            idold = iaio.getId();
            id.setText(String.valueOf(iaio.getId()));
            nom.setText(iaio.getNom());
            edat.setText(String.valueOf(iaio.getEdat()));
            incapacitat.setText(String.valueOf(iaio.getIncapacitat()));
        }
    }

    public static int getId() {
        return idold;
    }



}
