package com.dungeon.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Party {

    private final List<Hero> heroes = new ArrayList<>();

    public void add(Hero hero) {
        heroes.add(hero);
    }

    public List<Hero> getHeroes() {
        return Collections.unmodifiableList(heroes);
    }

    public List<Hero> getLivingHeroes() {
        return heroes.stream().filter(Hero::isAlive).collect(Collectors.toList());
    }

    public boolean isAlive() {
        return heroes.stream().anyMatch(Hero::isAlive);
    }

    public Hero pickRandomLivingHero(Random random) {
        List<Hero> livingHeroes = getLivingHeroes();
        if (livingHeroes.isEmpty()) {
            return null;
        }
        return livingHeroes.get(random.nextInt(livingHeroes.size()));
    }
}
