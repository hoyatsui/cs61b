public class OffByN implements CharacterComparator {
    /** A class for off-by-N comparators. */
    private int num;

    public OffByN(int N) {
        num = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == num) {
            return true;
        }
        return false;
    }


}
