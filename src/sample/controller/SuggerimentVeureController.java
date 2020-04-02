package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import sample.suggeriment.Suggeriment;
import sample.suggeriment.SuggerimentDao;
import sample.suggeriment.TaulaSuggeriments;

public class SuggerimentVeureController implements Initializable{
	
	private static int idE, idI, idP, idH;
	private Date dE, dS;
	private String nI;

    @FXML
    private TableView<TaulaSuggeriments> taulaSuggeriments;
	
    @FXML
    private Button bEdita, bEsborra, bAccepta;
    
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Suggeriment> as = CarregaDadesDao.getLlistaSuggeriments();

        ObservableList<TaulaSuggeriments> dades = taulaSuggeriments.getItems();

        for (Suggeriment i : as) {
            dades.add(new TaulaSuggeriments(String.valueOf(i.getIdEspai()), String.valueOf(i.getIdPlanta()), String.valueOf(i.getIdHabitacio()), i.getNomIaio(), String.valueOf(i.getIdIaio()), String.valueOf(i.getDataEntrada()), String.valueOf(i.getDataSortida())));
        }

    }
    
    @FXML
    public void clickItem(MouseEvent event) throws ParseException {
        if(!taulaSuggeriments.getSelectionModel().getSelectedCells().isEmpty()) {
            bEdita.setDisable(false);
            bEsborra.setDisable(false);
            bAccepta.setDisable(false);
            idE = Integer.parseInt(taulaSuggeriments.getSelectionModel().getSelectedItem().getIdEspai());
            idI = Integer.parseInt(taulaSuggeriments.getSelectionModel().getSelectedItem().getIdIaio());
            idP = Integer.parseInt(taulaSuggeriments.getSelectionModel().getSelectedItem().getIdPlanta());
            idH = Integer.parseInt(taulaSuggeriments.getSelectionModel().getSelectedItem().getIdHabitacio());
            nI = taulaSuggeriments.getSelectionModel().getSelectedItem().getNomIaio();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            dE = formatter.parse(taulaSuggeriments.getSelectionModel().getSelectedItem().getDataEntrada());
            dS = formatter.parse(taulaSuggeriments.getSelectionModel().getSelectedItem().getDataSortida());
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
        SuggerimentDao sdao = new SuggerimentDao();

        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();

        if (bot.equals("Edita")) {

        	sdao.eliminarSuggeriment(idE, idI);
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//suggerimentModifica.fxml"));
            stage.setTitle("Suggeriments");
            stage.setScene(new Scene(arrel));
            stage.show();


        } else if (bot.equals("Esborra")) {

        	sdao.eliminarSuggeriment(idE, idI);
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//suggerimentVeure.fxml"));
            stage.setTitle("Suggeriments");
            stage.setScene(new Scene(arrel));
            stage.show();
            
        }else if (bot.equals("Accepta")) {

        	sdao.acceptarSuggeriment(idE, idP, idH, nI, idI, dE, dS);
            sdao.eliminarSuggeriment(idE, idI);
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//SuggerimentVeure.fxml"));
            stage.setTitle("Suggeriments");
            stage.setScene(new Scene(arrel));
            stage.show();
        }
    }

}
