
public class Palindrome {
    /** A class for palindrome operations. */

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
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
        LinkedListDeque<Character> deque = (LinkedListDeque<Character>) wordToDeque(word);
        LinkedListDeque<Character> deque2 = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Character letter = word.charAt(i);
            deque2.addFirst(letter);
        }
        for (int i = 0; i < word.length(); i++) {
            if (deque.removeLast() != deque2.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        LinkedListDeque<Character> deque = (LinkedListDeque<Character>) wordToDeque(word);
        if ((word.length() % 2) != 0) {
            for (int i = 0; i < (word.length() - 1) % 2; i++) {
                if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                    return false;
                }
            }
            return true;
        } else {
            for (int i = 0; i < word.length() % 2; i++) {
                if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                    return false;
                }
            }
            return true;
        }
    }

}
