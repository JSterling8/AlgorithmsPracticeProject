import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jonathan_Sterling on 12/11/2014.
 */
public class SorterTest {
    private Sorter sorter;
    private int[] testNumbers;

    @Before
    public void setUp(){
        sorter = new Sorter();

        testNumbers = new int[100];
        testNumbers[0] = Integer.MIN_VALUE;
        testNumbers[1] = Integer.MIN_VALUE + 1;

        Random random = new Random();
        for(int i = 2; i < testNumbers.length; i++){
            testNumbers[i] = random.nextInt();
        }
    }

    @Test
    public void testBubbleSort(){
        assertListIsSorted(sorter.bubbleSort(testNumbers));
    }

    @Test
    public void testSelectionSort(){
        assertListIsSorted(sorter.selectionSort(testNumbers));
    }

    @Test
    public void testInsertionSort(){
        assertListIsSorted(sorter.insertionSort(testNumbers));
    }

    private void assertListIsSorted(int[] sortedNumbers) {
        assertEquals("The sorted array is a different size than the input array.",
                testNumbers.length,
                sortedNumbers.length);

        for(int j = 0; j < sortedNumbers.length - 1; j++){
            System.out.println(j + ": " +sortedNumbers[j]);
            assertTrue("The list is not sorted.", sortedNumbers[j] <= sortedNumbers[j + 1]);
        }
        System.out.println((sortedNumbers.length - 1) + ": " + sortedNumbers[sortedNumbers.length-1]);
    }
}
