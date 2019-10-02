import handler.DataWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Classe Main che imposta la risoluzione della finestra di gioco ad 800x600
// è stata realizzata a partire dall'esempio di base fornito con IntelliJ IDEA.

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
