package ch.alexb.arena.enemy;

import ch.alexb.arena.Player.Player;

import java.awt.*;

public class Enemy {

    protected int geschwindigkeit;
    protected int groesse;
    protected int schaden;
    protected int leben;
    protected int x;
    protected int y;


    public void enemyBewegen(Player player){

        if (x < player.getX()){
            x += geschwindigkeit;
        }if (x > player.getX()) {
            x -= geschwindigkeit;
        }if (y < player.getY()) {
            y += geschwindigkeit;
        }if (y > player.getY()) {
            y -= geschwindigkeit;
        }

    }


    public void enemyZeichnen(Graphics g){
        g.fillOval(x, y, groesse, groesse);
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void schadenNehmen(int schaden){
        leben -= schaden;
    }

    public int getLeben(){
        return leben;
    }

    public int getGroesse(){
        return groesse;
    }

    public int getSchaden(){
        return schaden;
    }

    public boolean trifftPlayer(Player player){
        return (Math.abs(player.getX() - x) < 30 &&
                Math.abs(player.getY() - y) < 30 );
    }

}
