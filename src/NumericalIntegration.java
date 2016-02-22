/**
 * Created by Vladimir Rodin.
 * Built on: Thinkpad Workstation W540
 * Date: 22.02.2016
 * Twitter: @heyhihellobro
 */
public class NumericalIntegration {

    /**
     * Входные параметры
     */
    static double a = 0;
    static double b = 1;
    static double n = 2;
    static double h = Math.abs((a - b) / n); // = 1/2
    static double[] roots;
    static double[] values;

    /**
     * Накапливающие переменные
     */
    static double temp; //для трапеций
    static double tempR; //для прямоугольников
    static double tempS_1; //для симпсона четные
    static double tempS_2; //для симпсона нечетные

    /**
     * Заданная функция по умолчанию
     *
     * @param x
     * @return (1 / (1 + Math.pow(x, 2)))
     */
    private static double f(double x) {
        return (1 / (1 + Math.pow(x, 2)));
    }

    /**
     * Нахождение узлов разбиения и значения функции в данных узлах
     */
    private static void findRootsAndValues() {
        roots = new double[(int) n + 1];
        values = new double[(int) n + 1];

        /* Узлы разбиения, которых будет на одну больше, чем количество отрезков */
        for (int i = 0; i < n + 1; i++) {

            roots[i] = a + i * h;
            values[i] = f(roots[i]);

            /* Накопление значений */
            if (i != 0 && i != n) {
                temp += f(roots[i]);

                if (i % 2 == 0) {
                    tempS_1 += f(roots[i]);
                }
            }

            tempR += f(roots[i] + h / 2);

            if (i % 2 == 1) {
                tempS_2 += f(roots[i]);
            }
            System.out.println("Узел разбиения x[" + i + "]: " + roots[i]);
            System.out.println("Значение функции в узле " + roots[i] + " = " + values[i]);
        }
    }

    /**
     * Метод трапеций
     *
     * @param values
     * @return result
     */
    private static double trapezeMethod(double[] values) {
        double result = 0.0;
        result = h * (((values[0] + values[(int) n]) / 2) + temp);
        System.out.println("Точное значение, вычисленное методом трапеций: " + result);
        return result;
    }

    /**
     * Метод средних прямоугольников
     *
     * @param tempR
     * @return
     */
    private static double centralRectangleMethod(double tempR) {
        double result = 0.0;
        result = h * tempR;
        System.out.println("Точное значение, вычисленное методом прямоугольников: " + result);
        return result;
    }

    /**
     * Метод симпсона
     * @param values
     * @return
     */
    private static double simpsonMethod(double[] values) {
        double result = 0.0;
        result = h / 3 * (values[0] + values[(int) n] + 2 * tempS_1 + 4 * tempS_2);
        System.out.println("Точное значение, вычисленное по формуле Симпсона: " + result);
        return result;
    }


    public static void main(String[] args) {
        findRootsAndValues();

        System.out.println();
        System.out.println("Промежуточные значения: ");
        System.out.println("Temp: " + temp);
        System.out.println("TempR: " + tempR);
        System.out.println("TempS1: " + tempS_1);
        System.out.println();
        System.out.println("Результаты: ");
        trapezeMethod(values);
        centralRectangleMethod(tempR);
        simpsonMethod(values);
    }
}
