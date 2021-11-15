/**
  * This file is named UnrestricedGuessingGame it a unrestriced 20 questions game.
  * @author Anaid Garcia
  * @version 4.28.20
*/
import java.io.*;
import java.util.Scanner;

public class UnrestricedGuessingGame extends GuessingGame{

  /**
   * This is a  function called treeObjects and
   * it uses the helper fucntion printObjects to print the leaf nodes.
   * @param GameTree<String> newTree
   * @return void
   */
  public static void treeObjects(GameTree<String> newTree){
    printObjects(newTree.getRoot());
    System.out.println();
  }
  /**
   * This is a function called instructions it tells the user the how to play
   * the 20 questions game.
   * @param String filename
   * @return void
   */
  public static void instructions(GameTree<String> newTree){
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
    treeObjects(newTree);
  }

  /**
   * This is a function creates a new updated version of a the tree. This function is called when we want to update the
   * right side of the tree.
   * @param BinaryTreeNode<String>  newRightside
   * @param GameTree<String> gameTree
   * @return GameTree<String>
   */
  public static GameTree<String> createTree(BinaryTreeNode<String>  newRightside, GameTree<String> gameTree){
    // GameTreeNode<String>  temp = gameTree.getRightChild()
    gameTree.getRoot().setRightChild(newRightside);
    return gameTree;
  }
  /**
   * This is a function creates a game tree using createTree it also gets the information from the user to build the tree.
   * @param GameTree<String> roots
   * @param GameTree<String> originalTree
   * @param String thing, String question
   * @return GameTree<String>
   */
  public static GameTree<String>  addOn(GameTree<String> roots, GameTree<String> originalTree, String thing, String question){

    GameTreeNode<String> newRoots = new GameTreeNode(question);
    GameTreeNode<String> newLeftC = new GameTreeNode(thing);
    BinaryTreeNode<String> newRightC = roots.getRoot();
    roots.setRoot(newRoots);
    roots.getRoot().setLeftChild(newLeftC);
    roots.getRoot().setRightChild(newRightC);
    return createTree(roots.getRoot(), originalTree);
  }

 public static void anotherRound(GameTree<String>  originalTree, GameTree<String> roots, String thing, String question, String fileName) throws FileNotFoundException{
   Scanner userInput = new Scanner(System.in);
   System.out.println ("Would you like to play again? ");
   String again = userInput.nextLine();
   while (!validExpression(again)){
     System.out.println("Would you like to play again?");
     again = userInput.nextLine();
   }
   while (again.equals("yes")){
     instructions(addOn(originalTree, roots,thing,question)); // this is where the problem is
     Execute(addOn(originalTree, roots,thing,question), fileName); // this is where the problem is
     System.out.println ("Would you like to play again? ");
     again = userInput.nextLine();
   }
   System.out.println ("This was fun comeback later, so we can play again! ");
 }

  /**
   * This is a  function called guessAnswer it prints the answer in a readable way
   * for the user
   * @param GameTree<String> roots
   * @return void
   */
  public static void enhancedguessAnswer(GameTree<String>  originalTree, GameTree<String> roots,String fileName) throws FileNotFoundException{
    Scanner userInput = new Scanner(System.in);
    System.out.println("Were you thinking of " + roots.getRoot().getData()+ " ?");
    String response = userInput.nextLine();
    if (response.equals("yes")){
      System.out.println("Yay! I got it!!");
      System.out.println ("Would you like to play again? ");
      String again = userInput.nextLine();
      while (!validExpression(again)){
        System.out.println("Would you like to play again?");
        again = userInput.nextLine();
      }
      while (again.equals("yes")){
        instructions(fileName);
        Execute(originalTree,fileName );
        System.out.println ("Would you like to play again? ");
        again = userInput.nextLine();
      }
      System.out.println ("This was fun comeback later, so we can play again! ");
    }
    else{
      System.out.println("Oh no, maybe I'll guess it right next time!");
      System.out.println("What were you thinking of?");
      String thing = userInput.nextLine();
      System.out.println("Please give me a yes/no question that would have determined your thing.");
      String description = userInput.nextLine();
      System.out.println("Is the answer to your question yes or no?");
      String answer = userInput.nextLine();
      anotherRound( originalTree, roots,  thing, description, fileName);
      //addOn(originalTree, roots, thing, description);
    }
  }

  /**
   * This is a  function called Execute it plays one round of 20 questions using
   * the file that the user inputs at the comand line.
   * @param String fileName
   * @return void
   */
  public static void Execute(GameTree<String>  newTree, String fileName) throws FileNotFoundException{
    GameTreeReader TwentyQ = new GameTreeReader(fileName);
    GameTree<String>  secondnewTree = TwentyQ.buildGameTree();
    // BinaryTree<String>  ogTree = newTree;
    Scanner userInput = new Scanner(System.in);
    while(!newTree.isEmpty()){
      if(newTree.getRoot().isLeaf()){
        enhancedguessAnswer(secondnewTree ,newTree, fileName);
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
    GameTreeReader TwentyQ = new GameTreeReader(args[0]);
    GameTree<String>  newTree = TwentyQ.buildGameTree();
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
        Execute(newTree, args[0]);
   }
 }
}
