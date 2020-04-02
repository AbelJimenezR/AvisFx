package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.CarregaDadesDao;
import sample.historial.Historial;
import sample.historial.TaulaHistorial;

public class HistorialController implements Initializable{
	
    @FXML
    private TableView<TaulaHistorial> taulaHistorial;
    
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Historial> ah = CarregaDadesDao.getLlistaHistorial();

        ObservableList<TaulaHistorial> dades = taulaHistorial.getItems();

        for (Historial i : ah) {
            dades.add(new TaulaHistorial(String.valueOf(i.getIdEspai()), String.valueOf(i.getIdPlanta()), String.valueOf(i.getIdHabitacio()), i.getNomIaio(), String.valueOf(i.getIdIaio()), String.valueOf(i.getDataEntrada()), String.valueOf(i.getDataSortida())));
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

}
