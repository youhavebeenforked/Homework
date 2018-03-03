package ru.sberbank.homework.kashin.serialization.externalization;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

public class InFileExternalizationRouteService extends RouteService<City, RouteExternalization<City>> {
    private HashMap<String, String> routeHashMap = new HashMap<>();

    public InFileExternalizationRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public RouteExternalization<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        RouteExternalization<City> route;
        String pathFile = routeHashMap.get(key);
        if (isNull(pathFile)) {
            Route<City> tmpRoute = super.getRoute(from, to);
            route = new RouteExternalization<City>(tmpRoute.getRouteName(), tmpRoute.getCities());
            serialize(pathProvider.getCacheDirectoryPath() + "/" + key, route);
            routeHashMap.put(key, pathProvider.getCacheDirectoryPath() + "/" + key);
        } else {
            route = deserialize(routeHashMap.get(key));
        }
        return route;
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected RouteExternalization<City> createRoute(List<City> cities) {
        return new RouteExternalization<>(UUID.randomUUID().toString(), cities);
    }

    private void serialize(String fileName, RouteExternalization route) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(route);
        } catch (IOException e) {
            throw new RouteFetchException(e);
        }
    }

    private RouteExternalization deserialize(String fileName) {
        RouteExternalization route;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            route = (RouteExternalization) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RouteFetchException(e);
        }
        return route;
    }
}
