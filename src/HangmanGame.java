import java.util.*;
public class HangmanGame {

    public void play() {

        //hangman ascii art
        List<String> hangmen = new LinkedList<>();
        hangmen.add("  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========");
        hangmen.add("  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========");
        hangmen.add("  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========");
        hangmen.add("  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========");
        hangmen.add("  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========");
        hangmen.add("  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========");
        hangmen.add("  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n=========");


        char guess;
        //keep track of what chars player has guessed
        List<Character> guessedLetters = new LinkedList<>();
        //term player is attempting to guess
        String term;
        //what chars of the term the player has guessed so far
        StringBuilder guessedTerm = new StringBuilder();
        int failedGuesses = 0;
        boolean win = false;
        Scanner scanner = new Scanner(System.in);


        //set term
        System.out.print("Term: ");
        term = scanner.nextLine().toLowerCase(Locale.ROOT);
        //create guessed term as a default of "___" (however many characters the term has)
        guessedTerm.append("_".repeat(term.length()));


        //if term contains spaces add those to the guessed term to show where the spaces are
        if (term.contains(" ")) {
            int index = term.indexOf(" ");
            while (index >= 0) {
                guessedTerm.setCharAt(index,term.charAt(index));
                index = term.indexOf(" ", index + 1);
            }
        }


        while (true) {

            //take in a guess from the player and make sure it is valid
            while (true) {
                System.out.print("Guess: ");
                try {guess = scanner.nextLine().toLowerCase(Locale.ROOT).charAt(0);}
                catch (Exception e) {continue;}

                //check guess if already guessed
                if (!guessedLetters.contains(guess)) {break;}
            }

            //does the term contain guessed letter
            if (term.contains(String.valueOf(guess))) {
                //grabs all the indexes of the guess in the term and adds only those chars to guessed term
                int index = term.indexOf(guess);
                while (index >= 0) {
                    guessedTerm.setCharAt(index,term.charAt(index));
                    index = term.indexOf(guess, index + 1);
                }
            }   else {
                //do they lose because ran out of guesses?
                failedGuesses++;
                System.out.println(hangmen.get(failedGuesses-1));
                if (failedGuesses == hangmen.size()) {break;}
                System.out.println("Term does not contain "  + "'" + guess + "'" + " :(");
                if (failedGuesses == hangmen.size()-1) {System.out.println("Last Chance!!");}
            }
            guessedLetters.add(guess);

            //does the guessed term equal the og term (have they won)
            if (guessedTerm.toString().equals(term)) {
                System.out.println("Term " + "\"" + term + "\"" + " guessed");
                win = true;
                break;
            }


            System.out.println(guessedTerm);
        }
        //win or lose
        if (win) {System.out.println("Win!!");}
        else {System.out.println("You lost!! The term was " + "\"" + term + "\"");}

    }

    public static void main(String[] args) {

        HangmanGame hangman = new HangmanGame();
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {

            hangman.play();

            while (true) {

                System.out.println("Play again? (y/n)");
                String userInput = scanner.nextLine();

                if (Objects.equals(userInput, "y")) {
                    playAgain = true;
                    break;
                }
                if (Objects.equals(userInput, "n")) {
                    playAgain = false;
                    break;
                }
            }
        } while (playAgain);

    }

}
