package ru.academits.martin.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {
    private static String handleSymbol(char symbol) {
        if (symbol == '>') {
            return "&gt;";
        } else if (symbol == '<') {
            return "&lt;";
        } else if (symbol == '&') {
            return "&amp;";
        } else {
            return Character.toString(symbol);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Передано неверное количество аргументов.");
            System.out.println("Нужно передать 2 аргумента: название входного файла в формате *.csv и название выходного файла в формате *html.");
            return;
        }
        try (PrintWriter writer = new PrintWriter(args[1]);
             Scanner scanner = new Scanner(new FileInputStream(args[0]))) {
            boolean isInsideComplexCell = false;

            writer.println("<!doctype html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>Таблица</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table>");

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                int index = 0;
                final char QUOTE = '\"';
                final char COMMA = ',';

                while (index < line.length()) {
                    char symbol = line.charAt(index);
                    if (isInsideComplexCell) {
                        if (index == line.length() - 1) {
                            if (symbol == QUOTE) {
                                isInsideComplexCell = false;
                                writer.print("</td>");
                                ++index;
                            } else {
                                writer.print(handleSymbol(symbol));
                                ++index;
                            }
                        } else {
                            if (symbol == QUOTE) {
                                if (line.charAt(index + 1) == COMMA) {
                                    isInsideComplexCell = false;
                                    writer.print("</td>");
                                    if (index == line.length() - 2) {
                                        writer.print("<td></td>");
                                    }
                                    index += 2;
                                } else if (line.charAt(index + 1) == QUOTE) {
                                    writer.print(handleSymbol(symbol));
                                    index += 2;
                                } else {
                                    System.out.println("Неверный формат данных.");
                                    return;
                                }
                            } else {
                                writer.print(handleSymbol(symbol));
                                ++index;
                            }
                        }
                    } else {
                        if (index == 0) {
                            writer.print("<tr><td>");
                            if (symbol == COMMA) {
                                writer.print("</td>");
                            } else if (symbol == QUOTE) {
                                isInsideComplexCell = true;
                            } else {
                                writer.print(handleSymbol(symbol));
                            }
                            ++index;
                        } else if (index == line.length() - 1) {
                            if (symbol == COMMA) {
                                if (line.charAt(index - 1) == COMMA) {
                                    writer.print("<td>");
                                }
                                writer.print("</td><td></td>");
                                ++index;
                            } else {
                                writer.print(handleSymbol(symbol));
                                writer.print("</td>");
                                ++index;
                            }
                        } else {
                            if (symbol == QUOTE) {
                                writer.print("<td>");
                                isInsideComplexCell = true;
                                ++index;
                            } else if (symbol == COMMA) {
                                if (line.charAt(index - 1) == COMMA) {
                                    writer.print("<td>");
                                }
                                writer.print("</td>");
                                ++index;
                            } else {
                                if (line.charAt(index - 1) == COMMA && line.charAt(index + 1) != QUOTE) {
                                    writer.print("<td>");
                                }
                                writer.print(handleSymbol(symbol));
                                ++index;
                            }
                        }
                    }
                }
                if (isInsideComplexCell) {
                    writer.print("<br/>");
                } else {
                    writer.println("</tr>");
                }
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
