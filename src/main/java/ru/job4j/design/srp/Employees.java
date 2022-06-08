package ru.job4j.design.srp;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "employeesssss")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElement(name = "Employee")
    private List<Employee> list;

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }
}
