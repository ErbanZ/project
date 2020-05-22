package com.example.demo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class XmlUtil {

    public static List getXmlComent(String filename){
        //获取xml文件完全路径
        String path= ClassLoader.getSystemResource(filename).getPath();
        List contList=new ArrayList();
        //dom4j中读取xml文件的方法
        SAXReader saxR=new SAXReader();
        try {
            Document doc=saxR.read(path);
            //存放顶结点
            Element eleroot=doc.getRootElement();
            //parMap，存放顶结点下一级结点
            Map parMap=new HashMap();
            for(Iterator i=eleroot.elementIterator();i.hasNext();){
                //parMap中存放的结点的子结点
                Element elepar=(Element)i.next();
                Map sonMap=new HashMap();
                for(Iterator j=elepar.elementIterator();j.hasNext();){
                    Element eleSon=(Element)j.next();
                    sonMap.put(eleSon.getName(), eleSon.getText());
                }
                parMap.put(elepar.getName(),sonMap);
                contList.add(parMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return contList;
    }
}
