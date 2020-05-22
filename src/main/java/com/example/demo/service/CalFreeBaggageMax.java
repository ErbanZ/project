package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.ExceptionOne;
import com.example.demo.exception.ExceptionTwo;


import java.util.ArrayList;


/**
 * 计算单个乘客所带行李是否在免费托运范围内
 */
public class CalFreeBaggageMax {
    public static ArrayList<ExceptionTwo> Check(ArrayList<Baggage> baggageList,//行李
                                                String region,//航线区域
                                                String passengerType,//乘客类型（成年，婴儿）
                                                String passengerLevel,//乘客等级
                                                String cabin//舱位类型
    ){
        double weightLimit = 0;//单件重量限制（国内航班特殊处理）
        int numLimit = 0;//数目限制
        double sizeLimit = 158;//单件尺寸限制，默认158cm
        ArrayList<ExceptionTwo> ExceptionList =new ArrayList<>();

        switch(region){
            case "AreaZero":{//国内航班
                if(passengerType.equals("TypeOne")){//成人或儿童客票
                    switch(cabin){
                        case "FirstClass":{
                            weightLimit=40;
                            break;
                        }
                        case "BusinessClass":{
                            weightLimit=30;
                            break;
                        }
                        case "EconomyClass":
                            weightLimit=20;
                            break;
                        case "EnjoyEconomyClass":
                            weightLimit=20;
                            break;
                        case "SuperEconomyClass":
                            weightLimit=20;
                            break;
                    }
                }else if(passengerType.equals("TypeTwo")){//婴儿客票
                    weightLimit=10;
                }
                double extraWeight=0;//会员额外重量
                switch (passengerLevel){
                    case "Normal":
                        extraWeight=0;
                        break;
                    case "classOne":
                        extraWeight=30;
                        break;
                    case "classTwo":
                        extraWeight=20;
                        break;
                    case "classThree":
                        extraWeight=20;
                        break;
                }
                double totalWeight = 0;
                for(Baggage baggage:baggageList){
                    totalWeight+=baggage.getWeight();
                }
                if(totalWeight>weightLimit+extraWeight){
                    ExceptionList.add(new ExceptionTwo(1,"总重量超出限制",0,(totalWeight-weightLimit-extraWeight),0));
                }
                return ExceptionList;
            }


            //国外航线 区域A
            case "AreaThree":{
                switch (cabin) {
                    case "FirstClass":
                        weightLimit = 32;
                        numLimit = 2;
                        break;
                    case "BusinessClass":
                        weightLimit = 32;
                        numLimit = 2;
                        break;
                    case "EnjoyEconomyClass":
                        weightLimit = 23;
                        numLimit = 2;
                        break;
                    case "SuperEconomyClass":
                        weightLimit = 23;
                        numLimit = 2;
                        break;
                    case "EconomyClass":
                        weightLimit = 23;
                        numLimit = 1;
                        break;
                }
                break;
            }
            case "AreaFour": {
                switch (cabin) {
                    case "FirstClass":
                        weightLimit = 32;
                        numLimit = 2;
                        break;
                    case "BusinessClass":
                        weightLimit = 32;
                        numLimit = 2;
                        break;
                    case "EnjoyEconomyClass":
                        weightLimit = 23;
                        numLimit = 2;
                        break;
                    case "SuperEconomyClass":
                        weightLimit = 23;
                        numLimit = 2;
                        break;
                    case "EconomyClass":
                        weightLimit = 23;
                        numLimit = 1;
                        break;
                }
                break;
            }

            //区域B
            case "AreaOne":{
                switch(cabin){
                    case "FirstClass":
                        weightLimit=32;
                        numLimit=2;
                        break;
                    case "BusinessClass":
                        weightLimit=32;
                        numLimit=2;
                        break;
                    case "EnjoyEconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                    case "SuperEconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                    case "EconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                }
                break;
            }
            case "AreaTwo":{
                switch(cabin){
                    case "FirstClass":
                        weightLimit=32;
                        numLimit=2;
                        break;
                    case "BusinessClass":
                        weightLimit=32;
                        numLimit=2;
                        break;
                    case "EnjoyEconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                    case "SuperEconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                    case "EconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                }
                break;
            }
            case "AreaFive":{
                switch(cabin){
                    case "FirstClass":
                        weightLimit=32;
                        numLimit=2;
                        break;
                    case "BusinessClass":
                        weightLimit=32;
                        numLimit=2;
                        break;
                    case "EnjoyEconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                    case "SuperEconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                    case "EconomyClass":
                        weightLimit=23;
                        numLimit=2;
                        break;
                }
                break;
            }
        }

        if(baggageList.size()>numLimit)
            ExceptionList.add(new ExceptionTwo(2,"超出免费行李件数限制",baggageList.size()-numLimit,0,0));

        for(Baggage baggage:baggageList){
            if(baggage.getWeight()>weightLimit&&baggage.getSize()<=sizeLimit)
                ExceptionList.add(new ExceptionTwo(3,"单件行李超重不超尺寸",0,baggage.getWeight()-weightLimit,0));
            if(baggage.getWeight()<=weightLimit&&baggage.getSize()>sizeLimit)
                ExceptionList.add(new ExceptionTwo(4,"单件行李不超重超尺寸",0,0,baggage.getSize()-sizeLimit));
            if(baggage.getWeight()>weightLimit&&baggage.getSize()>sizeLimit)
                ExceptionList.add(new ExceptionTwo(5,"单件行李超重超尺寸",0,baggage.getWeight()-weightLimit,baggage.getSize()-sizeLimit));
        }
        return ExceptionList;
    }

}
