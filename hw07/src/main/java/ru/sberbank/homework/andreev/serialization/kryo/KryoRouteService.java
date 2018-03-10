package ru.sberbank.homework.andreev.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.Input;
import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class KryoRouteService extends RouteService<City, Route<City>> {
    private Kryo kryo;
    private Set<String> cachedCity = new HashSet<>();

    public KryoRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
        kryo = new Kryo();
        kryo.register(City.class, new CitySerializer());
        kryo.register(Route.class, new RouteSerializer());
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
                 Input input = new Input(fis)) {
                route = Optional.of(kryo.readObject(input, Route.class));
            } catch (IOException e) {
            }
        }
        return route;
    }

    private Route<City> createAndCacheCityRoute(String key, String from, String to) {
        Route<City> route = super.getRoute(from, to);
        try (FileOutputStream fos = new FileOutputStream(pathProvider.getCacheDirectoryPath() + File.separator + key);
            Output output = new Output(fos)) {
            kryo.writeObject(output, route);
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
        return new Route<>(UUID.randomUUID().toString(), cities);
    }
}
