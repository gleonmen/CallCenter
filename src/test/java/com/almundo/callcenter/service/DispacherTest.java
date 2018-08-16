package com.almundo.callcenter.service;

import com.almundo.callcenter.domain.Call;
import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.domain.employee.Director;
import com.almundo.callcenter.domain.employee.Operator;
import com.almundo.callcenter.domain.employee.Supervisor;
import com.almundo.callcenter.service.Dispatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class DispacherTest {

    private static List<Call> calls;
    private static List<Employee> employees;

    @Test
    public  void testDispacher(){
        calls = createCalls(20);
        employees = createEmployees(10);
        Dispatcher dispatcher = new Dispatcher(employees);
        for (Call aCall : calls) {
            dispatcher.dispatchCall(aCall);
        }
        dispatcher.finishAssignerJob();

    }


    private static List<Call> createCalls(int numCalls) {
        List<Call> calls = new ArrayList<>();
        String[] categories = {"TECHNICAL", "NEW PRODUCTS", "SALES"};
        for (int i = 1; i <= numCalls; i++) {
            calls.add(new Call((i + 1), "CALL CATEGORY #" + categories[(int) (Math.random() * 3)]));
        }
        return calls;
    }

    private static List<Employee> createEmployees(int numberEmployees) {

        List<Employee> employees = new ArrayList<>();
        if (numberEmployees > 2) {
            employees.add(new Supervisor(1));
            employees.add(new Director(2));
            for (long i = 3; i <= numberEmployees; i++) {
                employees.add(new Operator(i));
            }
        } else if (numberEmployees == 2) {
            employees.add(new Director(1));
            employees.add(new Supervisor(2));
        } else if (numberEmployees == 1) {
            employees.add(new Director(1));
        }
        return employees;
    }
}
