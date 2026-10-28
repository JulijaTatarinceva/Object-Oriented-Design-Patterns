package com.dungeon.room;

import com.dungeon.enemy.Enemy;
import com.dungeon.weapon.Weapon;

public class Room {

    private final RoomType type;
    private final String title;
    private final Enemy enemy;
    private final int gold;
    private final Weapon loot;

    private Room(RoomType type, String title, Enemy enemy, int gold, Weapon loot) {
        this.type = type;
        this.title = title;
        this.enemy = enemy;
        this.gold = gold;
        this.loot = loot;
    }

    public static Room combat(String title, Enemy enemy) {
        return new Room(RoomType.COMBAT, title, enemy, 0, null);
    }

    public static Room treasure(String title, int gold, Weapon loot) {
        return new Room(RoomType.TREASURE, title, null, gold, loot);
    }

    public static Room boss(String title, Enemy enemy) {
        return new Room(RoomType.BOSS, title, enemy, 0, null);
    }

    public RoomType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getGold() {
        return gold;
    }

    public Weapon getLoot() {
        return loot;
    }
}
