import java.util.*;

public class Game {
    private List<Player> players;

    public Game(List<Player> players) {
        this.players = players;
    }

    public void showScore(String name) {
        Player p = findPlayer(name);
        if (p != null) {
            System.out.println(p.getName() + " has " + p.getScore() + " points");
        } else {
            System.out.println("Nonexistent player");
        }
    }

    public void showFleet(String name) {
        Player p = findPlayer(name);
        if (p != null) {
            System.out.println(p.getFleet());
        } else {
            System.out.println("Nonexistent player");
        }
    }

    public void shoot(String targetName, int pos) {
        Player target = findPlayer(targetName);
        if (target == null) {
            System.out.println("Nonexistent player");
            return;
        }

        if (pos <= 0 || pos > target.getFleet().length()) {
            System.out.println("Invalid shot position");
            return;
        }

        char[] fleetArray = target.getFleet().toCharArray();
        char targetChar = fleetArray[pos - 1];

        if (Character.isLetter(targetChar)) {
            char ship = targetChar;
            int count = 0;

            // Count how many times this ship appears
            for (char c : fleetArray) {
                if (c == ship) {
                    count++;
                }
            }

            // Replace all matching letters with '*'
            for (int i = 0; i < fleetArray.length; i++) {
                if (fleetArray[i] == ship) {
                    fleetArray[i] = '*';
                }
            }

            int pointsGained = 100 * count;
            target.addScore(pointsGained);
            System.out.println("+" + pointsGained + " points");

        } else if (targetChar == '.') {
            System.out.println("Hits the water.");
        } else if (targetChar == '*') {
            target.addScore(-30);
            System.out.println("Already destroyed.\n-30 points");
        }

        target.setFleet(new String(fleetArray));
    }



    public void showNextPlayer(int step) {
        if (players.isEmpty()) {
            System.out.println("No players in the game");
            return;
        }
        int index = step % players.size();
        System.out.println("Next player: " + players.get(index).getName());
    }

    public void showPlayersStillInGame() {
        System.out.println("Players still in game:");
        for (Player p : players) {
            if (p.isAlive()) {
                System.out.println("- " + p.getName());
            }
        }
    }

    public void showScoreRanking() {
        players.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        for (Player p : players) {
            System.out.println(p.getName() + " - " + p.getScore() + " points");
        }
    }

    private Player findPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
