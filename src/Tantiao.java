import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * 
 * @author Bobfeng
 * 
 */

public class Tantiao extends Pane {
    // The radius of all Ball
    public final double radius = 20;
    private double x = radius, y = radius;
    private double dx = 1, dy = 1;
    double x1, x2, y1, y2;
    long startMili;
    boolean lockstart = false;
    // The speed of ball
    double speed;
    boolean end = false;
    Circle circle = new Circle(x, y, radius);
    // The start position of main circle
    private Circle maincircle = new Circle(500, 281, radius);
    String s;

    private Timeline animation;
    
    Button start = new Button("Start");
    Button pause = new Button("Pause");
    Text showscore = new Text("Score: ");
    TextField score = new TextField();
    
    public Tantiao()
    { 
        
        start.setLayoutX(600);
        start.setLayoutY(520);
        start.setOnMouseClicked(e -> play());
        getChildren().add(start);
        
        
        pause.setLayoutX(670);
        pause.setLayoutY(520);
        pause.setOnMouseClicked(e -> stop());
        getChildren().add(pause);
        
        
        showscore.setLayoutX(750);
        showscore.setLayoutY(537);
        getChildren().add(showscore);
        
        
        score.setLayoutX(800);
        score.setLayoutY(520);
        score.setEditable(false);
        getChildren().add(score);
        
        // Set the chasing ball color red
        circle.setFill(Color.RED);
        // Set the main ball color red
        maincircle.setFill(Color.GREEN);
        // Place a ball into this pane
        getChildren().add(circle); 
        getChildren().add(maincircle);
        // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(150), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setRate(100);
        // 初始化speed
        speed = animation.getRate();
    }

    public void play()
    {
        Thread movemaincircle = new Threadmovemaincircle();
        movemaincircle.start();
        score.setText("");
        animation.play();
        lockstart = true;
        // Record the beginning time
        startMili = System.currentTimeMillis();
    }

    // Used for pause
    public void stop()
    {
        animation.pause();
        maincircle.setOnMouseDragged(null);
    }

    // Used for accelerate
    public void increaseSpeed()
    {
        animation.setRate(animation.getRate() + 1);
        speed = speed + 1;
    }

    // Used for slow down
    public void decreaseSpeed()
    {
        if (animation.getRate() > 1) {
            System.out.println("drcrease speed");
            animation.setRate(animation.getRate() - 1);
            speed = speed - 1;
        }
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    //Chase the ball
    protected void moveBall() {
        double randomNum = Math.random(); 
        // Check boundaries
        if (x < radius || x > getWidth() - radius) {
            dx *= -1; // Change ball move direction
        }

        if (y < radius || y > getHeight() - 40 - radius) {
            dy *= -1; // Change ball move direction
        }

        // Adjust ball position
        x = x + dx;
        y = y + dy;
        circle.setCenterX(x);
        if (circle.getCenterX() > 20) {
            x1 = circle.getCenterX();
        }
        circle.setCenterY(y);
        if (circle.getCenterY() > 20) {
            y1 = circle.getCenterY();
        }
    }
    
    public class Threadmovemaincircle extends Thread {
        @Override
        public void run() {
            maincircle.setOnMouseDragged(e ->
            {
                    maincircle.setCenterX(e.getX());
                    maincircle.setCenterY(e.getY());
                    x2 = maincircle.getCenterX();
                    y2 = maincircle.getCenterY();
                    Thread jifen2 = new Threadjifen();
                    jifen2.start();
            });
        }
    }
    
    // Used for counting distance
    public class Threadjifen extends Thread implements Runnable{
        @Override
        public void run() {
            // Keep counting scores until two balls meet
            while (true) {
                double distance = jifen(x1, x2, y1, y2);
                long endMili = System.currentTimeMillis();
                s = String.valueOf(endMili - startMili);
                //score.appendText(s);
                if (distance <= 40) {
                    // Stop the thread when two balls meet
                    animation.pause();
                    maincircle.setOnMouseDragged(null);
                    end = true;
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                        // TODO Auto-generated method stub
                           // Clear the old score
                           score.setText("");
                           score.setText(s); 
                        }
                        
                    });
                    break;
                }
            }
        }
    }
    
    // Used for counting score
    public double jifen (double x12 ,double x22, double y12, double y22) {
        double distance = Math.sqrt((x12 - x22) * (x12 - x22) + (y12 - y22) * (y12 - y22));
        System.out.println(distance);
        return distance;
    }
    
    // Used for showing result
    public void result (String s) {
        animation.pause();
        Rectangle eraser = new Rectangle(0, 0, 2500, 1500);
        eraser.setFill(Color.WHITE);
        getChildren().add(eraser);
        Text text0 = new Text(90, 200, "Game Over");
        text0.setFont(Font.font("Courier", FontWeight.BOLD,
                FontPosture.ITALIC, 150));
        Text text1 = new Text(110, 400, "Your Score Is: " + s);
        text1.setFont(Font.font("TimesRoman", FontWeight.BOLD,
                FontPosture.ITALIC, 80));
        Text text2 = new Text(850, 500, "Made by Bob");
        Text text3 = new Text(860, 520, "Ver:1.0");
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
    }

}
