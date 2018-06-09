package ru.academits.martin.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] items;
    private int length;

    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(Collection<T> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекция null");
        }
        items = (T[]) collection.toArray();
        length = collection.size();
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Первоначальная вместимость должна быть неотрицательной.");
        } else {
            items = (T[]) new Object[capacity];
        }
    }

    public void trimToSize() {
        if (length < items.length) {
            items = Arrays.copyOf(items, length);
        }
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    @SuppressWarnings({"unchecked", "RedundantCast"})
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Передан null");
        }
        if (a.length < length) {
            return (T1[]) Arrays.copyOf(items, length, a.getClass());
        }
        System.arraycopy((T1[]) items, 0, a, 0, length);
        if (a.length > length) {
            a[length] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        if (length >= items.length) {
            ensureCapacity(length * 2 + 1);
        }
        modCount++;
        items[length] = t;
        ++length;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    private boolean findCollectionElements(Collection<?> c, boolean type) {
        Objects.requireNonNull(c);
        return this.removeIf((T value) -> c.contains(value) != type);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(length, c);
    }

    private void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция null");
        }
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        if (c.size() == 0) {
            return false;
        }
        modCount++;
        length += c.size();
        if (length >= items.length) {
            ensureCapacity(length * 2);
        }
        if (index < length - 1) {
            System.arraycopy(items, index, items, index + c.size(), length - index - c.size());
        }

        ListIterator<T> listIterator = this.listIterator(index);
        for (T element : c) {
            listIterator.next();
            listIterator.set(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return findCollectionElements(c, false);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> c) {
        return findCollectionElements(c, true);
    }

    @Override
    public void clear() {
        modCount++;
        for (int i = 0; i < length; i++) {
            items[i] = null;
        }
        length = 0;
    }

    @Override
    public T get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        T oldElement = items[index];
        items[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        modCount++;
        if (length >= items.length) {
            ensureCapacity(length * 2 + 1);
        }
        if (index < length) {
            System.arraycopy(items, index, items, index + 1, length - index);
        }
        items[index] = element;
        ++length;
    }

    @Override
    public T remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        modCount++;
        T removedValue = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1,
                    items, index, length - index - 1);
        }
        --length;
        return removedValue;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (Objects.equals(o, items[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(o, items[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private class MyIterator implements Iterator<T> {
        int currentIndex = -1;
        int nextIndex = 0;
        int currentModCount = modCount;

        public boolean hasNext() {
            return nextIndex < length;
        }

        public T next() {
            int index = nextIndex;
            if (index >= length) {
                throw new NoSuchElementException("Коллекция закончилась");
            }
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }
            nextIndex = index + 1;
            currentIndex = index;
            return items[index];
        }

        @Override
        public void remove() {
            if (currentIndex < 0)
                throw new IllegalStateException();
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }
            ArrayList.this.remove(currentIndex);
            nextIndex = currentIndex;
            currentIndex = -1;
            currentModCount = modCount;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyListIterator extends MyIterator implements ListIterator<T> {

        MyListIterator(int index) {
            super();
            nextIndex = index;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        @Override
        public T previous() {
            int index = nextIndex - 1;
            if (index < 0) {
                throw new NoSuchElementException("Такого элемента нет");
            }
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }
            nextIndex = index;
            currentIndex = index;
            return items[index];
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("Такого элемента нет");
            }
            return nextIndex - 1;
        }

        @Override
        public void set(T t) {
            if (currentIndex < 0)
                throw new IllegalStateException();
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }
            ArrayList.this.set(currentIndex, t);
        }

        @Override
        public void add(T t) {
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }
            ArrayList.this.add(nextIndex, t);
            ++nextIndex;
            currentIndex = -1;
            currentModCount = modCount;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        return new MyListIterator(index);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}