package com.example.demo.controller;

import com.example.demo.dao.BaggageDao;
import com.example.demo.entity.*;
import com.example.demo.exception.ExceptionOne;
import com.example.demo.service.CalPrice;
import com.example.demo.service.CheckSingleBaggage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class myController {
    @Autowired
    private BaggageDao baggageDao;


    @GetMapping("/Baggage_calculator")
    public String toPage(Model model){
        ArrayList<Baggage> baggages=baggageDao.getAll();
        model.addAttribute("baggages",baggages);
        return "Baggage_calculator";
    }


    //来到添加页面
    @GetMapping("/Baggage_calculator/addBaggages")
    public  String toAddBaggagePage(){
        return "addBaggages";
    }

    //行李添加
    @PostMapping("/Baggage_calculator")
    public String Addbaggage(Baggage baggage)
    {
        //重定向
        System.out.println(baggage.getSize());
        baggageDao.save(baggage);
        return "redirect:/Baggage_calculator";
    }

    @PostMapping("/Baggage_calculator/result")
    public String result(@RequestParam("Cabin") String cabin,
                         @RequestParam("PassengerType") String passengerType,
                         @RequestParam("PassengerLevel") String passengerLevel,
                         @RequestParam("Region") String region,
                         @RequestParam("price") double price,
                         Model model){
        ArrayList<Baggage> baggages=baggageDao.getAll();
        Route r=null;
        if(region.equals("AreaZero")){
            r=new Route(true,false);
        }else{
            r=new Route(false,true);
        }
        double p= CalPrice.getPrice(baggages,region,passengerType,passengerLevel,cabin,price);
        System.out.println(p);
        String s="所需额外费用是:"+p+"元。";
        model.addAttribute("msg",s);
        return "result";
    }

}
