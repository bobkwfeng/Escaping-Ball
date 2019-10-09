package com.bobkwfeng.escapeball;
import javafx.scene.text.*;
import javafx.scene.layout.Pane;
/**
 * 
 * @author Bobfeng
 * 
 */

public class Instruction extends Pane

{
    public void instruction() {
        Text text1 = new Text(130, 400, "Press SPACE to start");
        text1.setFont(Font.font("TimesRoman", FontWeight.BOLD,
                FontPosture.ITALIC, 70));
        getChildren().add(text1);

    }
}
