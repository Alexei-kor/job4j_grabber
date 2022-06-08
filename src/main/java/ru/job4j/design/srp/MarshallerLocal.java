package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MarshallerLocal {

    public static Marshaller getInstance() {
        Marshaller rsl = null;
        try {
            JAXBContext cnt = JAXBContext.newInstance(Employees.class);
            rsl = cnt.createMarshaller();
            rsl.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return rsl;
    }

}
