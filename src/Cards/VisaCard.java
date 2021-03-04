package Cards;

public class VisaCard extends Card {
    public VisaCard(String ownerName) {
        super(ownerName);
    }

    @Override
    public String getSpendingLimit() {
        return "The spending limit is 2500 NS";
    }

    @Override
    public String getType() {
        return "Visa Card";
    }
}
