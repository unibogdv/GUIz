import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        primaryStage.setTitle("Java Expert Quiz");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        System.out.println(Main.class.getResource("game.fxml"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
