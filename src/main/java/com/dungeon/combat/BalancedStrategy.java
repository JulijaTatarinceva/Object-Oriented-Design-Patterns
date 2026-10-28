package com.dungeon.combat;

import com.dungeon.character.Hero;
import com.dungeon.enemy.Enemy;

public class BalancedStrategy implements CombatStrategy {

    private static final double HEAL_THRESHOLD = 0.50;

    @Override
    public CombatAction chooseAction(Hero hero, Enemy enemy) {
        if (hero.getHealthRatio() < HEAL_THRESHOLD) {
            return CombatAction.HEAL;
        }
        return CombatAction.ATTACK;
    }

    @Override
    public String getName() {
        return "Balanced";
    }
}
