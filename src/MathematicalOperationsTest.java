import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathematicalOperationsTest {
    static MathematicalOperations mathematicalOperations;

    @BeforeClass
    public static void init() {
        mathematicalOperations = new MathematicalOperations();
    }

    @Test
    public void testBinaryPlus() {
        assertEquals(8,mathematicalOperations.binaryPlus(3,5));
        assertEquals(1,mathematicalOperations.binaryPlus(-4,5));
        assertEquals(0,mathematicalOperations.binaryPlus(64,-64));
    }
}