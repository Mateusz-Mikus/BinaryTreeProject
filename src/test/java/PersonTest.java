import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PersonTest {

    @Test
    public void testPersonComparison(){
        Person p1 = new Person(25, "Mateusz");
        Person p2 = new Person(34, "Maciek");
        Person p3 = new Person(25, "Magda");

        assertFalse(p1.compareTo(p2) > 0);
        assertTrue(p3.compareTo(p2) < 0);
        assertEquals(0, p1.compareTo(p3));
    }

    @Test
    public void testPersonToStringFormat(){
        Person p = new Person(60, "Kuba");

        assertEquals("Kuba (60)", p.toString());
    }


}
