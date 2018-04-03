package ru.sberbank.homework.koval.serialization.externalizable;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ExternalizeRouteService extends RouteService<City, ExternalizableRoute> {

    Set<String> cachedRoutes = new HashSet<>();

    public ExternalizeRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, true);
    }

    @Override
    public ExternalizableRoute getRoute(String from, String to) {
        String key = from + "_" + to;
        if (cachedRoutes.contains(key)) {
            return deserialize(key);
        } else {
            ExternalizableRoute route = super.getRoute(from, to);
            serialize(route, key);
            cachedRoutes.add(key);
            return route;
        }
    }

    private void serialize(ExternalizableRoute route, String routeName) {
        String cachePath = pathProvider.getCacheDirectoryPath();
        try (FileOutputStream fos = new FileOutputStream(cachePath + routeName);
             ObjectOutputStream output = new ObjectOutputStream(fos)) {
            route.writeExternal(output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ExternalizableRoute deserialize(String routeName) {
        String cachePath = pathProvider.getCacheDirectoryPath();
        try (FileInputStream fis = new FileInputStream(cachePath + routeName);
             ObjectInputStream input = new ObjectInputStream(fis)) {
            ExternalizableRoute route = new ExternalizableRoute();
            route.readExternal(input);
            return route;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected ExternalizableRoute createRoute(List<City> cities) {
        return new ExternalizableRoute(UUID.randomUUID().toString(), cities);
    }
}
