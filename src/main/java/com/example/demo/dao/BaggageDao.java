package com.example.demo.dao;

import com.example.demo.entity.Baggage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class BaggageDao {
    private static ArrayList<Baggage> baggages=null;

    static{
        baggages=new ArrayList<Baggage>();
        baggages.add(0,new Baggage(0,0,0,0));
    }

    private static Integer initId = 1;
    public void save(Baggage baggage){
        if(baggage.getId()==null){
            baggage.setId(initId++);
        }
        baggages.add(initId-1,baggage);
    }

    public ArrayList<Baggage> getAll(){
        return baggages;
    }
    public Baggage get(Integer id){
        return baggages.get(id);
    }

    public void delete(Integer id){
        baggages.remove(id);
    }

}
