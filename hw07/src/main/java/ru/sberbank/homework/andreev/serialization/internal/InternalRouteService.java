package ru.sberbank.homework.andreev.serialization.internal;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class InternalRouteService extends RouteService<City, Route<City>> {
    private Set<String> cachedCity = new HashSet<>();

    public InternalRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        return getCachedCityRoute(key)
                .orElseGet(() -> createAndCacheCityRoute(key, from, to));
    }

    private Optional<Route<City>> getCachedCityRoute(String key) {
        Optional<Route<City>> route = Optional.empty();
        if (cachedCity.contains(key)) {
            try (FileInputStream fis = new FileInputStream(pathProvider.getCacheDirectoryPath() + File.separator + key);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                route = Optional.of((InternalRoute) ois.readObject());
            } catch (IOException | ClassNotFoundException doNothing) {
            }
        }
        return route;
    }

    private Route<City> createAndCacheCityRoute(String key, String from, String to) {
        Route<City> route = super.getRoute(from, to);
        try (FileOutputStream fos = new FileOutputStream(pathProvider.getCacheDirectoryPath() + File.separator + key)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(route);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cachedCity.add(key);
        return route;
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        return new InternalRoute(UUID.randomUUID().toString(), cities);
    }
}
