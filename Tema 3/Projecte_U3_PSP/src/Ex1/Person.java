package Ex1;

public class Person {
    private int id;
    private String name;
    private String surname;

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + surname;
    }

    public static Person fromString(String data) {
        String[] parts = data.split(",");
        return new Person(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }
}