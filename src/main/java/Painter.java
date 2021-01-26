import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.Color;

public class Painter extends JFrame {
    /**
     * class Painter
     * Данный класс выполняет функцию создания окна отрисовки
     * с учетом настроек, заданных пользователем:
     *
     * @param w - ширина окна
     * @param h - высота окна
     */
    public static void New(int w, int h) {
        JFrame window = new JFrame("Fractal Painting");

        window.setSize(w, h);


        switch (Start.sel_fig) {
            case "Круговой":
                window.setContentPane(new CircFract());
                break;
            case "Т - образный":
                window.setContentPane(new TSquare());
                break;
            case "Снежинка Коха":
                window.setContentPane(new Kohs_snowflake());
        }
        window.setResizable(false);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
