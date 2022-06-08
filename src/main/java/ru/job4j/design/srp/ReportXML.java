package ru.job4j.design.srp;

import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Marshaller marshaller = MarshallerLocal.getInstance();
        String rsl = "";
        try (StringWriter writer = new StringWriter()) {
            Employees employees = new Employees();
            employees.setList(store.findBy(filter));
            marshaller.marshal(employees, writer);
            rsl = writer.getBuffer().toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
