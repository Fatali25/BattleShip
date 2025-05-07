public class Player {
    private String name;
    private String fleet;
    private int score;

    public Player(String name, String fleet) {
        this.name = name;
        this.fleet = fleet;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public String getFleet() {
        return fleet;
    }

    public void setFleet(String fleet) {
        this.fleet = fleet;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int delta) {
        this.score += delta;
    }

    public boolean isAlive() {
        return fleet.matches(".*[A-Z].*");
    }
}
