package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.CarregaDadesDao;
import sample.espais.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EspaiNouController implements Initializable {

    ArrayList<ArrayList> planta = new ArrayList<ArrayList>();
    ArrayList<ArrayList> habitacion = new ArrayList<ArrayList>();
    private static ArrayList<Espai> llistaEspais = CarregaDadesDao.getLlistaEspais();
    TitledPane[] plant;
    List<TextField> textFieldContainer = new ArrayList();
    Boolean access;
    Espai ep;

    @FXML
     private Button ok;

    @FXML
    Pane espai;

    @FXML
    ChoiceBox tipus, accessibilitat, disponibilitat;

    @FXML
    private TextField nom, plantes, adreca, tipusEntitat;

    @FXML
    private Label tipE;

    @FXML
    Accordion acordeon, acordeon2;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
            ok.setVisible(false);

            /*for(int x=1;x<3;x++){
                if(x==1){
                    disponibilitat.getItems().add("SÍ");

                }else {
                    disponibilitat.getItems().add("NO");
                }
            }*/

        for (int x = 1; x < 3; x++) {
            if (x == 1) {
                accessibilitat.getItems().add("SÍ");

            } else {
                accessibilitat.getItems().add("NO");
            }
        }


        for (int x = 1; x < 3; x++) {
            if (x == 1) {
                tipus.getItems().add("PROPIETARI");
            } else {
                tipus.getItems().add("ENTITAT");
            }
        }

        tipus.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                String a = tipus.getItems().get((Integer) number2).toString();
                if (a.equals("PROPIETARI")) {
                    tipE.setVisible(false);
                    tipusEntitat.setVisible(false);
                } else {
                    tipE.setVisible(true);
                    tipusEntitat.setVisible(true);
                }
            }
        });
    }

    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();

        if (bot.equals("Tornar")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
            stage.setTitle("Avis sense llar");
            stage.setScene(new Scene(arrel));
            stage.show();


        } else if (bot.equals("Següent")) {
            if (comprovaDadesEspai()) {
                espai.setDisable(true);
                planta();

            }
        } else if (bot.equals("Ok")) {
            if (comprovaPanells()&&comprovaDadesEspai()) {



            access = (accessibilitat.getValue().toString().equals("SÍ")) ? true : false;
            //  disp= (disponibilitat.getValue().toString().equals("SÍ"))? true:false;

            int id = EspaiDao.ultimId() + 1;

            if (tipus.getValue().toString().equals("PROPIETARI")) {
                ep = new EspaiPropietari(id, adreca.getText(), access, true, Integer.parseInt(plantes.getText()), nom.getText());
            } else {
                ep = new EspaiEntitat(id, adreca.getText(), access, true, Integer.parseInt(plantes.getText()), nom.getText(), tipusEntitat.getText());
            }
            ArrayList<Planta> ap = ep.getPlantes();
            int pl = 0;
            for (Planta p : ap) {
                int hb = 0;

                p.setId(pl + 1);
                p.setSuperficie(Double.parseDouble(planta.get(pl).get(0).toString()));
                p.setNumSales(Integer.parseInt(planta.get(pl).get(1).toString()));
                p.setNumHabitacions(Integer.parseInt(planta.get(pl).get(2).toString()));
                ArrayList<Habitacio> h = p.getHabitacions();
                for (Habitacio hab : h) {
                    hab.setId(hb + 1);
                    for (int x = 0, j = 0; x < habitacion.size(); x++) {
                        if ((pl + 1) == Integer.parseInt(habitacion.get(x).get(0).toString())) {
                            if (Integer.parseInt(habitacion.get(x).get(1).toString()) == (hb + 1)) {
                                hab.setNumLlits(Integer.parseInt(habitacion.get(x).get(2).toString()));

                            }
                            j = j + 1;

                        }
                    }
                    hb = hb + 1;
                }
                pl = pl + 1;
            }
            ep.setNumPlaces();

            if (ep.getClass().getSimpleName().toString().equals("EspaiPropietari")) {
                EspaiDao ed = new EspaiDao((EspaiPropietari) ep);
                ed.crearEspai();
            } else {
                EspaiDao ed = new EspaiDao((EspaiEntitat) ep);
                ed.crearEspai();
            }
            System.out.println(ep);
            llistaEspais.add(ep);

        }
        }


    }

    public void planta() {
        int i = Integer.parseInt(plantes.getText());
        ArrayList<Integer> p = new ArrayList(i);
        for (int s = 0; s < i; s++) {
            p.add(s);
        }

        plant = new TitledPane[i];

        for (Integer a : p) {
            crearPanelPlanta(a, plant);
        }
        acordeon.getPanes().addAll(plant);

    }

    private void crearPanelPlanta(Integer a, TitledPane[] tps) {
        tps[a] = new TitledPane();
        tps[a].setId("panel" + a);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Superfície: "), 0, 0);
        TextField superficie = new TextField();
        textFieldContainer.add(superficie);//Add your textField to the container when you create it

        superficie.setId("superficie");
        grid.add(superficie, 1, 0);
        grid.add(new Label("Sales: "), 0, 1);
        TextField sales = new TextField();
        textFieldContainer.add(sales);//Add your textField to the container when you create it

        sales.setId("superficie");
        grid.add(sales, 1, 1);
        grid.add(new Label("Habitacions: "), 0, 2);
        TextField habitacions = new TextField();
        textFieldContainer.add(habitacions);//Add your textField to the container when you create it

        habitacions.setId("habitacions");
        grid.add(habitacions, 1, 2);
        Button b = new Button();
        b.setText("Ok");
        grid.add(b, 1, 3);
        tps[a].setText("Planta " + (a + 1));
        tps[a].setContent(grid);

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (comprovaDadesPlanta(superficie, sales, habitacions)) {
                    ArrayList l = new ArrayList();
                    l.add(superficie.getText());
                    l.add(sales.getText());
                    l.add(habitacions.getText());
                    planta.add(a, l);
                    b.setDisable(true);
                    superficie.setDisable(true);
                    habitacions.setDisable(true);
                    sales.setDisable(true);

                    habitacio(Integer.parseInt(habitacions.getText()), a);
                }
            }
        });

    }

    public void habitacio(int habitacions, int planta) {
        ArrayList<Integer> p = new ArrayList(habitacions);
        for (int s = 0; s < habitacions; s++) {
            p.add(s);
        }
        TitledPane[] tps = new TitledPane[habitacions];

        for (Integer a : p) {
            crearPanelHabitacion(a, tps, planta);
        }

        acordeon2.getPanes().addAll(tps);
    }

    public void crearPanelHabitacion(Integer a, TitledPane[] tps, int planta) {

        tps[a] = new TitledPane();
        tps[a].setId("panel" + a);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Llits: "), 0, 0);
        TextField llits = new TextField();
        textFieldContainer.add(llits);
        llits.setId("llits");
        grid.add(llits, 1, 0);

        Button b = new Button();
        b.setText("Ok");

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (comprovaDadesHabitacio(llits)) {
                    ArrayList l = new ArrayList();
                    l.add(planta + 1);
                    l.add(a + 1);
                    l.add(llits.getText());
                    habitacion.add(a, l);
                    b.setDisable(true);
                    llits.setDisable(true);
                    ok.setVisible(true);

                }
            }
        });

        grid.add(b, 1, 1);

        tps[a].setText("Planta : " + (planta + 1) + " Habitació " + (a + 1));
        tps[a].setContent(grid);


    }


    public boolean comprovaDadesEspai() {
        if (nom.getText().isEmpty() || (plantes.getText().isEmpty() || !plantes.getText().matches("\\d*")) || adreca.getText().isEmpty() || accessibilitat.getValue().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("Dades incorrectes");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();

            return false;
        }
        return true;

    }

    private boolean comprovaDadesPlanta(TextField superficie, TextField sales, TextField habitacions) {
        if ((superficie.getText().isEmpty() || !superficie.getText().matches("\\d*")) || (sales.getText().isEmpty() || !sales.getText().matches("\\d*"))
                || (habitacions.getText().isEmpty() || !habitacions.getText().matches("\\d*"))) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("Dades incorrectes");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean comprovaDadesHabitacio(TextField llits) {
        if (llits.getText().isEmpty() || !llits.getText().matches("\\d*")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("Dades incorrectes");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean comprovaPanells() {

        for (TextField node : textFieldContainer) {
            if(node.getText().isEmpty()){Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Advertència");
                alert.setHeaderText("Falten dades per introduïr");
                alert.setContentText("Torna-ho a provar");
                alert.showAndWait();

                return false;
            }
        }
        return true;
    }

}

