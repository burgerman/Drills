package com.wil.practice.datastructure.queue;

import javax.ws.rs.NotSupportedException;
import java.util.*;

public class CircularQueue<E> implements Queue<E> {
    private E[] data;
    private int head, tail;

    public static void main(String[] args) {
        CircularQueue cqueue = new CircularQueue();
        int res = 6 & 7;

    }

    public CircularQueue(int cap) {
        if(cap<=16) {
            cap = 16;
        } else {
            int initialCapacity = cap-1;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            cap = initialCapacity+1;
        }
        this.data = (E[])new Object[cap];
        head = 0;
        tail = 0;
    }

    public CircularQueue() {
        this(16);
    }

    @Override
    public int size() {
        if(head==tail) {
            return 0;
        } else {
            return (tail - head) & (data.length - 1);
        }
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        int mask = data.length - 1;
        int i = head;
        Object x;
        while ( (x = data[i]) != null) {
            if (o.equals(x)) {
                return true;
            }
            i = (i + 1) & mask;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = head;
            private int fence = tail;
            private int lastRet = -1;
            @Override
            public boolean hasNext() {
                return cursor != fence;
            }

            @Override
            public E next() {
                if (cursor == fence)
                    throw new NoSuchElementException();
                @SuppressWarnings("unchecked")
                E result = (E) data[cursor];
                // This check doesn't catch all possible comodifications,
                // but does catch the ones that corrupt traversal
                if (tail != fence || result == null)
                    throw new ConcurrentModificationException();
                lastRet = cursor;
                cursor = (cursor + 1) & (data.length - 1);
                return result;
            }
        };
    }


    private <T> T[] copyElements(T[] a) {
        if (head < tail) {
            System.arraycopy(data, head, a, 0, size());
        } else if (head > tail) {
            int headPortionLen = data.length - head;
            System.arraycopy(data, head, a, 0, headPortionLen);
            System.arraycopy(data, 0, a, headPortionLen, tail);
        }
        return a;
    }

    @Override
    public Object[] toArray() {
        return copyElements(new Object[size()]);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        E[] tmp = data;
        data = (E[]) a;
        T[] res = (T[])copyElements(new Object[size()]);
        data = tmp;
        return res;
    }

    private void resize() {
        assert head == tail;
        int p = head;
        int n = data.length;
        int r = n - p; // number of elements to the right of p
        int newCapacity = n << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException("Sorry, circular queue too big");
        }
        Object[] a = new Object[newCapacity];
        System.arraycopy(data, p, a, 0, r);
        System.arraycopy(data, 0, a, r, p);
        data = (E[])a;
        head = 0;
        tail = n;
    }

    private void contract() {
        int newCap = data.length>>1;
        data = Arrays.copyOf(data, newCap);
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        tail = (tail + 1) & (data.length - 1);
        if(size()!=0 && tail == head) {
            resize();
        }
        data[tail] = e;
        return true;
    }

    private void checkInvariants() {
        assert data[tail] == null;
        assert head == tail ? data[head] == null :
                (data[head] != null &&
                        data[(tail - 1) & (data.length - 1)] != null);
        assert data[(head - 1) & (data.length - 1)] == null;
    }

    private boolean delete(int i) {
        checkInvariants();
        final Object[] elements = this.data;
        final int mask = elements.length - 1;
        final int h = head;
        final int t = tail;
        final int front = (i - h) & mask;
        final int back  = (t - i) & mask;

        // Invariant: head <= i < tail mod circularity
        if (front >= ((t - h) & mask))
            throw new ConcurrentModificationException();

        // Optimize for least element motion
        if (front < back) {
            if (h <= i) {
                System.arraycopy(elements, h, elements, h + 1, front);
            } else { // Wrap around
                System.arraycopy(elements, 0, elements, 1, i);
                elements[0] = elements[mask];
                System.arraycopy(elements, h, elements, h + 1, mask - h);
            }
            elements[h] = null;
            head = (h + 1) & mask;
            return false;
        } else {
            if (i < t) { // Copy the null tail as well
                System.arraycopy(elements, i + 1, elements, i, back);
                tail = t - 1;
            } else { // Wrap around
                System.arraycopy(elements, i + 1, elements, i, mask - i);
                elements[mask] = elements[0];
                System.arraycopy(elements, 1, elements, 0, t);
                tail = (t - 1) & mask;
            }
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        if(isEmpty()) {
            throw new IllegalStateException("Circular Queue Empty");
        } else if(o==null) {
            throw new IllegalArgumentException("Object Null");
        }

        if (o == null)
            return false;
        int mask = data.length - 1;
        int i = head;
        Object x;
        while ( (x = data[i]) != null) {
            if (o.equals(x)) {
                delete(i);
                if(size()<(data.length>>1)) {
                    contract();
                }
                return true;
            }
            i = (i + 1) & mask;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean res = true;
        for (Object o : c) {
            res &= contains(o);
        }
        return res;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = true;
        for (E e : c) {
            res &= add(e);
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = true;
        for (Object o : c) {
            res &= remove(o);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotSupportedException("This method is not supported");
    }

    @Override
    public void clear() {
        throw new NotSupportedException("This method is not supported");
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        @SuppressWarnings("unchecked")
        E result = data[head];
        // Element is null if deque empty
        if (result == null) {
            return null;
        }
        data[head] = null;     // Must null out slot
        head = (head + 1) & (data.length - 1);
        return result;
    }

    @Override
    public E poll() {
        int t = (tail - 1) & (data.length - 1);
        @SuppressWarnings("unchecked")
        E result = (E) data[t];
        if (result == null) {
            return null;
        }
        data[t] = null;
        tail = t;
        return result;
    }

    /**
     * Return first element
     * @return
     */
    @Override
    public E element() {
        if(!isEmpty())
        {
            return data[head];
        } else {
            throw new IllegalStateException("Queue empty");
        }
    }

    /**
     * Return Last Element
     * @return
     */
    @Override
    public E peek() {
        if(!isEmpty())
        {
            return data[tail];
        } else {
            throw new IllegalStateException("Queue empty");
        }
    }
}
