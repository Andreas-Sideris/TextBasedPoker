import java.util.Scanner;

public class BetLoop {
    double bet;

    double cashamt;
    char decisionout;
    public BetLoop(double betin, double cash) {
        Scanner input = new Scanner(System.in);
        boolean test = false;
        char decision = 'X';
        while (!test) {
            System.out.println("Raise (r), Check (c), or Fold? (f)");
            System.out.println("Available cash: " + cash);
            decision = input.next().charAt(0);
            switch (decision) {
                case 'r':
                    System.out.println("Enter new extra bet: ");
                    boolean test2 = false;
                    while (!test2) {
                        double bet2 = input.nextDouble();
                        if (betin <= cash) {
                            test2 = true;
                            betin += bet2;
                            cash -= bet2;
                        }
                        else {
                            System.out.println("Bet is over available money. Enter new bet: ");
                        }
                    }
                    break;
                case 'c':
                    System.out.println("Moving on...");
                    test = true;
                    break;
                case 'f':
                    System.out.println("You fold. Next Hand...");
                    test = true;
                    break;
            }
        }
        decisionout = decision;
        bet = betin;
        cashamt = cash;
    }

    public char getDecision() {
        return decisionout;
    }

    public double getBet() {
        return bet;
    }

    public double getCash() {
        return cashamt;
    }
}
