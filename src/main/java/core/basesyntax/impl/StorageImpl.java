package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    static final int MAX_ARR_LENGTH = 10;
    K[] keys;
    V[] values;
    int count;

    @SuppressWarnings("unchecked")
    public StorageImpl() {
        keys = (K[]) new Object[MAX_ARR_LENGTH];
        values = (V[]) new Object[MAX_ARR_LENGTH];
        count = 0;
    }

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < MAX_ARR_LENGTH; i++) {
            if (keyEquals(keys[i], key)) {
                values[i] = value;
                return;
            }
        }
        if (count < MAX_ARR_LENGTH) {
            keys[count] = key;
            values[count] = value;
            count++;
        } else {
            throw new IllegalStateException("Storage is full");
        }
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < MAX_ARR_LENGTH; i++) {
            if (keyEquals(key, keys[i])) {
                return values[i];
            }
        }

        return null;
    }

    private boolean keyEquals(K a, K b) {
        return (a == b) || (a != null && a.equals(b));
    }

    @Override
    public int size() {
        return count;
    }
}
