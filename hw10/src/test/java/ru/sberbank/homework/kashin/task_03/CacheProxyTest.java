package ru.sberbank.homework.kashin.task_03;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.sberbank.homework.kashin.datafortest.Person;
import ru.sberbank.homework.kashin.datafortest.DataForTestInFile;
import ru.sberbank.homework.kashin.datafortest.TestCounter;
import ru.sberbank.homework.kashin.datafortest.TestPerson;
import ru.sberbank.homework.kashin.task_03.proxy.DynamicProxy;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CacheProxyTest {
    private DynamicProxy proxy = new DynamicProxy(DataForTestInFile.ROOT);

    @Mock
    private TestCounter counter;

    @InjectMocks
    private Person person = new TestPerson(10, "Qwerty");

    private Person proxyPerson = proxy.create(person.getClass().getInterfaces(), person);

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void withoutCache() {
        proxyPerson.doItWithoutCache(10);
        proxyPerson.doItWithoutCache(20);
        proxyPerson.doItWithoutCache(10);
        proxyPerson.doItWithoutCache(20);
        proxyPerson.doItWithoutCache(10);

        verify(counter, times(3)).count(10);
        verify(counter, times(2)).count(20);
    }

    @Test
    public void inFileCache() {
        DataForTestInFile.clearRootDirectory();

        proxyPerson.doItInFile(10);
        proxyPerson.doItInFile(20);
        proxyPerson.doItInFile(10);
        proxyPerson.doItInFile(20);
        proxyPerson.doItInFile(10);

        verify(counter, times(1)).count(10);
        verify(counter, times(1)).count(20);
    }

    @Test
    public void inMemoryCache() {
        proxyPerson.doItInMemory(10);
        proxyPerson.doItInMemory(20);
        proxyPerson.doItInMemory(10);
        proxyPerson.doItInMemory(20);
        proxyPerson.doItInMemory(10);

        verify(counter, times(1)).count(10);
        verify(counter, times(1)).count(20);
    }
}