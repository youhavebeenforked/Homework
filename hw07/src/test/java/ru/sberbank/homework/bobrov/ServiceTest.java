package ru.sberbank.homework.bobrov;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.bobrov.serialization.external.ExternalizeRouteService;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ServiceTest {
    RouteService<City, Route<City>> routeService;

    @Before
    public void pre() {
        //routeService = new KryoRouteService();
//        routeService = new InMemoryRouteService(() -> "C:\\temp\\");
//        routeService = new SerializeRouteService(() -> "H:\\bobrov\\");
        routeService = new ExternalizeRouteService(() -> "H:\\bobrov\\");
    }

    @Test
    public void testExampleRouteService() {
        long startTime = System.currentTimeMillis();
        Route<? extends City> route1 = routeService.getRoute("Saint-Petersburg", "Berlin");
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(route1 + " (" + endTime + ")");
        if (!routeService.isDevMode()) {
            assertTrue(endTime >= 2000);
        }
        startTime = System.currentTimeMillis();
        Route<? extends City> route2 = routeService.getRoute("Minsk", "Sverdlovsk");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(route2 + " (" + endTime + ")");
        if (!routeService.isDevMode()) {
            assertTrue(endTime >= 2000);
        }

        startTime = System.currentTimeMillis();
        Route<? extends City> deSerializedRoute1 = routeService.getRoute("Saint-Petersburg", "Berlin");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(deSerializedRoute1 + " (" + endTime + ")");
        assertTrue(endTime < 100);

        compareCities(deSerializedRoute1.getCities(), route1.getCities());

        startTime = System.currentTimeMillis();
        Route<? extends City> deSerializedRoute2 = routeService.getRoute("Minsk", "Sverdlovsk");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(deSerializedRoute2 + " (" + endTime + ")");
        assertTrue(endTime < 100);
        compareCities(deSerializedRoute2.getCities(), route2.getCities());

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
