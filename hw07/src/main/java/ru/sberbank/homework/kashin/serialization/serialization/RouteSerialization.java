package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.Serializable;
import java.util.List;

public class RouteSerialization<C extends City> extends Route implements Serializable {
    public RouteSerialization(String s, List<C> cities) {
        super(s, cities);
    }
}
