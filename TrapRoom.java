package dungeongame;

import java.util.Random;

public class TrapRoom extends Room {

    @Override
    public String enter(Player player) {

        Random rand = new Random();

        int[] values = {10, 20, 30, 40, 50};
        int effect = values[rand.nextInt(values.length)];

        boolean damage = rand.nextBoolean();

        if (damage) {
            player.damage(effect);
            return "💥 A trap hits you from the shadows...";
        } else {
            player.heal(effect);
            return "✨ Strange energy from the trap heals you...";
        }
    }
}