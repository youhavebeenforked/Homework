package ru.sberbank.homework.koval.serialization.serializable;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SerializeRouteService extends RouteService<City, SerializableRoute> {

    public SerializeRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, true);
    }

    Set<String> cachedRoutes = new HashSet<>();

    @Override
    public SerializableRoute getRoute(String from, String to) {
        String key = from + "_" + to;
        if (cachedRoutes.contains(key)) {
            return deserialize(key);
        } else {
            SerializableRoute route = super.getRoute(from, to);
            serialize(route, key);
            cachedRoutes.add(key);
            return route;
        }
    }

    private void serialize(SerializableRoute route, String routeName) {
        String cachePath = pathProvider.getCacheDirectoryPath();
        try (FileOutputStream fos = new FileOutputStream(cachePath + routeName);
             ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(route);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private SerializableRoute deserialize(String routeName) {
        String cachePath = pathProvider.getCacheDirectoryPath();
        try (FileInputStream fis = new FileInputStream(cachePath + routeName);
             ObjectInputStream input = new ObjectInputStream(fis)) {
            return (SerializableRoute) input.readObject();
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
    protected SerializableRoute createRoute(List<City> cities) {
        return new SerializableRoute(UUID.randomUUID().toString(), cities);
    }
}
