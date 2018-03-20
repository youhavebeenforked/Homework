package ru.sberbank.homework.bobrov.serialization.serial;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class SerializeRouteService extends RouteService<City, Route<City>> {


    public SerializeRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public RouteWrapperSer<City> getRoute(String from, String to) {
        RouteWrapperSer<City> wrapper = null;
        Route<City> original = null;
        String routeName = String.format("%s_%s%s", from, to, ".ser");
        File file = new File(pathProvider.getCacheDirectoryPath() + File.separator + routeName);
        if (file.exists()) {
            return deserialize(file.getPath());
        } else {
            original = super.getRoute(from, to);
            wrapper = new RouteWrapperSer(original.getCities());
            serialize(file.getPath(), wrapper);
            return wrapper;
        }
    }

    private void serialize(String path, RouteWrapperSer<City> route) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream ous = new ObjectOutputStream(fos)) {
            ous.writeObject(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RouteWrapperSer<City> deserialize(String path) {
        RouteWrapperSer<City> route = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            route = (RouteWrapperSer<City>) ois.readObject();
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
    protected Route<City> createRoute(List<City> cities) {
        return new Route<>(UUID.randomUUID().toString(), cities);
    }
}
