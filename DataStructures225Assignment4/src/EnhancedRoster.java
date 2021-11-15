/**
  * This file is named EnhancedRoster it inherts methods from the Roster.java class.
  * @author Anaid
  * @version 3.06.20
*/

import java.util.Scanner;

public class EnhancedRoster extends Roster{
/**
  * searchName is a method it is used to see if a name is in the liked list.
  * It also checks for duplicates.
  * @param LinkedList<String> LL
  * @param String name
  * @return boolean
**/
  public static boolean searchName(LinkedList<String> LL, String name){
    int count = 0;
    for (int i=0; i < LL.size(); i++){
      if(LL.get(i).equals(name)){
        count++;
      }
    }
    if (count > 0){
      return true;
    }
    return false;
  }
/**
  * This is method is called removeName it is a method that takes in a Linked List(LL)
  * and
  * 1. checkes is the name the user has inputed is in the LL.
  * 2. if it is then it removes the name from the linked list.
  * 3. if the name is not in the LL then it prints an error message.
  * It also checks for duplicates.
  * @param LinkedList<String> LL
  * @return void
**/
  public static void removeName(LinkedList<String> LL){
    Scanner userinputString= new Scanner(System.in);
    System.out.println();
    System.out.println("Enter the name of the student you would like to remove from the roster:");
    String nameRemove = userinputString.nextLine();
    if(searchName(LL, nameRemove) == false){
      System.out.println("THE NAME YOU HAVE ENTERED IS NOT ON THE ROSTER.");
    }
    else{
      LL.delete(indexRemName(LL, nameRemove));
    }
  }
/**
  * addName is used to add a name to a Linked List(LL)
  * 1. it checkes to see if the linked list is at cappcity then it quits the program
  * 2. is the name that is entered doesnt exist in the LL then the name will be added to the LL
  * 3. if the name exists in the LL then it prints a error message.
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
    if (searchName(LL, nameAdd) == false ){
      LL.add(nameAdd);
      System.out.println();
    }
    else{
      System.out.println("Name already exists.");
    }
  }
/**
  * the action method is used to deternmine what step the program needs to take next.
  * it takes in a String a that represents the action that needs to happen.
  * @param LinkedList<String> LL
  * @param int num
  * @param String a
  * @return void
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
        if(LL.isEmpty() == false){
          removeName(LL);
          System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit.");
          a = userinputString.nextLine();
        }
        else{
          System.out.println("Can not remove a name ROSTER IS EMPTY.");
          System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit.");
          a = userinputString.nextLine();
        }
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
