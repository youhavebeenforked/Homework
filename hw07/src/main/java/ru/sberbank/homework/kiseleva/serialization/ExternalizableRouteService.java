package ru.sberbank.homework.kiseleva.serialization;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ekaterina Kiseleva on 02.03.2018.
 */
public class ExternalizableRouteService extends RouteService<City, Route<City>> {
    private final String cachePath;
    private HashMap<String, Route<City>> routeHashMap = new HashMap<>();


    public ExternalizableRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, false);
        this.cachePath = cachePathProvider.getCacheDirectoryPath();
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;

        if (new File(cachePath + "/" + key).isFile()) {
            try {
                SerialRoute route = new SerialRoute();
                route.readExternal(new ObjectInputStream(new FileInputStream(cachePath + "/" + key)));
                return route;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Route<City> route = routeHashMap.get(key);
        if (route == null) {
            route = super.getRoute(from, to);
            routeHashMap.put(key, route);

            SerialRoute serialRoute = new SerialRoute(route.getRouteName(), route.getCities());
            try {
                serialRoute.writeExternal(new ObjectOutputStream(new FileOutputStream(cachePath + "/" + key)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return route;
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        return new Route<>(UUID.randomUUID().toString(), cities);
    }
}
