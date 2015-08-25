package acm;

/**
 * Card.java Represents a playing card.
 *
 * Created by Jay on 8/13/2015.
 */

import acm.graphics.*;

public class Card extends GCompound implements Comparable<Card>{
    private Suit suit;
    private Rank rank;
    private GImage front;
    private GImage back;

    /**
     * Constructs a playing card.
     */
    public Card(){
        suit = Suit.values()[
                (int)(Math.random() * 4)
                ];
        rank = Rank.values()[
                (int)(Math.random() * 13)
                ];
        back = new GImage("cards/b.gif");
        front = new GImage(getCardImage());
        add(front);
        add(back);
    }

    /**
     * Working constructor.
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
        back = new GImage("cards/b.gif");
        front = new GImage(getCardImage());
        add(front);
        add(back);
    }

    /**
     * Returns the suit.
     *
     * @return the suit.
     */
    public Suit getSuit(){

        return suit;
    }

    /**
     * Returns the rank.
     *
     * @return the rank
     */
    public Rank getRank(){

        return rank;
    }

    /**
     * Returns true if the "back" image is on top.
     */
    public boolean isFaceDown(){

        return this.getElement(0) != back;
    }

    /**
     * Private name of a card in the images folder.
     * @return random name with parts combined.
     */
    private String getCardImage(){
        String suits = "cshd";
        String ranks = "23456789tjkqa";

        return "cards/" + ranks.charAt(rank.ordinal()) + suits.charAt(suit.ordinal()) + ".gif";
    }

    /**
     * Flips the card so that the element on the bottom is on the top.
     */
    public void flip(){

        this.getElement(0).sendToFront();
    }

    /**
     * Returns the text representation of the card.
     *  @return text representation of the card.
     */
    public String toString(){
        return rank.toString().charAt(0) +
                rank.toString().substring(1).toLowerCase() +
                " of" +
                suit.toString().charAt(0) +
                suit.toString().substring(1).toLowerCase();
    }


    public int compareTo(Card other){
        if(this == other){
            return 0;
        }

        int rankDiff = getRank().compareTo(other.getRank());
        int suitDiff = getSuit().compareTo(other.getSuit());

        if(rankDiff == 0)
            return suitDiff;
            return rankDiff;

    }

}
