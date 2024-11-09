package everybody.codes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Quest2Test {
    private final Quest2 quest = new Quest2();

    @Test
    public void testPart1() {
        String[] words = {"THE", "OWE", "MES", "ROD", "HER"};

        assertEquals(4, quest.countRunicWords(words, "AWAKEN THE POWER ADORNED WITH THE FLAMES BRIGHT IRE"));
        assertEquals(3, quest.countRunicWords(words, "THE FLAME SHIELDED THE HEART OF THE KINGS"));
        assertEquals(2, quest.countRunicWords(words, "POWE PO WER P OWE R"));
        assertEquals(3, quest.countRunicWords(words, "THERE IS THE END"));
    }

    @Test
    public void testPart2() {}

    @Test
    public void testPart3() {}
}
