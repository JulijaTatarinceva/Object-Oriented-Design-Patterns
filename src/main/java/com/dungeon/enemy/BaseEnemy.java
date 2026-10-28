package com.dungeon.enemy;

public abstract class BaseEnemy implements Enemy {

    private final String name;
    private final int attackDamage;
    private int currentHealth;

    protected BaseEnemy(String name, int health, int attackDamage) {
        this.name = name;
        this.currentHealth = health;
        this.attackDamage = attackDamage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int attack() {
        return attackDamage;
    }

    @Override
    public void takeDamage(int damage) {
        currentHealth = Math.max(0, currentHealth - damage);
    }

    @Override
    public boolean isAlive() {
        return currentHealth > 0;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }
}
