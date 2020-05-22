package com.example.demo.test;

import com.example.demo.entity.Baggage;
import com.example.demo.service.CalPrice;
import com.example.demo.utils.csvParser;
import com.example.demo.utils.test;
import com.example.demo.utils.write2Xml;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalNationalPriceTest {

    class Passenger{
        String region;
        String passengerType;
        String passengerLevel;
        String cabin;
        Double price;
        ArrayList<Baggage> baggages = new ArrayList<>();
        void addBaggage(Baggage baggage){
            baggages.add(baggage);
        }
        double ticketPrice;
    }
    private double calprice = 0;
    private double realprice = 0;

    @DataProvider(name ="dataProvider1")
    public Object[][] provider() throws IOException {
        List parList= test.readXml("national.xml");
        List<Object> item=new ArrayList<Object>();;
        for(int i=0;i<parList.size();i++){
            Map<String,String> map= (Map<String, String>) parList.get(i);
            Passenger passenger=new Passenger();
            passenger.region=map.get("Region");
            passenger.passengerType=map.get("PassengerType");
            passenger.passengerLevel=map.get("PassengerLevel");
            passenger.cabin=map.get("Cabin");
            passenger.price=Double.valueOf(map.get("Price"));
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
    public Object[][] provider2() throws IOException {
        List parList= csvParser.readCsv("National.csv");
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

    @AfterMethod
    public void tearDown(){
        if(calprice==realprice){
            System.out.println("计算价格："+calprice+"  实际价格："+realprice+"   pass");
        }else{
            System.out.println("计算价格："+calprice+"  实际价格："+realprice+"   fail");
        }

    }

    @Test(dataProvider = "dataProvider1")
    public void testCheck(Passenger passenger) throws Exception{
        realprice=passenger.price;
        calprice= CalPrice.getPrice(passenger.baggages,passenger.region,passenger.passengerType,passenger.passengerLevel,passenger.cabin,1000.0);
    }
}
