package ch.alexb.arena;

import ch.alexb.arena.Player.Player;
import ch.alexb.arena.Player.Projektiel;
import ch.alexb.arena.enemy.Enemy;
import ch.alexb.arena.enemy.normalEnemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {

    boolean wGedrueckt = false;
    boolean aGedrueckt = false;
    boolean sGedrueckt = false;
    boolean dGedrueckt = false;

    ArrayList<Projektiel> projektiels = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    Player player = new Player(450, 350);
    Enemy enemy = new normalEnemy(0, 0);


    public GamePanel(){

        enemies.add(enemy);

        addKeyListener(this);
        setFocusable(true);

        Timer gameTimer = new Timer(16, e -> {

            //Player bewegen
            if (wGedrueckt){
                player.moveUp();
            }if (sGedrueckt) {
                player.moveDown();
            }if (aGedrueckt){
                player.moveLeft();
            }if (dGedrueckt){
                player.moveRight();
            }

            //Gegner Bewegen
            enemy.enemyBewegen(player);


            player.begrenzen(getWidth()-player.getGroesse(),
                    getHeight() -player.getGroesse());


            //PLayer schaden nehmen
            if (enemy.trifftPlayer(player)){
                player.schadenNehmen(enemy.getSchaden());
                System.out.println(player.getLeben());
            }

            // Projektile bewegen
            for (int i = projektiels.size() - 1; i >= 0; i--) {

                Projektiel projektiel = projektiels.get(i);

                projektiel.projektielBewegen();

                if (projektiel.loeschen()) {
                    projektiels.remove(i);
                    continue;
                }

                if (projektiel.trifftGegner(enemy)) {
                    enemy.schadenNehmen(player.getSchaden());
                    projektiels.remove(i);
                }
            }

            // Gegner entfernen
            for (int i = enemies.size() - 1; i >= 0; i--) {
                if (enemies.get(i).getLeben() <= 0) {
                    enemies.remove(i);
                }
            }



            repaint();

        });

        gameTimer.start();


    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        player.playerZeichnen(g);
        for (Enemy enemy:enemies) {
            enemy.enemyZeichnen(g);
        }

        for (Projektiel projektiel:projektiels){
            projektiel.projektielZeichnen(g);
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            //WASD
            case KeyEvent.VK_W ->  wGedrueckt = true;
            case KeyEvent.VK_S -> sGedrueckt = true;
            case KeyEvent.VK_A -> aGedrueckt = true;
            case KeyEvent.VK_D -> dGedrueckt = true;

            //Schiessen
            case KeyEvent.VK_SPACE -> {
                Projektiel projektiel = new Projektiel(player.getX(), player.getY(), enemy);
                projektiels.add(projektiel);
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W ->  wGedrueckt = false;
            case KeyEvent.VK_S -> sGedrueckt = false;
            case KeyEvent.VK_A -> aGedrueckt = false;
            case KeyEvent.VK_D -> dGedrueckt = false;
        }
    }
}

