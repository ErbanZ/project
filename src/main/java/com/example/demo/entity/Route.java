package com.example.demo.entity;

import java.util.Map;

/**
 * 航线类型
 * isDomestic：是否为国内航班
 * isInternational：是否为国际航班
 * isViaUSA：是否途径美国
 */
public class Route {

    private boolean isDomestic;
    private boolean isInternational;

    public Route(boolean isDomestic, boolean isInternational) {
        this.isDomestic = isDomestic;
        this.isInternational = isInternational;
    }

    public Map<String,Object> checkRoute(){
        Map<String,Object> map=null;
        if(isDomestic==isInternational)
            map.put("msg","domestic的值不能与international相同");
        return map;
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public boolean isInternational() {
        return isInternational;
    }

}
