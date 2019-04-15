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
    CheckBox cbRal, cbDav;                                       // отметка если полимерное покрытие
    Label lblength, lbQuanS, lbSht, lbQuan, lbPrice, lbQuanL,
            lbSell, lbprofit, lbost, lbdav, lbdavprof;           // поле для вывода штрипса, цены и т.д.

    @Override
    public void start(Stage myStage)throws Exception{
        myStage.setTitle("Калькулятор расчета доборных элементов v.1.0");

        FlowPane rootNode = new FlowPane(10,10);

        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 700, 270);

        myStage.setScene(myScene);

        lblength = new Label("Длина ДЭ(мм): ");
        lbQuanS = new Label("Кол-во(шт): ");
        lbSht = new Label("Размер штрипса(мм): ");
        lbQuan = new Label("Кол-во шт в листе: ");
        lbPrice = new Label("Цена 1 м.п.: ");
        lbQuanL = new Label("Кол-во листов на заказ(шт): ");
        lbSell = new Label("Цена ДЭ за м.п. : ");
        lbprofit = new Label("Прибыль по заказу(руб): ");
        lbost = new Label("Остаток металла: ширина(мм) и кол-во(шт)");
        lbdav = new Label(""); // показывает стоимость давальческого проката при выбранном флаге cbDav
        lbdavprof = new Label("");

        btStart = new Button("Запуск");
        btClean = new Button("Очистить");

        cbRal = new CheckBox("RAL");
        cbDav = new CheckBox("Давальческий прокат");

       for(int i = 0; i < tf.length; i++){
           tf[i] = new TextField();
           tf[i].setPrefColumnCount(3);
       }

        tflength = new TextField();
        tflength.setPrefColumnCount(3);

        tfQuan = new TextField();
        tfQuan.setPrefColumnCount(3);

        tfPrice = new TextField();
        tfPrice.setPrefColumnCount(3);

        // описание кнопки запуск
        btStart.setOnAction((ae) ->{
            try {
                int sum = 0;
                int count = 0; // считает количество гибов и резов в ДЭ
                for (int i = 0; i < tf.length; i++) {
                    if (tf[i].getText().equals("")) continue;
                    else {
                        sum += Integer.parseInt(tf[i].getText());
                        count++;
                    }
                }
                int ral;
                if (cbRal.isSelected()) ral = 5;
                else ral = 0;

                int quan = 1250 / sum;
                lbSht.setText("Штрипс(мм): " + sum);
                lbQuan.setText("Кол-во шт в листе: " + quan);
                int list1 = Integer.parseInt(tfQuan.getText());

                // поле прайса для расчета давльческого проката
                int price;
                if(tfPrice.getText().equals("")) price = 0;
                else price = Integer.parseInt(tfPrice.getText());

                int length = Integer.parseInt(tflength.getText());
                int listsum = 0;
                int ost = 0;

                if (list1 % quan > 0) {
                    listsum = (list1 / quan) + 1;
                    ost = (1 - (list1 % quan) / quan) * price / list1;
                } else {
                    listsum = list1 / quan;
                }

                lbQuanL.setText("Кол-во листов на заказ(шт): " + listsum);
                int sell1 = (int) ((price * (1 + 0.1 * count) / quan) + ost + ral);
                int sell2 = (int) (price * (1 + 0.1 * count * quan) / quan);
                lbSell.setText("Цена ДЭ за м.п.: " + sell1 + " (Старая цена: " + sell2 + ")");


                double profit = 0.00;
                double profit1 = 0.00;

                profit = (list1 * sell1 - price * listsum) * length / 1000;
                profit1 = (list1 * sell2 - price * listsum) * length / 1000;

                lbprofit.setText("Прибыль по заказу(руб): " + profit + " (" + profit1 + ")");

                //расчет остатков металла
                int ost1 = 0,ost2 = 0;
                int num1, num2;
                ost1 = 1250 - sum*quan;
                if(list1%quan > 0 & ost1 != 0){
                    num1 = listsum -1;
                    ost2 = 1250 -((list1 % quan) * sum);
                    num2 = 1;
                    lbost.setText("Остаток металла длинной "+ length + ": " + ost1 + "(мм) "
                            + num1 + "(шт) и "+
                            ost2 + "(мм) " + num2 + "(шт)");
                }else if(list1%quan == 0 & ost1 == 0){
                    lbost.setText("Остаток металла: " + "Нет остатков!");
                } else if(list1%quan == 0 & ost1 != 0){
                num1 = listsum;
                lbost.setText("Остаток металла длинной " + length + ": " + ost1 + "(мм) " + num1 + "(шт)");
                }else if(list1%quan > 0 & ost1 == 0){
                    ost2 = 1250 -((list1 % quan) * sum);
                    num2 = 1;
                    lbost.setText("Остаток металла длинной " + length + ": " + ost2 + "(мм) " + num2 + "(шт)");
                }

                // расчет стоимости давальческого проката
                if (cbDav.isSelected()) {
                    if(price == 0){
                        int s1 = 0;
                        s1= (count*30*1000)/length + ral;
                        lbdav.setText("Цена 1 м.п. проката: "+ s1);
                        lbdavprof.setText("Прибыль по заказу: " + s1*list1 + "(руб)");
                    }else {
                        int s2 =0;
                        s2= (count*price*100)/length + ral;
                        lbdav.setText("Цена 1 м.п. проката: "+s2);
                        lbdavprof.setText("Прибыль по заказу: " + s2*list1  + "(руб)");
                    }

                }else {
                    lbdav.setText("");
                    lbdavprof.setText("");
                }

            }catch (Exception e){}
        });

        // описание кнопки очистить
        btClean.setOnAction((ae) -> {
            tfPrice.setText("");
            tflength.setText("");
            tfQuan.setText("");
            for(int i=0; i < tf.length;i++)
                tf[i].setText("");
        });

        //Использовать разделитель для лучшей организации ввода
        Separator separator [] = new Separator[5];
        for(int i=0; i< separator.length; i++) {
            separator[i]= new Separator();
            separator[i].setPrefWidth(700);
        }
  /*
        separator1.setPrefWidth(700);
        Separator separator2 = new Separator();
        separator2.setPrefWidth(700);
        Separator separator3 = new Separator();
        separator3.setPrefWidth(700);
*/
        rootNode.getChildren().addAll(tf[0], tf[1], tf[2], tf[3], tf[4], tf[5], tf[6], tf[7], tf[8], tf[9], separator[0],
                lblength, tflength, lbQuanS, tfQuan,lbSht,lbQuan,separator[1],
                lbPrice, tfPrice, cbRal, btClean, btStart,separator[2],
                lbQuanL, lbSell, lbprofit, separator[3],
                lbost, separator[4],
                cbDav, lbdav, lbdavprof);
        myStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}