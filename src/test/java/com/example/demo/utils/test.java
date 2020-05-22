package com.example.demo.utils;

import com.example.demo.entity.Baggage;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class test {

    public static ArrayList readXml(String filename){
        List list=new ArrayList();
        String path= ClassLoader.getSystemResource(filename).getPath();
        SAXReader saxReader = new SAXReader();
        try{
            Document document = saxReader.read(path);
            Element root = document.getRootElement();
            for (Iterator iter = root.elementIterator(); iter.hasNext(); ) {
                Element element = (Element) iter.next();
                HashMap<String,String> hm=new HashMap<>();
                for ( Iterator iterInner = element.elementIterator(); iterInner.hasNext(); ) {
                    int num=0;
                    Element elementInner = (Element) iterInner.next();
                    if(elementInner.getName().contains("PassengerType")||elementInner.getName().contains("PassengerLevel")||
                            elementInner.getName().contains("Region")||elementInner.getName().contains("Cabin")||
                            elementInner.getName().contains("Price"))
                    {
                        hm.put(elementInner.getName(), elementInner.getText());
                    }
                    else {
                        for ( Iterator iterInner1 = elementInner.elementIterator(); iterInner1.hasNext(); ) {
                            num++;
                            Element element1 = (Element) iterInner1.next();
                            hm.put(String.format(element1.getName()+"%s" , num),element1.getText());
                            hm.put("num", String.valueOf(num));
                        }
                    }

                }
                list.add(hm);
            }
        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return (ArrayList) list;
    }
}