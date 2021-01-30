
public class Palindrome {
    /** A class for palindrome operations. */
    private static class LinkedListDeque<T> implements Deque<T> {

        private class StuffNode {
            T items;
            StuffNode next;
            StuffNode prev;

            private StuffNode(T item, StuffNode p, StuffNode n) {
                items = item;
                prev = p;
                next = n;
            }
        }

        private StuffNode sentinel;
        private StuffNode last;
        private int size;

        LinkedListDeque() {
            sentinel = new StuffNode(null, null, null);
            last = new StuffNode(null, null, null);
            sentinel.next = last;
            last.prev = sentinel;
            size = 0;
        }

        @Override
        public void addFirst(T item) {
            sentinel.next.prev = new StuffNode(item, sentinel, sentinel.next);
            sentinel.next = sentinel.next.prev;
            size += 1;
        }

        @Override
        public void addLast(T item) {
            last.prev.next = new StuffNode(item, last.prev, last);
            last.prev = last.prev.next;
            size += 1;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void printDeque() {
            StuffNode p = sentinel.next;
            while (p.next != null) {
                System.out.println(p.items);
                p = p.next;
            }
        }

        @Override
        public T removeFirst() {
            if (size == 0) {
                return null;
            }
            StuffNode temp = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return temp.items;
        }

        @Override
        public T removeLast() {
            if (size == 0) {
                return null;
            }
            StuffNode temp = last.prev;
            last.prev = last.prev.prev;
            last.prev.next = last;
            size -= 1;
            return temp.items;

        }

        @Override
        public T get(int index) {
            StuffNode temp = sentinel.next;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.items;
        }

        public T getRecursive(int index) {
            return getRecursiveHelper(index, sentinel.next).items;
        }

        private StuffNode getRecursiveHelper(int index, StuffNode n) {
            if (index == 0) {
                return n;
            }
            return getRecursiveHelper(index - 1, n.next);
        }
    }


    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Character letter = word.charAt(i);
            deque.addLast(letter);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word.equals("")) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        int times = word.length();
        if ((times % 2) != 0) {
            for (int i = 0; i < ((times - 1) / 2); i++) {
                if (deque.removeFirst() != deque.removeLast()) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < times / 2; i++) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if ((word.length() % 2) != 0) {
            for (int i = 0; i < (word.length() - 1) / 2; i++) {
                if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                    return false;
                }
            }
            return true;
        } else {
            for (int i = 0; i < word.length() / 2; i++) {
                if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                    return false;
                }
            }
            return true;
        }
    }

}
