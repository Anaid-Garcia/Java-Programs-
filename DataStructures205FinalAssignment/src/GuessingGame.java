/**
  * This file is named GuessingGame it a restriced 20 questions game.
  * @author Anaid Garcia
  * @version 4.28.20
*/
import java.io.*;
import java.util.Scanner;
public class GuessingGame{

  /**
   * This is a function called validExpression it checks the users response and
   * makes sure it is a yes or no response. If not it will print a prompt stating
   * that the user input if invalid.
   * @param String userResponse
   * @return boolean
   */
  public static boolean validExpression(String userResponse){
    if( userResponse.toLowerCase().equals("yes") || userResponse.toLowerCase().equals("no")){
      return true;
    }
    System.out.println("Expression is NOT vaild. Please re-enter a valid expression.");
    return false;
  }

  /**
   * This is a function called instructions it tells the user the how to play
   * the 20 questions game.
   * @param String filename
   * @return void
   */
  public static void instructions(String fileName){
    Scanner userInput = new Scanner(System.in);
    System.out.println("Would you like to play 20 Questions? [yes/no]");
    String playOrNot = userInput.nextLine();
    while (!validExpression(playOrNot)){
      System.out.println("Would you like to play 20 Questions? [yes/no]");
      playOrNot = userInput.nextLine();
    }
    if (playOrNot.equals("no")){
      System.exit(0);
    }
    System.out.println( "Okay! When a quetion appears, answer with 'yes' or 'no'.");
    System.out.println("Now think of an object from the list below, and I will try to guess it!" );
    System.out.println();
    treeObjects(fileName);
  }

  /**
   * This is a recursive helper function called printObjects and
   * it prints the leaf nodes from the GameTree.
   * @param BinaryTreeNode<String> root
   * @return void
   */
  public static void printObjects(BinaryTreeNode<String>root){
    String things = "";
    if (root == null){
      return;
    }
    if(root.isLeaf()){
      System.out.println(root.getData());
    }
    else{
      printObjects(root.getLeftChild());
      printObjects(root.getRightChild());
    }
  }

  /**
   * This is a  function called treeObjects and
   * it uses the helper fucntion printObjects to print the leaf nodes.
   * @param String fileName
   * @return void
   */
  public static void treeObjects(String fileName){
    GameTreeReader TwentyQ = new GameTreeReader(fileName);
    GameTree<String>  newTree = TwentyQ.buildGameTree();
    printObjects(newTree.getRoot());
    System.out.println();
  }

  /**
   * This is a  function called guessAnswer it prints the answer in a readable way
   * for the user
   * @param GameTree<String> roots
   * @return void
   */
  public static void guessAnswer(GameTree<String> roots){
    Scanner userInput = new Scanner(System.in);
    System.out.println("Were you thinking of " + roots.getRoot().getData()+ " ?");
    String response = userInput.nextLine();
    if (response.equals("yes")){
      System.out.println("Yay! I got it!!");
    }
    else{
      System.out.println("Oh no, maybe I'll guess it right next time!");
    }
  }

  /**
   * This is a  function called Execute it plays one round of 20 questions using
   * the file that the user inputs at the comand line.
   * @param String fileName
   * @return void
   */
  public static void Execute(String fileName) throws FileNotFoundException{
    GameTreeReader TwentyQ = new GameTreeReader(fileName);
    GameTree<String>  newTree = TwentyQ.buildGameTree();
    Scanner userInput = new Scanner(System.in);
    while(!newTree.isEmpty()){
      if(newTree.getRoot().isLeaf()){
        guessAnswer(newTree);
        break;
      }
      else{
        System.out.println(newTree.getRoot().getData());
        String response = userInput.nextLine();
        if(response.equals("yes")){
          newTree.setRoot(newTree.getRoot().getLeftChild());
        }
        else if(response.equals("no")) {
          newTree.setRoot(newTree.getRoot().getRightChild());
        }
      }
    }
  }
  public static void main(String args[]) throws FileNotFoundException{
    Scanner userInput = new Scanner(System.in);
      instructions(args[0]);
      System.out.println ("Are you ready to begin?");
      String starting = userInput.nextLine();
      while (!validExpression(starting)){
        System.out.println("Are you ready to begin?");
        starting = userInput.nextLine();
      }
      while(starting.equals("no")){
        System.out.println ("Okay I will give you more time to think! (: ");
        System.out.println ("Are you ready to begin?");
        starting = userInput.nextLine();
        while(!validExpression(starting)){
         System.out.println("Are you ready to begin?");
         starting = userInput.nextLine();
       }
      }
      if (starting.equals("yes")){
        Execute(args[0]);
        System.out.println ("Would you like to play again? ");
        String again = userInput.nextLine();
        while (!validExpression(again)){
          System.out.println("Would you like to play again?");
          again = userInput.nextLine();
        }
        while (again.equals("yes")){
          instructions(args[0]);
          Execute(args[0]);
          System.out.println ("Would you like to play again? ");
          again = userInput.nextLine();
        }
        System.out.println ("This was fun comeback later, so we can play again! ");
      }
  }
}
