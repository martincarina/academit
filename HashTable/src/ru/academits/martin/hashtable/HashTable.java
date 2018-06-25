package ru.academits.martin.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    //   private Item<T>[] table;
    //   LinkedList<T>[] items;
    //  private ArrayList<LinkedList<T>> items;//пока так, может, потом обычный массив попробую сделать
    private static class ListItem<T> {
        private T data;
        private ListItem<T> next;
        private ListItem<T> prev;

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

        public ListItem<T> getPrev() {
            return next;
        }

        public void setPrev(ListItem<T> next) {
            this.next = next;
        }
    }

    private ArrayList<ListItem<T>> items;
    private int count;//количество элементов в таблице. Если count>0.75 size. Обычно таблицу расширяют. TODO добавить проверку и расширение
    private int size;//размер таблицы

    public HashTable(int size) {
        this.size = size;
//        table = new Item[size];
        //   this.items = new LinkedList<T>[size];
        //   items = (LinkedList<T>[]) new Object[size];

        // items = new ArrayList<LinkedList<T>>();
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

    public void print() {//TODO переделать, а лучше вообще убрать, т.к. методы не должны печатать
     /*   for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                System.out.println(i + "" + items[i].getFirst());
            }
        }*/
        for (int i = 0; i < size; i++) {
            if (items.get(i) != null) {
                System.out.println(i + " " + items.get(i).getData());
            }
        }
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("В аргумент передан null");
        }
        int index = Math.abs(o.hashCode() % size);
        if (items.get(index) != null) {
/*            if (items.get(index).getData().equals(o)) {
                return true;
            } else {*/
            MyIterator listIterator = new MyIterator();
            while (listIterator.hasNext()) {
                if (listIterator.next().equals(o)) {
                    return true;
                }
            }
//            }
        }
        return false;
//        return items.get(index) != null;//TODO может, надо еще equals применить, а то вдруг просто hashCode совпал
    }

    private class MyIterator implements Iterator<T> {// TODO возможно, нужен modCount
        int currentElementNumber = 0;//номер текущего элемента таблицы
        int currentArrayIndex = 0;//индекс текущего элемента таблицы
        ListItem<T> currentElement;

        @Override
        public boolean hasNext() {
            return currentElementNumber < count;
        }

        @Override
        public T next() {
            for (; currentElementNumber != count; currentArrayIndex++) {
                if (items.get(currentArrayIndex) != null) {
                    if (currentElement == null) {
                        currentElement = items.get(currentArrayIndex);
                    }

                    currentElementNumber++;
                    T value = currentElement.getData();
                    currentElement = currentElement.getNext();
                    if (currentElement == null) {
                        currentArrayIndex++;
                    }
                    return value;
                }
            }
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {//TODO Должен быть массив со всеми значениями коллекции
        T[] array = (T[]) new Object[count];
        Iterator<T> listIterator = this.iterator();
 /*
  int i = 0;
  while (listIterator.hasNext()) {
            array[i] = listIterator.next();
            i++;
        }*/

        for (int j = 0; j < array.length; j++) {
            //           if (listIterator.hasNext()) { - надо ли? массив ведь длиной с количество элементов таблицы
            array[j] = listIterator.next();
            //          }
        }
        return array;
    }

    @Override
    public boolean add(Object o) {
        if(o==null){
            throw new NullPointerException("В аргумент передан null");//от null hashcode не считается.
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
            throw new NullPointerException("В аргумент передан null");
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
                break;
            }
            node = node.next;
        }
        --count;
        return true;
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

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
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
    public Object[] toArray(Object[] a) {
        if (a == null) {
            throw new NullPointerException("Передан null");
        }
        return new Object[0];
    }
}
