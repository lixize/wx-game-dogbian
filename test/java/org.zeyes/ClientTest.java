package org.zeyes;

import org.junit.Test;
import org.zeyes.controller.HeartController;
import org.zeyes.service.HeartService;

import java.util.Observable;
import java.util.Observer;

public class ClientTest {

    @Test
    public void test() {
        HeartController service = new HeartController();
        //service.sync();
    }

    @Test
    public void serviceTest() {
        MyObserver observer;
        HeartService service = new HeartService();
        service.setToken("b099ae6a1ab96f8ff529cb8e32ed6b2a");
        //Integer id = Integer.valueOf(425);
        Integer count = Integer.valueOf(100);
        //service.setDogId(id);
        service.setCount(count);
        observer = new MyObserver();
        service.addObserver(observer);
        service.run();
    }

    class MyObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("test");
        }
    }
}
