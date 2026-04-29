
package dungeongame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

   @Override
public void start(Stage stage) {
    GameController controller = new GameController(); 
    Scene scene = new Scene(controller.getView(), 800, 600);

    stage.setTitle("Dungeon Escape");
    stage.setScene(scene);

    // Application Close 
    stage.setOnCloseRequest(e -> {
        System.exit(0);
    });

    stage.show();
}

    public static void main(String[] args) {
        launch();
    }
}