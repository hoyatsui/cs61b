public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double REFACTOR = 2;

    /* create an empty list.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }


    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextLast > nextFirst) {
            System.arraycopy(items, nextFirst, a, nextFirst, size);
        } else {
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextFirst, a, nextFirst + capacity - items.length,
                    size - nextLast);
            nextFirst = nextFirst + capacity - items.length;
        }

        items = a;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize((int) (size * REFACTOR));
        }
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        items[nextFirst] = x;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize((int) (size * REFACTOR));
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

    @Override
    public T get(int index) {
        if ((nextFirst + index) < items.length) {
            return items[index + nextFirst];
        } else {
            index = index - items.length + nextFirst;

            return items[index];
        }
    }

    @Override
    public  T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = items[nextFirst];
        items[nextFirst] = null;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        size -= 1;
        if ((items.length / 4 >= size) && items.length > 16) {
            resize(items.length / 2);
        }
        return temp;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
            T temp = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
            size -= 1;
            if ((items.length / 4 >= size) && items.length > 16) {
                resize(items.length / 2);
            }
            return temp;
        } else {
            T temp = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
            size -= 1;
            if ((items.length / 4 >= size) && items.length > 16) {
                resize(items.length / 2);
            }
            return  temp;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
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