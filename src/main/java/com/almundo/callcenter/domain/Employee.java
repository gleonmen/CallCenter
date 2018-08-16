package com.almundo.callcenter.domain;

import com.almundo.callcenter.constants.EmployeeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.function.Supplier;


public abstract class Employee implements Supplier<String>, Comparable<Employee>{

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);
    private long id;
    private boolean isAvailable;
    private String type;
    private Call callIn;

    public Employee(long id, String type) {
        this.id = id;
        this.type = type;
        this.isAvailable = true;
        this.callIn = null;
    }

    public long getId() {
        return id;
    }

    public Call getCallIn() {
        return callIn;
    }

    public void setCallIn(Call callIn) {
        this.callIn = callIn;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    @Override
    public String get() {
        return attend();
    }

    @Override
    public int compareTo(Employee o) {
        int actual =EmployeeTypes.valueOf(this.getType()).getCode();
        int next =  EmployeeTypes.valueOf(o.getType()).getCode();

        if(actual > next){
            return 1;
        }else if(actual < next){
            return -1;
        }else{
            return 0;
        }

    }

    private String attend() {
        LOGGER.info("Call No " + callIn.getId() + " start at " + LocalDateTime.now());
        try {
            Thread.sleep(this.callIn.getDuration());
        } catch (InterruptedException e) {
            LOGGER.error("Error reported " + e.getMessage());

        }
        return this.attendCall();
    }

    public abstract String attendCall();

}

