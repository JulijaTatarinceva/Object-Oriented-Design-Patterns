package com.dungeon.run;

import com.dungeon.enemy.Goblin;
import com.dungeon.enemy.Ogre;
import com.dungeon.enemy.Skeleton;
import com.dungeon.room.CombatRoomIterator;
import com.dungeon.room.Room;
import com.dungeon.room.RoomIterator;
import com.dungeon.room.RoomType;
import com.dungeon.weapon.Weapon;
import com.dungeon.weapon.WeaponForge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SurvivalDungeonRun extends DungeonRun {

    public SurvivalDungeonRun(Random random, long seed) {
        super(random, seed);
    }

    @Override
    protected String getModeName() {
        return "Survival";
    }

    @Override
    protected List<Room> generateRooms() {
        int roomCount = 7 + random.nextInt(3);
        List<Room> generated = new ArrayList<>();
        for (int i = 0; i < roomCount - 1; i++) {
            generated.add(createStandardRoom());
        }
        generated.add(Room.boss("Ogre Lair", new Ogre()));
        return generated;
    }

    @Override
    protected RoomIterator createRoomIterator(List<Room> generatedRooms) {
        return new CombatRoomIterator(generatedRooms);
    }

    @Override
    protected void logTraversalPlan() {
        long treasureRooms = rooms.stream()
                .filter(room -> room.getType() == RoomType.TREASURE)
                .count();
        logger.log("Rooms discovered: " + rooms.size());
        logger.log("Traversal: combat-only iterator (skips treasure)");
        logger.log("Treasure rooms skipped: " + treasureRooms);
        logger.log("Rooms to visit: " + (rooms.size() - treasureRooms));
    }

    private Room createStandardRoom() {
        int roll = random.nextInt(4);
        if (roll == 0) {
            int gold = 10 + random.nextInt(21);
            Weapon loot = random.nextBoolean()
                    ? WeaponForge.enchantRandomly(WeaponForge.ancientSword(), random)
                    : null;
            return Room.treasure("Hidden Cache", gold, loot);
        }
        if (roll == 1) {
            return Room.combat("Goblin Encounter", new Goblin());
        }
        return Room.combat("Skeleton Patrol", new Skeleton());
    }
}
