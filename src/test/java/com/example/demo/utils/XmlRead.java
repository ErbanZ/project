package com.example.demo.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class XmlRead {

    public static ArrayList readXml(String filename){

        List list=new ArrayList();
        String path= ClassLoader.getSystemResource(filename).getPath();
        SAXReader saxReader = new SAXReader();
        try{
            Document document = saxReader.read(path);
            Element root = document.getRootElement();
            for ( Iterator iter = root.elementIterator(); iter.hasNext(); ) {
                Element element = (Element) iter.next();
                HashMap<String,String> hm=new HashMap<>();
                for ( Iterator iterInner = element.elementIterator(); iterInner.hasNext(); ) {
                    Element elementInner = (Element) iterInner.next();
                    hm.put(elementInner.getName(), elementInner.getText());
                }
                list.add(hm);
            }
        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return (ArrayList) list;
    }
}
