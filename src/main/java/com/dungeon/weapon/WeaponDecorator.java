package com.dungeon.weapon;

public abstract class WeaponDecorator implements Weapon {

    private final Weapon wrappedWeapon;

    protected WeaponDecorator(Weapon wrappedWeapon) {
        this.wrappedWeapon = wrappedWeapon;
    }

    protected Weapon getWrappedWeapon() {
        return wrappedWeapon;
    }

    @Override
    public String describe() {
        return getName() + " - " + getDamage() + " total damage";
    }
}
