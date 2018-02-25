package ru.sberbank.homework.dergun.serialization;

import javafx.util.Pair;
import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExternalizableRouteService extends RouteService<City, Route<City>> {
    private HashMap<String, Route<City>> routeHashMap = new HashMap<>();

    private Set<String> statistic = new HashSet<>();

    public ExternalizableRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        Route<City> route = null;
        String key = from + "_" + to;
        if (!statistic.contains(key)) {
            route = super.getRoute(from, to);
            statistic.add(key);
            try {
                FileOutputStream fos = new FileOutputStream(pathProvider.getCacheDirectoryPath() + "\\" + key + ".txt");
                ObjectOutputStream out = new ObjectOutputStream(fos);
                new ExternalizableRoute(route).writeExternal(out);
                out.flush();
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(pathProvider.getCacheDirectoryPath() + "\\" + key + ".txt");
                ObjectInputStream in = new ObjectInputStream(fis);
                ExternalizableRoute ext = new ExternalizableRoute();
                ext.readExternal(in);
                route = ext.getRoute();
                in.close();

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

class ExternalizableRoute implements Externalizable {

    private final Set<City> cities;
    private final List<City> route;
    private String routeName;

    public ExternalizableRoute() {
        cities = new HashSet<>();
        route = new ArrayList<>();
        routeName = "";
    }

    public ExternalizableRoute(Route<City> route) {
        cities = new HashSet<>();

        final Queue<City> q = new ArrayDeque<>();
        q.addAll(route.getCities());
        while (!q.isEmpty()) {
            City city = q.poll();
            if (!cities.contains(city)) {
                q.addAll(city.getNearCities());
                cities.add(city);
            }
        }

        this.routeName = route.getRouteName();
        this.route = new ArrayList<>(route.getCities());
    }

    public Route<City> getRoute() {
        return new Route<>(routeName, route);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(routeName);
        out.writeInt(cities.size());
        for (City city : cities) {
            out.writeInt(city.getId());
            out.writeObject(city.getCityName());
            out.writeObject(city.getFoundDate());
            out.writeLong(city.getNumberOfInhabitants());

            out.writeInt(city.getNearCities().size());
            for (City near : city.getNearCities()) {
                out.writeInt(near.getId());
            }
        }

        out.writeInt(route.size());
        for (City near : route) {
            out.writeInt(near.getId());
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        route.clear();
        cities.clear();

        routeName = (String) in.readObject();
        Map<Integer, Pair<City, List<Integer>>> visited = new HashMap<>();

        int citiesSize = in.readInt();
        for (int i = 0; i < citiesSize; i++) {
            int id = in.readInt();
            City city = new City(id, (String) in.readObject(), (LocalDate) in.readObject(), in.readLong());
            List<Integer> nearest = new ArrayList<>();
            int nearestSize = in.readInt();
            for (int j = 0; j < nearestSize; j++) {
                nearest.add(in.readInt());
            }
            visited.put(id, new Pair<>(city, nearest));
        }

        for (Pair<City, List<Integer>> p : visited.values()) {
            List<City> nearest = p.getValue().stream()
                    .map(id -> visited.get(id).getKey())
                    .collect(Collectors.toList());
            p.getKey().setNearCities(nearest);
            cities.add(p.getKey());
        }

        int routeSize = in.readInt();
        for (int i = 0; i < routeSize; i++) {
            route.add(visited.get(in.readInt()).getKey());
        }
    }
}
