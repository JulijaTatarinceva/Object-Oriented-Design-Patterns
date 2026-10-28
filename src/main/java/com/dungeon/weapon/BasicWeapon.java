package com.dungeon.weapon;

public class BasicWeapon implements Weapon {

    private final String name;
    private final int damage;

    public BasicWeapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String describe() {
        return name + " - " + damage + " damage";
    }
}
