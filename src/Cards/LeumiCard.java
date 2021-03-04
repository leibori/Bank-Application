package Cards;

public class LeumiCard extends Card {
    public LeumiCard(String ownerName) {
        super(ownerName);
    }

    @Override
    public String getSpendingLimit() {
        return "The spending limit is 10000 NS";
    }

    @Override
    public String getType() {
        return "Leumi Card";
    }
}
