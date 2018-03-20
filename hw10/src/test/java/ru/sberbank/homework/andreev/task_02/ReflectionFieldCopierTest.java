package ru.sberbank.homework.andreev.task_02;

import org.junit.Test;
import ru.sberbank.homework.andreev.task_02.objects.First;
import ru.sberbank.homework.andreev.task_02.objects.TrimFirst;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class ReflectionFieldCopierTest {
    private ReflectionFieldCopier reflectionFieldCopier = new ReflectionFieldCopier();

    @Test
    public void checkTwoEqualObject() {
        First from = new First("sdds", 15L, 15.05f, Arrays.asList("meeny", "miny", "moe"), Optional.of("SSDDD"));
        First to = new First();
        reflectionFieldCopier.copy(from, to);
        assertEquals(from, to);
    }

    @Test
    public void checkTwoLitleBitDifferenceObject() {
        First from = new First("sdds", 15L, 15.05f, Arrays.asList("meeny", "miny", "moe"), Optional.of("SSDDD"));
        TrimFirst to = new TrimFirst();
        TrimFirst expected = new TrimFirst("sdds", null,  15.050000190734863, Arrays.asList("meeny", "miny", "moe"), null);
        reflectionFieldCopier.copy(from, to);
        assertEquals(expected, to);
    }
}