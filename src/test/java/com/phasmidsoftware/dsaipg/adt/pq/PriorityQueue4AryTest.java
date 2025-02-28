package com.phasmidsoftware.dsaipg.adt.pq;

import com.phasmidsoftware.dsaipg.util.PrivateMethodTester;
import com.phasmidsoftware.dsaipg.util.Stopwatch;
import org.junit.Test;

import java.util.*;

import static java.util.Collections.shuffle;
import static org.junit.Assert.*;

@SuppressWarnings("ConstantConditions")
public class PriorityQueue4AryTest {

    @Test
    public void testInverted1a() {
        String[] binHeap = new String[3];
        binHeap[1] = "A";
        binHeap[2] = "B";
        boolean max = false;
        Iterable<String> pq = new PriorityQueue4Ary<>(max, binHeap, 1, 2, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(max, tester.invokePrivate("inverted", 1, 2));
    }

    @Test
    public void testInverted1b() {
        String[] binHeap = new String[3];
        binHeap[0] = "A";
        binHeap[1] = "B";
        boolean max = false;
        Iterable<String> pq = new PriorityQueue4Ary<>(max, binHeap, 0, 2, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(max, tester.invokePrivate("inverted", 0, 1));
    }

    @Test
    public void testInverted2() {
        String[] binHeap = new String[3];
        binHeap[1] = "A";
        binHeap[2] = "B";
        boolean max = true;
        Iterable<String> pq = new PriorityQueue4Ary<>(max, binHeap, 1, 2, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(max, tester.invokePrivate("inverted", 1, 2));
    }

    @Test
    public void testSwimUp0() {
        String[] binHeap = new String[3];
        String a = "A";
        String b = "B";
        binHeap[0] = a;
        binHeap[1] = b;
        // Create PQ which uses the 0th index.
        Iterable<String> pq = new PriorityQueue4Ary<>(true, binHeap, 0, 2, Comparator.comparing(String::toString), true);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(a, tester.invokePrivate("peek", 0));
        tester.invokePrivate("swimUp", 1);
        assertEquals(b, tester.invokePrivate("peek", 0));
    }

    @Test
    public void testSwimUp1() {
        String[] binHeap = new String[3];
        String a = "A";
        String b = "B";
        binHeap[1] = a;
        binHeap[2] = b;
        // Create PQ which does not use the 0th index.
        Iterable<String> pq = new PriorityQueue4Ary<>(true, binHeap, 1, 2, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(a, tester.invokePrivate("peek", 1));
        tester.invokePrivate("swimUp", 2);
        assertEquals(b, tester.invokePrivate("peek", 1));
    }

    @Test
    public void testSwimUp2() {
        String[] binHeap = new String[7];
        binHeap[1] = "Z";
        binHeap[2] = "A";
        binHeap[3] = "B";
        binHeap[4] = "C";
        binHeap[5] = "D";
        binHeap[6] = "E";
        // Create PQ as a max-heap.
        Iterable<String> pq = new PriorityQueue4Ary<>(true, binHeap, 1, 6, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        tester.invokePrivate("swimUp", 6); // Swim "E" upward.
        assertEquals("E", tester.invokePrivate("peek", 2)); // Peek at root.
    }

    @Test
    public void testSwimUp3() {
        String[] binHeap = new String[7];
        binHeap[1] = "D";
        binHeap[2] = "C";
        binHeap[3] = "E";
        binHeap[4] = "B";
        binHeap[5] = "F";
        binHeap[6] = "A";
        // Create PQ as a min-heap.
        Iterable<String> pq = new PriorityQueue4Ary<>(false, binHeap, 1, 6, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        tester.invokePrivate("swimUp", 6); // Swim "A" upward.
        assertEquals("A", tester.invokePrivate("peek", 1)); // Peek at root.
    }

    @Test
    public void testSink0a() {
        String[] binHeap = new String[3];
        String a = "A";
        String b = "B";
        String c = "C";
        binHeap[0] = b;
        binHeap[1] = c;
        binHeap[2] = a;
        Iterable<String> pq = new PriorityQueue4Ary<>(true, binHeap, 0, 3, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        tester.invokePrivate("sink", 0);
        assertEquals(c, tester.invokePrivate("peek", 0));
        assertEquals(a, tester.invokePrivate("peek", 2));
    }

    @Test
    public void testSink0b() {
        String[] binHeap = new String[4];
        String a = "A";
        String b = "B";
        String c = "C";
        binHeap[1] = a;
        binHeap[2] = b;
        binHeap[3] = c;
        Iterable<String> pq = new PriorityQueue4Ary<>(true, binHeap, 1, 3, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        tester.invokePrivate("sink", 1);
        assertEquals(c, tester.invokePrivate("peek", 1));
        assertEquals(a, tester.invokePrivate("peek", 3));
    }

    @Test
    public void testSink1() {
        String[] binHeap = new String[6];
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        binHeap[1] = a;
        binHeap[2] = b;
        binHeap[3] = c;
        binHeap[4] = d;
        binHeap[5] = e;
        Iterable<String> pq = new PriorityQueue4Ary<>(true, binHeap, 1, 5, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        tester.invokePrivate("sink", 1);
        assertEquals(e, tester.invokePrivate("peek", 1));
        assertEquals(a, tester.invokePrivate("peek", 5));
    }

    @Test
    public void testGive1() {
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(10, Comparator.comparing(String::toString));
        String key = "A";
        pq.give(key);
        assertEquals(1, pq.size());
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(key, tester.invokePrivate("peek", 0));
    }

    @Test
    public void testGive2() {
        // Test that we can comfortably give more elements than the the PQ has capacity for
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(1, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        String key = "A";
        pq.give(null); // This will never survive so it might as well be null
        assertEquals(1, pq.size());
        assertNull(tester.invokePrivate("peek", 0));
        pq.give(key);
        assertEquals(1, pq.size());
        assertEquals(key, tester.invokePrivate("peek", 0));
    }

    @Test
    public void testTake1() throws PQException {
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(10, Comparator.comparing(String::toString));
        String key = "A";
        pq.give(key);
        assertEquals(key, pq.take());
        assertTrue(pq.isEmpty());
    }

    @Test
    public void testTake2() throws PQException {
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(10, Comparator.comparing(String::toString));
        String a = "A";
        String b = "B";
        pq.give(a);
        pq.give(b);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(a, tester.invokePrivate("peek", 1));
        assertEquals(b, tester.invokePrivate("peek", 0));
        assertEquals(b, pq.take());
        assertEquals(a, pq.take());
        assertTrue(pq.isEmpty());

    }

    @Test(expected = PQException.class)
    public void testTake3() throws PQException {
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(10, Comparator.comparing(String::toString));
        pq.give("A");
        pq.take();
        pq.take();
    }

    @Test
    public void isEmpty() {
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(10, false, Comparator.comparing(String::toString));
        assertTrue(pq.isEmpty());
    }

    @Test
    public void size() throws PQException {
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(10, false, Comparator.comparing(String::toString));
        assertEquals(0, pq.size());
        pq.give("A");
        assertEquals(1, pq.size());
        pq.take();
        assertEquals(0, pq.size());
    }

    @Test
    public void doTake01() throws PQException {
        String[] binHeap = new String[5];
        binHeap[0] = "A";
        binHeap[1] = "B";
        binHeap[2] = "C";
        binHeap[3] = "D";
        binHeap[4] = "E";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(false, binHeap, 0, 5, Comparator.comparing(String::toString), false);
        pq.doTake(pq::snake);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals("B", tester.invokePrivate("peek", 0));
    }

    @Test
    public void doTake02() throws PQException {
        String[] binHeap = new String[5];
        binHeap[0] = "C";
        binHeap[1] = "A";
        binHeap[2] = "B";
        binHeap[3] = "D";
        binHeap[4] = "E";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(true, binHeap, 0, 5, Comparator.comparing(String::toString), false);
        pq.doTake(pq::sink);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals("E", tester.invokePrivate("peek", 0));
    }

    @Test
    public void doTake11() throws PQException {
        String[] binHeap = new String[6];
        binHeap[1] = "A";
        binHeap[2] = "B";
        binHeap[3] = "C";
        binHeap[4] = "D";
        binHeap[5] = "E";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(false, binHeap, 1, 5, Comparator.comparing(String::toString), false);
        pq.doTake(pq::snake);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals("B", tester.invokePrivate("peek", 1));
    }

    @Test
    public void doTake12() throws PQException {
        String[] binHeap = new String[6];
        binHeap[1] = "C";
        binHeap[2] = "A";
        binHeap[3] = "B";
        binHeap[4] = "D";
        binHeap[5] = "E";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(true, binHeap, 1, 5, Comparator.comparing(String::toString), false);
        pq.doTake(pq::sink);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals("E", tester.invokePrivate("peek", 1));
    }

    @Test
    public void iterator0() {
        String[] binHeap = new String[3];
        binHeap[0] = "C";
        binHeap[1] = "B";
        binHeap[2] = "D";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(true, binHeap, 0, 3, Comparator.comparing(String::toString), false);
        assertEquals(3, pq.size());
        Iterator<String> iterator = pq.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(binHeap[0], iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(binHeap[1], iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(binHeap[2], iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(3, pq.size());
    }

    @Test
    public void iterator1() {
        String[] binHeap = new String[4];
        binHeap[1] = "C";
        binHeap[2] = "B";
        binHeap[3] = "D";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(true, binHeap, 1, 3, Comparator.comparing(String::toString), false);
        assertEquals(3, pq.size());
        Iterator<String> iterator = pq.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(binHeap[1], iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(binHeap[2], iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(binHeap[3], iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(3, pq.size());
    }

    @Test
    public void testGetMax() {
        Iterable<String> pq = new PriorityQueue4Ary<>(10, false, Comparator.comparing(String::toString));
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(false, tester.invokePrivate("getMax"));
    }

    @Test
    public void testTake4() throws PQException {
        String[] binHeap = new String[5];
        binHeap[1] = "D";
        binHeap[2] = "A";
        binHeap[3] = "C";
        binHeap[4] = "B";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(true, binHeap, 1, 4, Comparator.comparing(String::toString), false);
        String takenValue = pq.take();
        assertEquals("D", takenValue); // Ensure the max-heap returns the largest element.
        assertEquals(3, pq.size()); // Ensure size is reduced after take.
    }

    @Test
    public void testTake5() throws PQException {
        String[] binHeap = new String[5];
        binHeap[1] = "A";
        binHeap[2] = "C";
        binHeap[3] = "B";
        binHeap[4] = "Z";
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(false, binHeap, 1, 4, Comparator.comparing(String::toString), false);
        String takenValue = pq.take();
        assertEquals("A", takenValue); // Ensure the min-heap returns the smallest element.
        assertEquals(3, pq.size()); // Ensure size is reduced after take.
    }

    @Test(expected = PQException.class)
    public void testTake6() throws PQException {
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(5, Comparator.comparing(String::toString));
        pq.take(); // Attempting to take from an empty queue should throw PQException.
    }

    @Test
    public void testPriorityQueueList() throws PQException {
        Integer[] binHeap = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        List<Integer> list = Arrays.asList(binHeap);
        shuffle(list, new Random(1234567890L));
        PriorityQueue4Ary<Integer> pq = new PriorityQueue4Ary<>(list, Integer::compare);
        assertEquals(Integer.valueOf(19), pq.take());
        assertEquals(Integer.valueOf(18), pq.take());
        assertEquals(Integer.valueOf(17), pq.take());
        assertEquals(Integer.valueOf(16), pq.take());
        assertEquals(Integer.valueOf(15), pq.take());
        assertEquals(Integer.valueOf(14), pq.take());
        assertEquals(Integer.valueOf(13), pq.take());
        assertEquals(Integer.valueOf(12), pq.take());
        assertEquals(Integer.valueOf(11), pq.take());
        assertEquals(Integer.valueOf(10), pq.take());
        assertEquals(Integer.valueOf(9), pq.take());
        assertEquals(Integer.valueOf(8), pq.take());
        assertEquals(Integer.valueOf(7), pq.take());
        assertEquals(Integer.valueOf(6), pq.take());
        assertEquals(Integer.valueOf(5), pq.take());
        assertEquals(Integer.valueOf(4), pq.take());
        assertEquals(Integer.valueOf(3), pq.take());
        assertEquals(Integer.valueOf(2), pq.take());
        assertEquals(Integer.valueOf(1), pq.take());
        assertEquals(Integer.valueOf(0), pq.take());
    }

    @Test
    public void testDoHeapifya() throws PQException {
        String[] binHeap = new String[]{null, "C", "D", "G", "E", "B", "F", "A", "H"};
        boolean max = false;
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(max, binHeap, 1, 8, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(7, tester.invokePrivate("doHeapifyStandard", 2));
        assertEquals(2, tester.invokePrivate("doHeapifyStandard", 1));

    }

    @Test
    public void testDoHeapifyb() throws PQException {
        String[] binHeap = new String[]{"C", "D", "A", "E", "B", "F", "A", "H"};
        boolean max = false;
        PriorityQueue4Ary<String> pq = new PriorityQueue4Ary<>(max, binHeap, 0, 8, Comparator.comparing(String::toString), false);
        final PrivateMethodTester tester = new PrivateMethodTester(pq);
        assertEquals(6, tester.invokePrivate("doHeapifyStandard", 1));
        assertEquals(1, tester.invokePrivate("doHeapifyStandard", 0));

    }

    @Test
    public void testBenchmark() throws PQException {

        //call two benchmark test in one
        PriorityQueueTest test = new PriorityQueueTest();
        test.testBenchmark();
        //

        Stopwatch watch = new Stopwatch();
//        watch.lap();
        Random random = new Random();
        int[] sizes = {16000,32000,64000,128000,256000};
        for(int n = 0; n < sizes.length; n ++){
            int size = sizes[n];
            Integer[] randomArray = new Integer[4095+size];
            for(int i = 0; i < 4095+size; i ++){
                randomArray[i] = random.nextInt();
            }
            PriorityQueue4Ary<Integer> basic = new PriorityQueue4Ary<>(4095,1,true, Comparator.comparing(Integer::intValue),false);
            PriorityQueue4Ary<Integer> basic_f = new PriorityQueue4Ary<>(4095,1,true, Comparator.comparing(Integer::intValue),true);
            // warm up
            for(int j = 0; j < 10; j ++){
                basic = new PriorityQueue4Ary<>(4095,1,true, Comparator.comparing(Integer::intValue),false);
                for(int i = 0; i < 4095; i ++){
                    basic.give(randomArray[i]);
                }
                for(int i = 4095; i < 4095+ size; i ++){
                    basic.give(randomArray[i]);
                }
                for(int i = 0; i < 4000; i ++){
                    basic.take();
                }
            }

            for(int j = 0; j < 10; j ++){
                basic_f = new PriorityQueue4Ary<>(4095,1,true, Comparator.comparing(Integer::intValue),true);
                for(int i = 0; i < 4095+ size; i ++){
                    basic_f.give(randomArray[i]);
                }
                for(int i = 0; i < 4000; i ++){
                    basic_f.take();
                }
            }
            // warm up end

            double repeat = 10;
            long totalTime = 0;
            System.out.println("inserting 4-ary for " + size + " elements and delete for 4000 elements");
            System.out.print("Max spilled elements for each repetition: ");
            for(int j = 0; j < repeat; j ++){
                basic = new PriorityQueue4Ary<>(4095,1,true, Comparator.comparing(Integer::intValue),false);
                for(int i = 0; i < 4095+size; i ++){
                    randomArray[i] = random.nextInt();
                }
                for(int i = 0; i < 4095; i ++){
                    basic.give(randomArray[i]);
                }
                watch.lap();
                for(int i = 4095; i < 4095+ size; i ++){
                    basic.give(randomArray[i]);
                }
                for(int i = 0; i < 4000; i ++){
                    basic.take();
                }
                totalTime += watch.lap();
                System.out.print( basic.getMaxSpilled()+ ", " );

            }
            System.out.println();
            System.out.println("4-ary heap: " + totalTime/repeat + "ms");

            totalTime = 0;
            System.out.print("Max spilled elements for each repetition: ");
            for(int j = 0; j < repeat; j ++){
                basic_f = new PriorityQueue4Ary<>(4095,1,true, Comparator.comparing(Integer::intValue),true);
                for(int i = 0; i < 4095+size; i ++){
                    randomArray[i] = random.nextInt();
                }
                for(int i = 0; i < 4095; i ++){
                    basic_f.give(randomArray[i]);
                }
                watch.lap();
                for(int i = 4095; i < 4095+ size; i ++){
                    basic_f.give(randomArray[i]);
                }
                for(int i = 0; i < 4000; i ++){
                    basic_f.take();
                }
                totalTime += watch.lap();
                System.out.print( basic_f.getMaxSpilled()+ ", " );

            }
            //        System.out.println("inserting for " + 4095+"+"+16000 + " elements and delete for 4000 elements");
            System.out.println();
            System.out.println("4-ary heap with Floyd's trick: " + totalTime/repeat + "ms");
            System.out.println();

        }
    }
}
