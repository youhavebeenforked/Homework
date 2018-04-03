package ru.sberbank.homework.maruev.serialization.kryo;

/**
 * Created by Иван.
 */

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class KryoService extends RouteService<City, Route<City>> {
    private final Kryo kryo = new Kryo();
    private Set<String> setRoute = new HashSet<>();
    private RouteKryo routeKryo = new RouteKryo();
    private CityKryo cityKryo = new CityKryo();


    public KryoService(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
        kryo.register(Route.class, routeKryo);
        kryo.register(City.class, cityKryo);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        Route<City> route;

        if (!setRoute.contains(key)) {
            route = super.getRoute(from, to);
            serialKryo(pathProvider.getCacheDirectoryPath() + "\\" + key, route);
            setRoute.add(key);
        } else {
            route = deserialKryo(pathProvider.getCacheDirectoryPath() + "\\" + key);
        }
        return route;
    }

    private void serialKryo(String path, Route<City> route) {
        try (FileOutputStream outFile = new FileOutputStream(path);
             Output output = new Output(outFile)) {
            kryo.writeObject(output, route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Route<City> deserialKryo(String path) {
        Route<City> route = null;

        try (FileInputStream inputFile = new FileInputStream(path);
             Input input = new Input(inputFile)) {
            route = kryo.readObject(input, Route.class);
        } catch (IOException e) {
            e.printStackTrace();
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
}
