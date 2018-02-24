package ru.sberbank.homework.kashin.serialization;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

public class InFileExternalizationRouteService extends RouteService<City, Route<City>> {
    private HashMap<String, String> routeHashMap = new HashMap<>();

    public InFileExternalizationRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        RouteSerialization<City> route;
        String pathFile = routeHashMap.get(key);
        if (isNull(pathFile)) {
            Route tmpRoute = super.getRoute(from, to);
            route = new RouteSerialization<>(tmpRoute.getRouteName(), tmpRoute.getCities());
            String absPath = pathProvider.getCacheDirectoryPath() + "/" + key;
            serialize(absPath, route);
            routeHashMap.put(key, absPath);
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

    public void serialize(String fileName, RouteSerialization route) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(route);
        } catch (IOException e) {
            throw new RouteFetchException(e);
        }
    }

    public RouteSerialization deserialize(String fileName) {
        RouteSerialization route;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            route = (RouteSerialization) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RouteFetchException(e);
        }
        return route;
    }
}
