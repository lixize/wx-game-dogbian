package org.zeyes.service;

import org.zeyes.controller.HeartController;

import java.text.SimpleDateFormat;
import java.util.*;

public class HeartService {
    Timer timer;
    HeartController c;
    private String token;
    private List<Integer> dogId;
    private static int index = 0;
    private Integer count;

    public HeartService() {
        c = new HeartController();
    }

    public void run() {
        index = 0;
        timer = new Timer();
        if (token != null && !token.isEmpty()) {
            System.out.println(c);
            System.out.println(token);
            c.setToken(token);
        }

        if (count != null) {
            c.setCount(count);
        } else {
            c.setCount(100);
        }
        TimerTask task = new HeartTimerTask(c);
        timer.schedule(task, 0L, 2000L);
        System.out.println("ok");
    }

    public void addObserver(Observer o) {
        if (o != null && c != null) {
            c.addObserver(o);
        }
    }

    public void cancel() {
        timer.cancel();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getDogId() {
        return dogId;
    }

    public void setDogId(List<Integer> dogId) {
        this.dogId = dogId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    class HeartTimerTask extends TimerTask {
        HeartController c;
        public HeartTimerTask(HeartController c) {
            this.c = c;
        }

        @Override
        public void run() {
            if (dogId != null && dogId.size() > 0) {
                c.setDogId(dogId.get(index++));
                index %= dogId.size();
                c.doHeart();
            } else {
                System.out.println("DogId Null");
            }
            //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //System.out.println(sf.format(System.currentTimeMillis()));
        }
    }
}

