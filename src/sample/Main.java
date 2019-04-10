package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import  javafx.scene.control.*;
import  javafx.event.*;
import javafx.geometry.*;
import java.io.*;

public class Main extends Application {
    TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10; // поля для ввода размеров
    TextField tflength;                                          // длина ДЭ
    TextField tfQuan ;                                           // поле для ввода кол-ва шт.
    TextField tfPrice;                                           // поле для ввода цены за 1 м.п.
    Button btStart, btClean;                                     // запуск расчета, очистка полей размеров
    CheckBox cbRal;                                              // отметка если полимерное покрытие
    Label lblength, lbQuanS, lbSht, lbQuan, lbPrice, lbQuanL,lbSell, lbprofit;                         // поле для вывода штрипса, количесва шт. в листе и цены

    @Override
    public void start(Stage myStage){
        myStage.setTitle("Калькулятор расчета доборных элементов v.1.0");

        FlowPane rootNode = new FlowPane(10,10);

        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 700, 200);

        myStage.setScene(myScene);

        lblength = new Label("Длина ДЭ(мм): ");
        lbQuanS = new Label("Кол-во(шт): ");
        lbSht = new Label("Размер штрипса(мм): ");
        lbQuan = new Label("Кол-во шт в листе: ");
        lbPrice = new Label("Цена 1 м.п.: ");
        lbQuanL = new Label("Кол-во листов на заказ(шт): ");
        lbSell = new Label("Цена ДЭ за м.п. : ");
        lbprofit = new Label("Прибыль по заказу(руб): ");

        btStart = new Button("Запуск");
        btClean = new Button("Очистить");

        cbRal = new CheckBox("RAL");

        tf1 = new TextField();
        //tf1.setPromptText("Введите имя файла1.");
        tf1.setPrefColumnCount(4);

        tf2 = new TextField();
        tf2.setPrefColumnCount(4);
        tf3 = new TextField();
        tf3.setPrefColumnCount(4);
        tf4 = new TextField();
        tf4.setPrefColumnCount(4);
        tf5 = new TextField();
        tf5.setPrefColumnCount(4);
        tf6 = new TextField();
        tf6.setPrefColumnCount(4);
        tf7 = new TextField();
        tf7.setPrefColumnCount(4);
        tf8 = new TextField();
        tf8.setPrefColumnCount(4);
        tf9 = new TextField();
        tf9.setPrefColumnCount(4);
        tf10 = new TextField();
        tf10.setPrefColumnCount(4);

        tflength = new TextField();
        tflength.setPrefColumnCount(4);

        tfQuan = new TextField();
        tfQuan.setPrefColumnCount(4);

        tfPrice = new TextField();
        tfPrice.setPrefColumnCount(7);


/*
        button.setOnAction((ae)-> {
            int i = 0, j = 0, k = 0;

            // убедимся что введены имена обоих файлов
            if (tf1.getText().equals("")) {
                result.setText("Отсутствует имя первого файла");
                return;
            }
            if (tf2.getText().equals("")) {
                result.setText("Отсутствует имя второго файла");
                return;

            // сравнить файлы, используя инструкцию try с ресурсами
            try (FileInputStream f1 = new FileInputStream(tf1.getText());
                 FileInputStream f2 = new FileInputStream(tf2.getText())) {
                do {
                    i = f1.read();
                    j = f2.read();
                    k++;
                    if (i != j & (i - 32) != j & (i + 32) != j) break; // вне зависимости от регистра
                } while (i != -1 && j != -1);

                if (i != j) {
                    result.setText("Файлы отличаются");
                    if (checkBox.isSelected())
                        checkBox.setText("Позиция расхождения: " + k);
                    else
                        checkBox.setText("");
                } else
                    result.setText("Файлы одинаковы");

            } catch (IOException exc) {
                result.setText("Ошибка файла");
            }
        });



        tf1.setOnAction( (ae) -> button.fire());
        tf2.setOnAction( (ae) -> button.fire());
*/
        //Использовать разделитель для лучшей организации ввода
        Separator separator1 = new Separator();
        separator1.setPrefWidth(700);
        Separator separator2 = new Separator();
        separator2.setPrefWidth(700);
        Separator separator3 = new Separator();
        separator3.setPrefWidth(700);

        rootNode.getChildren().addAll(tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, separator1,
                lblength, tflength, lbQuanS, tfQuan,lbSht,lbQuan,separator2,
                lbPrice, tfPrice, cbRal, btClean, btStart,separator3,
                lbQuanL, lbSell, lbprofit);

        myStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}