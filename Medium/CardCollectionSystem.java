package pkg5;

import java.util.*;

class CardCollectionSystem {
    private HashMap<String, List<String>> cardMap;

    public CardCollectionSystem() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public static void main(String[] args) {
        CardCollectionSystem system = new CardCollectionSystem();

        system.addCard("Hearts", "Ace of Hearts");
        system.addCard("Hearts", "King of Hearts");
        system.addCard("Spades", "Ace of Spades");
        system.addCard("Clubs", "Queen of Clubs");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the symbol to search for cards: ");
        String symbol = scanner.nextLine();

        List<String> cards = system.getCardsBySymbol(symbol);
        if (!cards.isEmpty()) {
            System.out.println("Cards found for symbol " + symbol + ": " + cards);
        } else {
            System.out.println("No cards found for symbol " + symbol);
        }

        scanner.close();
    }
}

