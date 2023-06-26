package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> a = new AListNoResizing<Integer>();
        BuggyAList<Integer> b = new BuggyAList<Integer>();
        a.addLast(4);
        b.addLast(4);
        a.addLast(5);
        b.addLast(5);
        a.addLast(6);
        b.addLast(6);
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeCorrect = correct.size();
                int sizeBroken = broken.size();
                assertEquals(sizeCorrect, sizeBroken);
                System.out.println("size()");
            } else if (operationNumber == 2) {
                // getLast
                if (correct.size() == 0) {
                    continue;
                }
                int lastCorrect = correct.getLast();
                int lastBroken = broken.getLast();
                assertEquals(lastCorrect, lastBroken);
                System.out.println("getLast()");
            } else {
                // removeLast
                if (correct.size() == 0) {
                    continue;
                }
                int lastCorrect = correct.removeLast();
                int lastBroken = broken.removeLast();
                assertEquals(lastCorrect, lastBroken);
                System.out.println("removeLast()");
            }
        }
    }
}
