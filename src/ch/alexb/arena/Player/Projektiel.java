package ch.alexb.arena.Player;

import ch.alexb.arena.enemy.Enemy;

import java.awt.*;

public class Projektiel {

    private double x;
    private double y;

    private double dx;
    private double dy;

    private double geschwindigkeit = 8;
    private int groesse = 8;


    public Projektiel(int x, int y, Enemy enemy){
        this.x = x;
        this.y = y;

        double richtungX = enemy.getX() - x;
        double richtungY = enemy.getY() - y;

        double laenge = Math.sqrt(
                richtungX * richtungX +
                        richtungY * richtungY
        );

        if (laenge != 0) {
            dx = richtungX / laenge;
            dy = richtungY / laenge;
        }
    }

    public void projektielBewegen() {
        x += dx * geschwindigkeit;
        y += dy * geschwindigkeit;
    }

    public boolean loeschen(){
        return x > 1000 || x < 0 || y > 700 || y < 0;
    }

    public boolean trifftGegner(Enemy enemy){
        return x < enemy.getX() + enemy.getGroesse()
                && x + groesse > enemy.getX()
                && y < enemy.getY() + enemy.getGroesse()
                && y + groesse > enemy.getY();

    }

    public void projektielZeichnen(Graphics g){
        g.fillOval((int) (x + groesse), (int) (y + groesse), groesse, groesse);
    }
}
