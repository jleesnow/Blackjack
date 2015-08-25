import acm.Card;
import acm.Rank;

/**
 * BlackJackHand.java
 *
 * Created by Jay on 8/13/2015.
 */
public class BlackJackHand extends Hand{

    public int evalHand(){
        int cards = getNumberOfCards();
        int result = 0;
        int i;

        for(i = 0; i < cards; i++){
            Card card = getCard(i);
            Rank rank = card.getRank();
            switch (rank){
                case TWO: result += 2; break;
                case THREE: result += 3; break;
                case FOUR: result += 4; break;
                case FIVE: result += 5; break;
                case SIX: result += 6; break;
                case SEVEN: result += 7; break;
                case EIGHT: result += 8; break;
                case NINE: result += 9; break;
                case ACE: result += 11; break;
                default: result += 10; break;
            }
        }
        return result;
    }
}
