package com.example.demo.test;

import com.example.demo.entity.Baggage;
import com.example.demo.exception.ExceptionTwo;
import com.example.demo.service.CalFreeBaggageMax;
import com.example.demo.utils.XmlRead;
import com.example.demo.utils.csvParser;
import com.example.demo.utils.test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalFreeBaggageMaxTest {
    private ArrayList<ExceptionTwo> ExceptionList;
    private static Object[][] obj;

    class Passenger{
        String region;
        String passengerType;
        String passengerLevel;
        String cabin;
        ArrayList<Baggage> baggages = new ArrayList<>();
        void addBaggage(Baggage baggage){
            baggages.add(baggage);
        }

        @Override
        public String toString() {
            return "Passenger{" +
                    "region='" + region + '\'' +
                    ", passengerType='" + passengerType + '\'' +
                    ", passengerLevel='" + passengerLevel + '\'' +
                    ", cabin='" + cabin + '\'' +
                    ", baggages=" + baggages +
                    '}';
        }
    }

    @DataProvider(name="dataProvider1")
    public Object[][] Numbers() throws IOException {
        List parList= test.readXml("domestic.xml");
        List<Object> item=new ArrayList<Object>();;
        for(int i=0;i<parList.size();i++){
            Map<String,String> map= (Map<String, String>) parList.get(i);
            Passenger passenger=new Passenger();
            passenger.region=map.get("Region");
            passenger.passengerType=map.get("PassengerType");
            passenger.passengerLevel=map.get("PassengerLevel");
            passenger.cabin=map.get("Cabin");
            int num=Integer.valueOf(map.get("num"));
            for(int j=1;j<=num;j++){
                double length= Double.valueOf(map.get("Baggage"+String.valueOf(j)).split("-")[0]);
                double width= Double.valueOf(map.get("Baggage"+String.valueOf(j)).split("-")[1]);
                double height= Double.valueOf(map.get("Baggage"+String.valueOf(j)).split("-")[2]);
                double weight= Double.valueOf(map.get("Baggage"+String.valueOf(j)).split("-")[3]);
                passenger.addBaggage(new Baggage(weight,length,width,height));
            }
            item.add(passenger);
        }
        Object[][] result=new Object[item.size()][];
        for(int i=0;i<item.size();i++){
            result[i]=new Object[]{item.get(i)};
        }
        return  result;
    }

    @DataProvider(name ="dataProvider2")
    public Object[][] provider() throws IOException {
        List parList=XmlRead.readXml("data.xml");
        System.out.println(parList);
        List<Object> item=new ArrayList<Object>();;
        for(int i=0;i<parList.size();i++){
            Map<String,String> map= (Map<String, String>) parList.get(i);
            Passenger passenger=new Passenger();
            passenger.region=map.get("Region");
            passenger.passengerType=map.get("PassengerType");
            passenger.passengerLevel=map.get("PassengerLevel");
            passenger.cabin=map.get("Cabin");
            passenger.addBaggage(new Baggage(Double.valueOf(map.get("weight")),Double.valueOf(map.get("length")),Double.valueOf(map.get("width")),Double.valueOf(map.get("height"))));
            item.add(passenger);
        }
        Object[][] result=new Object[item.size()][];
        for(int i=0;i<item.size();i++){
            result[i]=new Object[]{item.get(i)};
            System.out.println(result[i]);
        }
        return  result;

    }

    @AfterMethod
    public void tearDown() {
        if (ExceptionList.size()==0){
            System.out.println("该乘客的行李可以免费托运");
        }else {
            for (ExceptionTwo e:ExceptionList){
                System.out.println(e.getDesc()+",数量超出"+e.getNum_excess()+
                        ",尺寸超出"+e.getSieze_excess()+",重量超出"+e.getWeight_excess());
            }
        }
    }

    @Test(dataProvider = "dataProvider1")
    public void testCheck1(Passenger passenger) {
        System.out.println(passenger.baggages.toString());
        ExceptionList = CalFreeBaggageMax.Check(
                passenger.baggages,
                passenger.region,
                passenger.passengerType,
                passenger.passengerLevel,
                passenger.cabin);
    }
}
