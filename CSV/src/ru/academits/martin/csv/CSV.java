package ru.academits.martin.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {
    public static void main(String[] args) throws FileNotFoundException {

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

                if (!isInsideComplexCell) {
                    writer.print("<tr>");
                }

                while (index < line.length()) {
                    char c = line.charAt(index);
                    switch (c) {
                        case QUOTE:
                            if (isInsideComplexCell) {
                                if (index == line.length() - 1) {
                                    writer.print("</td>");
                                    isInsideComplexCell = false;
                                    isComplexCellClosed = true;
                                    ++index;
                                } else if (line.charAt(index + 1) == COMMA) {
                                    writer.print("</td>");
                                    isInsideComplexCell = false;
                                    index += 2;
                                } else if (line.charAt(index + 1) == QUOTE) {
                                    writer.print(c);
                                    index += 2;
                                } else {
                                    System.out.println("Неверный формат данных.");
                                    return;
                                }
                            } else if (isInsideSimpleCell) {
                                System.out.println("Неверный формат данных.");
                                return;
                            } else {
                                isInsideComplexCell = true;
                                writer.print("<td>");
                                ++index;
                            }
                            break;
                        case COMMA:
                            if (isInsideComplexCell) {
                                writer.print(c);
                                ++index;
                            } else if (isInsideSimpleCell) {
                                writer.print("</td>");
                                isInsideSimpleCell = false;
                                ++index;
                            } else {
                                writer.print("<td>" + "</td>");
                                ++index;
                            }
                            break;
                        case SPACE:
                        case TAB:
                            if (!isInsideComplexCell && !isInsideSimpleCell) {
                                ++index;
                            } else {
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
                            if (!isInsideComplexCell && !isInsideSimpleCell) {
                                isInsideSimpleCell = true;
                                writer.print("<td>" + c);
                                ++index;
                            } else {
                                writer.print(c);
                                ++index;
                            }
                    }
                }
                if (isInsideComplexCell) {
                    writer.print("<br/>");
                } else if (isInsideSimpleCell) {
                    isInsideSimpleCell = false;
                    writer.println("</td>" + "</tr>");
                } else if (isComplexCellClosed) {
                    isComplexCellClosed = false;
                    writer.println("</tr>");
                } else {
                    writer.println("<td>" + "</td>" + "</tr>");
                }
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
