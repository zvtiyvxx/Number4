package ru.vsuet.hashmap;

public class MyEntry<V> {
    private String key;
    private V value;

    public MyEntry(String key, V value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int keyHash = 0;

        if (key != null) {
            for (char c : key.toCharArray()) {
                keyHash = prime * keyHash + c;
            }
        }

        int result = keyHash;
        return result;


    }
}
