package ru.academits.martin.list;

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

    private ListItem<T> findPrevLinkByIndex(int index) {
        int currentIndex = 0;
        ListItem<T> prevLink = null;
        for (ListItem<T> link = head; link != null; link = link.getNext()) {
            if (currentIndex == index) {
                break;
            }
            prevLink = link;
            ++currentIndex;
        }
        return prevLink;
    }

    public T getElement(int index) {
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        ListItem<T> prevLink = findPrevLinkByIndex(index);
        if (prevLink == null) {
            return head.getData();
        } else {
            return prevLink.getNext().getData();
        }
    }

    public T changeElement(int index, T data) {
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        ListItem<T> prevLink = findPrevLinkByIndex(index);
        ListItem<T> link;
        if (prevLink == null) {
            link = head;
        } else {
            link = prevLink.getNext();
        }
        T oldData = link.getData();
        link.setData(data);
        return oldData;
    }


    public void insertInBeginning(T data) {
        head = new ListItem<>(data, head);
        ++size;
    }

    public void insertElement(int index, T data) {
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        ListItem<T> prevLink = findPrevLinkByIndex(index);
        if (prevLink == null) {
            insertInBeginning(data);
        } else {
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
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс отрицательный или превышает размер списка.");
        }
        ListItem<T> prevLink = findPrevLinkByIndex(index);
        T oldData;
        if (prevLink == null) {
            oldData = removeFirstElement();
        } else {
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
        for (ListItem<T> link = head, prev = null; link != null; prev = link, link = link.getNext()) {
            if ((link.getData() == null) ? value == null : link.getData().equals(value)) {
                if (prev == null) {
                    removeFirstElement();
                } else {
                    prev.setNext(link.getNext());
                    --size;
                }
                isValueRemoved = true;
                break;
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