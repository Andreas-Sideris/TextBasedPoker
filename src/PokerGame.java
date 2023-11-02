import java.util.Arrays;
import java.util.Scanner;
public class PokerGame {
    public static void main(String[] args) {
        boolean game = true;
        int gamecount = 0;
        double carry_cash = 0;
        double cash = 0;
        while (game) {
            if (gamecount == 0) {
                cash = 100.0;
                System.out.println("Game starting... You will start with $100 to bet");
            }
            else {
                cash = carry_cash;
                if (cash <= 0) {
                    System.out.println("You ran out of cash. Adding back a new $100 to start with.");
                    cash = 100;
                }
                else {
                    System.out.println("You are starting with available cash: " + cash);
                }
            }
            Scanner input = new Scanner(System.in);
            boolean test = false;
            System.out.println("Welcome to the table! Please enter your initial bet: ");
            double bet = 0;
            while (!test) {
                bet = input.nextDouble();
                if (bet <= cash) {
                    test = true;
                    cash -= bet;
                }
                else {
                    System.out.println("Bet is over available money. Enter new bet: ");
                    bet = 0;
                }
            }
            System.out.println("Current bet: " + bet);

            Deck newdeck = new Deck();
            newdeck.shuffle();
            Card[] hand = new Card[2];
            for (int i = 0; i < 2; i++) {
                hand[i] = newdeck.deal();
            }
            System.out.println("Your hand has: ");
            for (int i = 0; i < 2; i++) {
                System.out.println(hand[i].dispCard());
                if (i == 0) {
                    System.out.println(" and");
                }
            }
            Card[] opphand = new Card[2];
            for (int i = 0; i < 2; i++) {
                opphand[i] = newdeck.deal();
            }
            System.out.println("Your opponent has been dealt their cards.");
            Card[] table = new Card[5];
            for (int i = 0; i < 5; i++) {
                table[i] = newdeck.deal();
            }
            System.out.println("The flop cards are: ");
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    System.out.println(table[i].dispCard());
                }
                else {
                    System.out.print(table[i].dispCard() + " + ");
                }
            }
            BetLoop betinfo = new BetLoop(bet, cash);
            char decision = betinfo.getDecision();
            bet = betinfo.getBet();
            cash = betinfo.getCash();

            if (decision == 'c') {
                System.out.println("Current bet: " + bet);
                System.out.println("Here's the turn... ");
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        System.out.println(table[i].dispCard());
                    }
                    else {
                        System.out.print(table[i].dispCard() + " + ");
                    }
                }
                BetLoop betinfo2 = new BetLoop(bet, cash);
                char decision2 = betinfo2.getDecision();
                bet = betinfo2.getBet();
                cash = betinfo2.getCash();
                if (decision2 == 'c') {
                    System.out.println("Current bet: " + bet);
                    System.out.println("Here's the river... ");
                    for (int i = 0; i < 5; i++) {
                        if (i == 4) {
                            System.out.println(table[i].dispCard());
                        }
                        else {
                            System.out.print(table[i].dispCard() + " + ");
                        }
                    }
                    BetLoop betinfo3 = new BetLoop(bet, cash);
                    char decision3 = betinfo3.getDecision();
                    bet = betinfo3.getBet();
                    cash = betinfo3.getCash();
                    if (decision3 == 'c') {
                        System.out.println("Current bet: " + bet);
                        int my_score = checkHand(hand, table);
                        int opp_score = checkHand(opphand, table);
                        String outmsg = "";
                        String outmsg2 = "";
                        switch (my_score) {
                            case 1:
                                outmsg = "Royal Flush";
                                break;
                            case 2:
                                outmsg = "Straight Flush";
                                break;
                            case 3:
                                outmsg = "Four of a Kind";
                                break;
                            case 4:
                                outmsg = "Full House";
                                break;
                            case 5:
                                outmsg = "Flush";
                                break;
                            case 6:
                                outmsg = "Straight";
                                break;
                            case 7:
                                outmsg = "Three of a Kind";
                                break;
                            case 8:
                                outmsg = "Two Pair";
                                break;
                            case 9:
                                outmsg = "Pair";
                                break;
                        }
                        if (my_score > 100) {
                            outmsg = "High Card";
                        }
                        switch (opp_score) {
                            case 1:
                                outmsg2 = "Royal Flush";
                                break;
                            case 2:
                                outmsg2 = "Straight Flush";
                                break;
                            case 3:
                                outmsg2 = "Four of a Kind";
                                break;
                            case 4:
                                outmsg2 = "Full House";
                                break;
                            case 5:
                                outmsg2 = "Flush";
                                break;
                            case 6:
                                outmsg2 = "Straight";
                                break;
                            case 7:
                                outmsg2 = "Three of a Kind";
                                break;
                            case 8:
                                outmsg2 = "Two Pair";
                                break;
                            case 9:
                                outmsg2 = "Pair";
                                break;
                        }
                        if (opp_score > 100) {
                            outmsg2 = "High Card";
                        }
                        System.out.println("Your hand is a " + outmsg);
                        System.out.println("Your opponent's hand is a " + outmsg2);
                        if (my_score < opp_score) {
                            cash += (bet*2);
                            System.out.println("You won! Your new cash amount is: " + cash);
                        }
                        else if (my_score == opp_score) {
                            System.out.println("You had the same type of hand as your opponent. You will get your bet back.");
                            cash += bet;
                        }
                        else if (my_score > opp_score) {
                            System.out.println("You lost! Your remaining cash is: " + cash);
                        }
                        carry_cash = cash;
                    }
                }
            }
            System.out.println("Play again? (Y/N)");
            char retry = input.next().charAt(0);
            if (retry == 'N') {
                game = false;
            }
            else {
                gamecount++;
            }
        }
    }

    public static int checkHand(Card[] hand, Card[] table) {
        Card[] totalhand = new Card[7];
        for (int i = 0; i < 2; i++) {
            totalhand[i] = hand[i];
        }
        for (int i = 2; i < 7; i++) {
            totalhand[i] = table[i-2];
        }

        boolean flush = false;
        boolean twopair = false;
        boolean fullhouse = false;
        boolean straight = false;
        boolean straight_flush = false;
        boolean royal_flush = false;
        int[] suit_counts = {0,0,0,0};
        int[] card_counts = {0,0,0,0,0,0,0,0,0,0,0,0,0};
        int count_hand = 1;
        int twopair_count = 0;
        int highcard = 0;
        int triple_count = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (totalhand[j].getSuit() == i) {
                    suit_counts[i-1] += 1;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (suit_counts[i] >= 5) {
                flush = true;
            }
        }
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 7; j++) {
                if (totalhand[j].getType() == i) {
                    card_counts[i-1] += 1;
                }
            }
        }
        for (int i = 0; i < 13; i++) {
            if (card_counts[i] == 3) {
                triple_count += 1;
            }
            else if (card_counts[i] == 2) {
                twopair_count += 1;
            }
            switch (card_counts[i]) {
                case 2:
                    count_hand = 2;
                    break;
                case 3:
                    count_hand = 3;
                    break;
                case 4:
                    count_hand = 4;
                    break;
            }
        }
        if (twopair_count >= 2) {
            twopair = true;
        }
        if (triple_count == 1) {
            if (twopair_count >= 1) {
                fullhouse = true;
            }
        }
        int[] totalhandtype = new int[7];
        for (int i = 0; i < 7; i++) {
            totalhandtype[i] = totalhand[i].getType();
        }
        Arrays.sort(totalhandtype);
        int straight_test = 0;
        int[] straighthand = new int[5];
        for (int i = 1; i < 7; i++) {
            if (totalhandtype[i] == ((totalhandtype[i-1]) + 1)) {
                straight_test += 1;
            }
            else {
                straight_test = 0;
            }
            if (straight_test >= 4) {
                straighthand[4] = totalhandtype[i];
                straighthand[3] = totalhandtype[i-1];
                straighthand[2] = totalhandtype[i-2];
                straighthand[1] = totalhandtype[i-3];
                straighthand[0] = totalhandtype[i-4];
                straight = true;
                break;
            }
        }
        if (straight) {
            if (flush) {
                straight_flush = true;
                int royal = 0;
                for (int i = 0; i < 5; i++) {
                    switch (straighthand[i]) {
                        case 9:
                            royal = 1;
                            break;
                        case 10:
                            royal = 1;
                            break;
                        case 11:
                            royal = 1;
                            break;
                        case 12:
                            royal = 1;
                            break;
                        case 13:
                            royal = 1;
                            break;
                        default:
                            royal = 0;
                            break;
                    }
                }
                if (royal == 1) {
                    royal_flush = true;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (totalhand[i].getType() > highcard) {
                highcard = totalhand[i].getType();
            }
        }
        if (royal_flush) {
            return 1;
        }
        else if (straight_flush) {
            return 2;
        }
        else if (count_hand == 4) {
            return 3;
        }
        else if (fullhouse) {
            return 4;
        }
        else if (flush) {
            return 5;
        }
        else if (straight) {
            return 6;
        }
        else if (count_hand == 3) {
            return 7;
        }
        else if (twopair) {
            return 8;
        }
        else if (count_hand == 2) {
            return 9;
        }
        else {
            return highcard + 100;
        }
    }
}
