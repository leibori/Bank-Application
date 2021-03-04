package Cards;


public class CardFactory {

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
