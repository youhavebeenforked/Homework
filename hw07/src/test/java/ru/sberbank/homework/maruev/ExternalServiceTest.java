package ru.sberbank.homework.maruev;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;
import ru.sberbank.homework.maruev.serialization.externalizable.ExternalRoute;
import ru.sberbank.homework.maruev.serialization.externalizable.ExternalizableService;

import static org.junit.Assert.assertTrue;

/**
 * Created by Иван.
 */
public class ExternalServiceTest {
    RouteService<City, ExternalRoute> routeService;

    @Before
    public void pre() {
        routeService = new ExternalizableService(() -> "C:\\temp\\", true);
    }

    @Test
    public void testExampleRouteService() {
        long startTime = System.currentTimeMillis();
        Route<? extends City> route1 = routeService.getRoute("Saint-Petersburg", "Berlin");
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(route1 + " (" + endTime + ")");
        assertTrue(endTime >= 100);

        startTime = System.currentTimeMillis();
        Route<? extends City> deserialRoute = routeService.getRoute("Saint-Petersburg", "Berlin");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(deserialRoute + " (" + endTime + ")");
        assertTrue(endTime < 100);
    }
}
