import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This program can either read user input or read from a file. This program is
 * reading strings that represent passwords, and stating if the password is
 * valid or if it is not
 */
public class PasswordChecker {
    public static HashSet<String> wordsNdict = new HashSet<String>();

    /**
     * This method is called lengthCheck and it checks to see if the password is
     * at least 8 letters
     * 
     * @param the password we are analyzing
     * @return a boolean true or false
     */
    public static boolean lengthCheck(String password) {
        if (password.length() >= 8) {
            return true;
        }
        return false;
    }

    /**
     * This method is called splitLetnNums. This method uses the split method to
     * separate the nums and the letters form each other. This allows us to see
     * if the word in the front of the numbers is in the dictionary.
     * 
     * @param the password we are analyzing
     * @return list of strings which are
     */
    public static String[] splitLetnNums(String password) {
        String[] splitpass = password.split("(?<=\\D)(?=\\d)");
        return splitpass;

    }

    /**
     * This method does two things. It check and sees if the password has any
     * numbers if the password does then it calles the passwordsWithNums method.
     * 
     * @param the password we are analyzing
     * @return a boolean true or false
     */
    public static boolean hasNum(String password) {
        int lengthOfstr = password.length();
        for (int i = 0; i < lengthOfstr; i++) {
            if (Character.isDigit(password.charAt(i))) { // use the isDigit
                // method from the
                // character class
                return passwordsWithNums(password);

            }
        }
        return false;
    }

    /**
     * This method is called dictionary and it reads a file that contains
     * dictionary values. As the scanner is reading the file it adds it to a
     * hash set that was declared at the beginning of the program (wordsNdict).
     * 
     * @param the password we are analyzing
     */
    public static void dictionary(String filename)
            throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(filename))) {

            while (in.hasNextLine()) {
                String dictword = in.nextLine();
                wordsNdict.add(dictword);
            }
        }
    }

    /**
     * This method is called passwordsWhithNums and checks to see if the words
     * next to the numbers are in the dictionary
     * 
     * @param the password we are analyzing
     * @return a boolean true or false
     */
    public static boolean passwordsWithNums(String password) {
        String[] words = splitLetnNums(password);
        for (String wrd : words) {
            String temp = wrd.replaceAll("\\d", ""); // gets rid of the numbers
            // when we split the
            // password

            if (wordsNdict.contains(temp)) {

                return true;
            }

        }

        return false;
    }

    /**
     * This method is called passwordValid and checks to see if the password is
     * valid or not with the help of the methods above.
     * 
     * @param the password we are analyzing
     * @return a boolean true or false
     */
    public static boolean passwordValid(String password) {
        String lowerCasePassword = password.toLowerCase();

        if (lengthCheck(lowerCasePassword)
                && wordsNdict.contains(lowerCasePassword) == false
                && hasNum(lowerCasePassword) == false) { // check rule one, two
            // and three
            System.out.println(password + " is a good password");
            System.out.println();
            return true;
        } else {
            System.out.println(password + " is NOT a good password");
            System.out.println();
            return false;
        }

    }

    /**
     * This is the main method it takes in 0,1, or 2 args. The default dict is
     * Dict.txt unless the first arg is a specific dict that the user would like
     * to use that is different from the Dict.txt. The optional second are can
     * be a text file that contains passwords to check.
     * 
     * @param arg1 dictionary
     * @param arg2 passwords to check
     */
    public static void main(String[] args) {

        try {
            try {
                dictionary(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("File " + args[0] + " could not be found.");
            }
        } catch (IndexOutOfBoundsException e1) {
            try {
                dictionary("Dict.txt");
            } catch (FileNotFoundException e) {
                System.out.println("File " + " could not be found.");
            }
        }

        try (Scanner in = new Scanner(new File(args[1]))) {
            while (in.hasNext()) {
                String password = in.nextLine();
                System.out.println("Enter a word to check: " + password);
                passwordValid(password);
            }
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
            Scanner userInput = new Scanner(System.in);
            while (true) {
                System.out.println("If you would like to quit enter q");
                System.out.println("Enter a word to check:");
                String password = userInput.nextLine();

                if (password.equals("q")) {
                    System.out.println("Have a nice day!");
                    break;

                } else {
                    passwordValid(password);
                }

            }

        }

    }
}
