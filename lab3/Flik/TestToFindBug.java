import org.junit.Test;
import static org.junit.Assert.*;


public class TestToFindBug {

    @Test
    public void testFlik() {
        int a = 127;
        int exp = 127;
        assertTrue(Flik.isSameNumber(a, exp));
        a = 128;
        exp = 128;
        assertTrue(Flik.isSameNumber(a, exp));
    }


}
