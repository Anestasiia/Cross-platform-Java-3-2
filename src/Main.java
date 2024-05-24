import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Завдання №2");

        double a, b, c, d;

        // Введення значень a та b, з перевіркою на a < b
        do {
            System.out.print("Введіть значення a: ");
            a = scanner.nextDouble();
            System.out.print("Введіть значення b: ");
            b = scanner.nextDouble();
            if (a >= b) {
                System.out.println("Значення a повинно бути меншим за b. Спробуйте знову.");
            }
        } while (a >= b);

        // Введення значень c та d, з перевіркою на додатність сум a + c та b + d
        do {
            System.out.print("Введіть значення c: ");
            c = scanner.nextDouble();
            System.out.print("Введіть значення d: ");
            d = scanner.nextDouble();

            double sum_a_c = a + c;
            double sum_b_d = b + d;

            if (sum_a_c <= 0 || sum_b_d <= 0) {
                System.out.println("Суми a + c та b + d мають бути додатніми числами. Спробуйте знову.");
            }
        } while (a + c <= 0 || b + d <= 0);

        // Обчислення кроку зміни значень xStep та yStep для розташування по 8 точок на кожному відрізку
        double dif_a_b = b - a;
        double dif_c_d = d - c;
        double xStep, yStep;
        if (dif_a_b != 0 || dif_c_d != 0) {
            xStep = dif_a_b / 9.0;
            yStep = dif_c_d / 9.0;
        } else {
            xStep = 1;
            yStep = 1;
        }

        // Оголошення масивів xAxis та yAxis для зберігання значень x та y
        double[] xAxis = new double[8];
        double[] yAxis = new double[8];

        // Заповнення масивів xAxis та yAxis значеннями x та y
        for (int i = 0; i < 8; i++) {
            xAxis[i] = a + (i + 1) * xStep;
            yAxis[i] = c + (i + 1) * yStep;
        }

        // Обчислення значень функції для кожної комбінації значень x та y
        double[][] result = new double[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result[i][j] = 7 * Math.log10(xAxis[j] + yAxis[7 - i]);
            }
        }

        // Створення та заповнення таблиці значень функції
        double[][] table = new double[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0 && i == 8) {
                    table[i][j] = 0;
                } else if (j == 0) {
                    table[i][j] = yAxis[7 - i];
                } else if (i == 8) {
                    table[i][j] = xAxis[j - 1];
                } else {
                    table[i][j] = result[i][j - 1];
                }
            }
        }

        // Виведення таблиці значень функції
        String formattedNumber;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                formattedNumber = String.format("%.2f", table[i][j]);
                System.out.printf(formattedNumber + " ");
                if (j == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i == 7) {
                System.out.println("-----+----------------------------------------");
            }
        }
    }
}
