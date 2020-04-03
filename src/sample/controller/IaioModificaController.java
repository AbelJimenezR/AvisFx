package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.CarregaDadesDao;
import sample.Utilitat;
import sample.iaios.GrauIncapacitat;
import sample.iaios.IaioDao;
import sample.usuaris.Usuari;
import sample.usuaris.UsuarisDao;
import sample.iaios.Iaio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IaioModificaController implements Initializable {

    @FXML
    private TextField id, nom, edat;

    @FXML
    private ChoiceBox incapacitat;
    private Iaio iaio;
    private int idold;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for(int x=1;x<4;x++){
            if(x==1){
                incapacitat.getItems().add(x + "- BAIXA");
            }else if(x==2){
                incapacitat.getItems().add(x + "- ALTA");
            }else{
                incapacitat.getItems().add(x + "- MOLT ALTA");

            }
        }

        if (IaioVeureController.getId() != 0) {
            idold = IaioVeureController.getId();
        } else {
            idold = IaioBuscaController.getId();
        }

        iaio = buscaIaio(idold);
        System.out.println(iaio.getIncapacitat());

        if (String.valueOf(iaio.getIncapacitat()).equals("BAIX")) {
            incapacitat.getSelectionModel().select(0);
        } else if (String.valueOf(iaio.getIncapacitat()).equals("ALT")) {
            incapacitat.getSelectionModel().select(1);
        } else {
            incapacitat.getSelectionModel().select(2);
        }

        id.setText(String.valueOf(iaio.getId()));
        nom.setText(iaio.getNom());
        edat.setText(String.valueOf(iaio.getEdat()));

    }


    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();
        IaioVeureController.setId();


        if (bot.equals("Tornar")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
            stage.setTitle("Avis sense llar");
            stage.setScene(new Scene(arrel));
            stage.show();

        } else if (bot.equals("Modificar")) {
            if (comprovaDades()) {
                String d = incapacitat.getValue().toString();
                String c = d.substring(0, 1);
                try {
                    IaioDao id = new IaioDao();
                    id.actualitzarIaio(idold, nom.getText(), Integer.parseInt(edat.getText()), Integer.parseInt(c));


                } catch (Exception e) {
                }
                Parent arrel = FXMLLoader.load(getClass().getResource("..//view//iaioVeure.fxml"));
                stage.setTitle("Avis");
                stage.setScene(new Scene(arrel));
                stage.show();
            }
        }
    }

    public Iaio buscaIaio(int id) {
        ArrayList<Iaio> ai = CarregaDadesDao.getLlistaIaios();

        for (Iaio iaio : ai) {
            if (iaio.getId() == id) {
                return iaio;
            }
        }
        return null;
    }

    private boolean comprovaDades(){
        if(nom.getText().isEmpty()||(edat.getText().isEmpty()||!edat.getText().matches("\\d*"))||incapacitat.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("Falten dades per introduïr");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();

            return false;
        }
        return true;
    }


}
