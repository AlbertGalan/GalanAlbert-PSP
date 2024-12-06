package Ex1;

import java.io.*;
import java.util.*;

public class Database {
    private static final String FILE_NAME = "bbdd.txt";

    public synchronized void insert(Person person) throws IOException {
        List<Person> people = getAll();
        for (Person p : people) {
            if (p.getId() == person.getId()) {
                throw new IllegalArgumentException("ID already exists");
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(person.toString());
            writer.newLine();
        }
    }

    public synchronized Person select(int id) throws IOException {
        List<Person> people = getAll();
        for (Person p : people) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new NoSuchElementException("ID not found");
    }

    public synchronized void delete(int id) throws IOException {
        List<Person> people = getAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Person p : people) {
                if (p.getId() == id) {
                    found = true;
                } else {
                    writer.write(p.toString());
                    writer.newLine();
                }
            }
        }
        if (!found) {
            throw new NoSuchElementException("ID not found");
        }
    }

    private List<Person> getAll() throws IOException {
        List<Person> people = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                people.add(Person.fromString(line));
            }
        }
        return people;
    }
}