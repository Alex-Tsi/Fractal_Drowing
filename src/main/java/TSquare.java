import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;

public class TSquare extends JPanel {
    static int iter = 6;//количество итераций

    public static int drawTSQ(Graphics g, Point A, int size, int iter) {
        //параметры А - координата левого верхнего угла квадрата
        //size - длина стороны
        //iter - кол-во итераций
        //g - экземпляр библиотечного класса, ответственного за отрисовку
        if (iter == 1) {//если итерация одна, просто рисуем заполненный прямоугольник
            g.fillRect(A.x, A.y, size, size);
            return 0;
        }
        int d = size / 4; //вспомогательная переменная, четверть длины исходного квадрата
        Point M[] = new Point[4];//координаты левых верхних углов порожденных квадратов
        for (int i = 0; i < 4; i++) {
            M[i] = new Point();
        }
        M[0].x = A.x - d;//левый верхний квадрат
        M[0].y = A.y - d;
        M[1].x = A.x - d;//левый нижний
        M[1].y = A.y + size - d;
        M[2].x = A.x + size - d;//правый верхний
        M[2].y = A.y - d;
        M[3].x = A.x + size - d;//правый нижний
        M[3].y = A.y + size - d;


        for (int i = 0; i < 4; i++) {
            drawTSQ(g, M[i], size / 2, iter - 1);//вызываем рекурсивно для каждого квадрата
        }
        g.fillRect(A.x, A.y, size, size);//отрисовываем исходный квадрат
        return 0;

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);


        this.setBackground(Colorist.set_Color(Start.back_col));
        g.setColor(Colorist.set_Color(Start.fract_col));
        Point A = new Point(Start.next_window_w / 2 - Start.next_window_h / 4, Start.next_window_h / 4);//координаты левого верхнего угла исходного квадрата
        drawTSQ(g, A, Start.next_window_h / 2 - Start.next_window_h / 10, iter);//вызываем отрисовку фрактала
        repaint();

    }
}
