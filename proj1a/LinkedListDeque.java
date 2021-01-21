public class LinkedListDeque<T> {

    public class StuffNode {
        T items;
        StuffNode next;
        StuffNode prev;

        public StuffNode(T item, StuffNode f, StuffNode n) {
            items = item;
            prev = f;
            next = n;
        }
    }

    private StuffNode sentinel;
    private StuffNode last;
    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        last = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new StuffNode(null, null, null);
        last = new StuffNode(item, null, null);
        sentinel.next = last;
        last.prev = sentinel;
        size = 1;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new StuffNode(null, null, null);
        last = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    public void addFirst(T item) {
        sentinel.next = new StuffNode(item, sentinel, sentinel.next);
        last = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        last.next = new StuffNode(item, last, null);
        last = last.next;
        size += 1;
    }

    public boolean isEmpty() {
        return sentinel == last;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != null) {
            System.out.println(p.items);
            p = p.next;
        }
    }

    public T removeFirst() {
        StuffNode temp = sentinel.next;
        sentinel = sentinel.next;
        size -= 1;
        return temp.items;
    }

    public T removeLast() {
        StuffNode temp = last;
        last = last.prev;
        size -= 1;
        return temp.items;

    }

    public T get(int index) {
        StuffNode temp = sentinel;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.items;

    }
}
