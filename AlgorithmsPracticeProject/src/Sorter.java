/**
 * Created by Jonathan_Sterling on 12/11/2014.
 */
public class Sorter {
    public Sorter(){}

    public int[] bubbleSort(int[] input){
        for(int maxIndexToCheck = input.length - 1; maxIndexToCheck > 0; maxIndexToCheck--){
            for(int leftPos = 0; leftPos < maxIndexToCheck; leftPos++){
                if(input[leftPos] > input[leftPos+1]){
                    int temp = input[leftPos];
                    input[leftPos] = input[leftPos+1];
                    input[leftPos+1] = temp;
                }
            }
        }

        return input;
    }

    public int[] selectionSort(int[] input){
        for(int offset = 0; offset < input.length; offset++){
            int indexOfSmallest = offset;
            for(int indexChecking = offset; indexChecking < input.length; indexChecking++){
                if(input[indexChecking] < input[indexOfSmallest]){
                    indexOfSmallest = indexChecking;
                }
            }
            int temp = input[offset];
            input[offset] = input[indexOfSmallest];
            input[indexOfSmallest] = temp;
        }

        return input;
    }

    public int[] insertionSort(int[] input) {
        for (int j = 1; j < input.length; j++){
            int numToInsert = input[j];

            int i;
            for(i = j - 1; (i >= 0) && (input[i] > numToInsert); i--){
                input[i + 1] = input[i];
            }
            input[i+1] = numToInsert;
        }

        return input;
    }

}
