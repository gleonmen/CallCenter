package com.almundo.callcenter.domain.employee;


import com.almundo.callcenter.constants.EmployeeTypes;
import com.almundo.callcenter.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


public class Director extends Employee {
    private static final Logger LOGGER = LoggerFactory.getLogger(Director.class);

    public Director(long id) {
        super(id, EmployeeTypes.DIRECTOR.getValue());
    }

    @Override
    public String attendCall() {
        LOGGER.info("Call No " + this.getCallIn().getId() + " Good Morning I am  a " + this.getType() + " Number: " + this.getId());
        this.getCallIn().solve(this.getId());
        LOGGER.info("Call No " + this.getCallIn().getId() + " end at " + LocalDateTime.now());
        return "Call No " + this.getCallIn().getId() + " Description " + this.getCallIn().getDescription() + " Resolved by " + this.getType() + " " + this.getId();
    }
}