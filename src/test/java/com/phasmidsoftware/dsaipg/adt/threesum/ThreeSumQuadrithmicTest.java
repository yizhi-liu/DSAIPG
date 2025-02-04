package com.phasmidsoftware.dsaipg.adt.threesum;

import com.phasmidsoftware.dsaipg.util.Stopwatch;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class ThreeSumQuadrithmicTest {

    /**
     * Test case for getTriple method when the array contains a valid triple.
     */
    @Test
    public void testGetTriple_ValidTriple() {
        int[] inputArray = {-1, 0, 1, 2, -1, -4};
        Arrays.sort(inputArray); // Ensuring binarySearch works on a sorted array
        ThreeSumQuadrithmic threeSum = new ThreeSumQuadrithmic(inputArray);

        Triple result = threeSum.getTriple(2, 3); // Assuming index 2 = -1, index 3 = 0
        assertNotNull(result);
        assertEquals(0, result.sum());
        assertEquals(new Triple(-1, 0, 1), result);
    }

    /**
     * Test case for getTriple method when no valid triple exists.
     */
    @Test
    public void testGetTriple_NoTripleExists() {
        int[] inputArray = {1, 2, 3, 4, 5};
        Arrays.sort(inputArray);
        ThreeSumQuadrithmic threeSum = new ThreeSumQuadrithmic(inputArray);

        Triple result = threeSum.getTriple(0, 1); // Indices do not form a valid triple
        assertNull(result);
    }

    /**
     * Test case for getTriple method when negative numbers are involved.
     */
    @Test
    public void testGetTriple_WithNegatives() {
        int[] inputArray = {-5, -2, 0, 4, 6, -2};
        Arrays.sort(inputArray);
        ThreeSumQuadrithmic threeSum = new ThreeSumQuadrithmic(inputArray);

        Triple result = threeSum.getTriple(1, 2); // Assuming a valid triple exists
        assertNotNull(result);
        assertEquals(0, result.sum());
        assertEquals(new Triple(-2, -2, 4), result);
    }


    @Test
    public void Benchmark_for_three() {
        int m = 10; //repeat for m times find average
        int n = 100; // size of list

        int[] record = new int[m];
        int[] inputsize = {n, n*2, n*4, n*8, n*16, n*32};
        final Random random = new Random();

        System.out.println( m + " times of Quadrithmic benchmark: ");
        for (int j : inputsize) {
            for( int x = 0 ; x < m; x++){
                int[] xs = new int[j];
                for (int i = 0; i < xs.length; i++) xs[i] = random.nextInt();
                try (Stopwatch target = new Stopwatch()) {
                    target.lap();
                    Arrays.sort(xs);
                    ThreeSumQuadrithmic threeSum = new ThreeSumQuadrithmic(xs);

                    Triple[] result = threeSum.getTriples(); // Assuming a valid triple exists
                    record[x] = (int)target.lap();
                }
            }
            int sum = 0;
            for (int num : record) {
                sum += num;
            }
            System.out.print("size: " + j );
            System.out.println(",  average time: " + sum/m + " milliseconds ");
        }

        System.out.println( m + " times of Quadratic benchmark: ");
        for (int j : inputsize) {
            for( int x = 0 ; x < m; x++){
                int[] xs = new int[j];
                for (int i = 0; i < xs.length; i++) xs[i] = random.nextInt();
                try (Stopwatch target = new Stopwatch()) {
                    target.lap();
                    Arrays.sort(xs);
                    ThreeSumQuadratic threeSum = new ThreeSumQuadratic(xs);

                    Triple[] result = threeSum.getTriples(); // Assuming a valid triple exists
                    record[x] = (int)target.lap();
                }
            }
            int sum = 0;
            for (int num : record) {
                sum += num;
            }
            System.out.print("size: " + j );
            System.out.println(",  average time: " + sum/m + " milliseconds ");
        }

        System.out.println( m + " times of Cubic benchmark: ");
        for (int j : inputsize) {
            for( int x = 0 ; x < m; x++){
                int[] xs = new int[j];
                for (int i = 0; i < xs.length; i++) xs[i] = random.nextInt();
                try (Stopwatch target = new Stopwatch()) {
                    target.lap();
                    Arrays.sort(xs);
                    ThreeSumCubic threeSum = new ThreeSumCubic(xs);

                    Triple[] result = threeSum.getTriples(); // Assuming a valid triple exists
                    record[x] = (int)target.lap();
                }
            }
            int sum = 0;
            for (int num : record) {
                sum += num;
            }
            System.out.print("size: " + j );
            System.out.println(",  average time: " + sum/m + " milliseconds ");
        }
    }

}