package com.dungeon.weapon;

public class FireWeaponDecorator extends WeaponDecorator {

    public static final int FIRE_DAMAGE = 5;

    public FireWeaponDecorator(Weapon wrappedWeapon) {
        super(wrappedWeapon);
    }

    @Override
    public String getName() {
        return "Flaming " + getWrappedWeapon().getName();
    }

    @Override
    public int getDamage() {
        return getWrappedWeapon().getDamage() + FIRE_DAMAGE;
    }
}
