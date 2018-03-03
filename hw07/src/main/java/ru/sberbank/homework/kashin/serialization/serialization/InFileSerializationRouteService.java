package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.isNull;

public class InFileSerializationRouteService extends RouteService<City, RouteSerialization<City>> {
    private HashMap<String, String> routeHashMap = new HashMap<>();

    public InFileSerializationRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public RouteSerialization<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        RouteSerialization<City> route;
        String pathFile = routeHashMap.get(key);
        if (isNull(pathFile)) {
            Route<City> tmpRoute = super.getRoute(from, to);
            route = new RouteSerialization<>(tmpRoute.getRouteName(), tmpRoute.getCities());
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
    protected RouteSerialization<City> createRoute(List<City> cities) {
        return new RouteSerialization<>(UUID.randomUUID().toString(), cities);
    }

    private void serialize(String fileName, RouteSerialization<City> route) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(route);
        } catch (IOException e) {
            throw new RouteFetchException(e);
        }
    }

    private RouteSerialization<City> deserialize(String fileName) {
        RouteSerialization<City> route;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            route = (RouteSerialization<City>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RouteFetchException(e);
        }
        return route;
    }

}
