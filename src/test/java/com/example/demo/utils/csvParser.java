package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class csvParser {
    public static List readCsv(String filename) {
        List<Object[]> sample = new ArrayList();
        Object[][] test_sample = null;
        List<Map<String, String>> data = null;
        String path= ClassLoader.getSystemResource(filename).getPath();
        try {
            data = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(isr, 5 * 1024 * 1024);

            String tem;
            tem = reader.readLine();
            while ((tem = reader.readLine()) != null) {
                Map<String, String> m = new HashMap<>();
                String[] temp = tem.split(",");
                m.put("PassengerType", temp[0]);
                m.put("PassengerLevel", temp[1]);
                m.put("Cabin", temp[2]);
                m.put("Region", temp[3]);
                String[] package_info = temp[4].split("\\|");
                for (int i = 1; i <= package_info.length; i++) {
                    m.put("Baggage" + i, package_info[i - 1]);
                }
                m.put("num",String.valueOf(package_info.length));
                data.add(m);
            }
//    		System.out.println(data.size()+"   "+data.get(0).size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
