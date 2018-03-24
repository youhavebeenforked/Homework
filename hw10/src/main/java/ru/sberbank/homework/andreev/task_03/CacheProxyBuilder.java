package ru.sberbank.homework.andreev.task_03;

import ru.sberbank.homework.andreev.task_03.annotation.CacheType;
import ru.sberbank.homework.andreev.task_03.cache.CacheParameterProcessor;

public class CacheProxyBuilder {
    private CacheType cacheType = CacheType.IN_MEMORY;
    private String fileNamePrefix = "";
    private boolean zip = false;
    private Class[] identityBy = new Class[0];
    private int listSize = 0;
    private String rootFolder = System.getProperty("user.dir");

    public CacheProxyBuilder(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    public CacheProxyBuilder() {
    }

    public CacheProxyBuilder setCacheType(CacheType cacheType) {
        if (!cacheType.equals(CacheType.EMPTY)) {
            this.cacheType = cacheType;
        }
        return this;
    }

    public CacheProxyBuilder setFileNamePrefix(String prefix) {
        this.fileNamePrefix = prefix;
        return this;
    }

    public CacheProxyBuilder setZip(boolean zipStatus) {
        this.zip = zipStatus;
        return this;
    }

    public CacheProxyBuilder setSizeForListResult(int listSize) {
        this.listSize = listSize;
        return this;
    }

    public CacheProxyBuilder setRootFolder(String root) {
        this.rootFolder = root;
        return this;
    }

    public CacheProxy getCaheProxy() {
        return new CacheProxy(new CacheParameterProcessor(cacheType, rootFolder, fileNamePrefix, zip, identityBy, listSize));
    }

}
