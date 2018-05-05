package org.zeyes.ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.zeyes.bean.HeartPacket;
import org.zeyes.service.HeartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DogController {
    private boolean run = false;
    HeartService service;
    MyObserver observer;
    @FXML
    private Button btnOk;
    @FXML
    private TextField tf_total;
    @FXML
    private TextField tf_add;
    @FXML
    private Label lb_status;
    @FXML
    private TextField tf_token;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_count;

    @FXML
    public void initialize() {
        /*tf_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_id.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });*/
        tf_count.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_count.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void onBtnOkAction(ActionEvent event) {
        run = !run;
        if (run) {
            btnOk.setText("停止");
            lb_status.setText("开始运行...");
            service = new HeartService();
            service.setToken(tf_token.getText());
            String ids_str = tf_id.getText();
            String ids[] = ids_str.split(",");
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                list.add(Integer.valueOf(ids[i].trim()));
            }
            Integer count = Integer.valueOf(tf_count.getText());
            service.setDogId(list);
            service.setCount(count);
            if (observer == null) { observer = new MyObserver(); }
            service.addObserver(observer);
            service.run();
        } else {
            btnOk.setText("开始");
            if (service != null) {
                service.cancel();
            }
            lb_status.setText("已经停止.");
        }


    }


    class MyObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            HeartPacket hp = (HeartPacket)arg;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    if (hp != null) {
                        BigDecimal gold = new BigDecimal(hp.getGold());
                        BigDecimal add = new BigDecimal(hp.getAddGold());
                        String errorInfo = hp.getError();
                        if (errorInfo == null || errorInfo.isEmpty()) {
                            tf_total.setText(gold.toString());
                            tf_add.setText(add.toString());
                            lb_status.setText("正常");
                        } else {
                            lb_status.setText(errorInfo);
                            //run = false;
                            //if (service != null) {
                            //    service.cancel();
                            //}
                            //btnOk.setText("开始");
                            //lb_status.setText("已经停止.");
                        }
                    }
                }
            });

        }
    }
}