package com.yk.bug.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
@Service
public class BugService {

    /**
     * version:3.0
     * 采用web展示
     */
//    private static final ResourceBundle rb = ResourceBundle.getBundle("resources/gibing");

//    private static final ResourceBundle rb = ResourceBundle.getBundle("com/self/bug/resources/gibing");
    private static final String[] str = {"A", "B", "C", "D"};
    private static String substring;
    private static String session;
    private static int max;
    private static int min;
    private static double status;
    private static String urlPath = "submit=9057%2C9096%2C9360%2C9361%2C9065" +
            "%2C9328%2C17636%2C14414%2C14357%2C9115%2C9153%2C9108" +
            "%2C9350%2C9131%2C9143%2C9139%2C14489%2C14391%" +
            "2C9059%2C9103%2C9124%2C9309%2C9321%2C14403%" +
            "2C9068%2C9165%2C9105%2C9369%2C9380%2C14383%2C9377%" +
            "2C14543%2C9155%2C9116%2C9376%2C9179%2C14412%2C17624%" +
            "2C9123%2C14382%2C17632%2C9110%2C14374%2C9375%" +
            "2C9085%2C14400%2C9075%2C14483%2C9195%2C14542&" +
            "answer=C%2CB%2CC%2CABCD%2CC%2CA%2CA%2CB%2CB%2CD%2CD%" +
            "2CA%2CC%2CC%2CA%2CC%2CC%2CB%2CAC%2CC%2CC%2CC%2CAD%2CA%" +
            "2CC%2CA%2CAB%2CD%2CD%2CA%2CC%2CB%2CB%2CA%2CC%2CB%2CD%2CB%" +
            "2CD%2CA%2CD%2CA%2CC%2CD%2CBC%2CA%2CAB%2CAB%2CC%2CD&time=" ;

    public void realBug(HttpServletRequest req, HttpServletResponse resp) throws IOException, InterruptedException {
        session = req.getParameter("session");
        int loop = Integer.parseInt(req.getParameter("loopNum"));
        max = Integer.parseInt(req.getParameter("maxNum"));
        min = Integer.parseInt(req.getParameter("minNum"));
        PrintWriter out = resp.getWriter();
        Map<String, Double> map = new HashMap<>();
        String s = null;
        for (int i = 0; i < loop; i++) {
            get();
            double num = bug(); // 这里返回的是分钟 分钟换算成等多少秒
            System.out.println(num + "min");
            double ms = num * 60 * 1000;
            System.out.println("需要等待" + (num * 60 * 1000) + 3000 + "ms");
            map.put("min" + i, num);
            map.put("ms" + i, ms);
            map.put("status" + i, status);
            System.out.println(min);
            System.out.println(max);
            s = JSONObject.toJSONString(map);
            System.out.println(s);
            Thread.sleep(5000);
        }
        out.print(s);
//MzIwOTk2ZDMtNzYyYi00MzI0LTgyZmQtNjQ0NWQ5MTY1Njk3

    }


    public static String readModel(String urlPath, String[] model) {
        if (substring == null) {
            System.out.println("substring为空");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        /*
         * answer=C%2CB%2CC%2CABCD%2CC%2CA%2CA%2CB%2CB%2CD%2CD%2CA%2CC%2CC%2CA%2CC%2CC%2CB%2CAC%2CC%2CC%2CC%2CAD%2CA%2CC%2CA%2CAB%2CD%2CD%2CA%2CC%2CB%2CB%2CA%2CC%2CB%2CD%2CB%2CD%2CA%2CD%2CA%2CC%2CD%2CBC%2CA%2CAB%2CAB%2CC%2CD*/

        sb.append("answer=");
        for (int i = 0; i < model.length; i++) {
            if (i != 0) {
                sb.append("%2C");
            }
            sb.append(model[i]);
        }
        return urlPath.replaceAll(substring, sb.toString());
    }

    public static String[] getTrueAnswer(String urlPath) {
        String[] trueAnswer;
        substring = urlPath.substring(urlPath.indexOf("answer="), urlPath.indexOf("&time"));
        trueAnswer = substring.split("%");
        for (int i = 0; i < trueAnswer.length; i++) {
            if (i == 0) {
                String[] split = trueAnswer[0].split("=");
                trueAnswer[i] = split[i + 1];
            } else {
                trueAnswer[i] = trueAnswer[i].substring(2);
            }
        }
        return trueAnswer;
    }

    public static String[] getFalseAnswer(String[] trueAnswer) {
        String[] falseAnswer = new String[trueAnswer.length];
        for (int i = 0; i < trueAnswer.length; i++) {
            int j = (int) (Math.random() * 4);
            falseAnswer[i] = str[j];
        }
        return falseAnswer;
    }

    public static String[] mix(String[] trueAnswer, String[] falseAnswer, int mistake) {
        String[] arr = new String[trueAnswer.length];
        int index = mistake;
        for (int i = mistake; i < arr.length; i++) {
            if (index >= 0) {
                // 原数组 0表示从哪儿开始， 新数组 从0开始 mistake复制多少个
                System.arraycopy(falseAnswer, 0, arr, 0, mistake);
                index = -1;
            }
            arr[i] = trueAnswer[i];
        }
        return arr;
    }

    public static void get() throws IOException {
        String url = "https://www.fzhd.fuzhouhuada.com:81/examTest/examination?courseId=502&typesOf=1";
        URL obj = new URL(url); // 填入网址
        // 打开连接
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (WindowsNT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36 SLBrowser/8.0.1.1171 SLBChan/103");
        con.setRequestProperty("Cookie", "SESSION=" + session);
        con.setDoOutput(true);
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
    }

    public static double bug() throws IOException {
        // 生成一个2-5 分钟之间的值
        double random = (2 + (Math.random() * 3));
        String url = "https://www.fzhd.fuzhouhuada.com:81/examAjax/correctExamination";
        URL obj = new URL(url); // 填入网址
        // 打开连接
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                " Chrome/92.0.4515.131 Safari/537.36 SLBrowser/8.0.1.1171 SLBChan/103");

        con.setRequestProperty("Cookie", "SESSION=" + session);
        random = new BigDecimal(random).setScale(2, RoundingMode.HALF_UP).doubleValue();
        urlPath = urlPath + random + "&courseId=502";
        System.out.println("max" + max);
        int i = (int) (min + Math.random() * (max - min + 1));
        String[] trueAnswer = getTrueAnswer(urlPath);
        String[] falseAnswer = getFalseAnswer(trueAnswer);
        String[] model = mix(trueAnswer, falseAnswer, i); // mistake是从0开始的 所以请请注意！！
        String s = readModel(urlPath, model);
        System.out.println(random);
        con.setDoOutput(true);

        DataOutputStream dos = new DataOutputStream(con.getOutputStream());
        if (s == null) {
            System.out.println("null存在");
            return 0.0;
        }
        dos.writeBytes(s);
        dos.flush();
        dos.close();

        status = con.getResponseCode();
        System.out.println(status);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        System.out.println(response);
        return random;
    }

}
