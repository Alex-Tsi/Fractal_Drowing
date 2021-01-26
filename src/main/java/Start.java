import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.text.DecimalFormat;


import javax.swing.*;

public class Start extends Application {
    public static int next_window_h = 0;// painter window's height
    public static int next_window_w = 0;// painter window's wight
    public static int next_window_size_figure = 0;
    public static String sel_fig = "Круговой";
    public static String back_col = "Черный";
    public static String fract_col = "Желтый";


    @Override
    public void start(Stage stage) {

        Label label1 = new Label("Выберите размер фигуры:");
        Label label2 = new Label("Выберите форму отрисовки:");
        Label label3 = new Label("Выберите цвет фона:");
        Label label4 = new Label("Выберите цвет отрисовки:");
        TextField size_figure = new TextField("175");
        Slider slider = new Slider();
        Button drow = new Button("Рисовать");
        Figures Circle = new Figures("Круговой");
        Figures T_form = new Figures("Т - образный");
        Figures Snowflake = new Figures("Снежинка Коха");

        ObservableList<Figures> fig //
                = FXCollections.observableArrayList(Circle, T_form, Snowflake);

        ChoiceBox<Figures> choiceBox //
                = new ChoiceBox<Figures>(fig);

        /* Testing*/
        ObservableList<String> col //
                = FXCollections.observableArrayList("Белый", "Черный", "Красный", "Синий", "Желтый", "Зеленый", "Розовый");

        ChoiceBox<String> choiceBackCol //
                = new ChoiceBox<>(col);
        ChoiceBox<String> choiceFractCol
                = new ChoiceBox<>(col);

        choiceBox.setValue(Circle);
        choiceBackCol.setValue("Черный");
        choiceFractCol.setValue("Желтый");
        /**/
        slider.setMin(100);
        slider.setMax(250);
        slider.setValue(175);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setBlockIncrement(50);
        DecimalFormat dec = new DecimalFormat("#0");


        // Добавление слушателя событий для слайдера,
        // который меняет значение поля, пока бегает ползунок
        slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {

                size_figure.setText("" + dec.format(newValue));
            }
        });
        ChangeListener<Figures> changeListener = new ChangeListener<Figures>() {

            @Override
            public void changed(ObservableValue<? extends Figures> observable, //
                                Figures oldValue, Figures newValue) {
                if (newValue != null) {
                    sel_fig = newValue.toString();
                }
            }
        };
        choiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
        choiceBackCol.setOnAction(event -> back_col = choiceBackCol.getValue());
        choiceFractCol.setOnAction(event -> fract_col = choiceFractCol.getValue());
        // Добавление слушателя событий для кнопки,
        // которая инициализирует значения фигуры
        // и создает объект класса - новое окно и
        // и в нём рисуется сама фигура

        drow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                next_window_size_figure = (int) Double.parseDouble(size_figure.getText());

                if ((next_window_size_figure <= 250) && (next_window_size_figure >= 100)) {
                    next_window_h = next_window_size_figure * 3;
                    next_window_w = next_window_size_figure * 5;
                    Painter Fractal = new Painter();
                    Fractal.New(next_window_w, next_window_h);
                } else {
                    JOptionPane.showMessageDialog(null, "Введенное значение должно быть в диапазоне 100-250",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.getChildren().addAll(label2, choiceBox, label3, choiceBackCol, label4, choiceFractCol, label1, slider, size_figure, drow);
        stage.setTitle("Опции");
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void show() {
        Application.launch();
    }


}