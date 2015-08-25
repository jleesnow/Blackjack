/**
 * Created by Jay on 8/13/2015.
 */

import acm.Card;

import java.util.*;

public class Deck {

    private ArrayList<Card> cards;

    /**
     * Construct an empty deck.
     */
    public Deck(){

        cards = new ArrayList<Card>();
    }

    /**
     * Add a new card to the deck.
     * @param card to add.
     */
    public void addCard(Card card){

        cards.add(card);
    }

    /**
     * Returns the number of cards left in the deck.
     * @return the number of cards left in the deck.
     */
    public int cardsLeft(){

        return cards.size();
    }

    /**
     * Deal the top card on the deck. null if empty.
     * @return top card or null.
     */
    public Card dealCard(){
        if(cards.isEmpty()){
            return null;
        }
        else{
            return cards.remove(0);
        }
    }

    /**
     * Remove all of the cards.
     */
    public void removeAllCards(){

        cards.clear();
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }
}
