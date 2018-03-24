package ru.sberbank.homework.andreev.task_03.cache;

import ru.sberbank.homework.andreev.task_03.annotation.Cache;
import ru.sberbank.homework.andreev.task_03.annotation.CacheType;
import ru.sberbank.homework.andreev.task_03.annotation.Zip;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CacheParameterProcessor {
    private CacheType cacheType;
    private String fileNamePrefix;
    private boolean zip;
    private Class[] identityBy;
    private int listSize;

    private String rootFolder;
    private Integer objectHash;

    private CacheProvider inMemory;
    private CacheProvider inFile;

    private CacheParameterProcessor(Cache annotation, CacheParameterProcessor boilerplate) {
        if (CacheType.EMPTY.equals(annotation.cacheType())) {
            this.cacheType = boilerplate.cacheType;
        } else {
            this.cacheType = annotation.cacheType();
        }
        if ("".equals(annotation.fileNamePrefix())) {
            this.fileNamePrefix = boilerplate.fileNamePrefix;
        } else {
            this.fileNamePrefix = annotation.fileNamePrefix();
        }
        if (Zip.EMPTY.equals(annotation.zip())) {
            this.zip = boilerplate.zip;
        } else {
            this.zip = annotation.zip().getValue();
        }
        this.identityBy = annotation.identityBy();
        if (annotation.listSize() == 0) {
            this.listSize = boilerplate.listSize;
        } else {
            this.listSize = annotation.listSize();
        }
        this.rootFolder = boilerplate.rootFolder;
        this.inMemory = boilerplate.inMemory;
        this.inFile = boilerplate.inFile;
        this.objectHash = boilerplate.objectHash;
    }

    public CacheParameterProcessor(CacheParameterProcessor template) {
        this(template.cacheType, template.rootFolder, template.fileNamePrefix, template.zip, new Class[0], template.listSize);
    }

    public CacheParameterProcessor(CacheType cacheType, String rootFolder, String fileNamePrefix, boolean zip, Class[] identityBy, int listSize) {
        this.cacheType = cacheType;
        this.fileNamePrefix = fileNamePrefix;
        this.zip = zip;
        this.identityBy = identityBy;
        this.listSize = listSize;
        this.rootFolder = rootFolder;
        initializeCacheProviders();
    }

    private void initializeCacheProviders() {
        inMemory = new InMemoryCacheProvider(this);
        inFile = new FileCacheProvider(this);
    }

    public CacheParameterProcessor getCacheParameterProcessor(Method method) {
        CacheParameterProcessor result = this;
        if (method.isAnnotationPresent(Cache.class)) {
            Cache annotation = method.getAnnotation(Cache.class);
            result = new CacheParameterProcessor(annotation, this);
        }
        return result;
    }

    public Integer calculateHash(Object[] args) {
        Object[] toHash = filterInputArgs(args);
        return Objects.hash(toHash);
    }

    private Object[] filterInputArgs(Object[] args) {
        if (Objects.isNull(args)) {
            return new Object[0];
        }
        MethodParameterAnalyzer methodParameterAnalyzer = new MethodParameterAnalyzer(identityBy, args);
        return methodParameterAnalyzer.filterByIdentity();

    }

    public CacheProvider getCacheProvider() {
        if (this.cacheType == CacheType.IN_MEMORY) {
            return inMemory;
        } else if (this.cacheType == CacheType.FILE) {
            return inFile;
        } else {
            throw new CacheProxyException("Wrong CacheType: " + this.cacheType);
        }
    }

    public Object checkListResult(Object input) {
        Object result = input;
        if (result instanceof List) {
            result = this.getSublist((List) result);
        }
        return result;
    }

    private List<Object> getSublist(List<Object> list) {
        List<Object> result = null;
        if ((listSize == 0) || (list.size() < listSize)) {
            result = list;
        }
        if (listSize > 0) {
            result = new ArrayList<>(list.subList(0, listSize));
        }
        if (listSize < 0) {
            throw new CacheProxyException("Parameter list size is negative: " + listSize);
        }
        return result;
    }

    public File getFile(Method method, Object[] arg) {
        Path directories = Paths.get(rootFolder, method.getName() + method.hashCode() + objectHash);
        try {
            Files.createDirectories(directories);
        } catch (IOException e) {
            throw new CacheProxyException("Some problem occurs when creating directories for path: " + directories.toString(), e);
        }
        Path pathToFile = Paths.get(directories.toString(), fileNamePrefix + calculateHash(arg).toString());
        return new File(pathToFile.toString());
    }

    public OutputStream zipOutputStreamIfNeeded(FileOutputStream fos) throws IOException {
        if (zip) {
            return new GZIPOutputStream(fos);
        }
        return fos;
    }

    public InputStream zipInputStreamIfNeeded(FileInputStream fis) throws IOException {
        if (zip) {
            return new GZIPInputStream(fis);
        }
        return fis;
    }

    public void setObjectHash(Integer objectHash) {
        this.objectHash = objectHash;
    }
}
