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
    TextField tf []= new TextField[10];                          // поля для ввода размеров
    TextField tflength;                                          // длина ДЭ
    TextField tfQuan ;                                           // поле для ввода кол-ва шт.
    TextField tfPrice;                                           // поле для ввода цены за 1 м.п.
    Button btStart, btClean;                                     // запуск расчета, очистка полей размеров
    CheckBox cbRal;                                              // отметка если полимерное покрытие
    Label lblength, lbQuanS, lbSht, lbQuan, lbPrice, lbQuanL,lbSell, lbprofit;                         // поле для вывода штрипса, количесва шт. в листе и цены

    @Override
    public void start(Stage myStage)throws Exception{
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

        tf[0]=new TextField();
        //tf[0].setPromptText("Введите имя файла1.");
        tf[0].setPrefColumnCount(4);

        tf[1] = new TextField();
        tf[1].setPrefColumnCount(4);
        tf[2] = new TextField();
        tf[2].setPrefColumnCount(4);
        tf[3] = new TextField();
        tf[3].setPrefColumnCount(4);
        tf[4] = new TextField();
        tf[4].setPrefColumnCount(4);
        tf[5] = new TextField();
        tf[5].setPrefColumnCount(4);
        tf[6] = new TextField();
        tf[6].setPrefColumnCount(4);
        tf[7] = new TextField();
        tf[7].setPrefColumnCount(4);
        tf[8] = new TextField();
        tf[8].setPrefColumnCount(4);
        tf[9] = new TextField();
        tf[9].setPrefColumnCount(4);



        tflength = new TextField();
        tflength.setPrefColumnCount(4);

        tfQuan = new TextField();
        tfQuan.setPrefColumnCount(4);

        tfPrice = new TextField();
        tfPrice.setPrefColumnCount(7);


        btStart.setOnAction((ae) ->{
            int sum = 0;
            for(int i=0; i < tf.length;i++){
                sum += Integer.parseInt(tf[i].getText()) ;
            }
            int quan = 1250 / sum;
            lbSht.setText("Штрипс(мм): "+ sum);
            lbQuan.setText("Кол-во шт в листе: " + quan);

    });



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

        rootNode.getChildren().addAll(tf[0], tf[1], tf[2], tf[3], tf[4], tf[5], tf[6], tf[7], tf[8], tf[9], separator1,
                lblength, tflength, lbQuanS, tfQuan,lbSht,lbQuan,separator2,
                lbPrice, tfPrice, cbRal, btClean, btStart,separator3,
                lbQuanL, lbSell, lbprofit);
        myStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}