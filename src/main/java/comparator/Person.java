package comparator;

public class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean equals(Object other) {
        if (other instanceof Person) {
            return this.age == ((Person) other).age;
        } else {
            return false;
        }
    }

    public String toString() {
        return String.valueOf(age); // OR "" + age;
    }
}