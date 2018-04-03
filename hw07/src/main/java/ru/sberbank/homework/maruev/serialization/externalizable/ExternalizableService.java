package ru.sberbank.homework.maruev.serialization.externalizable;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Иван.
 */
public class ExternalizableService extends RouteService<City, ExternalRoute> {

    private Set<String> routeSet = new HashSet<>();

    public ExternalizableService(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
    }

    @Override
    public ExternalRoute getRoute(String from, String to) {
        String key = from + "_" + to;
        ExternalRoute route;

        if (!routeSet.contains(key)) {
            Route<City> routeHelper = super.getRoute(from, to);
            route = new ExternalRoute(routeHelper.getRouteName(), routeHelper.getCities());
            extSerialize(pathProvider.getCacheDirectoryPath() + "/" + key, route);
            routeSet.add(key);
        } else {
            route = extDeserialize(pathProvider.getCacheDirectoryPath() + "/" + key);
        }
        return route;
    }

    private void extSerialize(String pathToFile, ExternalRoute route) {
        try (FileOutputStream fileOut = new FileOutputStream(pathToFile);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            objOut.writeObject(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExternalRoute extDeserialize(String pathToFile) {

        ExternalRoute route = null;

        try (FileInputStream fileInput = new FileInputStream(pathToFile);
             ObjectInputStream objInput = new ObjectInputStream(fileInput)) {
            route = (ExternalRoute) objInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected ExternalRoute createRoute(List<City> cities) {
        return new ExternalRoute(UUID.randomUUID().toString(), cities);
    }
}
