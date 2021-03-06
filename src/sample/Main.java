package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        CarregaDadesDao cd = new CarregaDadesDao();
        cd.carregaUsuaris();
        cd.carregaIaios();
        cd.carregaEspais();
        cd.carregaSuggeriments();
        cd.carregaHistorial();

        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        primaryStage.setTitle("Avis sense llar");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
