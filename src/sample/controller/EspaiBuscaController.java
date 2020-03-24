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
import sample.espais.Espai;
import sample.iaios.Iaio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EspaiBuscaController implements Initializable {

    @FXML
    private TextField id, nom, edat, incapacitat, idBusca;
    private Espai espai;
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

        } else if (bot.equals("Veure")) {
            buscaEspai();
        }
    }

    public void buscaEspai() throws IOException {
        int i = 0;
        try {
             i = Integer.parseInt(idBusca.getText());

        ArrayList<Espai> ae = CarregaDadesDao.getLlistaEspais();
        for (Espai e : ae) {
            if (e.getId() == i) {
                espai = e;
                break;
            }
        }
        if (espai == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("No s'ha trobat cap espai");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();
            idBusca.setText("");
        } else {
            idold = espai.getId();

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//espaiModifica.fxml"));
            Stage stage = (Stage) idBusca.getScene().getWindow();
            stage.setTitle("ESPAIS");
            stage.setScene(new Scene(arrel));
            stage.show();


        }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("No s'ha trobat cap espai");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();
            idBusca.setText("");
        }
    }

    public static int getId() {
        return idold;
    }



}
