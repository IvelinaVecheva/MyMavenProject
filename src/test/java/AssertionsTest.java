import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertEquals;

public class AssertionsTest {
    int a, b;


    @Parameters({"a","b"})
    @Test()

    public void assertionsAB (int a, int b){
        int sum = (a + b);
        assertEquals(sum, 11);

        SoftAssert sa = new SoftAssert();
        int difference = b-a;
        sa.assertTrue(difference == 2);
        int multiplication = a*b;
        sa.assertTrue(multiplication == 30);

        sa.assertAll();

    }
}
