package com.example.dobozok;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {


    public Pane pane;
    public Label openCount;
    public Label closedCount;
    public ImageView arrow;

    int open = 0;
    int closed = 0;

    Label[][] t = new Label[10][15];
    String[][] tn = new String[10][15];

    public void initialize(){
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 15; x++){
                t[y][x] = new Label();
                t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/null.png"))));
                tn[y][x] = "icons/null.png";
                t[y][x].setLayoutX(10+x*64);
                t[y][x].setLayoutY(10+y*64);
                int finalY = y;
                int finalX = x;
                t[y][x].setOnMouseEntered(event -> t[finalY][finalX].setStyle("-fx-background-color: lightgreen"));
                t[y][x].setOnMouseExited(event -> t[finalY][finalX].setStyle("-fx-background-color: white"));
                pane.getChildren().add(t[y][x]);
            }
        }
    }

    public void onRandomClick() {

    }
}