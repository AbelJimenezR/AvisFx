package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.CarregaDadesDao;
import sample.usuaris.Usuari;
import sample.usuaris.UsuarisDao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsuariModificaController implements Initializable {

    @FXML
    private TextField id, nom, pass;

    @FXML
    private PasswordField pass_hidden;

    @FXML
    private Button veurePass;

    @FXML
    private ChoiceBox rol;
    private Usuari usuari;
    private int rolOld, idold;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for(int x=1;x<4;x++){
            if(x==1){
                rol.getItems().add(x + "- Usuari Coordinador");
            }else if(x==2){
                rol.getItems().add(x + "- Usuari Gestor");
            }else{
                rol.getItems().add(x + "- Usuari");

            }
        }

        if (UsuariVeureController.getId() != 0) {
            idold = UsuariVeureController.getId();
        } else {
            idold = UsuariBuscaController.getId();
        }
        usuari = buscaUsuari(idold);

        if (usuari.getClass().getSimpleName().equals("Usuari")) {
            rol.getSelectionModel().select(2);
            rolOld = 3;
        } else if (usuari.getClass().getSimpleName().equals("UsuariGestor")) {
            rol.getSelectionModel().select(1);
            rolOld = 2;
        } else {
            rol.getSelectionModel().select(0);
            rolOld = 1;
        }

        id.setText(String.valueOf(usuari.getId()));
        nom.setText(usuari.getNom());
        //pass.setText(usuari.getPassword());
        pass_hidden.setText(usuari.getPassword());
    }


    @FXML
    protected void optBotons(ActionEvent event) throws IOException {

        UsuariVeureController.setId();
        Button boto = (Button) event.getSource();
        Stage stage = (Stage) boto.getScene().getWindow(); //this accesses the window.
        String bot = boto.getText();
        Boolean x = false;
        if (bot.equals("Tornar")) {
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//menuCoordinador.fxml"));
            stage.setTitle("Avis sense llar");
            stage.setScene(new Scene(arrel));
            stage.show();
            UsuariVeureController.setId();
        } else if (bot.equals("Modifica")) {
            if (comprovaDades()){
            String d = rol.getValue().toString();
            String c = d.substring(0, 1);
            try {
                if (rolOld == Integer.parseInt(c)) {
                    UsuarisDao ud = new UsuarisDao();
                    ud.actualitzarUsuari(idold, nom.getText(), pass_hidden.getText());
                } else {
                    UsuarisDao ud = new UsuarisDao();
                    ud.eliminarUsuari(idold);
                    ud.crearUsuari(nom.getText(), pass_hidden.getText(), Integer.parseInt(c));


                }


            } catch (Exception e) {
            }
            Parent arrel = FXMLLoader.load(getClass().getResource("..//view//usuariVeure.fxml"));
            stage.setTitle("Usuaris");
            stage.setScene(new Scene(arrel));
            stage.show();
        }
        }
    }

    public Usuari buscaUsuari(int id) {
        ArrayList<Usuari> au = CarregaDadesDao.getLlistaUsuaris();

        for (Usuari u : au) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    private boolean comprovaDades(){
        if(nom.getText().isEmpty()||pass.getText().isEmpty()||rol.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertència");
            alert.setHeaderText("Falten dades per introduïr");
            alert.setContentText("Torna-ho a provar");
            alert.showAndWait();

            return false;
        }
        return true;
    }

    @FXML
    private void showPass(ActionEvent event) {
        Button boto = (Button) event.getSource();
        String bot = boto.getText();
        if(bot.equals("Mostra")) {
            pass_hidden.setVisible(false);
            pass.setVisible(true);
            pass.setText(pass_hidden.getText());
            veurePass.setText("Amaga");
        }else{
            pass_hidden.setVisible(true);
            pass.setVisible(false);
            veurePass.setText("Mostra");
            pass_hidden.setText(pass.getText());


        }
    }

    @FXML
    private void setPass(){
        pass.setText(pass_hidden.getText());
    }

}
