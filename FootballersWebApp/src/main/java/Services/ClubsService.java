package Services;

import Model.Club;

import java.util.ArrayList;

public interface ClubsService {

    ArrayList<Club> getAllClubs();

    boolean addClub(String name);

    boolean deleteClub(String name);
}
