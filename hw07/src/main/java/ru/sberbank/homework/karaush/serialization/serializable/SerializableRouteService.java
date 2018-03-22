package ru.sberbank.homework.karaush.serialization.serializable;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;
import ru.sberbank.homework.karaush.serialization.externalizable.ExternalizableCity;
import ru.sberbank.homework.karaush.serialization.externalizable.ExternalizableRoute;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SerializableRouteService extends RouteService<City, Route<City>> {

    private Set<String> cache = new HashSet<>();

    private void create(String name, String from, String to) {
        Route<City> route = super.getRoute(from, to);
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathProvider.getCacheDirectoryPath() + File.separator + name);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Route<City> retrieve(String name) {
        try (FileInputStream fileInputStream = new FileInputStream(pathProvider.getCacheDirectoryPath() + File.separator + name);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Route<City>) objectInputStream.readObject();    
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SerializableRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        if (!cache.contains(key)) {
            create(key, from, to);
            cache.add(key);
        }
        return retrieve(key);
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new ExternalizableCity(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        return new ExternalizableRoute(UUID.randomUUID().toString(), cities);
    }
}
