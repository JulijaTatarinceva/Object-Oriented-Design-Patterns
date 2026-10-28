package com.dungeon.weapon;

import java.util.Random;

public final class WeaponForge {

    private WeaponForge() {
    }

    public static Weapon ironSword() {
        return new BasicWeapon("Iron Sword", 10);
    }

    public static Weapon oakStaff() {
        return new BasicWeapon("Oak Staff", 8);
    }

    public static Weapon shortBow() {
        return new BasicWeapon("Short Bow", 12);
    }

    public static Weapon ancientSword() {
        return new BasicWeapon("Ancient Sword", 10);
    }

    public static Weapon enchantRandomly(Weapon weapon, Random random) {
        Weapon result = weapon;
        if (random.nextBoolean()) {
            result = new FireWeaponDecorator(result);
        }
        if (random.nextBoolean()) {
            result = new PoisonWeaponDecorator(result);
        }
        return result;
    }

    public static Weapon fullyEnchantedIronSword() {
        Weapon weapon = ironSword();
        weapon = new FireWeaponDecorator(weapon);
        weapon = new PoisonWeaponDecorator(weapon);
        return weapon;
    }
}
