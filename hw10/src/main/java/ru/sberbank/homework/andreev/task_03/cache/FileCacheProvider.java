package ru.sberbank.homework.andreev.task_03.cache;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Optional;

public class FileCacheProvider implements CacheProvider {

    private CacheParameterProcessor defaultParameterProcessor;
    Kryo kryo = new Kryo();

    public FileCacheProvider(CacheParameterProcessor cacheParameterProcessor) {
        this.defaultParameterProcessor = cacheParameterProcessor;
    }

    @Override
    public Optional<Object> getCachedResult(Method method, Object[] args) {
        CacheParameterProcessor parameterProcessor = defaultParameterProcessor.getCacheParameterProcessor(method);

        File resultFile = parameterProcessor.getFile(method, args);
        if (!resultFile.exists()) {
            return Optional.empty();
        }


        try (InputStream is = defaultParameterProcessor.zipInputStreamIfNeeded(new FileInputStream(resultFile));
             Input input = new Input(is)) {
            Object result = kryo.readClassAndObject(input);
            return Optional.of(result);
        } catch (IOException e) {
            throw new CacheProxyException("Some problem with reading file" + resultFile.toString(), e);
        } catch (KryoException e) {
            throw new CacheProxyException("Your return class don't have no-arg constructor and can't be deserialize. \n "
                    + e.getMessage(), e);
        }

    }

    @Override
    public void putCachedResult(Method method, Object[] args, Object result) {
        CacheParameterProcessor parameterProcessor = defaultParameterProcessor.getCacheParameterProcessor(method);

        File putFile = parameterProcessor.getFile(method, args);

        try (OutputStream is = defaultParameterProcessor.zipOutputStreamIfNeeded(new FileOutputStream(putFile));
             Output output = new Output(is)) {
            kryo.writeClassAndObject(output, result);
        } catch (IOException e) {
            throw new CacheProxyException("Some problem with reading file " + putFile.toString(), e);
        }
    }
}
