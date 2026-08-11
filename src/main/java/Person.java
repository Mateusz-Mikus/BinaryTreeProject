/**
 * Klasa definiująca typ danych reprezentująca człowieka.
 * Obiekty tej klasy wykorzystywane są jako węzły w strukturze binarnej drzewa.
 * @author Mateusz Mikus
 */

public class Person implements Comparable <Person>{
    private int age;
    private String name;

    /**
     * Konstruktor inicjalizujący stan obiektu.
     * @param age wartość liczbowa reprezentująca wiek.
     * @param name ciąg znaków reprezentujących imie.
     */
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString(){
        return name +"(" + age + ")";
    }
}