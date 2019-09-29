import handler.DataWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Classe Main che imposta la risoluzione della finestra di gioco ad 800x600
// è stata realizzata a partire dall'esempio di base fornito con IntelliJ IDEA.
// Pian piano ho costruito la logica di gioco, i vari controller, la grafica, etc.
// Da ragazzino mi sarebbe piaciuto fare il programmatore di videogiochi, ma il grosso
// della documentazione era in Inglese ed ero troppo piccolo per comprendere tutto...
// Ora a 42 ho imparato Java da zero (l'Inglese non è più un ostacolo!) e sono riuscito a
// realizzare questo gioco che ai più sembrerà banale, ma che per me è un traguardo importante.
// La grafica si ispira al film "Ready Player One", un omaggio agli anni '80, i miei anni più belli.
// (CC) Gianluigi Di Vaio - C.S. 8014 - Informatica per il Management A.A. 2018/19.

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        primaryStage.setTitle("GUIz");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        System.out.println(Main.class.getResource("game.fxml"));
    }

    public static void main(String[] args) {
        new DataWriter(); // Ripristina le impostazioni di default ad ogni esecuzione
        launch(args);
    }
}
