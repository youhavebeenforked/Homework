package ru.sberbank.homework.kashin.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

public class SerializerRoute extends Serializer<Route> {

    @Override
    public void write(Kryo kryo, Output output, Route object) {
        kryo.writeObject(output, object.getRouteName());
        kryo.writeObject(output, object.getCities().size());
        for (Object city : object.getCities()) {
            kryo.writeObject(output, city);
        }
    }

    @Override
    public Route read(Kryo kryo, Input input, Class<Route> type) {
        Route route = new Route();
        route.setRouteName(kryo.readObject(input, String.class));
        int size = kryo.readObject(input, int.class);
        for (int i = 0; i < size; i++) {
            route.getCities().add(kryo.readObject(input, City.class));
        }
        return route;
    }
}
