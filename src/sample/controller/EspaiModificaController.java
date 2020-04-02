package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.CarregaDadesDao;
import sample.espais.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EspaiModificaController  implements Initializable {

    ArrayList<ArrayList> planta = new ArrayList<ArrayList>();
    ArrayList<ArrayList> habitacion = new ArrayList<ArrayList>();
    ArrayList<Espai> llistaEspais = new ArrayList<>();
    ArrayList<Planta> llPlanta = new ArrayList<>();
    ArrayList<Habitacio> llHab = new ArrayList<>();
    Boolean access,disp;
    Espai ep;
    int id;

    @FXML
    private TableView<TaulaPlanta> taulaPlanta;

    @FXML
    private TableColumn pl;

    @FXML
    private TableView<TaulaHabitacio> taulaHabitacio;

    @FXML
    ChoiceBox tipus,accessibilitat, disponibilitat;

    @FXML
    private TextField nom, plantes, adreca,tipusEntitat;

    @FXML
    private Label tipE;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(int x=1;x<3;x++){
            if(x==1){
                disponibilitat.getItems().add("SÍ");

            }else {
                disponibilitat.getItems().add("NO");
            }
        }

        for(int x=1;x<3;x++){
            if(x==1){
                accessibilitat.getItems().add("SÍ");

            }else {
                accessibilitat.getItems().add("NO");
            }
        }

        for(int x=1;x<3;x++){
            if(x==1){
                tipus.getItems().add("PROPIETARI");
            }else {
                tipus.getItems().add("ENTITAT");
            }
        }

        tipus.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                String a = tipus.getItems().get((Integer) number2).toString();
                if(a.equals("PROPIETARI")) {
                    tipE.setVisible(false);
                    tipusEntitat.setVisible(false);
                }else{
                    tipE.setVisible(true);
                    tipusEntitat.setVisible(true);
                }
            }
        });

        espai();

    }

    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();
        EspaiVeureController.setId();


        if (bot.equals("Tornar")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//espaiVeure.fxml"));
            stage.setTitle("ESPAIS");
            stage.setScene(new Scene(arrel));
            stage.show();


        } else if(bot.equals("Ok")){
            access= (accessibilitat.getValue().toString().equals("SÍ"))? true:false;
            disp= (disponibilitat.getValue().toString().equals("SÍ"))? true:false;


          /*  if(tipus.getValue().toString().equals("PROPIETARI")) {
                ep = new EspaiPropietari(id, adreca.getText(), access, disp, Integer.parseInt(plantes.getText()), nom.getText());
            }else{
                ep = new EspaiEntitat(id, adreca.getText(), access, disp, Integer.parseInt(plantes.getText()), nom.getText(),tipusEntitat.getText());
            }*/
            ArrayList<Planta> ap = ep.getPlantes();
            int pl=0;
            for(Planta p : ap){
              /*  p.setSuperficie(taulaPlanta.getItems().indexOf(0).);
                EspaiDao ed=new EspaiDao();
                ed.actualitzaPlanta(p);*/

               /* p.setSuperficie(Double.parseDouble(planta.get(pl).get(0).toString()));
                p.setNumSales(Integer.parseInt(planta.get(pl).get(1).toString()));
                p.setNumHabitacions(Integer.parseInt(planta.get(pl).get(2).toString()));
                ArrayList<Habitacio> h =p.getHabitacions();*/
                /*for(Habitacio hab : h) {
                    hab.setId(hb+1);
                    for(int x = 0,j=0;x<habitacion.size();x++){
                        if((pl+1)==Integer.parseInt(habitacion.get(x).get(0).toString())) {
                            if (Integer.parseInt(habitacion.get(x).get(1).toString()) == (hb + 1)) {
                                hab.setNumLlits(Integer.parseInt(habitacion.get(x).get(2).toString()));

                            }
                            j=j+1;

                        }
                    }
                    hb=hb+1;
                }*/
                pl=pl+1;
            }
            ep.setNumPlaces();

            if(ep.getClass().getSimpleName().toString().equals("EspaiPropietari")){
                EspaiDao ed = new EspaiDao((EspaiPropietari) ep);
                ed.crearEspai();
            }else{
                EspaiDao ed = new EspaiDao((EspaiEntitat) ep);
                ed.crearEspai();
            }
            System.out.println(ep);

        }

    }

    public void espai(){

        if (EspaiVeureController.getId() != 0) {
            id = EspaiVeureController.getId();
        } else {
            id  = EspaiBuscaController.getId();
        }


        ep = buscaEspai(id);

        if(ep.getClass().getSimpleName().equals("EspaiPropietari")){
            EspaiPropietari ePr = (EspaiPropietari) ep;
            llPlanta=ePr.getPlantes();
            tipus.getSelectionModel().select(0);
            nom.setText(ePr.getNomPropietari());
            plantes.setText(String.valueOf(ePr.getNumPlantes()));
            adreca.setText(ePr.getAdreca());
            if(ePr.getDisponibilitat()){
                disponibilitat.getSelectionModel().select(0);
            }else{
                disponibilitat.getSelectionModel().select(1);
            }
            if(ePr.getAccessibilitat()){
                accessibilitat.getSelectionModel().select(0);
            }else{
                accessibilitat.getSelectionModel().select(1);
            }
        }else{
            EspaiEntitat eEn = (EspaiEntitat) ep;
            llPlanta=eEn.getPlantes();
            tipus.getSelectionModel().select(1);
            nom.setText(eEn.getNomEntitat());
            plantes.setText(String.valueOf(eEn.getNumPlantes()));
            adreca.setText(eEn.getAdreca());
            if(eEn.getDisponibilitat()){
                disponibilitat.getSelectionModel().select(0);
            }else{
                disponibilitat.getSelectionModel().select(1);
            }
            if(eEn.getAccessibilitat()){
                accessibilitat.getSelectionModel().select(0);
            }else{
                accessibilitat.getSelectionModel().select(1);
            }
            tipusEntitat.setText(eEn.getTipusEntitat());
        }

        planta();
    }

    public void planta(){

        ArrayList<Planta> ap = ep.getPlantes();
        ObservableList<TaulaPlanta> dades = taulaPlanta.getItems();

        for (Planta p : ap) {
            dades.add(new TaulaPlanta(String.valueOf(p.getId()),String.valueOf(p.getSuperficie()),String.valueOf(p.getNumSales()),String.valueOf(p.getNumHabitacions())));
            habitacio(p);
        }
    }

    public void habitacio(Planta p){
        ArrayList<Habitacio> ah = p.getHabitacions();
        ObservableList<TaulaHabitacio> dades = taulaHabitacio.getItems();

        for (Habitacio h : ah) {
            dades.add(new TaulaHabitacio(String.valueOf(p.getId()),String.valueOf(h.getId()),String.valueOf(h.getNumLlits())));
        }
    }




    public boolean comprovaDadesEspai(){
        if(nom.getText().isEmpty()||plantes.getText().isEmpty()||adreca.getText().isEmpty()||accessibilitat.getValue().toString().isEmpty()||disponibilitat.getValue().toString().isEmpty()){
            return false;
        }
        return true;

    }

    private boolean comprovaDadesPlanta(TextField superficie, TextField sales, TextField habitacions) {
        if(superficie.getText().isEmpty()||sales.getText().isEmpty()||habitacions.getText().isEmpty()){
            return false;
        }
        return true;
    }

    private boolean comprovaDadesHabitacio(TextField llits) {
        if(llits.getText().isEmpty()){
            return false;
        }
        return true;
    }

    public Espai buscaEspai(int id) {
        ArrayList<Espai> ae = CarregaDadesDao.getLlistaEspais();

        for (Espai e : ae) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void clickItem(){
        id = Integer.parseInt(taulaPlanta.getSelectionModel().getSelectedItem().getPlanta());
    }

}



