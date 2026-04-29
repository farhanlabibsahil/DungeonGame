package dungeongame;


public abstract class Room {
    public abstract String enter(Player player) throws GameException;
}