package everybody.codes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Quest1Test {
    Quest1 quest1 = new Quest1();

    @Test
    public void testPart1() {
        assertEquals(quest1.computePotions("ABBAC", 1), 5);
    }

    @Test
    public void testPart2() {
        assertEquals(quest1.computePotions("AxBCDDCAxD", 2), 28);
    }

    @Test
    public void testPart3() {
        assertEquals(quest1.computePotions("xBxAAABCDxCC", 3), 30);
    }
}
