package ru.sberbank.homework.dergun.serialization;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SerializableRouteService extends RouteService<City, Route<City>> {
    private HashMap<String, Route<City>> routeHashMap = new HashMap<>();
    private Route<City> route = null;
    private Set<String> statistic = new HashSet<>();
    public SerializableRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        if (!statistic.contains(key)) {
            route = super.getRoute(from, to);
            statistic.add(key);
            try {
                FileOutputStream fos = new FileOutputStream(pathProvider.getCacheDirectoryPath() + "\\" + key + ".txt");
                ObjectOutputStream out = new ObjectOutputStream(fos);

                out.writeObject(new SerializableRoute(route));


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(pathProvider.getCacheDirectoryPath() + "\\" + key + ".txt");
                ObjectInputStream in = new ObjectInputStream(fis);
                route = ((SerializableRoute) in.readObject()).getRoute();
            } catch (IOException | ClassNotFoundException e) {
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

class SerializableCity implements Serializable {
    private final int id;
    private final String cityName;
    private final LocalDate foundDate;
    private final long numberOfInhabitants;
    private final List<SerializableCity> nearCities;

    public SerializableCity(City city, Map<City, SerializableCity> visited) {
        visited.put(city, this);
        id = city.getId();
        cityName = city.getCityName();
        foundDate = city.getFoundDate();
        numberOfInhabitants = city.getNumberOfInhabitants();
        nearCities = city.getNearCities()
                .stream()
                .map(x -> visited.containsKey(x) ? visited.get(x) : new SerializableCity(x, visited))
                .collect(Collectors.toList());
    }

    public City getCity(Map<SerializableCity, City> visited) {
        City city = visited.get(this);
        if (city != null) {
            return city;
        }
        city = new City(id, cityName, foundDate, numberOfInhabitants);
        visited.put(this, city);
        city.setNearCities(nearCities.stream()
                .map(x -> x.getCity(visited))
                .collect(Collectors.toList()));
        return city;
    }
}

class SerializableRoute implements Serializable {
    private final String name;
    private final List<SerializableCity> cities;

    public SerializableRoute(Route<City> route) {
        Map<City, SerializableCity> visited = new HashMap<>();
        name = route.getRouteName();
        cities = route.getCities()
                .stream()
                .map(x -> visited.containsKey(x) ? visited.get(x) : new SerializableCity(x, visited))
                .collect(Collectors.toList());
    }

    public Route<City> getRoute() {
        Map<SerializableCity, City> visited = new HashMap<>();
        return new Route<>(name, cities.stream()
                .map(x -> x.getCity(visited))
                .collect(Collectors.toList()));
    }
}
