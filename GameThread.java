package dungeongame;

// Multithreading
public class GameThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Game running in background...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}