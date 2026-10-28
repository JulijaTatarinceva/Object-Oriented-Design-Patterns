package com.dungeon.combat;

import com.dungeon.character.Hero;
import com.dungeon.enemy.Enemy;

public interface CombatStrategy {

    CombatAction chooseAction(Hero hero, Enemy enemy);

    String getName();
}
