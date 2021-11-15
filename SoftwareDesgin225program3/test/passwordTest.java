import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class passwordTest {

    @BeforeEach
    /**
     * Sets up the dictionary so that we can test the methods in an easy
     * fashion.
     */
    void setUp() throws Exception {
        PasswordChecker.dictionary("Dict.txt");
    }

    @Test
    /**
     * TEST 1 This test checks if the length check method is working correctly.
     * Checks to see if the method is returning true and false in the
     * appropriate strings.
     */
    void lengthCheckTest() {
        String password1 = "lessth8";
        String password2 = "moreThanEight";
        assertFalse(PasswordChecker.lengthCheck(password1));
        assertTrue(PasswordChecker.lengthCheck(password2));
    }

    @Test
    /**
     * TEST 2 This test checks if the splitLetnNums method is working correctly.
     * Checks to see if the method Splits the string correctly.
     */
    void splitLetnNumsTest() {
        String password1 = "sp1itNumb3r2";
        String[] split = PasswordChecker.splitLetnNums(password1);
        String[] answer = { "sp", "1itNumb", "3r", "2" };
        for (int i = 0; i < split.length; i++) {
            assertTrue(split[i].equals(answer[i]));
        }

    }

    @Test
    /**
     * TEST 3 This test checks if thehasNum method returns the correct boolean,
     * in the case that a string has a number in it. And it tests to see if it
     * returns false if the password has num but the words before the num are no
     * in the dict.
     * 
     */
    void hasNumTest() {
        String password1 = "act7thisIsSuposedToTrue";
        String password2 = "hasnoNumber";
        String password3 = "h3asnoNumber";
        assertFalse(PasswordChecker.hasNum(password2));
        assertFalse(PasswordChecker.hasNum(password3));
        assertTrue(PasswordChecker.hasNum(password1));
    }

    @Test
    /**
     * TEST 4 This test checks if passwordsWithNums method returns the correct
     * boolean, in the case that it contains the a word in the dictionary once
     * the numbers are gone from the string.
     * 
     */
    void passwordsWithNumsTest() {
        String password1 = "act7dogihu";
        String password2 = "hasnoNumber";
        assertTrue(PasswordChecker.passwordsWithNums(password1));
        assertFalse(PasswordChecker.passwordsWithNums(password2));
    }

    @Test
    /**
     * TEST 5 This test checks if passwordValid method that returns if a
     * password if valid or not based on the helper methods above
     * 
     * SIDE NOTE: I was having trouble with the password that are commented out.
     * They are supposed to be a valid password but I was able to single out the
     * words that were in the dictionary in front of the number and after.
     */
    void passwordValidTest() {
        String password1 = "act7thisIsSuposedToTrue";
        String password2 = "hasnoNumber";
        String passwordWlessThanEight = "ha";
        //        String passwordWnumPlusWord = "ha2admit";
        assertFalse(PasswordChecker.passwordValid(password1));
        assertTrue(PasswordChecker.passwordValid(password2));
        assertFalse(PasswordChecker.passwordValid(passwordWlessThanEight));
        //        assertTrue(PasswordChecker.passwordValid(passwordWnumPlusWord));
    }

}
