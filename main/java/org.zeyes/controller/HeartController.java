package org.zeyes.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zeyes.bean.HeartPacket;
import org.zeyes.http.Client;
import org.zeyes.http.Request;
import org.zeyes.http.Response;
import org.zeyes.http.constant.ContentType;
import org.zeyes.http.enums.Method;

import java.util.*;

public class HeartController extends Observable {
    private final static Random rdm = new Random(System.currentTimeMillis());
    private final static Method method = Method.GET;
    private final static String host = "https://s315-1.11h5.com";
    private final static String path = "/10_105_18_176/6660/game";
    private static int seq = 10;
    private String token = "b099ae6a1ab96f8ff529cb8e32ed6b2a";
    private int dogId;
    private int count;

    public HeartPacket doHeart() {
        return doHeart(dogId, count);
    }

    /**
     * 模拟心跳
     * @param dogId 宠物狗的内部ID
     * @param count 点击次数（100以内）
     * @return 心跳结果
     */
    public HeartPacket doHeart(int dogId, int count) {
        String param = "?cmd=sync";
        param += "&token=" + token;
        param += "&a=[]";
        param += "&e1_p=0";
        param += "&now=" + Calendar.getInstance().getTimeInMillis();
        param += "&seq=" + (seq++);
        param += "&c=[["+dogId+","+count+"]]";
        param += "&e1=[]&e2_mer=[]&e2_eat=[]";
        System.out.println(host + param);
        Request request = new Request(method, host, path + param, 2000);
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        headers.put("content-type", ContentType.CONTENT_TYPE_JSON);
        headers.put("charset", "utf-8");
        request.setHeaders(headers);
        request.setQuerys(null);

        try {
            Response response = Client.execute(request);
            System.out.println(response.getStatusCode());
            //System.out.println(response.getBody());
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略Bean中不存在的字段
            HeartPacket hp = mapper.readValue(response.getBody(), HeartPacket.class);
            System.out.println(hp);
            setChanged();
            notifyObservers(hp);
            return hp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机生成[start...end)范围的随机数
     * @param start
     * @param end
     * @return
     */
    public int getRandom(int start, int end) {
        return Math.abs(rdm.nextInt()) % (end - start + 1) + start;
    }

    public static int getSeq() {
        return seq;
    }

    public static void setSeq(int seq) {
        HeartController.seq = seq;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
