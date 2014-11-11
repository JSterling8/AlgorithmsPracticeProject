/**
 * This is for the Top Coder 2014 "Code Your Way In" problem.  The solution is my own.  The problem spec is as follows:
 * 
 * Elly and Kris play the following game. In the beginning Kristina thinks of a number between 1 and 1,000,000,000, inclusive. 
 * After that Elly starts trying to guess it. In each round she says a number and Kristina says what is the absolute difference 
 * between the number she has thought of, and the number Elly guessed. Now Elly wonders if the guesses she has already made are 
 * sufficient to uniquely determine Kristina's number.
 * 
 * You are given a int[] guesses and a int[] answers of the same length. For each valid i, in round i of the game (0-based index) 
 * Elly guessed the number guesses[i] and Kristina answered answers[i]. If Kristina's number can be uniquely determined, return 
 * that number. If there are multiple possibilities that are consistent with the current set of guesses and answers, return -1. 
 * If it can be shown that at some point Kristina has lied (some of her answers were inconsistent), return -2.
 * Definition
 * 
 * Class: EllysNumberGuessing
 * Method: getNumber (be sure your method is public)
 * Parameters: int[], int[]
 * Returns: int
 * Method signature: int getNumber(int[] guesses, int[] answers)
 * Constraints
 * 
 * guesses and answers will each contain between 1 and 50 elements, inclusive.
 * guesses and answers will contain the same number of elements.
 * Each element of guesses will be between 1 and 1,000,000,000, inclusive.
 * Each element of answers will be between 1 and 999,999,999, inclusive.
 * Examples
 * 
 * Example #1
 * {600, 594}
 * {6, 12}
 * Returns: 606
 * 
 * Example #2
 * {100, 50, 34, 40}
 * {58, 8, 8, 2}
 * Returns: 42
 * 
 * Example #3
 * {500000, 600000, 700000}
 * {120013, 220013, 79987}
 * Returns: -2
 * 
 * Example #4
 * {500000000}
 * {133742666}
 * Returns: -1
 * 
 * Example #5
 * {76938260, 523164588, 14196746, 296286419, 535893832, 41243148, 364561227, 270003278, 472017422, 367932361, 395758413, 301278456, 186276934, 316343129, 336557549, 52536121, 98343562, 356769915, 89249181, 335191879}
 * {466274085, 20047757, 529015599, 246925926, 7318513, 501969197, 178651118, 273209067, 71194923, 175279984, 147453932, 241933889, 356935411, 226869216, 206654796, 490676224, 444868783, 186442430, 453963164, 208020466}
 * Returns: 543212345
 * 
 * Example #6
 * {42}
 * {42}
 * Returns: 84
 * 
 * Example #7
 * {999900000}
 * {100001}
 * Returns: 999799999
 * 
 * 
 * @author Jonathan Sterling
 *
 */
public class EllysNumberGuessing {
    public int getNumber(int[] guesses, int[] answers){
        int correctAnswer = 0;
        int[] possibleAnswers = new int[2];

        // Get our control variables
        possibleAnswers[0] = guesses[0] - answers[0];
        possibleAnswers[1] = guesses[0] + answers[0];

        // If our two possibles are outside of the valid range, then return -2 (signifying a lie)
        if((possibleAnswers[0] > 1000000000 || possibleAnswers[0] < 1) &&
                (possibleAnswers[1] > 1000000000 || possibleAnswers[1] < 1)){
            return -2;
        }

        if(!isEnoughInfo(guesses, answers)){
            return -1;
        }

        /**
         * If the first possible answer is outside the valid range, then the second must be the correct answer.
         * Conversely, if the second possible answer is outside of the valid range, the first must be the correct answer.
         * 
         * However if they're both in a valid range, we have to check based on the next possible answers to see which is correct.
         */
        if(possibleAnswers[0] < 1){
            correctAnswer = possibleAnswers[1];
        } else if (possibleAnswers[1] > 1000000000){
            correctAnswer = possibleAnswers[0];
        } else {
            correctAnswer = findCorrectAnswer(possibleAnswers, guesses, answers);
        }

        if(correctAnswer == -2 || correctAnswer > 1000000000 || correctAnswer < 1){
            return -2;
        }

        // The correct answer must come up in all other possible answer pairs
        correctAnswer = ensureNoLies(guesses, answers, correctAnswer);

        return correctAnswer;
    }

    public boolean isEnoughInfo(int[] guesses, int[] answers) {
        if(guesses.length == 1){
            if(guesses[0] - answers[0] < 1 && guesses[0] + answers[0] > 1000000000){
                return false;
            }
        }
        boolean allSame = true;
        for(int i = 1; i < guesses.length; i++){
            if(guesses[i] != guesses[i-1] || (guesses[i] == guesses[i-1] && answers[i] != answers[i-1])){
                allSame = false;
                break;
            }
        }

        // The can all be the same, so long as of the two possible answers, one is outside of the valid range and one is inside.
        if(allSame &&
                ((guesses[0] - answers[0] < 1 && guesses[0] + answers[0] <= 1000000000) ||
                        (guesses[0] + answers[0] > 1000000000 && guesses[0] - answers[0] >= 1))){
            return true;
        }

        // If they are all the same, and there are two possible answers for the first guess and answer that are both valid, return false.
        // If they aren't all the same, return true.
        return !allSame;
    }

    /**
     * The correct answer is where one of the first two possible answers lines up with one of the second two possible answers.
     * <b>Caveat:</b> We must ensure none of them are lies though, which is done later on.
     */
    private int findCorrectAnswer(int[] possibleAnswers, int[] guesses, int[] answers) {
        int[] secondPossibleAnswers = new int[2];

        for(int i = 1; i < answers.length; i++){
            secondPossibleAnswers[0] = guesses[i] - answers[i];
            secondPossibleAnswers[1] = guesses[i] + answers[i];

            if(possibleAnswers[0] != secondPossibleAnswers[0] ||
                    possibleAnswers[1] != secondPossibleAnswers[1]){
                break;
            }
        }

        if(secondPossibleAnswers[0] == possibleAnswers[0] || secondPossibleAnswers[0] == possibleAnswers[1]){
            return secondPossibleAnswers[0];
        } else if (secondPossibleAnswers[1] == possibleAnswers[0] || secondPossibleAnswers[1] == possibleAnswers[1]){
            return secondPossibleAnswers[1];
        } else {
            return -2;
        }
    }

    /**
     * The correct answer must be one of the possible answers for all pairs of guesses and answers.
     */
    private int ensureNoLies(int[] guesses, int[] answers, int correctAnswer) {
        for(int i = 1; i < answers.length; i++){
            if((guesses[i] - answers[i] != correctAnswer) &&
                    (guesses[i] + answers[i] != correctAnswer)){
                return -2;
            }
        }
        return correctAnswer;
    }
}