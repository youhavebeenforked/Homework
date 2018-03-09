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
import java.util.List;
import java.util.UUID;

public class KryoRouteService extends RouteService<City, Route<City>> {
    private Kryo kryo;

    public KryoRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
        kryo = new Kryo();
        kryo.register(City.class, new CitySerializer());
        kryo.register(Route.class, new RouteSerializer());
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        Route<City> route = null;
        try (FileInputStream fis = new FileInputStream(pathProvider.getCacheDirectoryPath() + File.separator + key)) {
            Input input = new Input(fis);
            route = kryo.readObject(input, Route.class);
            input.close();
        } catch (IOException e) {
//            e.printStackTrace();
        }

        if (route == null) {
            route = super.getRoute(from, to);
            try (FileOutputStream fos = new FileOutputStream(pathProvider.getCacheDirectoryPath() + File.separator + key)) {
                Output output = new Output(fos);
                kryo.writeObject(output, route);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
