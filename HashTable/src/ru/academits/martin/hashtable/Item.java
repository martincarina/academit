package ru.academits.martin.hashtable;

public class Item<T> {
    private T key;

    public Item(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
