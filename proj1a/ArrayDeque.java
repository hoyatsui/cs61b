public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int REFACTOR = 10;

    /* create an empty list.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }

//    public ArrayDeque(ArrayDeque<T> other) {
//        items = (T[]) new Object[8];
//        nextFirst = 3;
//        nextLast = 3;
//        size = 0;
//
//        for (int i = 0; i < other.items.length; i++) {
//            addLast(other.get(i));
//        }
//    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextLast > nextFirst) {
            System.arraycopy(items, nextFirst, a, nextFirst, nextLast - nextFirst);
        } else {
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextFirst, a, nextFirst + capacity - items.length,
                    items.length - nextFirst);
            nextFirst = nextLast + capacity - items.length;
        }

        items = a;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        items[nextFirst] = x;
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        if (nextLast == items.length - 1) {
            items[nextLast] = x;
            nextLast = 0;
        } else {
            items[nextLast] = x;
            nextLast += 1;
        }
        size += 1;
    }

    public T get(int index) {
        if ((nextFirst + index) < items.length) {
            return items[index + nextFirst];
        } else {
            index = index - items.length + nextFirst;

            return items[index];
        }
    }

    public  T removeFirst() {
        T temp = items[nextFirst];
        items[nextFirst] = null;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (nextLast == 0) {
            nextLast = items.length - 1;
            T temp = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
            size -= 1;
            return temp;
        } else {
            T temp = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
            size -= 1;
            return  temp;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        if (size != 0) {
            if (nextLast > nextFirst) {
                for (int i = nextFirst; i < nextLast; i++) {
                    System.out.println(items[i]);
                }
            } else {
                for (int i = nextFirst; i < items.length; i++) {
                    System.out.println(items[i]);
                }
                for (int i = 0; i < nextLast; i++) {
                    System.out.println(items[i]);
                }
            }
        } else {
            System.out.println("List is empty!");
        }
    }

}