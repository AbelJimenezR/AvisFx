package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCoordinadorController implements Initializable {
    private static int rolUser;

    @FXML
    Menu menuUser, menuSuggeriment;

    @FXML
    MenuItem menuVeureSuggeriment;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rolUser = MainController.getRol();
        if (rolUser != 1) {
            menuUser.setVisible(false);
            menuVeureSuggeriment.setVisible(false);
        }

        if (rolUser == 3) {
        	menuSuggeriment.setVisible(false);
        }

        Image image = null;
        try {
            image = new Image(new FileInputStream("avis.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setImage(image);


    }


    public void menuUsuariCoordinador(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) imageView.getScene().getWindow(); //this accesses the window.

        MenuItem item = (MenuItem) actionEvent.getSource();
        String sItem = item.getText();

        if (sItem.equals("Nou Usuari")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//usuariNou.fxml"));

            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Busca Usuari")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//usuariBusca.fxml"));

            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Veure Usuaris")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//usuariVeure.fxml"));

            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        //MENU IAIOS**********************/
        if (sItem.equals("Nou Iaio")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//iaioNou.fxml"));

            stage.setTitle("Avis");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Busca Iaios")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//iaioBusca.fxml"));

            stage.setTitle("Avis");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Veure Iaios")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//iaioVeure.fxml"));

            stage.setTitle("Avis");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        //MENU ESPAIS//
        if (sItem.equals("Nou Espai")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//espaiNou.fxml"));
            stage.setTitle("Espais");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Busca Espais")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//espaiBusca.fxml"));

            stage.setTitle("Espais");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Veure Espais")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//espaiVeure.fxml"));
            stage.setTitle("Espais");
            stage.setX(300);
            stage.setY(300);
            stage.setScene(new Scene(arrel));
            stage.show();
        }

      //MENU SUGGRIMENTS//
        if (sItem.equals("Nou Suggeriment")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//suggerimentNou.fxml"));
            stage.setTitle("Suggeriments");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Veure Suggeriments")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//suggerimentVeure.fxml"));
            stage.setTitle("Espais");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Veure Historial")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//historial.fxml"));
            stage.setTitle("Historial");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Veure Disponibilitat")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//disponibilitat.fxml"));
            stage.setTitle("Disponibilitat");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        //TANCA SESSI�*********************/
        if (sItem.equals("Tancar Sessio")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//Main.fxml"));

            stage.setTitle("Avis sense Llar");
            stage.setScene(new Scene(arrel));
            stage.show();
        }

        if (sItem.equals("Sortir")) {
            System.exit(0);
        }


    }
}
