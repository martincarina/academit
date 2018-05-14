package ru.academits.martin.list;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirstElement() {
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }
        return head.getData();
    }

    private ListItem<T> findLinkByIndex(int index) {
        int currentIndex = 0;
        ListItem<T> link = head;
        while (link.getNext() != null) {
            if (currentIndex == index) {
                break;
            }
            ++currentIndex;
            link = link.getNext();
        }
        return link;
    }

    public T getElement(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        ListItem<T> link = findLinkByIndex(index);
        return link.getData();
    }

    public T changeElement(int index, T data) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        ListItem<T> link = findLinkByIndex(index);
        T oldData = link.getData();
        link.setData(data);
        return oldData;
    }

    public void insertInBeginning(T data) {
        head = new ListItem<>(data, head);
        ++size;
    }

    public void insertElement(int index, T data) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }

        if (index == 0) {
            insertInBeginning(data);
        } else {
            ListItem<T> prevLink = findLinkByIndex(index - 1);
            ListItem<T> node = new ListItem<>(data);
            ListItem<T> link = prevLink.getNext();
            prevLink.setNext(node);
            node.setNext(link);
            ++size;
        }
    }

    public T removeFirstElement() {
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }
        T oldData = head.getData();
        head = head.getNext();
        --size;
        return oldData;
    }

    public T removeElementByIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        T oldData;
        if (index == 0) {
            oldData = removeFirstElement();
        } else {
            ListItem<T> prevLink = findLinkByIndex(index - 1);
            ListItem<T> link = prevLink.getNext();
            oldData = link.getData();
            prevLink.setNext(link.getNext());
            --size;
        }
        return oldData;
    }

    public boolean isRemovedElementByValue(T value) {
        if (size == 0) {
            return false;
        }
        boolean isValueRemoved = false;
        if (Objects.equals(value, head.getData())) {
            removeFirstElement();
            isValueRemoved = true;
        } else {
            for (ListItem<T> link = head.getNext(), prev = head; link != null; prev = link, link = link.getNext()) {
                if (Objects.equals(value, link.getData())) {
                    prev.setNext(link.getNext());
                    --size;
                    isValueRemoved = true;
                    break;
                }
            }
        }
        return isValueRemoved;
    }

    public void reverseList() {
        if (size == 0) {
            return;
        }
        ListItem<T> linkPrev = null;
        for (ListItem<T> link = head, prev = null; link != null; prev = link, link = link.getNext()) {
            if (prev != null) {
                prev.setNext(linkPrev);
                linkPrev = prev;
                if (link.getNext() == null) {
                    head = link;
                    link.setNext(linkPrev);
                    break;
                }
            }
        }
    }

    public SinglyLinkedList<T> copyList() {
        SinglyLinkedList<T> list2 = new SinglyLinkedList<>();
        ListItem<T> link2 = null;
        list2.size = size;
        for (ListItem<T> link = head; link != null; link = link.getNext()) {
            if (link2 == null) {
                list2.head = new ListItem<>(link.getData());
                link2 = list2.head;
            } else {
                ListItem<T> node = new ListItem<>(link.getData());
                link2.setNext(node);
                link2 = link2.getNext();
            }
        }
        return list2;
    }
}