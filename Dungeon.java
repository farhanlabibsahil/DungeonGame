package dungeongame;

import java.util.*;

public class Dungeon {

    private List<Room> rooms;
    private Random rand;

    public Dungeon() {
        rooms = new ArrayList<>();
        rand = new Random();

        // Only normal rooms now
        rooms.add(new PuzzleRoom());
        rooms.add(new TrapRoom());
    }

    public Room getRandomRoom() {

        Room room = rooms.get(rand.nextInt(rooms.size()));

        // Always generate fresh puzzle
        if (room instanceof PuzzleRoom) {
            return new PuzzleRoom();
        }

        return room;
    }
}