/**
 * Deals a simple hand of BlackJack and displays it, allowing the player to hit or stand.
 *
 * Created by Jay on 8/13/2015.
 *
 */
import acm.Card;
import acm.Rank;
import acm.Suit;
import acm.program.*;
import acm.graphics.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackJack extends GraphicsProgram {

    private JLabel statusLbl;
    private JLabel logoLbl;
    private JButton dealHandBtn;
    private JButton hitBtn;
    private JButton standBtn;


    public static void main(String[] args) {

        new BlackJack().start(args);
    }

    // Create Deck variable and a Deck object.
    private Deck deck = new Deck();

    // Create Hand variables for player and dealer.
    private Hand player;
    private Hand dealer;

    /**
     * The playerPile shows the player's cards as they are dealt.
     * The dealerPile shows the dealer's cards when dealt.
     */
    private GCompound playerPile = new GCompound();
    private GCompound dealerPile = new GCompound();

    /**
     * The newDeck() method.
     * Populate the deck of cards with 52 shuffled cards.
     */
    public void newDeck() {
        deck.removeAllCards();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.addCard(new Card(rank, suit));
            }
        }
        deck.shuffle();
    }

    /**
     *  Show the cards in a Hand on the table.
     *
     *  @param hand the Hand of cards
     *  @param pile the pile to display the cards
     */
    private void showCards(Hand hand, GCompound pile) {
        pile.removeAll();
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            Card card = hand.getCard(i);
            if (card.isFaceDown()){
                card.flip();
            }
            pile.add(card, i * 20, 0);
        }
    }

    /**
     *  Score the game when Stand is pressed.
     *
     *  @param dealerHand dealer's hand
     *  @param playerHand player's hand
     */
    private void scoreGame(Hand dealerHand, Hand playerHand) {
        int dealerPoints = dealerHand.evalHand();
        int playerPoints = playerHand.evalHand();

        String message = "";
        if (dealerPoints > 21) {
            message = "Dealer Busts! You win.";
        }
        else if (dealerPoints == playerPoints) {
            message = "Tie Game!";
        }
        else if (playerPoints < dealerPoints) {
            message = "Dealer (" + dealerPoints + ") beats you ("
                    + playerPoints + ")";
        }
        else {
            message = "You (" + playerPoints + ") beat the dealer ("
                    + dealerPoints + ")";

        }

        // Set the buttons back to normal
        dealHandBtn.setEnabled(true);
        hitBtn.setEnabled(false);
        standBtn.setEnabled(false);
        statusLbl.setText(message);
    }


    /**
     *  Write the showScore method.
     *  Display the score of a particular hand.
     *  @param playerHand the Hand to score.
     *  @param dealerHand the Hand to score.
     */
    public void showScore(Hand playerHand, Hand dealerHand) {
        int playerScore = playerHand.evalHand();
        int dealerScore = dealerHand.evalHand();

        if (playerScore == 21) {
            statusLbl.setText("Blackjack! You Won!");
            dealHandBtn.setEnabled(true);
            hitBtn.setEnabled(false);
            standBtn.setEnabled(false);

        }
        else if (playerScore > 21) {
            statusLbl.setText("Sorry, you busted! Score is " + playerScore);
            dealHandBtn.setEnabled(true);
            hitBtn.setEnabled(false);
            standBtn.setEnabled(false);

        }
        else {
            statusLbl.setText("Score is " + playerScore + ". Dealer score is " + dealerScore + ". Hit or Stand?");
            dealHandBtn.setEnabled(false);
            hitBtn.setEnabled(true);
            standBtn.setEnabled(true);
        }
    }


    /**
     * The DealHand class to respond to the dealHandBtn clicks
     */
    class DealHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dealerPile.removeAll();
            if (deck == null || deck.cardsLeft() < 2) {
                newDeck();
            }
            player = new BlackJackHand();
            dealer = new BlackJackHand();

            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());

            showCards(player, playerPile);
            showCards(dealer, dealerPile);
            showScore(player, dealer);
        }
    }


    /**
     * Initialize the user interface.
     */
    public void init() {
        // Set the table color to a dark green
        setBackground(new Color(0, 128, 0));

        // Add the player and dealer piles to the table
        add(playerPile, 20, 20);
        add(dealerPile, getWidth() / 2 + 20, 20);


        // Initialize the three JLabels
        logoLbl = new JLabel(new ImageIcon("cards/21.gif"));
        statusLbl = new JLabel("Welcome to Blackjack");

        // Add your labels to the program's surface.
        add(logoLbl, NORTH);
        add(statusLbl, NORTH);

        // Construct your JButtons and add them to the surface
        dealHandBtn = new JButton("Deal New Hand");
        hitBtn = new JButton("Hit Me");
        standBtn = new JButton("Stand");

        add(dealHandBtn, SOUTH);
        add(hitBtn, SOUTH);
        add(standBtn, SOUTH);

        // Hook up the dealHandBtn JButton
        dealHandBtn.addActionListener(new DealHandler());

        // Hook up the hitBtn JButton to an anonymous handler
        hitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (deck == null || deck.cardsLeft() < 2) {
                    newDeck();
                }
                player.addCard(deck.dealCard());
                showCards(player, playerPile);
                showScore(player, dealer);
            }
        });

        // Finish the game with the Stand button
        standBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (deck == null || deck.cardsLeft() < 2) {
                    newDeck();
                }

                while (dealer.evalHand() < 17) {
                    dealer.addCard(deck.dealCard());
                }

                showCards(player, dealerPile);
                scoreGame(dealer, player);
            }
        });
    }
}

