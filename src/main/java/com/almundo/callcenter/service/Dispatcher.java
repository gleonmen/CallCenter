package com.almundo.callcenter.service;


import com.almundo.callcenter.domain.Call;
import com.almundo.callcenter.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dispatcher.class);
    private static final int MAX_VALUE = 10000;
    private static final int MIN_VALUE = 5000;
    private List<Employee> employees;
    private ExecutorService dispatcher;

    public Dispatcher(List<Employee> employees) {
        this.employees = employees;
        dispatcher = Executors.newFixedThreadPool(employees.size());
    }


    private Employee getEmployeeAvailable() {
        return employees.stream().sorted().filter(Employee::isAvailable).findFirst().orElse(null);
    }

    public void finishAssignerJob() {
        dispatcher.shutdown();
    }

    public void dispatchCall(Call call) {
        call.setDuration((int) (Math.random() * MAX_VALUE) + MIN_VALUE);
        Employee employeeAvailable = getEmployeeAvailable();
        if (employeeAvailable != null) {
            employeeAvailable.setCallIn(call);
            employeeAvailable.setAvailable(false);
            CompletableFuture
                    .supplyAsync(employeeAvailable, dispatcher)
                    .thenAccept(response -> {
                        LOGGER.info("RESPONSE " + response);
                        employeeAvailable.setAvailable(true);
                        employeeAvailable.setCallIn(null);
                    });
        } else {
            putOnWait(call);
        }
    }

    private void putOnWait(Call call) {
        try {
            Thread.sleep(MAX_VALUE);
        } catch (InterruptedException e) {
            LOGGER.error("Error %s, Msg: %s ", e.getMessage(), e.getMessage());
            e.printStackTrace();
        }
        dispatchCall(call);
    }
}
