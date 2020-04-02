package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.CarregaDadesDao;
import sample.espais.*;
import sample.usuaris.TaulaUsuaris;
import sample.usuaris.Usuari;
import sample.usuaris.UsuarisDao;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EspaiVeureController implements Initializable {
    private static int id;

    @FXML
    private TableView<TaulaEspais> taulaEspais;

    @FXML
    private Button bEdita, bEsborra;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Espai> ae = CarregaDadesDao.getLlistaEspais();
        String nom=null;
        String tipus=null;

        ObservableList<TaulaEspais> dades = taulaEspais.getItems();

        for (Espai e : ae) {
            if(e.getClass().getSimpleName().equals("EspaiPropietari")){
            EspaiPropietari ep = (EspaiPropietari) e;
            nom=ep.getNomPropietari();
            tipus="-";
            }else{
                EspaiEntitat ee = (EspaiEntitat) e;
                nom=ee.getNomEntitat();
                tipus=ee.getTipusEntitat();
            }

            dades.add(new TaulaEspais(String.valueOf(e.getId()), nom  , tipus, String.valueOf(e.getNumPlantes()),e.getAdreca(),
                    String.valueOf(e.getAccessibilitat()), String.valueOf(e.getNumPlaces()),String.valueOf(e.getDisponibilitat())));
        }

    }

    @FXML
    public void clickItem(MouseEvent event) {
        bEdita.setDisable(false);
        bEsborra.setDisable(false);
        id = Integer.parseInt(taulaEspais.getSelectionModel().getSelectedItem().getId());
        System.out.println(id);
    }

    @FXML
    protected void tornaPrincipal(ActionEvent event) throws IOException {
        EspaiVeureController.setId();

        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
        stage.setTitle("Avis sense llar");
        stage.setScene(new Scene(arrel));
        stage.show();
    }

    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        EspaiDao ed = new EspaiDao();

        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();

        if (bot.equals("Edita")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//espaiModifica.fxml"));
            stage.setTitle("ESPAIS");
            //stage.setX(300);
            //stage.setY(300);
            stage.setScene(new Scene(arrel));
            stage.show();

        } else if (bot.equals("Esborra")) {
            ed.eliminaEspai(id);
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//espaiVeure.fxml"));
            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();
        }
    }

    public static Integer getId() {
        return id;
    }

    public static void setId() {
        id = 0;
    }

}


