package ru.sberbank.homework.kashin.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static java.util.Objects.isNull;

public class KryoRouteService extends RouteService<City, Route<City>> {
    private HashMap<String, String> routeHashMap = new HashMap<>();
    private Serializer serializerRoute = new SerializerRoute();
    private Serializer serializerCity = new SerializerCity();
    private final ThreadLocal<Kryo> kryoFactory = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.register(Route.class, serializerRoute);
        kryo.register(City.class, serializerCity);
        return kryo;
    });


    public KryoRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        Route<City> route;
        String pathFile = routeHashMap.get(key);
        if (isNull(pathFile)) {
            route = super.getRoute(from, to);
            String absPath = pathProvider.getCacheDirectoryPath() + "/" + key;
            serialize(absPath, route);
            routeHashMap.put(key, absPath);
        } else {
            route = deserialize(routeHashMap.get(key));
        }
        return route;
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        return new Route<>(UUID.randomUUID().toString(), cities);
    }

    private void serialize(String fileName, Route route) {
        Kryo kryo = kryoFactory.get();
        try (FileOutputStream outputStream = new FileOutputStream(fileName);
             Output output = new Output(outputStream)) {
            kryo.writeObject(output, route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Route deserialize(String fileName) {
        Kryo kryo = kryoFactory.get();
        Route route = null;
        try (FileInputStream outputStream = new FileInputStream(fileName);
             Input input = new Input(outputStream)) {
            route = kryo.readObject(input, Route.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return route;
    }

}
