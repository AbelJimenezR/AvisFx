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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.CarregaDadesDao;
import sample.espais.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EspaiModificaController implements Initializable {

    ArrayList<ArrayList> planta = new ArrayList<ArrayList>();
    ArrayList<ArrayList> habitacion = new ArrayList<ArrayList>();
    ArrayList<Espai> llistaEspais = new ArrayList<>();
    ArrayList<Planta> llPlanta = new ArrayList<>();
    ArrayList<Habitacio> llHab = new ArrayList<>();
    Boolean access, disp;
    Espai ep;
    int id, plantOld, habOld;
    ObservableList<TaulaPlanta> dades;
    ObservableList<TaulaHabitacio> dadesHab;


    @FXML
    private TableView<TaulaPlanta> taulaPlanta;

    @FXML
    private TableColumn<TaulaPlanta, String> sup, sales;

    @FXML
    private TableColumn<TaulaHabitacio, String> llits;


    @FXML
    private TableView<TaulaHabitacio> taulaHabitacio;

    @FXML
    ChoiceBox tipus, accessibilitat, disponibilitat;

    @FXML
    private TextField nom, plantes, adreca, tipusEntitat;

    @FXML
    private Label tipE;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int x = 1; x < 3; x++) {
            if (x == 1) {
                disponibilitat.getItems().add("SÍ");

            } else {
                disponibilitat.getItems().add("NO");
            }
        }

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

        espai();
        addButtonToTable();
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


        } else if (bot.equals("Ok")) {
            access = (accessibilitat.getValue().toString().equals("SÍ")) ? true : false;
            disp = (disponibilitat.getValue().toString().equals("SÍ")) ? true : false;

            if (ep.getClass().getSimpleName().equals("EspaiPropietari")) {
                EspaiPropietari esp = (EspaiPropietari) ep;
                esp.setNomPropietari(nom.getText());
                esp.setNumPlantes(Integer.parseInt(plantes.getText()));
                esp.setAdreca(adreca.getText());
                esp.setAccessibilitat(access);
            } else {
                EspaiEntitat esp = (EspaiEntitat) ep;
                esp.setNomEntitat(nom.getText());
                esp.setNumPlantes(Integer.parseInt(plantes.getText()));
                esp.setAdreca(adreca.getText());
                esp.setAccessibilitat(access);
            }

            List<TaulaPlanta> a = taulaPlanta.getItems();
            List<TaulaHabitacio> b = taulaHabitacio.getItems();

            ArrayList<Planta> ap = ep.getPlantes();
            int pl = 0;
            int hb = 0;
            try {
                for (Planta p : ap) {

                    p.setId(Integer.parseInt(a.get(pl).getPlanta()));
                    p.setSuperficie(Double.parseDouble(a.get(pl).getSuperficie()));
                    p.setNumHabitacions(Integer.parseInt(a.get(pl).getHabitacions()));
                    p.setNumSales(Integer.parseInt(a.get(pl).getSales()));
                    pl++;

                    ArrayList<Habitacio> h = p.getHabitacions();
                    for (Habitacio hab : h) {
                        hab.setId(Integer.parseInt(b.get(hb).getHabitacio()));
                        hab.setNumLlits(Integer.parseInt(b.get(hb).getLlits()));
                        hb++;
                    }

                    ep.setNumPlaces();

                }
                EspaiDao ed = new EspaiDao();
                ed.actualitzaEspai(ep);

            } catch (Exception NumberFormatException) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Advertència");
                alert.setHeaderText("Format incorrecte");
                alert.setContentText("Torna-ho a provar");
                alert.showAndWait();
            }
        }
    }

    public void espai() {

        if (EspaiVeureController.getId() != 0) {
            id = EspaiVeureController.getId();
        } else {
            id = EspaiBuscaController.getId();
        }


        ep = buscaEspai(id);

        if (ep.getClass().getSimpleName().equals("EspaiPropietari")) {
            EspaiPropietari ePr = (EspaiPropietari) ep;
            llPlanta = ePr.getPlantes();
            tipus.getSelectionModel().select(0);
            nom.setText(ePr.getNomPropietari());
            plantes.setText(String.valueOf(ePr.getNumPlantes()));
            plantOld = ePr.getNumPlantes();
            adreca.setText(ePr.getAdreca());
            if (ePr.getDisponibilitat()) {
                disponibilitat.getSelectionModel().select(0);
            } else {
                disponibilitat.getSelectionModel().select(1);
            }
            if (ePr.getAccessibilitat()) {
                accessibilitat.getSelectionModel().select(0);
            } else {
                accessibilitat.getSelectionModel().select(1);
            }
        } else {
            EspaiEntitat eEn = (EspaiEntitat) ep;
            llPlanta = eEn.getPlantes();
            tipus.getSelectionModel().select(1);
            nom.setText(eEn.getNomEntitat());
            plantes.setText(String.valueOf(eEn.getNumPlantes()));
            plantOld = eEn.getNumPlantes();
            adreca.setText(eEn.getAdreca());
            if (eEn.getDisponibilitat()) {
                disponibilitat.getSelectionModel().select(0);
            } else {
                disponibilitat.getSelectionModel().select(1);
            }
            if (eEn.getAccessibilitat()) {
                accessibilitat.getSelectionModel().select(0);
            } else {
                accessibilitat.getSelectionModel().select(1);
            }
            tipusEntitat.setText(eEn.getTipusEntitat());
        }

        planta();
    }

    public void planta() {

        ArrayList<Planta> ap = ep.getPlantes();
        dades = taulaPlanta.getItems();

        for (Planta p : ap) {
            dades.add(new TaulaPlanta(String.valueOf(p.getId()), String.valueOf(p.getSuperficie()), String.valueOf(p.getNumSales()), String.valueOf(p.getNumHabitacions())));
            habitacio(p);
        }

        editCellSup(sup);
        editCellSales(sales);


    }

    public void habitacio(Planta p) {
        ArrayList<Habitacio> ah = p.getHabitacions();
        dadesHab = taulaHabitacio.getItems();

        for (Habitacio h : ah) {
            dadesHab.add(new TaulaHabitacio(String.valueOf(p.getId()), String.valueOf(h.getId()), String.valueOf(h.getNumLlits())));
        }


        editCellLlits(llits);


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

    public void clickItem() {
        if (!taulaPlanta.getSelectionModel().getSelectedCells().isEmpty()) {
            id = Integer.parseInt(taulaPlanta.getSelectionModel().getSelectedItem().getPlanta());
        }
    }

    private void editCellSup(TableColumn<TaulaPlanta, String> x) {
        x.setCellFactory(TextFieldTableCell.<TaulaPlanta>forTableColumn());

        x.setOnEditCommit(
                (TableColumn.CellEditEvent<TaulaPlanta, String> t) -> {
                    ((TaulaPlanta) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setSuperficie(t.getNewValue());
                });
    }

    private void editCellSales(TableColumn<TaulaPlanta, String> x) {
        x.setCellFactory(TextFieldTableCell.<TaulaPlanta>forTableColumn());
        x.setOnEditCommit(
                (TableColumn.CellEditEvent<TaulaPlanta, String> t) -> {
                    ((TaulaPlanta) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setSales(t.getNewValue());
                });
    }

    private void editCellLlits(TableColumn<TaulaHabitacio, String> x) {
        x.setCellFactory(TextFieldTableCell.<TaulaHabitacio>forTableColumn());
        x.setOnEditCommit(
                (TableColumn.CellEditEvent<TaulaHabitacio, String> t) -> {
                    ((TaulaHabitacio) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLlits(t.getNewValue());
                });
    }

    @FXML
    private void addPlant() {

        plantOld++;
        plantes.setText(String.valueOf(plantOld));
        dades.add(new TaulaPlanta(String.valueOf(plantOld), "0", "0", "0"));
    }


    private void addButtonToTable() {
        TableColumn<TaulaPlanta, Void> colBtn = new TableColumn("");

        Callback<TableColumn<TaulaPlanta, Void>, TableCell<TaulaPlanta, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<TaulaPlanta, Void> call(final TableColumn<TaulaPlanta, Void> param) {
                final TableCell<TaulaPlanta, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("+");


                    {
                        btn.setOnAction((ActionEvent event) -> {
                            TaulaPlanta tp = getTableView().getItems().get(getIndex());
                            habOld = Integer.parseInt(tp.getHabitacions());
                            habOld++;
                            tp.setHabitacions(String.valueOf(habOld));
                            int h = Integer.parseInt(tp.getPlanta());
                            dadesHab.add(new TaulaHabitacio(String.valueOf(h), String.valueOf(habOld), "0"));
                        });


                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);

                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        taulaPlanta.getColumns().add(colBtn);

    }
}



