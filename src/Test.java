import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * 
 * @author Bobfeng
 * 
 */

public class Test extends Application {
    public void start(Stage primaryStage) {
        Tantiao ballPane = new Tantiao(); // Create a ball pane
        ballPane.requestFocus();

        // Accelerate or decrease speed
        ballPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                ballPane.increaseSpeed();
            } else if (e.getCode() == KeyCode.DOWN) {
                ballPane.decreaseSpeed();
            }
        });
        // Initialize
        Jiemain start = new Jiemain();
        start.chushi();
        Scene scene = new Scene(start, 1000, 562);
        // Add scene to stage
        primaryStage.setScene(scene);
        // Lock the window size
        primaryStage.setResizable(false);
        // Set the stage title
        primaryStage.setTitle("ESCAPE BALL");
        // Display the stage
        primaryStage.show();

        start.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                // The main scene
                Scene scene2 = new Scene(ballPane, 1000, 562);
                // Put scene on the stage
                primaryStage.setScene(scene2);
                // Display the stage
                primaryStage.show(); 
            }
        });
        
        // Get focus so the keyboard can control
        start.requestFocus();        
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
