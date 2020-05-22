package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.ExceptionTwo;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class CalPrice {
    public static double getPrice(ArrayList<Baggage> baggageList,//行李
                                  String region,//航线区域
                                  String passengerType,//乘客类型（成年，婴儿）
                                  String passengerLevel,//乘客等级
                                  String cabin,//舱位类型
                                  Double ticketPrice
    ){
        ArrayList<ExceptionTwo> ExceptionList=CalFreeBaggageMax.Check(baggageList,region,passengerType,passengerLevel,cabin);
        double extraPrice=0;
        for(ExceptionTwo e : ExceptionList){
            switch (e.getValue()){
                case 1:{
                    extraPrice=ticketPrice*0.015*ExceptionList.get(0).getWeight_excess();
                    break;
                }
                case 2:{//件数超出限制
                    if (region.equals("AreaOne")){
                        switch (e.getNum_excess()){
                            case 0:
                                extraPrice+=0;
                                break;
                            case 1:
                                extraPrice+=1400;
                                break;
                            case 2:
                                extraPrice+=1400+2000;
                                break;
                            case 3:
                                extraPrice+=3400+(e.getNum_excess()-2)*3000;
                                break;
                        }
                    }
                    if (region.equals("AreaTwo")){
                        if(e.getNum_excess()==1){
                            extraPrice=1100;
                        }else if(e.getNum_excess()==2){
                            extraPrice=2200;
                        }else extraPrice=2200+(e.getNum_excess()-2)*1590;
                    }
                    if (region.equals("AreaThree")){
                        if(e.getNum_excess()==1){
                            extraPrice=1170;
                        }else if(e.getNum_excess()==2){
                            extraPrice=2340;
                        }else extraPrice=2340+(e.getNum_excess()-2)*1590;
                    }
                    if (region.equals("AreaFour")){
                        if(e.getNum_excess()==1){
                            extraPrice=1180;
                        }else if(e.getNum_excess()==2){
                            extraPrice=2360;
                        }else extraPrice=2360+(e.getNum_excess()-2)*1590;
                    }
                    if (region.equals("AreaFive")){
                        if(e.getNum_excess()==1){
                            extraPrice=830;
                        }else if(e.getNum_excess()==2){
                            extraPrice=1930;
                        }else extraPrice=1930+(e.getNum_excess()-2)*1590;
                    }
                    break;
                }

                case 3:{
                    if(region.equals("AreaOne")){
                        if(e.getWeight_excess()>23&&e.getWeight_excess()<=28){
                            extraPrice+=380;
                        }
                        if(e.getWeight_excess()>28&&e.getWeight_excess()<=32){
                            extraPrice+=980;
                        }
                    }
                    if(region.equals("AreaTwo")){
                        if(e.getWeight_excess()>23&&e.getWeight_excess()<=28){
                            extraPrice=280;
                        }
                        if(e.getWeight_excess()>28&&e.getWeight_excess()<=32){
                            extraPrice+=690;
                        }
                    }
                    if(region.equals("AreaThree")){
                        extraPrice+=520;
                    }
                    if(region.equals("AreaFour")){
                        if(e.getWeight_excess()>23&&e.getWeight_excess()<=28){
                            extraPrice+=690;
                        }
                        if(e.getWeight_excess()>28&&e.getWeight_excess()<=32){
                            extraPrice+=1040;
                        }
                    }
                    if(region.equals("AreaFive")){
                        if(e.getWeight_excess()>23&&e.getWeight_excess()<=28){
                            extraPrice+=210;
                        }
                        if(e.getWeight_excess()>28&&e.getWeight_excess()<=32){
                            extraPrice+=520;
                        }
                    }
                    break;
                }
                case 4:{
                    if(region.equals("AreaOne")){
                        extraPrice+=980;
                    }
                    if(region.equals("AreaTwo")){
                        extraPrice+=690;
                    }
                    if(region.equals("AreaThree")){
                        extraPrice+=520;
                    }
                    if(region.equals("AreaFour")){
                        extraPrice+=1040;
                    }
                    if(region.equals("AreaFive")){
                        extraPrice+=520;
                    }
                    break;
                }
                case 5:{
                    if(region.equals("AreaOne")){
                        extraPrice+=1400;
                    }
                    if(region.equals("AreaTwo")){
                        extraPrice+=1100;
                    }
                    if(region.equals("AreaThree")){
                        extraPrice+=520;
                    }
                    if(region.equals("AreaFour")){
                        extraPrice+=2050;
                    }
                    if(region.equals("AreaFive")){
                        extraPrice+=830;
                    }
                    break;
                }
            }
        }
        return extraPrice;
    }
}
