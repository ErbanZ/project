package com.example.demo.utils;

import com.example.demo.entity.Baggage;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class write2Xml {
    public static void write(String pt, String pl, String rg, String cb, String p,ArrayList<Baggage> baggages) throws IOException {
        //1.创建document对象，代表整个xml文档
        Document document=DocumentHelper.createDocument();
        Element s1=document.addElement("case");
        Element s2=s1.addElement("PassengerType");
        s2.setText(pt);
        Element s3=s1.addElement("PassengerLevel");
        s3.setText(pl);
        Element s4=s1.addElement("Region");
        s4.setText(rg);
        Element s5=s1.addElement("Cabin");
        s5.setText(cb);
        Element s6=s1.addElement("Baggages");
        for(int i=0;i<baggages.size();i++){
            Element s7=s6.addElement("Baggage");
            s7.setText(baggages.get(i).toString());
        }
        Element s8=s1.addElement("Price");
        s8.setText(p);
        //5.设置生成xml的格式
        OutputFormat format=OutputFormat.createPrettyPrint();
        //生成不一样的编码
        format.setEncoding("UTF-8");
        //6.生成xml文件
        FileWriter fw = new FileWriter("result.xml",true);
        XMLWriter writer=new XMLWriter(fw,format);
        //设置是否转义，默认设置是true,代表转义
        writer.setEscapeText(false);
        writer.write(document);
        writer.close();
    }
}
