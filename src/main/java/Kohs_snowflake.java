import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * Класс, отвечающий за прорисовку кривой Коха.
 */
public class Kohs_snowflake extends JPanel {


    /**
     * Формирует цвета и масштаб, рекурсивно вызывает метод
     * <code>drawKochLine</code>.
     *
     * @param g the specified Graphics context
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Colorist.set_Color(Start.back_col));
        g.setColor(Colorist.set_Color(Start.fract_col));
        Point a = new Point(0, 10);
        Point b = new Point(this.getWidth(), 10);

        drawKochLine(g, a, b, 0, 10);
    }

    /**
     * Рисует рекурсивно линию Коха. При этом отрезок (a; b) делится на 3 равных
     * части. Средняя из них заменяется равносторонним треугольником со стороной
     * равной данной и без данной стороны (рисуется только две, не принадлежащие
     * отрезку стороны треугольника).
     *
     * @param g  the specified Graphics context
     * @param a  начальная точка линии
     * @param b  конечная точка линии
     * @param fi угол поворота линии
     * @param n  оставшаяся глубина рекурсии
     */
    public void drawKochLine(Graphics g, Point a, Point b, double fi, int n) {
        if (n <= 0) {
            // рисуем прямую, если достигнута необходимая глубина рекурсии.
            g.drawLine(a.x, a.y, b.x, b.y);
        } else {
            // находим длину отрезка (a; b).
            double length = Math.pow(Math.pow(b.y - a.y, 2)
                    + Math.pow(b.x - a.x, 2), 0.5);
            // находим длину 1/3 отрезка (a; b)
            double length1of3 = length / 3;

            // находим т., делящую отрезок как 1:3.
            Point a1 = new Point(a.x
                    + (int) Math.round((length1of3 * Math.cos(fi))), a.y
                    + (int) Math.round((length1of3 * Math.sin(fi))));

            // находим т., делящую отрезок как 2:3.
            Point b1 = new Point(a1.x
                    + (int) Math.round((length1of3 * Math.cos(fi))), a1.y
                    + (int) Math.round((length1of3 * Math.sin(fi))));

            // находим т., которая будет вершиной равностороннего
            // треугольника.
            Point c = new Point(a1.x
                    + (int) Math
                    .round((length1of3 * Math.cos(fi + Math.PI / 3))),
                    a1.y
                            + (int) Math.round((length1of3 * Math.sin(fi
                            + Math.PI / 3))));

            n--;
            drawKochLine(g, a1, c, fi + Math.PI / 3, n);
            drawKochLine(g, c, b1, fi - Math.PI / 3, n);

            n--;
            drawKochLine(g, a, a1, fi, n);
            drawKochLine(g, b1, b, fi, n);
        }
    }

}