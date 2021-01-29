import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.*/
    static Palindrome palindrome = new Palindrome();
    static OffByOne obo = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("1234321"));
        assertFalse(palindrome.isPalindrome("hdsauhdasdh"));
        assertTrue(palindrome.isPalindrome("123321"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aa"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("aeoqrpdb", obo));
    }
//    Uncomment this class once you've created your Palindrome class.
}
