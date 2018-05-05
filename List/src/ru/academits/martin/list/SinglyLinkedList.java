package ru.academits.martin.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirstElement() {
        if (size < 1) {
            throw new NullPointerException("Список пуст.");
        }
        return head.getData();
    }

    public T getElement(int index) {
        if (size < 1) {
            throw new NullPointerException("Список пуст.");
        }
        if (index >= size) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка.");
        }
        int currentIndex = 0;
        ListItem<T> link = head;
        while (link.getNext() != null) {
            if (currentIndex == index) {
                break;
            }
            ++currentIndex;
            link = link.getNext();
        }
        return link.getData();
    }

    public T changeElement(int index, T data) {
        if (size < 1) {
            throw new NullPointerException("Список пуст.");
        }
        if (index >= size) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка.");
        }
        int currentIndex = 0;
        ListItem<T> link = head;
        while (link.getNext() != null) {
            if (currentIndex == index) {
                break;
            }
            ++currentIndex;
            link = link.getNext();
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
        if (size < 1) {
            throw new NullPointerException("Список пуст.");
        }
        if (index >= size) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка.");
        }

        int currentIndex = 0;
        for (ListItem<T> link = head, prev = null; link != null; prev = link, link = link.getNext()) {
            if (currentIndex == index) {
                if (prev == null) {
                    insertInBeginning(data);
                } else {
                    ListItem<T> node = new ListItem<>(data);
                    prev.setNext(node);
                    node.setNext(link);
                    ++size;
                }
                break;
            }
            ++currentIndex;
        }
    }


    public T removeFirstElement() {
        if (size < 1) {
            throw new NullPointerException("Список пуст.");
        }
        T oldData = head.getData();
        head = head.getNext();
        --size;
        return oldData;
    }

    public T removeElementByIndex(int index) {
        if (size < 1) {
            throw new NullPointerException("Список пуст.");
        }
        if (index >= size) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка.");
        }
        int currentIndex = 0;
        T oldData = null;
        for (ListItem<T> link = head, prev = null; link != null; prev = link, link = link.getNext()) {
            if (currentIndex == index) {
                if (prev == null) {
                    oldData = removeFirstElement();
                } else {
                    oldData = link.getData();
                    prev.setNext(link.getNext());
                    --size;
                }
                break;
            }
            ++currentIndex;
        }
        return oldData;
    }

    public boolean isRemovedElementByValue(T value) {
        if (size == 0) {
            System.out.println("Список пуст.");
            return false;
        }
        boolean isValueRemoved = false;
        for (ListItem<T> link = head, prev = null; link != null; prev = link, link = link.getNext()) {
            if (link.getData().equals(value)) {
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
            System.out.println("Список пуст.");
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
        for (ListItem<T> link = head; link != null; link = link.getNext()) {
            if (link2 == null) {
                list2.head = new ListItem<>(link.getData());
                link2 = list2.head;
            } else {
                ListItem<T> node = new ListItem<>(link.getData());
                node.setNext(link2.getNext());
                link2.setNext(node);
                link2 = link2.getNext();
            }
            ++list2.size;
        }
        return list2;
    }
}
