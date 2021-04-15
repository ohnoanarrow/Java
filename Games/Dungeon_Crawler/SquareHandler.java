import java.util.Stack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Collections;

//I created this class so we could randomly shuffle and place buttons on the board.
//I modeled it after a class I made to shuffle a deck of cards.
//I made a separate class to handle each kind of monster and their associated game (sorry).
public class SquareHandler{

    private Stack<Monster> monsters;

    public SquareHandler(){
        monsters = new Stack<Monster>();
        //I intend to make a method in each class that returns a JButton or if I decide to make a button who knows
        monsters.push(new Fairies());
        monsters.push(new Monolith());
        monsters.push(new Dragon());
        monsters.push(new Goblins());
        monsters.push(new Item());
        monsters.push(new Mermaids());
        monsters.push(new Pirate());
        monsters.push(new Start());
        monsters.push(new Wizard());

        //create 9 buttons
        Collections.shuffle(monsters);
    }//constructor
    public Stack<Monster> getMonsters() {
      return monsters;
    }

}
