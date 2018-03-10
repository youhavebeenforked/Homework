package ru.sberbank.homework.solovyov.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class InFileKryoRouteService extends RouteService<City, Route<City>> {

    private final RouteKryoSerializer routeSerializer = new RouteKryoSerializer();
    private final CityKryoSerializer citySerializer = new CityKryoSerializer();

    private final Kryo kryo = new Kryo();


    public InFileKryoRouteService(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
        kryo.register(Route.class, routeSerializer);
        kryo.register(City.class, citySerializer);
    }

    public InFileKryoRouteService(CachePathProvider pathProvider) {
        this(pathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String routeKey = "kryo_" + from + "_" + to;

        String routeCacheFilePath = pathProvider.getCacheDirectoryPath() + "\\" + routeKey;
        File routeCacheFile = new File(routeCacheFilePath);

        Route<City> route;
        if (routeCacheFile.exists()) {
            route = deserialize(routeCacheFilePath);
        } else {
            route = super.getRoute(from, to);
            serialize(routeCacheFilePath, route);
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

    private void serialize(String filename, Route route) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             Output output = new Output(fos)) {
            kryo.writeObject(output, route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Route<City> deserialize(String filename) {
        Route<City> route = null;
        try (FileInputStream fis = new FileInputStream(filename);
             Input input = new Input(fis)) {
            route = kryo.readObject(input, Route.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return route;
    }
}
