package ru.sberbank.homework.kashin.serialization.externalization;

import ru.sberbank.homework.common.*;
import ru.sberbank.homework.kashin.serialization.serialization.RouteSerialization;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

public class InFileExternalizationRouteService<C extends ExternalizationCity, T extends RouteExternalization> extends RouteService<C, T> {
    private HashMap<String, String> routeHashMap = new HashMap<>();

    public InFileExternalizationRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public T getRoute(String from, String to) {
        String key = from + "_" + to;
        RouteExternalization<ExternalizationCity> route;
        String pathFile = routeHashMap.get(key);
        if (isNull(pathFile)) {
            Route tmpRoute = super.getRoute(from, to);
            route = new RouteExternalization<>(tmpRoute.getRouteName(), tmpRoute.getCities());
            String absPath = pathProvider.getCacheDirectoryPath() + "/" + key;
            serialize(absPath, route);
            routeHashMap.put(key, absPath);
        } else {
            route = deserialize(routeHashMap.get(key));
        }
        return (T) route;
    }

    @Override
    protected C createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return (C) new ExternalizationCity(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected T createRoute(List<C> cities) {
        return (T) new RouteExternalization<>(UUID.randomUUID().toString(), cities);
    }

    public void serialize(String fileName, RouteExternalization route) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(route);
        } catch (IOException e) {
            throw new RouteFetchException(e);
        }
    }

    public RouteExternalization deserialize(String fileName) {
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
