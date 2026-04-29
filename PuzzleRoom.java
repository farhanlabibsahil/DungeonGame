package dungeongame;

import java.util.Random;

public class PuzzleRoom extends Room {

    private int correctAnswer;
    private String question;
    private int effectValue; // hidden

    public PuzzleRoom() {
        generatePuzzle();
    }

    private void generatePuzzle() {
        Random rand = new Random();

        int a = rand.nextInt(10) + 1;
        int b = rand.nextInt(10) + 1;

        correctAnswer = a + b;
        question = "Solve: " + a + " + " + b;

        int[] values = {10, 20, 30, 40, 50};
        effectValue = values[rand.nextInt(values.length)];
    }

    public String getQuestion() {
        return question; // 🔥 no hint about HP change
    }

    public String checkAnswer(int answer, Player player) {

        if (answer == correctAnswer) {
            player.heal(effectValue);
            return "✅ Correct! You feel stronger...";
        } else {
            player.damage(effectValue);
            return "❌ Wrong! Something drains your energy...";
        }
    }

    @Override
    public String enter(Player player) {
        return "You entered a mysterious puzzle room...";
    }
}