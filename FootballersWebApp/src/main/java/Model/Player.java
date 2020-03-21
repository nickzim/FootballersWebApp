package Model;

public class Player {

    private String name;
    private int age;
    private String country;
    private String position;
    private int club_id;

    public Player(String name, int age, String country, String position, int club_id) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.position = position;
        this.club_id = club_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }
}
