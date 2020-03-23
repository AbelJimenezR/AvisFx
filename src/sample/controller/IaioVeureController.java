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
import sample.iaios.Iaio;
import sample.iaios.IaioDao;
import sample.iaios.TaulaIaios;
import sample.usuaris.TaulaUsuaris;
import sample.usuaris.Usuari;
import sample.usuaris.UsuarisDao;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IaioVeureController implements Initializable {
    private static int id;


    @FXML
    private TableView<TaulaIaios> taulaIaios;

    @FXML
    private Button bEdita, bEsborra;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Iaio> ai = CarregaDadesDao.getLlistaIaios();

        ObservableList<TaulaIaios> dades = taulaIaios.getItems();

        for (Iaio i : ai) {
            dades.add(new TaulaIaios(String.valueOf(i.getId()), i.getNom(), String.valueOf(i.getEdat()), String.valueOf(i.getIncapacitat())));
        }

    }

    @FXML
    public void clickItem(MouseEvent event) {
        if(!taulaIaios.getSelectionModel().getSelectedCells().isEmpty()) {
            bEdita.setDisable(false);
            bEsborra.setDisable(false);
            id = Integer.parseInt(taulaIaios.getSelectionModel().getSelectedItem().getId());
            System.out.println(id);
        }
    }

    @FXML
    protected void tornaPrincipal(ActionEvent event) throws IOException {
        IaioVeureController.setId();
        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
        stage.setTitle("Avis sense llar");
        stage.setScene(new Scene(arrel));
        stage.show();
    }

    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        IaioDao idao = new IaioDao();

        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();

        if (bot.equals("Edita")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//iaioModifica.fxml"));
            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();


        } else if (bot.equals("Esborra")) {

            idao.eliminarIaio(id);
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//iaioVeure.fxml"));
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


