package com.wil.practice.algo.lfu;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class LFUCache<K, V> {
    private final int INIT_FREQUENCY = 1;
    private final int capacity;
    private final Map<K, V> cache;
    private final Map<K, Integer> frequencies;
    private final PriorityBlockingQueue<Map.Entry<K, Integer>> minHeap;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>(capacity);
        this.frequencies = new ConcurrentHashMap<>(capacity);
        this.minHeap = new PriorityBlockingQueue<>(capacity, (Comparator.comparingInt(Map.Entry::getValue)));
    }

    public V get(K key) {
        if(!cache.containsKey(key)) {
            return null;
        }
        frequencies.compute(key, (k,v)-> v+=1);
        minHeap.stream().forEach(e->{
            if(e.getKey().equals(key)) {
                minHeap.remove(e);
            }
        });
        frequencies.entrySet().stream().forEach(e->{
            if(e.getKey().equals(key)) {
                minHeap.offer(e);
            }
        });
        return cache.get(key);
    }

    public void put (K key, V value) {
        if(cache.size() == capacity && !cache.containsKey(key)) {
            Map.Entry<K, Integer> lfuEntry = minHeap.poll();
            cache.remove(lfuEntry.getKey());
            frequencies.remove(lfuEntry.getKey());
        }
        cache.computeIfAbsent(key, k->value);
        frequencies.putIfAbsent(key, INIT_FREQUENCY);
        frequencies.entrySet().stream().forEach(e->{if(e.getKey().equals(key)) minHeap.offer(e);});
    }



}
