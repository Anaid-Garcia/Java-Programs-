/**
  * This file is named Roster it is the parent class of EnhancedRoster.java.
  * @author Anaid
  * @version 3.06.20
*/


import java.util.Scanner;

public class Roster{
/**
  * CharCheck checks to see if the user inputed a valid string
  * It also checks for duplicates.
  * @param String userInput
  * @return boolean
**/
  public static boolean CharCheck(String userInput){
    if(userInput.length() != 1){
      return false;
    }
    if (userInput.equals("a") == false && userInput.equals("r") == false && userInput.equals("d") == false && userInput.equals("q") == false){
      return false;}
      return true;
    }
    public static void checkSize(LinkedList<String> LL, int num){
      int lengthLL = LL.size();
      if (lengthLL == num){
        System.out.println("The roster is full");
        System.exit(0);
      }
    }
/**
  * addName adds a name to the Linked list
  * @param LinkedList<String> LL
  * @param int size
  * @return void
**/
    public static void addName(LinkedList<String> LL, int size){
      checkSize(LL, size);
      Scanner userinputString= new Scanner(System.in);
      System.out.println();
      System.out.println("Enter the name of the student to add to the roster in Lastname, Firstname format:");
      String nameAdd = userinputString.nextLine();
      LL.add(nameAdd);
      System.out.println();
    }
/**
  * This method retruns the index of the given name
  * @param LinkedList<String> LL
  * @param String name
  * @return int
**/
    public static int indexRemName(LinkedList<String> LL, String name){
      int index = 0;
      for(int i=0; i < LL.size(); i++){
        if(LL.get(i).equals(name)){
          index = i ;
        }
      }
      return index;
    }
/**
  * This method performs the action that the user inputs.
  * @param LinkedList<String> LL
  * @param String a
  * @param String num
  * @return int
**/
    public static void action(String a, LinkedList<String> LL,int num){
      Scanner userinputString= new Scanner(System.in);
      while(a.equals("q") == false){
        while (CharCheck(a) == false){
          System.out.println("NOT A VALID STRING. Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit.");
          a = userinputString.nextLine();
        }
        if(a.equals("a")){
          addName(LL, num);
          System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit.");
          a = userinputString.nextLine();
        }
        if(a.equals("r")){
          removeName(LL);
          System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit.");
          a = userinputString.nextLine();
        }
        if (a.equals("d")){
          System.out.println();
          System.out.println(LL.toString());
          System.out.println();
          System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit.");
          a = userinputString.nextLine();
        }
      }
    }
/**
  * This method removes name from a Linked list
  * @param LinkedList<String> LL
  * @return void
**/
    public static void removeName(LinkedList<String> LL){
      Scanner userinputString= new Scanner(System.in);
      System.out.println();
      System.out.println("Enter the name of the student you would like to remove from the roster:");
      String nameRemove = userinputString.nextLine();
      LL.delete(indexRemName(LL, nameRemove));
    }
    //   public static void alphabetizeLL(LinkedList<String> LL){
    //     // use a compareto method
    //     // this is has an infinite loop that is why it is not working
    //     // maybe use a tmp
    //    for(int i=0; i<LL.size()-1; i++){
    //     for (int j =1; j<LL.size(); j++)
    //      if (LL.get(i).compareTo(LL.get(j))>0){
    //       String tmpi = LL.get(i);
    //       String tmpj = LL.get(j);
    //        LL.add(i,tmpj);
    //        LL.add(j, tmpi);
    //     }
    //   }
    // }
/**                  MAIN METHOD                 */
    public static void main(String[] args){
      Scanner userinputInt = new Scanner(System.in);
      Scanner userinputString = new Scanner(System.in);
      LinkedList<String> rosterNames = new LinkedList<String>();
      System.out.println("What is the maximum size of your roster?");
      int rosterLen = userinputInt.nextInt();
      System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit.");
      String action = userinputString.nextLine();
      action(action, rosterNames, rosterLen);
    }
  }
