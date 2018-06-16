package ru.academits.martin.hashtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> implements Collection<T> {
    //   private Item<T>[] table;
    //   LinkedList<T>[] items;
    private ArrayList<LinkedList<T>> items;//пока так, может, потом обычный массив попробую сделать
    private int count;//количество в таблице
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

    public void print() {
     /*   for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                System.out.println(i + "" + items[i].getFirst());
            }
        }*/
        for (int i = 0; i < size; i++) {
            if (items.get(i) != null) {
                System.out.println(i + " " + items.get(i).getFirst());
            }
        }
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        int index = Math.abs(o.hashCode() % size);
    /*    if (items[index] == null) {
            //      items[index] = new LinkedList<>();
            items[index].add((T) o);
        }*/
        if (items.get(index) != null) {
            items.get(index).add((T) o);
            ++count;
            return true;
        }
        LinkedList<T> node = new LinkedList<>();
        node.add((T) o);
        items.set(index, node);
        ++count;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

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
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
