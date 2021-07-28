import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        boolean wantToPlay = true;
        Scanner inputScanner = new Scanner(System.in);
        Random rSeed = new Random();
        int numOfRounds = 0; // 1 to 10, inclusive.
        int userSelection = 0;  // 1. Rock, 2. Paper, 3. Scissors
        int computerSelection = 0;  // 1. Rock, 2. Paper, 3. Scissors
        String[] rpsArray = {"Rock", "Paper", "Scissors"};
        String input;
        int userWinCount;
        int userTieCount;
        int userLossCount;
        boolean validAnswer;

        while (wantToPlay) {
            while (true){
                System.out.println("How many rounds do you want to play?");
                numOfRounds = Integer.parseInt(inputScanner.nextLine());
                if (1 <= numOfRounds && numOfRounds <=10){
                    break;
                }
                System.out.println("Round must be between 1 to 10.");

            }
            System.out.println("Playing " + numOfRounds + " round(s).");

            userWinCount = 0;
            userTieCount = 0;
            userLossCount = 0;
            // Loop as many times as number of rounds.
            for (int i=0; i<numOfRounds; i++){

                // User selection and Computer selection.
                validAnswer = false;
                while (!validAnswer) {
                    System.out.println("Type in integer: 1. Rock, 2. Paper, 3. Scissors");
                    try{
                        userSelection = Integer.parseInt(inputScanner.nextLine());
                        if (0< userSelection && userSelection <=3){
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
                System.out.println("User selected: " + rpsArray[userSelection-1]);
                computerSelection = 1 + rSeed.nextInt(3);
                System.out.println("Computer selected: " + rpsArray[computerSelection-1]);

                // win/loss logic
                switch (reportResults(userSelection, computerSelection)){
                    case 1:
                        userWinCount +=1;
                        System.out.println("You won this round.");
                        break;
                    case -1:
                        userLossCount +=1;
                        System.out.println("You lost this round.");
                        break;
                    case 0:
                        userTieCount +=1;
                        System.out.println("Tied this round.");
                        break;
                }
            }

            // Report results
            System.out.println("Number of wins: " + userWinCount);
            System.out.println("Number of ties: " + userTieCount);
            System.out.println("Number of losses: " + userLossCount);
            if (userWinCount > userLossCount){
                System.out.println("You have won this game!");
            }
            else if (userWinCount < userLossCount){
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

        The integer equivalent of rock paper scissors have the following pattern:
        If user value is bigger than the computer value by 1, user wins.
        One edge case is when user selects 1, in this case computer cannot select 0. -> use -2
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
