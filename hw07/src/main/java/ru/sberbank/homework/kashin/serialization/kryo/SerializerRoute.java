package ru.sberbank.homework.kashin.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.Route;

public class SerializerRoute extends Serializer<Route> {

    @Override
    public void write(Kryo kryo, Output output, Route object) {
        kryo.writeClassAndObject(output, object.getRouteName());
        kryo.writeClassAndObject(output, object.getCities().size());
        for (Object city : object.getCities()) {
            kryo.writeClassAndObject(output, city);
        }
    }

    @Override
    public Route read(Kryo kryo, Input input, Class<Route> type) {
        Route route = new Route();
        route.setRouteName((String)kryo.readClassAndObject(input));
        int size = (Integer) kryo.readClassAndObject(input);
        for(int i = 0; i < size; i++) {
            route.getCities().add(kryo.readClassAndObject(input));
        }
        return route;
    }
}
