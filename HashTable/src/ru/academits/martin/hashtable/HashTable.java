package ru.academits.martin.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static class ListItem<T> {
        private T data;
        private ListItem<T> next;
        private ListItem<T> prev;

        ListItem(T data) {
            this.data = data;
        }

        private T getData() {
            return data;
        }

        private ListItem<T> getNext() {
            return next;
        }
    }

    private ListItem<T>[] items;
    private int count;
    private int size;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    private float threshold = size * DEFAULT_LOAD_FACTOR;

    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть не меньше 1.");
        }
        this.size = size;
        items = new ListItem[size];
    }

    private int findIndex(Object o) {
        int hash = (o == null) ? 0 : o.hashCode();
        return Math.abs(hash % size);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = size * 2;
        ListItem<T>[] newItems = new ListItem[newCapacity];
        size = newCapacity;
        for (T element : this) {
            ListItem<T> node = new ListItem<>(element);
            int newIndex = findIndex(element);
            if (newItems[newIndex] != null) {
                node.next = newItems[newIndex];
                node.next.prev = node;
            }
            newItems[newIndex] = node;
        }
        items = newItems;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = findIndex(o);
        if (items[index] != null) {
            for (T element : this) {
                if (Objects.equals(element, o)) {
                    return true;
                }
            }
        }
        return false;
    }

    private class MyIterator implements Iterator<T> {
        int currentElementNumber = 0;
        int currentArrayIndex = 0;
        ListItem<T> currentElement;
        ListItem<T> prevElement;
        int prevArrayIndex = 0;

        int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentElementNumber < count;
        }

        @Override
        public T next() {
            if (currentElementNumber >= count) {
                throw new NoSuchElementException("Коллекция закончилась");
            }
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }
            for (; currentElementNumber != count; currentArrayIndex++) {
                if (items[currentArrayIndex] != null) {
                    if (currentElement == null) {
                        currentElement = items[currentArrayIndex];
                    }
                    currentElementNumber++;
                    T value = currentElement.getData();
                    prevElement = currentElement;
                    currentElement = currentElement.getNext();
                    prevArrayIndex = currentArrayIndex;
                    if (currentElement == null) {
                        currentArrayIndex++;
                    }
                    return value;
                }
            }
            return null;
        }

        @Override
        public void remove() {
            if (currentElementNumber < 0) {
                throw new IllegalStateException();
            }
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }

            if (prevElement.prev != null) {
                prevElement.prev.next = prevElement.next;
            } else {
                items[prevArrayIndex] = prevElement.next;
            }
            if (prevElement.next != null) {
                prevElement.next.prev = prevElement.prev;
            }
            --count;
            --currentElementNumber;
            prevElement = null;
            modCount++;
            currentModCount = modCount;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[count];

       /* for (T element : this) {
            array[] = element;
        }*/

        Iterator<T> listIterator = this.iterator();

        for (int j = 0; j < array.length; j++) {
            array[j] = listIterator.next();
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(Object o) {
        if (count + 1 >= threshold) {
            this.resize();
        }
        ListItem<T> node = new ListItem<>((T) o);
        int index = findIndex(o);
        if (items[index] != null) {
            node.next = items[index];
            node.next.prev = node;
        }
        items[index] = node;
        ++count;
        ++modCount;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = findIndex(o);
        if (items[index] == null) {
            return false;
        }
        ListItem<T> node = items[index];
        while (node != null) {
            if (Objects.equals(o, node.getData())) {
                if (node.prev != null) {
                    node.prev.next = node.next;
                } else {
                    items[index] = node.next;
                }
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
                --count;
                ++modCount;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция null");
        }
        if (c.size() == 0) {
            return false;
        }
        for (Object element : c) {
            this.add(element);
        }
        modCount++;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        count = 0;
    }

    private boolean findCollectionElements(Collection<?> c, boolean type) {
        Objects.requireNonNull(c);
        return this.removeIf((T value) -> c.contains(value) != type);
    }

    @Override
    public boolean retainAll(Collection c) {
        return findCollectionElements(c, true);
    }

    @Override
    public boolean removeAll(Collection c) {
        return findCollectionElements(c, false);
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция null");
        }
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Передан null");
        }
        if (a.length < count) {
            Object[] array = this.toArray();
            return (T1[]) Arrays.copyOf(array, count, a.getClass());
        }
        Iterator<T> listIterator = this.iterator();
        for (int j = 0; j < count; j++) {
            a[j] = (T1) listIterator.next();
        }
        if (a.length > count) {
            a[count] = null;
        }
        return a;
    }
}
