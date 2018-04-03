package ru.sberbank.homework.maruev.serialization.serializable;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Иван on 13.03.2018.
 */
public class SerializableService extends RouteService<City, SerialRoute<City>> {
    private Set<String> routeSet = new HashSet<>();

    public SerializableService(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
    }

    @Override
    public SerialRoute<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        SerialRoute<City> route;

        if (!routeSet.contains(key)) {
            Route<City> routeHelper = super.getRoute(from, to);
            route = new SerialRoute<>(routeHelper.getRouteName(), routeHelper.getCities());

            serializeRoute(pathProvider.getCacheDirectoryPath() + "\\" + key, route);
            routeSet.add(key);
        } else {
            route = deserializeRoute(pathProvider.getCacheDirectoryPath() + "\\" + key);
        }
        return route;
    }

    private SerialRoute<City> deserializeRoute(String pathToFile) {
        SerialRoute<City> route = null;

        try (FileInputStream fileInput = new FileInputStream(pathToFile);
             ObjectInputStream objInput = new ObjectInputStream(fileInput)) {
            route = (SerialRoute<City>) objInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return route;
    }

    private void serializeRoute(String pathToFile, SerialRoute<City> route) {
        try (FileOutputStream fileOut = new FileOutputStream(pathToFile);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            objOut.writeObject(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected SerialRoute<City> createRoute(List<City> cities) {
        return new SerialRoute<>(UUID.randomUUID().toString(), cities);
    }
}
