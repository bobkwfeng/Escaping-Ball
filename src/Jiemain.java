import javafx.scene.text.*;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Bobfeng
 * 
 */

public class Jiemain extends Pane
{
	public void chushi() 
	{
	      //Headline 1
		  Text text0 = new Text(80, 200, "Escape Ball");
		  text0.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 130));
		  //Headline 2
		  Text text1 = new Text(130, 400, "Press SPACE to start");
  		  text1.setFont(Font.font("TimesRoman",FontWeight.BOLD, FontPosture.ITALIC, 70)); 
  		  Text text2 = new Text(110,280, "(See how quick you can catch the ball!)");
  		  text2.setFont(Font.font("TimesRoman",FontWeight.BOLD, FontPosture.ITALIC, 40));
  		  getChildren().add(text0);
  		  getChildren().add(text1);
  		  getChildren().add(text2);
	}
     
}
