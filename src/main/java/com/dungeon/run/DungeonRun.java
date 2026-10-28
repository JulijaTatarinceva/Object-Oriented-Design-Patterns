package com.dungeon.run;

import com.dungeon.character.Hero;
import com.dungeon.character.Party;
import com.dungeon.combat.AggressiveStrategy;
import com.dungeon.combat.BalancedStrategy;
import com.dungeon.combat.CombatAction;
import com.dungeon.combat.CombatStrategy;
import com.dungeon.combat.DefensiveStrategy;
import com.dungeon.enemy.Enemy;
import com.dungeon.logger.GameLogger;
import com.dungeon.room.Room;
import com.dungeon.room.RoomIterator;
import com.dungeon.weapon.Weapon;
import com.dungeon.weapon.WeaponForge;

import java.util.List;
import java.util.Random;

public abstract class DungeonRun {

    protected final GameLogger logger = GameLogger.getInstance();
    protected final Random random;
    protected final long seed;

    protected Party party;
    protected List<Room> rooms;
    protected int roomsCleared;
    protected int enemiesDefeated;
    protected int goldCollected;

    protected DungeonRun(Random random, long seed) {
        this.random = random;
        this.seed = seed;
    }

    public final void execute() {
        logger.log("=== DUNGEON SIMULATOR ===");
        logger.blankLine();
        logger.log("Mode: " + getModeName());
        logger.log("Generating dungeon...");
        logger.log("Seed: " + seed);

        prepareParty();
        rooms = generateRooms();
        logTraversalPlan();
        logger.blankLine();
        printParty();
        exploreRooms();
        printSummary();
    }

    protected abstract String getModeName();

    protected abstract List<Room> generateRooms();

    protected abstract RoomIterator createRoomIterator(List<Room> generatedRooms);

    protected void logTraversalPlan() {
        logger.log("Rooms discovered: " + rooms.size());
        logger.log("Traversal: every room, in order");
    }

    protected void prepareParty() {
        party = new Party();
        party.add(new Hero("Knight", 120, WeaponForge.fullyEnchantedIronSword(), pickStrategy()));
        party.add(new Hero("Mage", 70, WeaponForge.enchantRandomly(WeaponForge.oakStaff(), random), pickStrategy()));
        party.add(new Hero("Archer", 90, WeaponForge.enchantRandomly(WeaponForge.shortBow(), random), pickStrategy()));
    }

    protected void exploreRooms() {
        RoomIterator iterator = createRoomIterator(rooms);
        int roomNumber = 1;
        while (iterator.hasNext() && party.isAlive()) {
            Room room = iterator.next();
            resolveRoom(room, roomNumber);
            roomNumber++;
        }
    }

    protected void resolveRoom(Room room, int roomNumber) {
        logger.log("--- Room " + roomNumber + ": " + room.getTitle() + " ---");
        logger.blankLine();

        switch (room.getType()) {
            case COMBAT:
            case BOSS:
                resolveCombat(room.getEnemy());
                break;
            case TREASURE:
                resolveTreasure(room);
                break;
            default:
                break;
        }

        if (party.isAlive()) {
            roomsCleared++;
        }
        logger.blankLine();
    }

    protected void resolveCombat(Enemy enemy) {
        while (enemy.isAlive() && party.isAlive()) {
            for (Hero hero : party.getLivingHeroes()) {
                if (!enemy.isAlive()) {
                    break;
                }
                performHeroAction(hero, enemy);
            }
            if (enemy.isAlive() && party.isAlive()) {
                Hero target = party.pickRandomLivingHero(random);
                int damage = enemy.attack();
                int healthBefore = target.getCurrentHealth();
                target.takeDamage(damage);
                int applied = healthBefore - target.getCurrentHealth();
                logger.log(enemy.getName() + " attacks " + target.getName() + " for " + applied + " damage!");
            }
        }

        if (!enemy.isAlive()) {
            enemiesDefeated++;
            logger.blankLine();
            logger.log(enemy.getName() + " defeated!");
        }
    }

    protected void performHeroAction(Hero hero, Enemy enemy) {
        CombatAction action = hero.getCombatStrategy().chooseAction(hero, enemy);
        switch (action) {
            case ATTACK:
                int damage = hero.strike();
                enemy.takeDamage(damage);
                logger.log(hero.getName() + " attacks " + enemy.getName() + " for " + damage
                        + " damage with " + hero.getWeapon().getName() + "!");
                break;
            case HEAL:
                int restored = hero.heal();
                logger.log(hero.getName() + " heals for " + restored + " HP (now "
                        + hero.getCurrentHealth() + "/" + hero.getMaximumHealth() + ")");
                break;
            case DEFEND:
                hero.defend();
                logger.log(hero.getName() + " raises a guard (next hit is halved)");
                break;
            default:
                break;
        }
    }

    protected void resolveTreasure(Room room) {
        goldCollected += room.getGold();
        logger.log("Gold acquired: " + room.getGold());
        Weapon loot = room.getLoot();
        if (loot != null) {
            Hero luckyHero = party.pickRandomLivingHero(random);
            if (luckyHero != null) {
                luckyHero.equip(loot);
                logger.log("Found: " + loot.describe());
                logger.log(luckyHero.getName() + " equips " + loot.getName());
            }
        }
    }

    protected void printParty() {
        logger.log("Party assembled:");
        for (Hero hero : party.getHeroes()) {
            logger.log("- " + hero.getName()
                    + " (HP: " + hero.getCurrentHealth()
                    + ", " + hero.getWeapon().describe()
                    + ", strategy: " + hero.getCombatStrategy().getName() + ")");
        }
        logger.blankLine();
    }

    protected void printSummary() {
        logger.log("=== DUNGEON SUMMARY ===");
        logger.log("Rooms cleared: " + roomsCleared);
        logger.log("Enemies defeated: " + enemiesDefeated);
        logger.log("Gold collected: " + goldCollected);
        logger.log("Party survived: " + (party.isAlive() ? "Yes" : "No"));
        if (party.isAlive()) {
            for (Hero hero : party.getHeroes()) {
                logger.log("  " + hero.getName() + " HP: " + hero.getCurrentHealth()
                        + "/" + hero.getMaximumHealth()
                        + " | " + hero.getWeapon().getName());
            }
        }
    }

    private CombatStrategy pickStrategy() {
        int choice = random.nextInt(3);
        if (choice == 0) {
            return new AggressiveStrategy();
        }
        if (choice == 1) {
            return new DefensiveStrategy();
        }
        return new BalancedStrategy();
    }
}
