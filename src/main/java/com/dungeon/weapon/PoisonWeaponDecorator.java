package com.dungeon.weapon;

public class PoisonWeaponDecorator extends WeaponDecorator {

    public static final int POISON_DAMAGE = 3;

    public PoisonWeaponDecorator(Weapon wrappedWeapon) {
        super(wrappedWeapon);
    }

    @Override
    public String getName() {
        return getWrappedWeapon().getName() + " of Poison";
    }

    @Override
    public int getDamage() {
        return getWrappedWeapon().getDamage() + POISON_DAMAGE;
    }
}
