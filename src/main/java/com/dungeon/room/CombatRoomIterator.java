package com.dungeon.room;

import java.util.List;
import java.util.NoSuchElementException;

public class CombatRoomIterator implements RoomIterator {

    private final List<Room> rooms;
    private int index;

    public CombatRoomIterator(List<Room> rooms) {
        this.rooms = rooms;
        this.index = findNextCombatIndex(0);
    }

    @Override
    public boolean hasNext() {
        return index < rooms.size();
    }

    @Override
    public Room next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more combat rooms");
        }
        Room room = rooms.get(index);
        index = findNextCombatIndex(index + 1);
        return room;
    }

    private int findNextCombatIndex(int start) {
        for (int current = start; current < rooms.size(); current++) {
            RoomType type = rooms.get(current).getType();
            if (type == RoomType.COMBAT || type == RoomType.BOSS) {
                return current;
            }
        }
        return rooms.size();
    }
}
