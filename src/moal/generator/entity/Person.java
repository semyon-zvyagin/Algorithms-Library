package moal.generator.entity;

import moal.generator.Generator;

import java.util.Objects;

/**
 * Simple entity with name and ID
 */
public class Person {

    private static int counter = 0;
    private final int id;
    private final String name;

    public Person(String name) {
        this.id = counter++;
        this.name = name;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Person getRandom() {
        return new Person(Generator.getRandomName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
