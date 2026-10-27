package ch.alexb.arena.Player;

import java.awt.*;

public class Player {
    private int x;
    private int y;
    private int geschwindigkeit = 5;
    private int groesse = 30;
    private int leben = 100;
    private int schaden = 1;


    public Player(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void schadenNehmen(int schaden){
        leben -= schaden;

        if (leben < 0){
            leben = 0;
        }
    }

    public void moveUp(){
        y -= geschwindigkeit;
    }

    public void moveDown(){
        y += geschwindigkeit;
    }

    public void moveLeft() {
        x -= geschwindigkeit;
    }

    public void moveRight(){
        x += geschwindigkeit;
    }

    public void begrenzen(int maxX, int maxY) {
        if (maxX > 0 && maxY > 0) {
            x = Math.max(0, Math.min(x, maxX));
            y = Math.max(0, Math.min(y, maxY));
        }
    }

    public void playerZeichnen(Graphics g){
        g.drawOval(x, y, groesse, groesse);
    }

    public int getGroesse(){
        return groesse;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getLeben(){
        return leben;
    }

    public int getSchaden(){
        return schaden;
    }


}
