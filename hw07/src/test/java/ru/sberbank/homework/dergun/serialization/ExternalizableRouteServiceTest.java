package ru.sberbank.homework.dergun.serialization;

import org.junit.Test;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ExternalizableRouteServiceTest {

    @Test
    public void simple() {
        ExternalizableRouteService service = new ExternalizableRouteService(() -> "C:\\test\\");
        Route<? extends City> serialize = service.getRoute("Saint-Petersburg", "Berlin");
        System.out.println(serialize);
        Route<? extends City> deserialize = service.getRoute("Saint-Petersburg", "Berlin");
        System.out.println(deserialize);
        compareCities(deserialize.getCities(), serialize.getCities());
    }

    private void compareCities(List<? extends City> cached, List<? extends City> unCached) {
        for (int i = 0; i < cached.size(); i++) {
            City city = unCached.get(i);
            City city1 = cached.get(i);
            boolean equals = city.compare(city1);
            System.out.println(city + (equals ? " == " : " != ") + city1);
            assertTrue(equals);
        }
    }

}