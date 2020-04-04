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
import sample.espais.Espai;
import sample.historial.Historial;
import sample.historial.TaulaDisponibilitat;

public class DisponibilitatController implements Initializable {

	private static int idEspai, numPlaces;

	@FXML
	private TableView<TaulaDisponibilitat> taulaDisponibilitat;

	@FXML
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<Espai> ae = CarregaDadesDao.getLlistaEspais();
		ArrayList<Historial> ah = CarregaDadesDao.getLlistaHistorial();

		ObservableList<TaulaDisponibilitat> dades = taulaDisponibilitat.getItems();

		for (int i = 0; i < ae.size(); i++) {
			idEspai = ae.get(i).getId();
			numPlaces = ae.get(i).getNumPlaces();
			if (ae.get(i).getDisponibilitat()) {
				int compt = 0;
				for (int j = 0; j < ah.size(); j++) {
					if (ah.get(j).getIdEspai() == idEspai) {
						compt++;
					}
				}
				dades.add(new TaulaDisponibilitat(String.valueOf(idEspai), String.valueOf((numPlaces - compt))));
			}
		}
	}

	@FXML
	protected void tornaPrincipal(ActionEvent event) throws IOException {
		IaioVeureController.setId();
		Button boto = (Button) event.getSource();
		Stage stage = (Stage) boto.getScene().getWindow(); // this accesses the window.
		Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
		stage.setTitle("Avis sense llar");
		stage.setScene(new Scene(arrel));
		stage.show();
	}

}
