import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    @Test
    public void testForInsertAndSearchIntegerTree(){
        Tree<Integer> intTree = new Tree<>();
        intTree.insert(10);
        intTree.insert(15);
        intTree.insert(19);

        assertTrue(intTree.search(10));
        assertTrue(intTree.search(15));
        assertFalse(intTree.search(20));
    }

    @Test
    public void testForDeleteIntegerTree(){
        Tree<Integer> intTree = new Tree<>();

        intTree.insert(10);
        assertTrue(intTree.search(10));
        intTree.deleteNode(10);
        assertFalse(intTree.search(10));
    }

    @Test
    public void testDrawForIntegerTree(){
        Tree<Integer> intTree = new Tree<>();
        intTree.insert(10);
        String drawnTree = intTree.draw();

        assertNotNull(drawnTree);
        assertFalse(drawnTree.isEmpty());
    }

    @Test
    public void testForInsertAndSearchPersonTree(){
        Tree<Person> personTree = new Tree<>();
        Person p1 = new Person(25, "Marek");
        Person p2 = new Person(90, "Grażyna");
        personTree.insert(p1);

        assertTrue(personTree.search(p1));
        assertFalse(personTree.search(p2));
    }

    @Test
    public void testForDeletePersonTree(){
        Tree<Person> personTree = new Tree<>();
        Person p1 = new Person(18, "Michał");

        personTree.insert(p1);
        assertTrue(personTree.search(p1));
        personTree.deleteNode(p1);
        assertFalse(personTree.search(p1));
    }

    @Test
    public void testForDrawPersonTree(){
        Tree<Person> personTree = new Tree<>();
        Person p1 = new Person(29, "Kamil");
        personTree.insert(p1);
        String drawnTree = personTree.draw();

        assertNotNull(drawnTree);
        assertFalse(drawnTree.isEmpty());
    }
}
