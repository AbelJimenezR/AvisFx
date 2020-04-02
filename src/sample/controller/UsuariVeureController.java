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
import sample.usuaris.TaulaUsuaris;
import sample.usuaris.Usuari;
import sample.usuaris.UsuarisDao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsuariVeureController implements Initializable {
    private static int id;
   /* private String nom;
    private String pass;
    private int rol;*/

    @FXML
    private TableView<TaulaUsuaris> taulaUsuaris;

    @FXML
    private Button bEdita, bEsborra;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Usuari> au = CarregaDadesDao.getLlistaUsuaris();

        ObservableList<TaulaUsuaris> dades = taulaUsuaris.getItems();

        for (Usuari u : au) {
            String pass=maskPass(u.getPassword());
            dades.add(new TaulaUsuaris(String.valueOf(u.getId()), u.getNom(), pass, u.getClass().getSimpleName()));
        }

    }

    @FXML
    public void clickItem(MouseEvent event) {
        if(!taulaUsuaris.getSelectionModel().getSelectedCells().isEmpty()) {
            bEdita.setDisable(false);
            bEsborra.setDisable(false);
            id = Integer.parseInt(taulaUsuaris.getSelectionModel().getSelectedItem().getId());
            System.out.println(id);
        }
    }

    @FXML
    protected void tornaPrincipal(ActionEvent event) throws IOException {
        UsuariVeureController.setId();

        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
        stage.setTitle("Avis sense llar");
        stage.setScene(new Scene(arrel));
        stage.show();
    }

    @FXML
    protected void optBotons(ActionEvent event) throws IOException {
        UsuarisDao ud = new UsuarisDao();

        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();

        if (bot.equals("Edita")) {

            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//usuariModifica.fxml"));
            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();

        } else if (bot.equals("Esborra")) {
            ud.eliminarUsuari(id);
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//usuariVeure.fxml"));
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

    private String maskPass(String password) {
        String output = "";
        for(int i = 0; i < 10; i++) {
            output += "\u2022";
        }
        return output;
    }

}


