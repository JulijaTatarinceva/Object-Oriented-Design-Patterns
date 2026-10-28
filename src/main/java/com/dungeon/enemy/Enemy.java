package com.dungeon.enemy;

public interface Enemy {

    String getName();

    int attack();

    void takeDamage(int damage);

    boolean isAlive();

    int getCurrentHealth();
}
