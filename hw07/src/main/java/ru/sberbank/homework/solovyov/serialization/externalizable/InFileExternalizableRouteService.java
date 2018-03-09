package ru.sberbank.homework.solovyov.serialization.externalizable;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class InFileExternalizableRouteService extends RouteService<City, Route<City>> {

    public InFileExternalizableRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    public InFileExternalizableRouteService(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String routeKey = "ext_" + from + "_" + to;
        String routeCacheFilePath = pathProvider.getCacheDirectoryPath() + "\\" + routeKey;
        File routeCacheFile = new File(routeCacheFilePath);
        Route<City> route;
        if (routeCacheFile.exists()) {
            route = deserialize(routeCacheFilePath);
        } else {
            route = super.getRoute(from, to);
            serialize(routeCacheFilePath, new ExternalizableRoute<>(route.getRouteName(), route.getCities()));
        }
        return route;

    }


    private void serialize(String filename, ExternalizableRoute<City> route) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExternalizableRoute<City> deserialize(String filename) {
        ExternalizableRoute<City> route;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            route = (ExternalizableRoute<City>) ois.readObject();
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
    protected ExternalizableRoute<City> createRoute(List<City> cities) {
        return new ExternalizableRoute<>(UUID.randomUUID().toString(), cities);
    }
}
