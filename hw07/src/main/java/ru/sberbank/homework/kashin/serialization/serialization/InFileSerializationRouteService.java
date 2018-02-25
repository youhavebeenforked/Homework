package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.isNull;

public class InFileSerializationRouteService<C extends SerializationCity, T extends RouteSerialization> extends RouteService<C, T> {
    private HashMap<String, String> routeHashMap = new HashMap<>();

    public InFileSerializationRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public T getRoute(String from, String to) {
        String key = from + "_" + to;
        RouteSerialization<SerializationCity> route;
        String pathFile = routeHashMap.get(key);
        if (isNull(pathFile)) {
            Route tmpRoute = super.getRoute(from, to);
            route = new RouteSerialization<>(tmpRoute.getRouteName(), tmpRoute.getCities());
            serialize(pathProvider.getCacheDirectoryPath() + "/" + key, route);
            routeHashMap.put(key, pathProvider.getCacheDirectoryPath() + "/" + key);
        } else {
            route = deserialize(routeHashMap.get(key));
        }
        return (T) route;
    }

    @Override
    protected C createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return (C) new SerializationCity(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected T createRoute(List<C> cities) {
        return (T) new RouteSerialization(UUID.randomUUID().toString(), cities);
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
