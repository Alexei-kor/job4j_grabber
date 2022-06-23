package ru.job4j.parking;

import java.util.List;

public interface Parking {

    boolean add(List<Autos> autos);

    boolean delete(Autos autos);

}
