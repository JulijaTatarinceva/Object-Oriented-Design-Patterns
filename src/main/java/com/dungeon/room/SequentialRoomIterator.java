package com.dungeon.room;

import java.util.List;
import java.util.NoSuchElementException;

public class SequentialRoomIterator implements RoomIterator {

    private final List<Room> rooms;
    private int index;

    public SequentialRoomIterator(List<Room> rooms) {
        this.rooms = rooms;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < rooms.size();
    }

    @Override
    public Room next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more rooms");
        }
        Room room = rooms.get(index);
        index++;
        return room;
    }
}
