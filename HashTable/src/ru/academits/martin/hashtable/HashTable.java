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

        public T getData() {
            return data;
        }

 /*       public void setData(T data) {
            this.data = data;
        }*/

        public ListItem<T> getNext() {
            return next;
        }

 /*       public void setNext(ListItem<T> next) {
            this.next = next;
        }

        public ListItem<T> getPrev() {
            return next;
        }

        public void setPrev(ListItem<T> next) {
            this.next = next;
        }*/
    }

    private ArrayList<ListItem<T>> items;//пока так, может, потом обычный массив попробую сделать
    private int count;//количество элементов в таблице. Если count>0.75 size. Обычно таблицу расширяют. TODO добавить проверку и расширение
    private int size;

    public HashTable(int size) {
        this.size = size;
        items = new ArrayList<>();
        items.ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            items.add(null);
        }
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
        if (o == null) {
            throw new NullPointerException("В аргумент передан null");
        }
        int index = Math.abs(o.hashCode() % size);
        if (items.get(index) != null) {
            Iterator listIterator = new MyIterator();
            //          Iterator<T> listIterator = this.iterator();//можно foreach попробовать
            while (listIterator.hasNext()) {
                if (listIterator.next().equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    private class MyIterator implements Iterator<T> {// TODO наверное, нужен modCount
        int currentElementNumber = 0;
        int currentArrayIndex = 0;
        ListItem<T> currentElement;
        ListItem<T> nextElement;

        @Override
        public boolean hasNext() {
            return currentElementNumber < count;
        }

        @Override
        public T next() {
            for (; currentElementNumber != count; currentArrayIndex++) {
                if (items.get(currentArrayIndex) != null) {
                    currentElement = nextElement;
                    if (currentElement == null) {
                        currentElement = items.get(currentArrayIndex);
                    }

                    currentElementNumber++;
                    T value = currentElement.getData();
                    nextElement = currentElement.getNext();
                    if (nextElement == null) {
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
     /*       if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }*/

            if (currentElement.prev != null) {
                currentElement.prev.next = currentElement.next;
            } else {
                items.set(currentArrayIndex, currentElement.next);
            }
            if (currentElement.next != null) {
                currentElement.next.prev = currentElement.prev;
            }
            --count;
            --currentElementNumber;
            currentElement = null;
            //           currentModCount = modCount;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[count];
        Iterator<T> listIterator = this.iterator();

        for (int j = 0; j < array.length; j++) {
            //           if (listIterator.hasNext()) { - надо ли? массив ведь длиной с количество элементов таблицы
            array[j] = listIterator.next();
            //          }
        }
        return array;
    }

    @Override
    public boolean add(Object o) {
        if (o == null) {
            throw new NullPointerException("В аргумент передан null");
        }
        ListItem<T> node = new ListItem<>((T) o);
        int index = Math.abs(o.hashCode() % size);
        if (items.get(index) != null) {
            node.next = items.get(index);
            node.next.prev = node;
        }
        items.set(index, node);
        ++count;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        int index = Math.abs(o.hashCode() % size);
        if (items.get(index) == null) {
            return false;
        }
        ListItem<T> node = items.get(index);
        while (node != null) {
            if (o.equals(node.getData())) {
                if (node.prev != null) {
                    node.prev.next = node.next;
                } else {
                    items.set(index, node.next);
                }
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
                --count;
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
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            if (items.get(i) != null) {
                items.set(i, null);
            }
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
