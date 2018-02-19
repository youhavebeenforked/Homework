package ru.sberbank.homework.common;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

public abstract class RouteService<C extends City, T extends Route> {
    private final Set<String> cities = new HashSet<>(Arrays.asList("Санкт-Петербург", "Москва", "Челябинск", "Свердловск", "Пермь", "Сыктывкар", "Мурманск",
            "Ростов-на-Дону", "Воронеж", "Владивосток", "Омск"));
    private int idCounter = 0;
    private SecureRandom rand = new SecureRandom();

    public RouteService() {

    }

    /**
     * Медленный, неэффективный, возможно расположенный в другой стране / на другой планете сервис.
     */
    public T getRoute(String from, String to) {
        checkNames(from, to);
        List<String> names = new ArrayList<>(cities);
        Collections.shuffle(names);

        List<C> generated = new ArrayList<>(names.size());
        names.forEach(s -> generated.add(generateCity(s)));

        generated.forEach(c -> addRandomCities(c, generated));

        generated.sort((o1, o2) -> {
            if (o1.getCityName().equals(from) || o2.getCityName().equals(to)) {
                return -1;
            } else if (o1.getCityName().equals(to) ||o2.getCityName().equals(from)) {
                return 1;
            }
            return 0;
        });

        T route = createRoute(generated);

        sleep();

        return route;
    }

    private void sleep() {
        try {
            Thread.sleep(2000 + rand.nextInt(1000));
        } catch (InterruptedException ignore) {

        }
    }

    private void addRandomCities(C city, List<C> source) {
        do {
            C next = source.get(rand.nextInt(source.size()));
            if (!city.equals(next) && !city.getNearCities().contains(next)) {
                city.addCity(next);
            }
        } while (city.getNearCities().size() < 3);
    }

    private C generateCity(String name) {
        LocalDate date = LocalDate.of(100 + rand.nextInt(1900), 1 + rand.nextInt(11), 1 + rand.nextInt(27));
        return createCity(idCounter++, name, date, 6 * rand.nextInt(1000000));
    }

    protected abstract C createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants);

    protected abstract T createRoute(List<C> cities);

    private void checkNames(String from, String to) {
        checkName(from);
        checkName(to);
    }

    private void checkName(String name) {
        if (!cities.contains(name)) {
            throw new UnknownCityException(name);
        }
    }
}
