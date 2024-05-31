package ru.vsuet.hashmap;

import java.util.LinkedList;
import java.util.function.BiConsumer;

public class HashMap<V> {
    private final int defaultSize = 16;
    private final float loadFactor = 0.75f;
    private LinkedList<MyEntry<V>>[] buckets;
    private int size;

    public HashMap(){
        buckets = new LinkedList[defaultSize];
        size = 0;
    }

    public void put(String key, V value){
        if (key == null)
            throw new IllegalStateException("The key is undefined");

        check(key, (entry, bucket) -> {
            if (entry != null) {
                entry.setValue(value);
            } else {
                bucket.add(new MyEntry<>(key, value));
                size++;
            }
        });

        if (checkResize()){
            resize();
        }
    }

    public V get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is undefined");
        }

        MyEntry<V> entry = check(key, (e, b) -> {});
        return (entry == null) ? null : entry.getValue();
    }

    public void remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is undefined");
        }

        check(key, (entry, bucket) -> {
            if (entry != null) {
                bucket.remove(entry);
                size--;
            }
        });
    }

    private void resize() {
        int newSize = buckets.length * 2;
        LinkedList<MyEntry<V>>[] newBuckets = new LinkedList[newSize];

        for (LinkedList<MyEntry<V>> bucket : buckets) {
            if (bucket != null) {
                for (MyEntry<V> entry : bucket) {
                    int newIndex = Math.abs(hash(entry.getKey())) % newSize;
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new LinkedList<>();
                    }
                    newBuckets[newIndex].add(entry);
                }
            }
        }
        buckets = newBuckets;
    }

    private boolean checkResize(){
        float presentLoadFactor = (float) size / buckets.length;
        return presentLoadFactor >= loadFactor;
    }

    private MyEntry<V> check(String key, BiConsumer<MyEntry<V>, LinkedList<MyEntry<V>>> checks) {
        int index = Math.abs(hash(key)) % buckets.length;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        LinkedList<MyEntry<V>> bucket = buckets[index];
        for (MyEntry<V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                checks.accept(entry, bucket);
                return entry;
            }
        }
        checks.accept(null, bucket);
        return null;
    }

    public int hash(String key){
        return key.hashCode();
    }

    public void printSize(){
        System.out.println("Размер матрицы " + buckets.length);
    }

    public String printMap() {
        StringBuilder result = new StringBuilder();
        for (LinkedList<MyEntry<V>> bucket : buckets) {
            if (bucket != null) {
                for (MyEntry<V> entry : bucket) {
                    result.append("Key: ").append(entry.getKey()).append(", Value: ").append(entry.getValue()).append("\n");
                }
            }
        }
        return result.toString();
    }
}
