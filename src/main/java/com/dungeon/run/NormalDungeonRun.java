package com.dungeon.run;

import com.dungeon.enemy.Goblin;
import com.dungeon.enemy.Ogre;
import com.dungeon.enemy.Skeleton;
import com.dungeon.room.Room;
import com.dungeon.room.RoomIterator;
import com.dungeon.room.SequentialRoomIterator;
import com.dungeon.weapon.Weapon;
import com.dungeon.weapon.WeaponForge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalDungeonRun extends DungeonRun {

    public NormalDungeonRun(Random random, long seed) {
        super(random, seed);
    }

    @Override
    protected String getModeName() {
        return "Normal";
    }

    @Override
    protected List<Room> generateRooms() {
        int roomCount = 5 + random.nextInt(3);
        List<Room> generated = new ArrayList<>();
        for (int index = 0; index < roomCount - 1; index++) {
            generated.add(createStandardRoom());
        }
        generated.add(Room.boss("Ogre Lair", new Ogre()));
        return generated;
    }

    @Override
    protected RoomIterator createRoomIterator(List<Room> generatedRooms) {
        return new SequentialRoomIterator(generatedRooms);
    }

    private Room createStandardRoom() {
        int roll = random.nextInt(3);
        if (roll == 0) {
            return Room.combat("Goblin Encounter", new Goblin());
        }
        if (roll == 1) {
            return Room.combat("Skeleton Patrol", new Skeleton());
        }
        int gold = 20 + random.nextInt(41);
        Weapon loot = random.nextBoolean()
                ? WeaponForge.enchantRandomly(WeaponForge.ancientSword(), random)
                : null;
        return Room.treasure("Treasure Chamber", gold, loot);
    }
}
