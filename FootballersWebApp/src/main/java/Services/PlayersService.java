package Services;

import Model.Player;

import java.util.ArrayList;

public interface PlayersService {

    ArrayList<Player> getClubPlayersList(String club);

    boolean addPlayer(String name, int age, String country, String position, String club);

    boolean deletePlayer(String name);
}
