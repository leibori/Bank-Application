package Cards;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;


public abstract class Card implements Serializable {
    private String ownerName;
    private int cardNum;
    private LocalDate make;
    private LocalDate exp;
    private String spendingLimit;

    public Card(String ownerName) {
        this.ownerName = ownerName;
        Random rand = new Random();
        this.cardNum = rand.nextInt(9999999-2000000);
        setCardDate();

        this.spendingLimit = getSpendingLimit();
    }
    public String getOwnerName() { return this.ownerName; }
    public int getCardNum() { return this.cardNum; }
    public void setCardDate() {
        this.make = LocalDate.now();
        Period p = Period.ofYears(2);
        LocalDate twoYearsLater = make.plus(p);
        this.exp = twoYearsLater;
    }

    public abstract String getSpendingLimit();

    public abstract String getType();

    @Override
    public String toString() {
        return "Card{" +
                "owner Name='" + ownerName + '\'' +
                ", card Num=" + cardNum +
                ", card date=" + make +
                ", exp date=" + exp +
                ", spendingLimit=" + spendingLimit +
                '}';
    }
}
