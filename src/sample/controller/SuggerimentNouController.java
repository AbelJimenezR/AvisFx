package sample.controller;

import sample.espais.*;
import sample.historial.Historial;
import sample.iaios.*;
import sample.suggeriment.*;
import sample.CarregaDadesDao;
import java.util.Date;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.beans.value.*;

//falta controlar el boolea disponibilitat
public class SuggerimentNouController implements Initializable {

	ArrayList<Espai> ae = CarregaDadesDao.getLlistaEspais();
	ArrayList<Iaio> ai = CarregaDadesDao.getLlistaIaios();
	ArrayList<Historial> ah = CarregaDadesDao.getLlistaHistorial();

	@FXML
	private Label nI;

	@FXML
	private ChoiceBox<Integer> idE, idP, idH, idI;

	@FXML
	private DatePicker dE, dS;

	@FXML
	private Label labelOk;

	@FXML
	public void initialize(URL url, ResourceBundle resourceBundle) {

		
		for (int i = 0; i < ae.size(); i++) {
			if (ae.get(i).getDisponibilitat()) {
				idE.getItems().add(ae.get(i).getId());
			}
		}
		
		/*
		for(int j=0;j<ae.size();j++) {
			int suma = 0;
			for(int i = 0; i < ah.size(); i++) {
				if(ah.get(i).getIdEspai()== ae.get(j).getId()) {
					suma = suma + 1;
				}
			}
			if(suma==ae.get(j).getNumPlaces()) {
				//Update de espai amb disponibilitat = false
			}
		}
		*/

		idE.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observableValue, Integer number, Integer number2) {
				idE.setDisable(true);
				int id = number2;
				for (int i = 0; i < ae.size(); i++) {
					if (id == ae.get(i).getId()) {
						for (int j = 0; j < ae.get(i).getNumPlantes(); j++) {
							int sumaLlitsPlanta = 0;
							int sumaIaiosPlanta = 0;
							for (int l = 0; l < ah.size(); l++) {
								if (ah.get(l).getIdEspai() == ae.get(i).getId()
										&& ah.get(l).getIdPlanta() == ae.get(i).getPlantes().get(j).getId()) {
									sumaIaiosPlanta = sumaIaiosPlanta + 1;
								}
							}
							for (int k = 0; k < ae.get(i).getPlantes().get(j).getNumHabitacions(); k++) {
								sumaLlitsPlanta = sumaLlitsPlanta
										+ ae.get(i).getPlantes().get(j).getHabitacions().get(k).getNumLlits();
							}
							if (sumaIaiosPlanta < sumaLlitsPlanta) {
								idP.getItems().add(ae.get(i).getPlantes().get(j).getId());
							}
						}
					}
				}
			}
		});

		idP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observableValue, Integer number, Integer number2) {
				idP.setDisable(true);
				int id = (int) idE.getValue();
				for (int i = 0; i < ae.size(); i++) {
					if (id == ae.get(i).getId()) {
						int idp = number2;
						for (int j = 0; j < ae.get(i).getNumPlantes(); j++) {
							if (idp == ae.get(i).getPlantes().get(j).getId()) {
								for (int k = 0; k < ae.get(i).getPlantes().get(j).getNumHabitacions(); k++) {
									int sumaIaiosHabitacio = 0;
									for (int l = 0; l < ah.size(); l++) {
										if (ah.get(l).getIdEspai() == ae.get(i).getId()
												&& ah.get(l).getIdPlanta() == ae.get(i).getPlantes().get(j).getId()
												&& ah.get(l).getIdHabitacio() == ae.get(i).getPlantes().get(j)
														.getHabitacions().get(k).getId()) {
											sumaIaiosHabitacio = sumaIaiosHabitacio + 1;
										}
									}
									if (sumaIaiosHabitacio < ae.get(i).getPlantes().get(j).getHabitacions().get(k)
											.getNumLlits()) {
										idH.getItems()
												.add(ae.get(i).getPlantes().get(j).getHabitacions().get(k).getId());
									}
								}
							}
						}
					}
				}
			}
		});

		for (int i = 0; i < ai.size(); i++) {
			idI.getItems().add(ai.get(i).getId());
		}

		idI.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observableValue, Integer number, Integer number2) {
				int id = number2;
				for (int i = 0; i < ai.size(); i++) {
					if (id == ai.get(i).getId()) {
						nI.setText(ai.get(i).getNom());
					}
				}
			}
		});
	}

	@FXML
	protected void optBotons(ActionEvent event) throws IOException {
		Button boto = (Button) event.getSource();
		Stage stage = (Stage) boto.getScene().getWindow();
		String bot = boto.getText();
		if (bot.equals("Tornar")) {

			Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
			stage.setTitle("Avis sense llar");
			stage.setScene(new Scene(arrel));
			stage.show();

		} else if (bot.equals("Ok")) {

			if (dE.getValue().compareTo(LocalDate.now()) < 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Advertencia");
				alert.setHeaderText("La data d'entrada ha de ser igual o posterior a la data actual.");
				alert.setContentText("Torna-ho a provar");
				alert.showAndWait();
				dE.setValue(null);
			}

			if (dS.getValue().compareTo(dE.getValue()) <= 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Advertencia");
				alert.setHeaderText("La data de sortida ha de ser posterior a la data d'entrada.");
				alert.setContentText("Torna-ho a provar");
				alert.showAndWait();
				dS.setValue(null);
			}

			for (int j = 0; j < ah.size(); j++) {
				if (ah.get(j).getIdIaio() == idI.getValue()) {
					if (convertToDateViaSqlDate(dE.getValue()).compareTo(ah.get(j).getDataSortida()) <= 0) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Advertencia");
						alert.setHeaderText(
								"Aquest avi en aquesta data d'entrada encara no a sortit de l'altre centre on està assignat.");
						alert.setContentText("Torna-ho a provar");
						alert.showAndWait();
						dE.setValue(null);
					}
				}
			}

			SuggerimentDao s = new SuggerimentDao();
			if (s.crearSuggeriment(idE.getValue().intValue(), idP.getValue().intValue(), idH.getValue().intValue(),
					nI.getText(), idI.getValue().intValue(), convertToDateViaSqlDate(dE.getValue()),
					convertToDateViaSqlDate(dS.getValue()))) {
				labelOk.setText("Suggeriment afegit correctament");
				PauseTransition pT = new PauseTransition(Duration.seconds(1));
				pT.setOnFinished(e -> labelOk.setText(""));
				pT.play();
			}

		}
	}

	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}
}
