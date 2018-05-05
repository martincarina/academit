package ru.academits.martin.list;

public class ListItem<T> {
    private T data;
    private ListItem<T> next;

    ListItem(T data) {
        this.data = data;
    }

    ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListItem<T> getNext() {
        return next;
    }

    public void setNext(ListItem<T> next) {
        this.next = next;
    }
}
