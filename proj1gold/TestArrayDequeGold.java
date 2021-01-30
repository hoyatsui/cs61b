import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testStudentArrayAddFirstRemoveLast() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        // AddFirst
        for (int i = 0; i < 10; i++) {
            Integer number = StdRandom.uniform(0, 10);
            sad.addFirst(number);
            ads.addFirst(number);
        }
        for(int i = 0; i < 10; i++) {
            Integer actual = sad.get(i);
            Integer expected = ads.get(i);
            assertEquals("\nError with addFirst method in StudentArrayDeque!\nWe need: " + expected
                    + ", and we got: " + actual, expected, actual);
        }

        // Remove Last
        for(int i = 0; i < 10; i++) {
            Integer actual = sad.removeLast();
            Integer expected = ads.removeLast();
            assertEquals("\nError with removeLast method in StudentArrayDeque!\nWe need: " + expected
                            + ", and we got: " + actual, expected, actual);
        }

    }

    @Test
    public void testStudentArrayAddLastRemoveFirst() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i++) {
            Integer number = StdRandom.uniform(0, 10);
            sad.addLast(number);
            ads.addLast(number);
        }
        for(int i = 0; i < 10; i++) {
            Integer actual = sad.get(i);
            Integer expected = ads.get(i);
            assertEquals("\nError with addLast method in StudentArrayDeque!\nWe need: " + expected
                    + ", and we got: " + actual, expected, actual);
        }

        for(int i = 0; i < 10; i++) {
            Integer actual = sad.removeFirst();
            Integer expected = ads.removeFirst();
            assertEquals("\nError with removeFirst method in StudentArrayDeque!\nWe need: " + expected
                    + ", and we got: " + actual, expected, actual);
        }
    }
}
