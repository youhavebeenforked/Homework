package ru.sberbank.homework.maruev;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;
import ru.sberbank.homework.maruev.serialization.kryo.KryoService;

import static org.junit.Assert.*;

/**
 * Created by Иван.
 */
public class KryoServiceTest {
    RouteService<City, Route<City>> routeService;

    @Before
    public void pre() {
        routeService = new KryoService(() -> "C:\\temp\\", true);
    }

    @Test
    public void testExampleRouteService() {
        long startTime = System.currentTimeMillis();
        Route<? extends City> route = routeService.getRoute("Saint-Petersburg", "Berlin");
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(route + " (" + endTime + ")");
        assertTrue(endTime >= 50);

        startTime = System.currentTimeMillis();
        Route<? extends City> deserialRoute = routeService.getRoute("Saint-Petersburg", "Berlin");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(deserialRoute + " (" + endTime + ")");
        assertTrue(endTime < 100);
    }
}