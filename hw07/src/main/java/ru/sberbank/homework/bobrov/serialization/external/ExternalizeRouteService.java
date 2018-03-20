package ru.sberbank.homework.bobrov.serialization.external;


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

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 12.03.2018
 */


public class ExternalizeRouteService extends RouteService<City, Route<City>> {
    public ExternalizeRouteService(CachePathProvider pathProvider) {
        super(pathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        RouteWrapperExt wrapper = null;
        Route<City> original = null;
        String routeName = String.format("%s_%s%s", from, to, ".ext");
        File file = new File(pathProvider.getCacheDirectoryPath() + File.separator + routeName);
        if (file.exists()) {
            return deserialize(file.getPath());
        } else {
            original = super.getRoute(from, to);
            wrapper = new RouteWrapperExt(original.getRouteName(), original.getCities());
            serialize(file.getPath(), wrapper);
            return wrapper;
        }
    }

    private void serialize(String path, RouteWrapperExt<CityWrapperExt> route) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream ous = new ObjectOutputStream(fos)) {
            ous.writeObject(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Route<City> deserialize(String path) {
        Route<City> route = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            route = (Route<City>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    protected CityWrapperExt createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new CityWrapperExt(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected RouteWrapperExt<City> createRoute(List<City> cities) {
        return new RouteWrapperExt<>(UUID.randomUUID().toString(), cities);
    }

}
