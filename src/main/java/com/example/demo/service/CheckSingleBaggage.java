package com.example.demo.service;

import com.example.demo.entity.Baggage;
import com.example.demo.entity.Route;
import com.example.demo.exception.ExceptionOne;

import java.util.Map;

/**
 * 计算单件行李是否超出限制
 */
public class CheckSingleBaggage {
    public static ExceptionOne check(Route route, Baggage baggage) {
        if (route.isDomestic()) {//国内航线
            if (baggage.getLength() > 100) {
                return new ExceptionOne(1, "行李长度超出限制" + (baggage.getSize() - 100) + "cm");
            }
            if (baggage.getWidth() > 60) {
                return new ExceptionOne(2, "行李宽度超出限制" + (baggage.getSize() - 60) + "cm");
            }
            if (baggage.getHeight() > 40) {
                return new ExceptionOne(3, "行李高度超出限制" + (baggage.getSize() - 40) + "cm");
            }
            if (baggage.getWeight() > 40) {
                return new ExceptionOne(4, "行李重量超出限制" + (baggage.getSize() - 40) + "kg");
            }
        }
        if (route.isInternational()) {
            if (baggage.getSize() > 203)
                return new ExceptionOne(5, "行李总尺寸超出限制" + (baggage.getSize() - 158) + "cm");
            if (baggage.getWeight() > 32)
                return new ExceptionOne(4, "该件行李重量超出限制" + (baggage.getWeight() - 32) + "kg");

        }
        return null;
    }

}

