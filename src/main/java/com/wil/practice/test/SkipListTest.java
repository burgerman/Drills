package com.wil.practice.test;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class SkipListTest {

    /**
     * Special value used to identify base-level header
     */
    private static final Object BASE_HEADER = new Object();

    public static void main(String[] args) {

        ConcurrentSkipListMap<String, Integer> cslm = new ConcurrentSkipListMap();
        cslm.put("a",1);
        cslm.put("b",2);
        cslm.put("c",3);
        cslm.put("d",4);
        cslm.put("e",5);
        cslm.put("f",6);
        System.out.println("descendingKeySet: "+cslm.descendingKeySet());
        System.out.println("descendingMap: "+cslm.descendingMap());
        System.out.println("firstEntry: "+cslm.firstEntry());
        System.out.println("lastEntry: "+cslm.lastEntry());
        System.out.println("navigableKeySet: "+cslm.navigableKeySet());
    }

    static final class Node<K,V> {
        final K key;
        volatile Object value;
        volatile SkipListTest.Node<K,V> next;

        /**
         * Creates a new regular node.
         */
        Node(K key, Object value, SkipListTest.Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Creates a new marker node. A marker is distinguished by
         * having its value field point to itself.  Marker nodes also
         * have null keys, a fact that is exploited in a few places,
         * but this doesn't distinguish markers from the base-level
         * header node (head.node), which also has a null key.
         */
        Node(SkipListTest.Node<K,V> next) {
            this.key = null;
            this.value = this;
            this.next = next;
        }

        /**
         * compareAndSet value field
         */
        boolean casValue(Object cmp, Object val) {
            return UNSAFE.compareAndSwapObject(this, valueOffset, cmp, val);
        }

        /**
         * compareAndSet next field
         */
        boolean casNext(SkipListTest.Node<K,V> cmp, SkipListTest.Node<K,V> val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        /**
         * Returns true if this node is a marker. This method isn't
         * actually called in any current code checking for markers
         * because callers will have already read value field and need
         * to use that read (not another done here) and so directly
         * test if value points to node.
         *
         * @return true if this node is a marker node
         */
        boolean isMarker() {
            return value == this;
        }

        /**
         * Returns true if this node is the header of base-level list.
         * @return true if this node is header node
         */
        boolean isBaseHeader() {
            return value == BASE_HEADER;
        }

        /**
         * Tries to append a deletion marker to this node.
         * @param f the assumed current successor of this node
         * @return true if successful
         */
        boolean appendMarker(SkipListTest.Node<K,V> f) {
            return casNext(f, new SkipListTest.Node<K,V>(f));
        }

        /**
         * Helps out a deletion by appending marker or unlinking from
         * predecessor. This is called during traversals when value
         * field seen to be null.
         * @param b predecessor
         * @param f successor
         */
        void helpDelete(SkipListTest.Node<K,V> b, SkipListTest.Node<K,V> f) {
            /*
             * Rechecking links and then doing only one of the
             * help-out stages per call tends to minimize CAS
             * interference among helping threads.
             */
            if (f == next && this == b.next) {
                if (f == null || f.value != f) // not already marked
                    casNext(f, new SkipListTest.Node<K,V>(f));
                else
                    b.casNext(this, f.next);
            }
        }

        /**
         * Returns value if this node contains a valid key-value pair,
         * else null.
         * @return this node's value if it isn't a marker or header or
         * is deleted, else null
         */
        V getValidValue() {
            Object v = value;
            if (v == this || v == BASE_HEADER)
                return null;
            @SuppressWarnings("unchecked") V vv = (V)v;
            return vv;
        }

        /**
         * Creates and returns a new SimpleImmutableEntry holding current
         * mapping if this node holds a valid value, else null.
         * @return new entry or null
         */
        AbstractMap.SimpleImmutableEntry<K,V> createSnapshot() {
            Object v = value;
            if (v == null || v == this || v == BASE_HEADER)
                return null;
            @SuppressWarnings("unchecked") V vv = (V)v;
            return new AbstractMap.SimpleImmutableEntry<K,V>(key, vv);
        }

        // UNSAFE mechanics

        private static final sun.misc.Unsafe UNSAFE;
        private static final long valueOffset;
        private static final long nextOffset;

        static {
            try {
                UNSAFE = sun.misc.Unsafe.getUnsafe();
                Class<?> k = SkipListTest.Node.class;
                valueOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("value"));
                nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

}
