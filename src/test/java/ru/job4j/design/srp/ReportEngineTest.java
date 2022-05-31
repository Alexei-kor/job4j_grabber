package ru.job4j.design.srp;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ReportEngineTest {

    @Ignore
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Petr", now, now, 200));
        store.add(new Employee("Sidor", now, now, 300));
        Report engine = new ReportAcc(store);
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Hired; Fired; Salary (thousand)").append(System.lineSeparator());
        expect.append("Ivan;").append(now).append(";").append(now).append(";").append(0.1).append(";").append(System.lineSeparator());
        expect.append("Petr;").append(now).append(";").append(now).append(";").append(0.2).append(";").append(System.lineSeparator());
        expect.append("Sidor;").append(now).append(";").append(now).append(";").append(0.3).append(";").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Petr", now, now, 200));
        store.add(new Employee("Sidor", now, now, 300));
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Salary;").append(System.lineSeparator());
        expect.append("Sidor;").append(300d).append(";").append(System.lineSeparator());
        expect.append("Petr;").append(200d).append(";").append(System.lineSeparator());
        expect.append("Ivan;").append(100d).append(";").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenDevGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Petr", now, now, 200));
        store.add(new Employee("Sidor", now, now, 300));
        Report engine = new ReportDev(store);
        StringBuilder expect = new StringBuilder();
        expect.append("<table>").append(System.lineSeparator());
        expect.append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>").append(System.lineSeparator());
        expect.append("<tr>")
                .append("<td>").append("Ivan").append("</td>")
                .append("<td>").append(now).append("</td>")
                .append("<td>").append(now).append("</td>")
                .append("<td>").append(100d).append("</td>")
                .append("</tr>")
                .append(System.lineSeparator());
        expect.append("<tr>")
                .append("<td>").append("Petr").append("</td>")
                .append("<td>").append(now).append("</td>")
                .append("<td>").append(now).append("</td>")
                .append("<td>").append(200d).append("</td>")
                .append("</tr>")
                .append(System.lineSeparator());
        expect.append("<tr>")
                .append("<td>").append("Sidor").append("</td>")
                .append("<td>").append(now).append("</td>")
                .append("<td>").append(now).append("</td>")
                .append("<td>").append(300d).append("</td>")
                .append("</tr>")
                .append(System.lineSeparator());
        expect.append("</table>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}