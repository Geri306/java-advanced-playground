package comparator;


import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Person> persons = new TreeSet<>(new PersonComparator());
        persons.add(new Person(32));
        persons.add(new Person(17));
        persons.add(new Person(13));
        persons.add(new Person(35));
        persons.add(new Person(27));
        Iterator<Person> iter = persons.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
