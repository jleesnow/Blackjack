import acm.Card;

import java.util.ArrayList;

/**
 * Hand.java represents a hand of cards.
 *
 * Created by Jay on 8/13/2015.
 */
public abstract class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    /**
     * evaluateHand() Evaluates the hand.
     *
     * @return an integer corresponding to the rating of the hand.
     */
    public abstract int evalHand();

    /**
     * compareTo(Hand other) Compares two hands.
     *
     * @param otherHand the hand being compared.
     * @return < 0 if this hand is less than the other hand, 0 if the two hands
     *         are the same, or > 0 if this hand is greater then the other hand.
     */
    public int compareTo(Hand other){
        if(evalHand() == other.evalHand()){
            return 0;
        }
        if(evalHand() < other.evalHand()){
            return -1;
        }
        else{
            return 1;
        }
    }

    /**
     * Adds a card to this hand.
     *
     * @param card card to be added to the current hand.
     */
    public void addCard(Card card){
        hand.add(card);
    }

    /**
     * Obtains the card stored at the specified location in the hand. Does not
     * remove the card from the hand.
     *
     * @param index position of card to be accessed.
     * @return the card of interest, or the null reference if the index is out
     *         of bounds.
     */
    public Card getCard(int index){
        return hand.get(index);
    }

    /**
     * The number of cards held in the hand.
     *
     * @return number of cards currently held in the hand.
     */
    public int getNumberOfCards(){
        return hand.size();
    }

    /**
     * Returns a description of the hand.
     *
     * @return a list of cards held in the hand.
     */
    public String toString(){
        return hand.toString();
    }

}
