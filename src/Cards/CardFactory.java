package Cards;
/**
 *  The card factory class gives the client the opportunity to have 3 types of bank cards.
 *  The factory get the card type and the owner name and create the desire card type.
 *
 */

public class CardFactory {
    /**
     * Constructor
     * @param cardType The type of card
     * @param ownerName
     * @return Card
     */
    public static Card createCard(String cardType, String ownerName) {
        switch (cardType) {
            case "Master":
                System.out.println("A Master Card has been created.");
                return new MasterCard(ownerName);
            case "Visa":
                System.out.println("A Visa Card has been created.");
                return new VisaCard(ownerName);
            case "Leumi":
                System.out.println("A Leumi Card has been created.");
                return new LeumiCard(ownerName);

            default:
                return null;
        }
    }
}
