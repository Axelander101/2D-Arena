package ch.alexb.arena.enemy;

public class normalEnemy extends Enemy{

    public normalEnemy(int x, int y){
        this.x = x;
        this.y = y;
        geschwindigkeit = 3;
        schaden = 2;
        groesse = 30;
        leben = 10;
    }

}
