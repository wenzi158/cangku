package com.zhaoyue.chash;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhaoyue on 16-7-29.
 */
public class HashWork {
    public static String ip = null;
    public static long data3=0;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> arrayListt = new ArrayList<String>();
    public static HashMap<String,Integer> resultmap=new HashMap<String, Integer>();
    public static HashMap<Long, String> map = new HashMap<Long, String>();

    static void putNum(String IP){
        if(null == resultmap.get(IP)){
            resultmap.put(IP,0);
        }
        int plus=resultmap.get(IP);
        plus++;
        resultmap.put(IP, plus);
    }
    static int getNum(String IP){
        if(null == resultmap.get(IP)){
            resultmap.put(IP, 0);
        }
        return resultmap.get(IP);
    }

    public static long Hash(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return Long.parseLong(new String(str).substring(0, 16));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
//    public long hash(String key) {
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        md5.update(key.getBytes());
//        byte[] bKey = md5.digest();
//        long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8)
//                | (long) (bKey[0] & 0xFF);
//        return res & 0xffffffffL;
//    }

    public void add() {
        for (int i = 0; i < arrayList.size(); i++) {
            ip = arrayList.get(i);
            long chash = Hash(ip);
            long yushu = chash % (4294967295l);
            map.put(yushu, ip);
            for (long j = yushu; j <= 4294967295l; j = j + 1500000) {
                map.put(j, ip);
            }
            for (long j = yushu; j >= 0; j = j - 1500000) {
                map.put(j, ip);
            }
        }
    }

    public void data() {
        for (int m = 0; m < arrayListt.size(); m++) {
            String data1 = arrayListt.get(m);
            long data2 = Hash(data1);
            data3 = data2 % (4294967295l);
            while (map.get(data3) == null) {
                data3++;
            }
            if (map.get(data3).equals("192.168.200.1")){
                putNum("192.168.200.1");
            }else if (map.get(data3).equals("192.168.200.2")){
                putNum("192.168.200.2");
            }else if (map.get(data3).equals("192.168.200.3")){
                putNum("192.168.200.3");
            }else if(map.get(data3).equals("192.168.200.4")){
                putNum("192.168.200.4");
            }
        }
    }

    public static void main(String[] args) {
        HashWork hashWork = new HashWork();
        hashWork.arrayList.add("192.168.200.1");
        hashWork.arrayList.add("192.168.200.2");
        hashWork.arrayList.add("192.168.200.3");
        hashWork.arrayList.add("192.168.200.4");
        hashWork.add();
        for (int n=0;n<80;n++) {
            hashWork.arrayListt.add("This is a test!");
            hashWork.arrayListt.add("This also is a test");
            hashWork.arrayListt.add("My name is ZhaoYue!");
            hashWork.arrayListt.add("My age is 21!");
            hashWork.arrayListt.add("I am a man!");
            hashWork.arrayListt.add("I am chainese!");
            hashWork.arrayListt.add("I like table tennis!");
            hashWork.arrayListt.add("I love WenZi");
        }
        hashWork.data();
        System.out.println("192.168.200.1服务器上共收到"+getNum("192.168.200.1")+"条数据");
        System.out.println("192.168.200.2服务器上共收到"+getNum("192.168.200.2")+"条数据");
        System.out.println("192.168.200.3服务器上共收到"+getNum("192.168.200.3")+"条数据");
        System.out.println("192.168.200.4服务器上共收到"+getNum("192.168.200.4")+"条数据");
    }
}
