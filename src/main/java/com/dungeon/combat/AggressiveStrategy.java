package com.dungeon.combat;

import com.dungeon.character.Hero;
import com.dungeon.enemy.Enemy;

public class AggressiveStrategy implements CombatStrategy {

    @Override
    public CombatAction chooseAction(Hero hero, Enemy enemy) {
        return CombatAction.ATTACK;
    }

    @Override
    public String getName() {
        return "Aggressive";
    }
}
