package ru.sberbank.homework.your_lastname;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;
import ru.sberbank.homework.your_lastname.serialization.InMemoryRouteService;

import static org.junit.Assert.assertTrue;

public class ServiceTest {
    RouteService<City, Route<City>> routeService;

    @Before
    public void pre() {
        routeService = new InMemoryRouteService();
    }

    @Test
    public void testExampleRouteService() {
        long startTime = System.currentTimeMillis();
        Route route = routeService.getRoute("Санкт-Петербург", "Владивосток");
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(route);
        System.out.println(endTime);
        assertTrue(endTime >= 1000);

        startTime = System.currentTimeMillis();
        route = routeService.getRoute("Москва", "Пермь");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(route);
        System.out.println(endTime);
        assertTrue(endTime >= 1000);

        startTime = System.currentTimeMillis();
        route = routeService.getRoute("Москва", "Пермь");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(route);
        System.out.println(endTime);
        assertTrue(endTime < 10);

        startTime = System.currentTimeMillis();
        route = routeService.getRoute("Санкт-Петербург", "Владивосток");
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(route);
        System.out.println(endTime);
        assertTrue(endTime < 10);

    }
}
