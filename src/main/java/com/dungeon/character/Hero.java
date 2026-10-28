package com.dungeon.character;

import com.dungeon.combat.CombatStrategy;
import com.dungeon.weapon.Weapon;

public class Hero {

    private static final int HEAL_AMOUNT = 18;

    private final String name;
    private final int maximumHealth;
    private int currentHealth;
    private Weapon weapon;
    private final CombatStrategy combatStrategy;
    private boolean defending;

    public Hero(String name, int maximumHealth, Weapon weapon, CombatStrategy combatStrategy) {
        this.name = name;
        this.maximumHealth = maximumHealth;
        this.currentHealth = maximumHealth;
        this.weapon = weapon;
        this.combatStrategy = combatStrategy;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public double getHealthRatio() {
        return (double) currentHealth / maximumHealth;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void equip(Weapon newWeapon) {
        this.weapon = newWeapon;
    }

    public CombatStrategy getCombatStrategy() {
        return combatStrategy;
    }

    public int strike() {
        defending = false;
        return weapon.getDamage();
    }

    public void defend() {
        defending = true;
    }

    public int heal() {
        int restored = Math.min(HEAL_AMOUNT, maximumHealth - currentHealth);
        currentHealth += restored;
        defending = false;
        return restored;
    }

    public void takeDamage(int amount) {
        int applied = defending ? amount / 2 : amount;
        defending = false;
        currentHealth = Math.max(0, currentHealth - applied);
    }
}
