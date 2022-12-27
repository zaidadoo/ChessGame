public class Player {
    PlayerController PlayerColor;

    Player(PlayerController player)
    {
        this.PlayerColor = player;
    }

    @Override
    public String toString() {
        return PlayerColor.toString() + " player";
    }
}
