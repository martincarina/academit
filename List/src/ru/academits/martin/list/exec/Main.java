package ru.academits.martin.list.exec;

import ru.academits.martin.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insertInBeginning(100);
        list.insertInBeginning(200);
        System.out.println("Получить первый элемент списка");
        System.out.println(list.getFirstElement());
        System.out.println("Размер списка");
        System.out.println(list.getSize());

        System.out.println("Удалить первый элемент списка");
        System.out.println(list.removeFirstElement());
        System.out.println("Получить новый первый элемент списка");
        System.out.println(list.getFirstElement());
        System.out.println("Новый размер списка");
        System.out.println(list.getSize());

        list.insertInBeginning(200);
        list.insertInBeginning(null);
        list.insertInBeginning(400);
        System.out.println("Новый размер списка");
        System.out.println(list.getSize());
        System.out.println("Получение значения по индексу");
        System.out.println(list.getElement(3));
        System.out.println("Изменение элемента по индексу");
        System.out.println(list.changeElement(3, 1000));
        System.out.println("Новое значение элемента по индексу");
        System.out.println(list.getElement(3));

        System.out.println("Удаление по индексу");
        System.out.println("Получение значения по индексу");
        System.out.println(list.getElement(0));
        System.out.println("Удаление значения по индексу");
        System.out.println(list.removeElementByIndex(0));
        System.out.println("Получение нового значения по индексу");
        System.out.println(list.getElement(0));

        System.out.println("Вставка по индексу");
        System.out.println("Старое значение");
        System.out.println(list.getElement(2));
        list.insertElement(2, 2);
        System.out.println("Новое значение");
        System.out.println(list.getElement(2));

        System.out.println("Удаление по значению");
        if (list.isRemovedElementByValue(1000)) {
            System.out.println("Значение найдено и удалено из списка.");
        } else {
            System.out.println("Значение в списке отсутствует.");
        }

        System.out.println("Разворот списка");
        System.out.println("Первоначальный список");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getElement(i));
        }

        list.reverseList();
        System.out.println("Развернутый список");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getElement(i));
        }

        System.out.println("Копирование списка");
        SinglyLinkedList<Integer> list2 = list.copyList();
        System.out.println("Размер списка-копии");
        System.out.println(list2.getSize());
        System.out.println("Элементы списка-оригинала");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getElement(i));
        }
        System.out.println("Элементы списка-копии");
        for (int i = 0; i < list2.getSize(); i++) {
            System.out.println(list2.getElement(i));
        }

        System.out.println("Вставка элемента в конец списка");
        System.out.println("Размер списка до вставки:");
        System.out.println(list.getSize());
        list.insertElement(3, 10);
        System.out.println("Размер списка после вставки:");
        System.out.println(list.getSize());
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getElement(i));
        }
    }
}
