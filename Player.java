package dungeongame;

public class Player {

    private Stat stat;  
    private int health;

    public Player(Stat stat) {
        this.stat = stat;
        this.health = 100;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return stat.getName();
    }

    public int getAge() {
        return stat.getAge();
    }

    public void damage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public void heal(int amount) {
        health += amount;
        if (health > 100) health = 100;
    }
}