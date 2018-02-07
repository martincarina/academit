package ru.academits.martin.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = args[0];
        String outputFile = args[1];
        try (PrintWriter writer = new PrintWriter(args[1]);
             Scanner scanner = new Scanner(new FileInputStream(args[0]))) {
            boolean isInsideSimpleCell = false;
            boolean isInsideComplexCell = false;
            boolean isComplexCellClosed = false;

            writer.println("<!doctype html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>" + "Таблица" + "</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table>");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                int index = 0;
                final char QUOTE = '\"';
                final char COMMA = ',';
                final char SPACE = ' ';
                final char TAB = '\t';
                final char GT = '>';
                final char LT = '<';
                final char AMP = '&';

                if (!isInsideComplexCell) {//если не внутри сложной ячейки, то начинается новая строка
                    writer.print("<tr>");
                }

                while (index < line.length()) {
                    char c = line.charAt(index);
                    switch (c) {
                        case QUOTE:
                            if (isInsideComplexCell) {//надо или нет? может, потом после цикла что-то подмутить-не получается
                                if (index == line.length() - 1) {// одиночная кавычка в конце сложной ячейки
                                    writer.print("</td>");
                                    isInsideComplexCell = false;
                                    isComplexCellClosed = true;
                                    ++index;
                                } else if (line.charAt(index + 1) == COMMA) {//запятая после одиночной кавычки внутри сложной ячейки
                                    writer.print("</td>");
                                    isInsideComplexCell = false;
                                    index += 2;
                                } else if (line.charAt(index + 1) == QUOTE) {//двойная кавычка внутри сложной ячейки
                                    writer.print(c);
                                    index += 2;
                                } else {
                                    System.out.println("Неверный формат данных.");
                                    return;
                                }
                            } else if (isInsideSimpleCell) {//кавычка внутри простой ячейки
                                System.out.println("Неверный формат данных.");
                                return;
                            } else {//кавычка снаружи ячеек- начало новой ячейки
                                isInsideComplexCell = true;
                                writer.print("<td>");
                                ++index;
                            }
                            break;
                        case COMMA:
                            if (isInsideComplexCell) {//запятая внутри сложной ячейки
                                writer.print(c);
                                ++index;
                            } else if (isInsideSimpleCell) {//запятая внутри простой ячейки - коней ячейки
                                writer.print("</td>");
                                isInsideSimpleCell = false;
                                ++index;
                            } else {//запятая снаружи ячеек - пустая ячейка
                                writer.print("<td>" + "</td>");
                                ++index;
                            }
                            break;
                        case SPACE:
                        case TAB:
                            if (!isInsideComplexCell && !isInsideSimpleCell) {//пробел снаружи ячейки
                                ++index;
                            } else {//пробел внутри ячейки
                                writer.print(c);
                                ++index;
                            }
                            break;
                        case GT:
                            writer.print("&gt");
                            ++index;
                            break;
                        case LT:
                            writer.print("&lt");
                            ++index;
                            break;
                        case AMP:
                            writer.print("&amp");
                            ++index;
                            break;
                        default:
                            if (!isInsideComplexCell && !isInsideSimpleCell) {//любой другой символ снаружи ячейки
                                isInsideSimpleCell = true;
                                writer.print("<td>" + c);
                                ++index;
                            } else {//любой другой символ внутри ячейки
                                writer.print(c);
                                ++index;
                            }
                    }
                }
                if (isInsideComplexCell) {//если внутри сложной ячейки-перенос строки внутри ячейки
                    writer.print("<br/>");
                } else if (isInsideSimpleCell) {//если внутри простой ячейки-конец ячейки и строки
                    isInsideSimpleCell = false;
                    writer.println("</td>" + "</tr>");
                } else if (isComplexCellClosed) {//если снаружи ячейки, но последней ячейкой была сложная и она закрыта
                    isComplexCellClosed = false;
                    writer.println("</tr>");
                } else {//если снаружи ячейки - значит, пустая ячейка
                    writer.println("<td>" + "</td>" + "</tr>");
                }
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
