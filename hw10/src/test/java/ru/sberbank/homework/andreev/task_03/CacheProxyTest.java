package ru.sberbank.homework.andreev.task_03;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.andreev.task_03.annotation.CacheType;
import ru.sberbank.homework.andreev.task_03.method_classes.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CacheProxyTest {

    private final String TEST_DIRECTORY = "test";

    private CacheProxyBuilder cacheProxyBuilder;

    @Before
    public void setUp() throws Exception {
        cacheProxyBuilder = new CacheProxyBuilder(TEST_DIRECTORY);
    }

    @After
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(new File(TEST_DIRECTORY));
    }

    @Test
    public void inMemoryHappyPath() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.IN_MEMORY).getCaheProxy();
        IMethodClass methodClassMock = mock(IMethodClass.class);
        when(methodClassMock.discriminant(5d, 12d, 0d)).thenReturn(new BigDecimal(144));

        IMethodClass proxyMethod = cacheProxy.cache(methodClassMock);
        proxyMethod.discriminant(5d, 12d, 0d);
        proxyMethod.discriminant(5d, 12d, 0d);
        proxyMethod.discriminant(5d, 12d, 0d);

        verify(methodClassMock, times(1)).discriminant(5d, 12d, 0d);
    }

    @Test
    public void fileHappyPath() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.FILE).getCaheProxy();
        IMethodClass methodClassMock = mock(IMethodClass.class);
        when(methodClassMock.discriminant(5d, 12d, 0d)).thenReturn(new BigDecimal(144));

        IMethodClass proxyMethod = cacheProxy.cache(methodClassMock);
        proxyMethod.discriminant(5d, 12d, 0d);
        proxyMethod.discriminant(5d, 12d, 0d);
        proxyMethod.discriminant(5d, 12d, 0d);

        verify(methodClassMock, times(1)).discriminant(5d, 12d, 0d);
    }

    @Test
    public void listSizeFromAnnotationParameter() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.IN_MEMORY).getCaheProxy();
        IMethodClass proxyMethod = cacheProxy.cache(new MethodClass());
        List<String> result = proxyMethod.createStringList(10, "str");
        assertEquals(10, result.size());
        result = proxyMethod.createStringList(10, "str");
        assertEquals(5, result.size());
    }

    @Test
    public void listSizeFromDefaultParameter() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.FILE)
                .setSizeForListResult(15).getCaheProxy();
        IMethodClass proxyMethod = cacheProxy.cache(new MethodClass());
        List<Integer> result = proxyMethod.collectSubnumber(30);
        assertEquals(30, result.size());
        result = proxyMethod.collectSubnumber(30);
        assertEquals(15, result.size());
    }

    @Test
    public void methodWithZeroArgumentCache() {
        CacheProxy cacheProxy = cacheProxyBuilder.getCaheProxy();
        IMethodClass methodClassMock = mock(IMethodClass.class);
        when(methodClassMock.mc()).thenReturn(new MethodClass());
        IMethodClass proxyMethod = cacheProxy.cache(methodClassMock);
        proxyMethod.mc();
        proxyMethod.mc();
        verify(methodClassMock, times(1)).mc();
    }

    @Test
    public void checkTwoProxyMethodDontInfluenceInFile() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.FILE).getCaheProxy();
        WithInternalParameter proxyMethodFirst = cacheProxy.cache(new WithInternalParameterImpl(18.8));
        WithInternalParameter proxyMethodSecond = cacheProxy.cache(new WithInternalParameterImpl(10d));
        proxyMethodFirst.substract(5d);
        assertEquals(13.8, (Object) proxyMethodFirst.substract(5d));
        proxyMethodSecond.substract(5d);
        assertEquals(5d, (Object) proxyMethodSecond.substract(5d));
        Collection<File> filesWithResult = FileUtils.listFiles(new File(TEST_DIRECTORY), null, true);
        assertEquals(2, filesWithResult.size());
    }

    @Test
    public void checkTwoProxyMethodDontInfluenceInMemory() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.FILE).getCaheProxy();
        WithInternalParameter proxyMethodFirst = cacheProxy.cache(new WithInternalParameterImpl(2000.8));
        WithInternalParameter proxyMethodSecond = cacheProxy.cache(new WithInternalParameterImpl(10_000d));
        Double firstCast = proxyMethodFirst.substract(5d);
        Double secondCast = proxyMethodFirst.substract(5d);
        assertEquals(1995.8, (Object) secondCast);
        assertEquals(firstCast, secondCast);
        firstCast = proxyMethodSecond.substract(5d);
        secondCast = proxyMethodSecond.substract(5d);
        assertEquals(9_995d, (Object) secondCast);
        assertEquals(firstCast, secondCast);
    }

    @Test
    public void checkSerializationForCustomClass() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.FILE).getCaheProxy();
        WithInternalParameter proxyMethod = cacheProxy.cache(new ForSerializationSubObject(2.71));
        proxyMethod.returMyself();
        proxyMethod.returMyself();
        proxyMethod.returMyself();
        Collection<File> filesWithResult = FileUtils.listFiles(new File(TEST_DIRECTORY), null, true);
        assertEquals(1, filesWithResult.size());
    }

    @Test
    public void checkZipParameter() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.FILE).setZip(true).getCaheProxy();
        IMethodClass methodClass = cacheProxy.cache(new MethodClass());
        methodClass.sum(4, 4);
        Collection<File> filesWithResult = FileUtils.listFiles(new File(TEST_DIRECTORY), null, true);
        assertEquals(1, filesWithResult.size());
        File potentiallyGzippedFile = filesWithResult.stream().findFirst().get();
        assertTrue(isGZipped(potentiallyGzippedFile));
    }

    private static boolean isGZipped(File potentiallyGzippedFile) {
        try (InputStream is = new FileInputStream(potentiallyGzippedFile)) {
            byte[] b = new byte[2];
            int n = is.read(b);
            if (n != 2) {
                return false;
            }
            if ((b[0] == (byte) 0x1f) && (b[1] == (byte) 0x8b)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Test
    public void identityByChecker() {
        CacheProxy cacheProxy = cacheProxyBuilder.setCacheType(CacheType.FILE).getCaheProxy();
        ForIdentityCheck methodClass = cacheProxy.cache(new ForIdentityCheckImpl());

        String firstWithoutEmptyMark = methodClass.methodWithoutEmptyMark("first", 42, "third", "fourth", true);
        String secondWithoutEmptyMark = methodClass.methodWithoutEmptyMark("any", 42, "third", "thing", true);
        String firstWithEmptyMark = methodClass.methodWithEmptyMark("first", 42, "third", "fourth", true);
        String secondWithEmptyMark = methodClass.methodWithEmptyMark("some", 42, "third", "shiiieeet", true);

        assertEquals(firstWithoutEmptyMark, secondWithoutEmptyMark);
        assertEquals(firstWithEmptyMark, secondWithEmptyMark);
        assertEquals(firstWithEmptyMark, secondWithoutEmptyMark);
        Collection<File> filesWithResult = FileUtils.listFiles(new File(TEST_DIRECTORY), null, true);
        assertEquals(2, filesWithResult.size());
    }
}