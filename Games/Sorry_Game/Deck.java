import java.util.Stack;
import java.util.Random;

public class Deck{

    private static Stack<Integer> deck = new Stack<Integer>(); //Only variables we need

    public Deck(){
        for (int i = 0; i < 5; i++){ //Add all of the 1s
            deck.push(1);
        }
        for (int j = 0; j < 4; j++){ //Add all of the other cards
            deck.push(0);
            deck.push(2);
            deck.push(3);
            deck.push(4);
            deck.push(5);
            deck.push(7);
            deck.push(8);
            deck.push(10);
            deck.push(11);
            deck.push(12);
        }
    } // Deck() constructor

    public static Stack Shuffle(){
        Random rand = new Random();
        Stack<Integer> stackA = new Stack<Integer>();
        Stack<Integer> stackB = new Stack<Integer>();
        Stack<Integer> stackC = new Stack<Integer>();

        int choice;
        int card;
        while(!deck.empty()){ // Shuffling the cards into 3 separate stacks
            choice = rand.nextInt(3);
            if (choice == 0){
                card = deck.pop();
                stackA.push(card);
            } else if (choice == 1){
                card = deck.pop();
                stackB.push(card);
            } else if (choice == 2){
                 card = deck.pop();
                 stackC.push(card);
            }
        }
        while (!stackA.empty()){ // Emptying the stacks back into the deck
            card = stackA.pop();
            deck.push(card);
        }
        while (!stackB.empty()){
             card = stackB.pop();
             deck.push(card);
        }
        while (!stackC.empty()){
             card = stackC.pop();
             deck.push(card);
        }
        return deck;
    } // Shuffle()

	public static int drawCard() {
    //pop top card from shuffled deck in Deck class
	int value;

	value = deck.pop();

	return value;

    }//drawCard

} // Deck class

