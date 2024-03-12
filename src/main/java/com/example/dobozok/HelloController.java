package com.example.dobozok;

import javafx.animation.AnimationTimer;
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
    int[][] ticks = new int[10][15];

    long step = 0;
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(l > step){
                step = l + 100_000_000;
                arrow.setRotate(arrow.getRotate()+36);
                for(int y = 0; y < 10; y++){
                    for(int x = 0; x < 15; x++){
                        if(ticks[y][x] != -1){
                            if(ticks[y][x] == 10){
                                tn[y][x] = "icons/null.png";
                                t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/null.png"))));
                                open++;
                                ticks[y][x] = -2;
                            }
                            ticks[y][x]++;
                        }
                    }
                }
                openCount.setText(open+" db");
            }
        }
    };

    public void initialize(){
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 15; x++){
                t[y][x] = new Label();
                t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/null.png"))));
                tn[y][x] = "icons/null.png";
                t[y][x].setLayoutX(10+x*64);
                t[y][x].setLayoutY(10+y*64);
                ticks[y][x] = -1;
                int finalY = y;
                int finalX = x;
                t[y][x].setOnMouseEntered(event -> t[finalY][finalX].setStyle("-fx-background-color: lightgreen"));
                t[y][x].setOnMouseExited(event -> t[finalY][finalX].setStyle("-fx-background-color: white"));
                t[y][x].setOnMousePressed(event -> katt(finalY,finalX));
                pane.getChildren().add(t[y][x]);
            }
        }
        onRandomClick();
        timer.start();
    }

    public void katt(int y, int x){
        if(y == 9 && tn[y][x] == "icons/null.png"){
            tn[y][x] = "icons/box.png";
            closed++;
            t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/box.png"))));
            closedCount.setText(closed+" db");
            return;
        }
        if(tn[y][x] == "icons/null.png" && tn[y+1][x] == "icons/box.png"){
            tn[y][x] = "icons/box.png";
            t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/box.png"))));
            closed++;
            closedCount.setText(closed+" db");
            return;
        }
        if(y == 0){
            if(tn[y][x] == "icons/box.png"){
                tn[y][x] = "icons/boxopen.png";
                t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/boxopen.png"))));
                ticks[y][x]++;
            }
            return;
        }
        if(y != 0){
            if(tn[y-1][x] == "icons/null.png" && tn[y][x] == "icons/box.png"){
                tn[y][x] = "icons/boxopen.png";
                t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/boxopen.png"))));
                ticks[y][x]++;
            }
            return;
        }
    }

    public void onRandomClick() {
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 15; x++){
                t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/null.png"))));
                tn[y][x] = "icons/null.png";
                ticks[y][x] = -1;
            }
        }
        for(int x = 0; x < 15; x++){
            int rand = (int) (Math.random()*10);
            for(int y = 9; y > 9-rand; y--){
                tn[y][x] = "icons/box.png";
                t[y][x].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/box.png"))));
            }
        }
    }
}