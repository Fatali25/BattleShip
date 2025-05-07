import java.io.*;
import java.util.*;

public class Main {

    private static final String quit = "quit";
    private static final String menu = "menu";
    private static final String score = "score";
    private static final String fleet = "fleet";
    private static final String shoot = "shoot";
    private static final String player = "player";
    private static final String playersCmd = "players";
    private static final String scoresCmd = "scores";
    private static int steps = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> fleetList = new ArrayList<>();
        List<Player> players = new ArrayList<>();

        // Load fleets from file
        try (BufferedReader reader = new BufferedReader(new FileReader("fleets.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fleetList.add(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("Error reading fleets.txt: " + e.getMessage());
            return;
        }

        if (fleetList.isEmpty()) {
            System.out.println("No fleets found in fleets.txt.");
            return;
        }

        System.out.print("Enter number of players: ");
        int numPlayers = Integer.parseInt(in.nextLine());
        List<String> playerNames = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            String name;
            while (true) {
                System.out.print("Enter name of player " + (i + 1) + ": ");
                name = in.nextLine().trim();
                if (playerNames.contains(name)) {
                    System.out.println("This name is already taken. Please choose a different name.");
                } else if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please enter a valid name.");
                } else {
                    playerNames.add(name);
                    break;
                }
            }

        // Show available fleets
            System.out.println("Available fleets:");
            for (int j = 0; j < fleetList.size(); j++) {
                System.out.println((j + 1) + ": " + fleetList.get(j));
            }

            int chosenFleet = -1;
            while (chosenFleet < 1 || chosenFleet > fleetList.size()) {
                System.out.print("Choose fleet number (1-" + fleetList.size() + "): ");
                chosenFleet = Integer.parseInt(in.nextLine());
            }

            String fleet = fleetList.get(chosenFleet - 1);
            players.add(new Player(name, fleet));
        }

        Game game = new Game(players);
        interpreter(in, game);
        in.close();
    }

    private static void menu() {
        System.out.println(quit + " - Quit the program (Terminate execution)");
        System.out.println(menu + " - List all the commands");
        System.out.println(score + " (name) - Show the score of player");
        System.out.println(fleet + " (name) - Show the state of the fleet of the player");
        System.out.println(shoot + " (playerName) (position) - Shoot at position");
        System.out.println(player + " - Indicates the next player");
        System.out.println(playersCmd + " - Show players still in game");
        System.out.println(scoresCmd + " - Show score ranking");
    }

    private static void interpreter(Scanner in, Game game) {
        menu();
        while (true) {
            System.out.print("Input command: ");
            String command = in.nextLine();
            String[] cmd = command.split(" ");
            if (cmd[0].equals(quit)) Quit(game);
            else if (cmd[0].equals(menu)) menu();
            else if (cmd[0].equals(score) && cmd.length >= 2)
                game.showScore(command.substring(cmd[0].length() + 1));
            else if (cmd[0].equals(fleet) && cmd.length >= 2)
                game.showFleet(command.substring(cmd[0].length() + 1));
            else if (cmd[0].equals(shoot) && cmd.length == 3) {
                String targetName = cmd[1];
                int pos = Integer.parseInt(cmd[2]);
                game.shoot(targetName, pos);
                steps++;
            }
            else if (cmd[0].equals(player)) game.showNextPlayer(steps);
            else if (cmd[0].equals(playersCmd)) game.showPlayersStillInGame();
            else if (cmd[0].equals(scoresCmd)) game.showScoreRanking();
            else System.out.println("Invalid command");
        }
    }

    private static void Quit(Game game) {
        System.out.println("Final scores:");
        game.showScoreRanking();
        System.exit(0);
    }
}
