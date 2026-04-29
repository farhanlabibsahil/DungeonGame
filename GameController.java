package dungeongame;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.Platform;
import java.util.Optional;

public class GameController {

    private BorderPane root = new BorderPane();
    private TextArea log = new TextArea();

    private Button playBtn = new Button("PLAY");
    private Button nextLevelBtn = new Button("NEXT LEVEL");
    private Button quitBtn = new Button("QUIT");

    private Player player;
    private Dungeon dungeon;

    private int level = 0;

    public GameController() {

        setupLog();
        styleButtons();

        VBox mainMenu = createMenu(playBtn, quitBtn);
        VBox gameMenu = createMenu(nextLevelBtn, quitBtn);

        root.setCenter(mainMenu);
        root.setBottom(log);

        initGame();

        playBtn.setOnAction(e -> startGame(gameMenu));
        nextLevelBtn.setOnAction(e -> nextLevel());
        quitBtn.setOnAction(e -> Platform.exit());
    }

    public BorderPane getView() {
        return root;
    }

    // ---------------- SETUP ----------------
    private void setupLog() {
        log.setEditable(false);
        log.setStyle(
                "-fx-font-size: 14px;"
                + "-fx-control-inner-background: black;"
                + "-fx-text-fill: lime;"
        );
    }

    private void styleButtons() {
        Button[] buttons = {playBtn, nextLevelBtn, quitBtn};

        for (Button b : buttons) {
            b.setStyle(
                    "-fx-font-size: 14px;"
                    + "-fx-background-color: #222;"
                    + "-fx-text-fill: white;"
            );
        }
    }

    private VBox createMenu(Button... buttons) {
        VBox box = new VBox(15, buttons);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private void initGame() {

        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Player Setup");
        nameDialog.setHeaderText("Enter your name:");

        String name = "Player";
        int age = 18;

        Optional<String> nameResult = nameDialog.showAndWait();
        if (nameResult.isPresent()) {
            name = nameResult.get();
        }

        TextInputDialog ageDialog = new TextInputDialog();
        ageDialog.setTitle("Player Setup");
        ageDialog.setHeaderText("Enter your age:");

        Optional<String> ageResult = ageDialog.showAndWait();
        if (ageResult.isPresent()) {
            try {
                age = Integer.parseInt(ageResult.get());
            } catch (NumberFormatException e) {
                log.appendText("Invalid age entered. Default age used.\n");
            }
        }

        Stat stat = new Stat(name, age);
        player = new Player(stat);

        dungeon = new Dungeon();
        level = 0;

        log.appendText("Welcome " + player.getName() + " (Age: " + player.getAge() + ")\n");
        log.appendText("Game Ready\n\n");
    }

    // ---------------- GAME FLOW ----------------
    private void startGame(VBox gameMenu) {
        root.setCenter(gameMenu);
        log.appendText("Game Started\n\n");
        nextLevel();
    }

    private void nextLevel() {

        if (isPlayerDead()) {
            return;
        }

        level++;

        if (level > 10) {
            log.appendText("YOU ESCAPED THE DUNGEON\n");
            nextLevelBtn.setDisable(true);
            return;
        }

        log.appendText("Level " + level + "\n");

        dungeon = new Dungeon();
        explore();
    }

    private void explore() {

        try {
            Room room = dungeon.getRandomRoom();

            if (room instanceof PuzzleRoom puzzle) {
                handlePuzzle(puzzle);
            } else {
                log.appendText(room.enter(player) + "\n");
            }

            log.appendText("HP: " + player.getHealth() + "\n\n");

            if (isPlayerDead()) {
                log.appendText("GAME OVER\n");
            }

        } catch (Exception e) {
            log.appendText("Error: " + e.getMessage() + "\n");
        }
    }

    // ---------------- HELPERS ----------------
    private void handlePuzzle(PuzzleRoom puzzle) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Puzzle Room");
        dialog.setHeaderText(puzzle.getQuestion());

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            try {
                int answer = Integer.parseInt(input);
                log.appendText(puzzle.checkAnswer(answer, player) + "\n");
            } catch (NumberFormatException e) {
                log.appendText("Invalid input. Enter a number.\n");
            }
        });
    }

    private boolean isPlayerDead() {
        if (player.getHealth() <= 0) {
            log.appendText("You are dead\n");
            return true;
        }
        return false;
    }
}
