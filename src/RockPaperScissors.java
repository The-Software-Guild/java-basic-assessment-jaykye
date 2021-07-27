import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        boolean wantToPlay = true;
        Scanner inputScanner = new Scanner(System.in);
        Random rSeed = new Random();
        int nRounds = 0; // 1 to 10, inclusive.
        int userRps = 0;  // 1. Rock, 2. Paper, 3. Scissors
        int computerRps = 0;  // 1. Rock, 2. Paper, 3. Scissors
        String[] rpsArray = {"Rock", "Paper", "Scissors"};
        String input;
        int nUserWin;
        int nUserTie;
        int nUserLoss;
        boolean validAnswer;

        while (wantToPlay) {
            while (true){
                System.out.println("How many rounds do you want to play?");
                nRounds = Integer.parseInt(inputScanner.nextLine());
                if (1 <= nRounds && nRounds <=10){
                    break;
                }
                System.out.println("Round must be between 1 to 10.");

            }
            System.out.println("Playing " + nRounds + " round(s).");

            nUserWin = 0;
            nUserTie = 0;
            nUserLoss = 0;
            // Loop as many times as number of rounds.
            for (int i=0; i<nRounds; i++){

                // User selection and Computer selection.
                validAnswer = false;
                while (!validAnswer) {
                    System.out.println("Type in integer: 1. Rock, 2. Paper, 3. Scissors");
                    try{
                        userRps = Integer.parseInt(inputScanner.nextLine());
                        if (0< userRps && userRps <=3){
                            validAnswer = true;
                        }
                        else{
                            System.out.println("Integer must be between 1 and 3.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Enter integer value.");
                    }
                }
                System.out.println("User selected: " + rpsArray[userRps-1]);
                computerRps = 1 + rSeed.nextInt(3);
                System.out.println("Computer selected: " + rpsArray[computerRps-1]);

                // win/loss logic
                switch (reportResults(userRps, computerRps)){
                    case 1:
                        nUserWin +=1;
                        System.out.println("You won this round.");
                        break;
                    case -1:
                        nUserLoss +=1;
                        System.out.println("You lost this round.");
                        break;
                    case 0:
                        nUserTie +=1;
                        System.out.println("Tied this round.");
                        break;
                }
            }

            // Report results
            System.out.println("Number of wins: " + nUserWin);
            System.out.println("Number of ties: " + nUserTie);
            System.out.println("Number of losses: " + nUserLoss);
            if (nUserWin > nUserLoss){
                System.out.println("You have won this game!");
            }
            else if (nUserWin < nUserLoss){
                System.out.println("You have lost this game!");
            }
            else{
                System.out.println("Tied Game!");

            }
            // Ask if want to play more.
            validAnswer = false;
            while (!validAnswer){
                System.out.println("Do you want to play more? Yes/No");
                input = inputScanner.nextLine();

                switch (input) {
                    case "No":
                    case "no":
                    case "n":
                        wantToPlay = false;
                        validAnswer = true;
                        break;
                    case "Yes":
                    case "yes":
                    case "y":
                        validAnswer = true;
                        break;
                    default:
                        System.out.println("Not a valid answer.");
                }
            }
        }
        System.out.println("Thanks for playing!");
    }

    public static int reportResults(int userVal, int compVal){
        /*
        Win / Loss logic
        if user wins, this method returns 1, ties 0, loss -1
        */
        if (userVal - compVal == 1 || userVal - compVal == -2){
            return 1;
        }
        else if (userVal == compVal){
            return 0;
        }
        else{
            return -1;
        }
    }
}
