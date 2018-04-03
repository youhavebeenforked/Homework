package ru.sberbank.homework.maruev;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;
import ru.sberbank.homework.maruev.serialization.serializable.SerialRoute;
import ru.sberbank.homework.maruev.serialization.serializable.SerializableService;

import static org.junit.Assert.assertTrue;

/**
 * Created by Иван.
 */
public class SerialServiceTest {
    RouteService<City, SerialRoute<City>> routeService;

    @Before
    public void pre() {
        routeService = new SerializableService(() -> "C:\\temp\\", true);
    }

    @Test
    public void testExampleRouteService() {
        long startTime = System.currentTimeMillis();
        Route<? extends City> route = routeService.getRoute("Saint-Petersburg", "Berlin");
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(route + " (" + endTime + ")");
        assertTrue(endTime >= 100);

        startTime = System.currentTimeMillis();
        Route<? extends City> deserialRoute = routeService.getRoute("Saint-Petersburg", "Berlin");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(deserialRoute + " (" + endTime + ")");
        assertTrue(endTime < 100);
    }
}
