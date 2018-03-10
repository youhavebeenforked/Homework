package ru.sberbank.homework.solovyov.serialization.serializable;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class InFileSerializableRouteService extends RouteService<City, Route<City>> {

    public InFileSerializableRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    public InFileSerializableRouteService(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String routeKey = "ser_" + from + "_" + to;
        String routeCacheFilePath = pathProvider.getCacheDirectoryPath() + "\\" + routeKey;
        File routeCacheFile = new File(routeCacheFilePath);
        Route<City> route;
        if (routeCacheFile.exists()) {
            route = deserialize(routeCacheFilePath);
        } else {
            route = super.getRoute(from, to);
            serialize(routeCacheFilePath, new SerializableRoute<>(route.getRouteName(), route.getCities()));
        }
        return route;
    }


    private void serialize(String filename, SerializableRoute<City> route) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SerializableRoute<City> deserialize(String filename) {
        SerializableRoute<City> route;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            route = (SerializableRoute<City>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RouteFetchException(e);
        }
        return route;

    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected SerializableRoute<City> createRoute(List<City> cities) {
        return new SerializableRoute<>(UUID.randomUUID().toString(), cities);
    }
}
