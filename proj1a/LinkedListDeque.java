

public class LinkedListDeque<T> {

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

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        last = new StuffNode(null, null, null);
        sentinel.next = last;
        last.prev = sentinel;
        size = 0;
    }

//    public LinkedListDeque(T item) {
//        sentinel = new StuffNode(null, null, null);
//        last = new StuffNode(item, null, null);
//        sentinel.next = last;
//        last.prev = sentinel;
//        size = 1;
//    }
//
//    public LinkedListDeque(LinkedListDeque<T> other) {
//        sentinel = new StuffNode(null, null, null);
//        last = sentinel;
//        size = 0;
//
//        for (int i = 0; i < other.size(); i++) {
//            addLast(other.get(i));
//        }
//    }

    public void addFirst(T item) {
        sentinel.next.prev = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    public void addLast(T item) {
        last.prev.next = new StuffNode(item, last.prev, last);
        last.prev = last.prev.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p.next != null) {
            System.out.println(p.items);
            p = p.next;
        }
    }

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

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
//
//        l1.addFirst(1);
//        l1.removeLast();
//        l1.addFirst(7);
//        l1.get(0);
//    }
}

