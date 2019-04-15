import java.io.File;
import java.util.ArrayList;

public class Deck extends ArrayList<Card> {
    public Deck() {
        makeDeck();
    }

    public void makeDeck() {
        this.add(new Card(EnumSuit.CLUBS, EnumValue.ACE, new File("data/images/AC.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.TWO, new File("data/images/2C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.THREE, new File("data/images/3C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.FOUR, new File("data/images/4C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.FIVE, new File("data/images/5C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.SIX, new File("data/images/6C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.SEVEN, new File("data/images/7C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.EIGHT, new File("data/images/8C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.NINE, new File("data/images/9C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.TEN, new File("data/images/10C.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.JACK, new File("data/images/JC.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.QUEEN, new File("data/images/QC.png")));
        this.add(new Card(EnumSuit.CLUBS, EnumValue.KING, new File("data/images/KC.png")));

        this.add(new Card(EnumSuit.HEARTS, EnumValue.ACE, new File("data/images/AH.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.TWO, new File("data/images/2H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.THREE, new File("data/images/3H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.FOUR, new File("data/images/4H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.FIVE, new File("data/images/5H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.SIX, new File("data/images/6H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.SEVEN, new File("data/images/7H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.EIGHT, new File("data/images/8H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.NINE, new File("data/images/9H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.TEN, new File("data/images/10H.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.JACK, new File("data/images/JH.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.QUEEN, new File("data/images/QH.png")));
        this.add(new Card(EnumSuit.HEARTS, EnumValue.KING, new File("data/images/KH.png")));

        this.add(new Card(EnumSuit.SPADES, EnumValue.ACE, new File("data/images/AS.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.TWO, new File("data/images/2S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.THREE, new File("data/images/3S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.FOUR, new File("data/images/4S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.FIVE, new File("data/images/5S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.SIX, new File("data/images/6S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.SEVEN, new File("data/images/7S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.EIGHT, new File("data/images/8S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.NINE, new File("data/images/9S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.TEN, new File("data/images/10S.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.JACK, new File("data/images/JS.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.QUEEN, new File("data/images/QS.png")));
        this.add(new Card(EnumSuit.SPADES, EnumValue.KING, new File("data/images/KS.png")));

        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.ACE, new File("data/images/AD.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.TWO, new File("data/images/2D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.THREE, new File("data/images/3D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.FOUR, new File("data/images/4D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.FIVE, new File("data/images/5D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.SIX, new File("data/images/6D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.SEVEN, new File("data/images/7D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.EIGHT, new File("data/images/8D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.NINE, new File("data/images/9D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.TEN, new File("data/images/10D.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.JACK, new File("data/images/JD.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.QUEEN, new File("data/images/QD.png")));
        this.add(new Card(EnumSuit.DIAMONDS, EnumValue.KING, new File("data/images/KD.png")));
    }
}
