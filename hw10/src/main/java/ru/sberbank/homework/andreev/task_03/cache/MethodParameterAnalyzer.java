package ru.sberbank.homework.andreev.task_03.cache;

import ru.sberbank.homework.andreev.task_03.annotation.Empty;

import java.util.*;
import java.util.stream.Collectors;

public class MethodParameterAnalyzer {
    private static final Class EMPTY_CLASS = Empty.class;

    private List<Class> identityBy;
    private List<Object> inputArgs;

    public MethodParameterAnalyzer(Class[] identityBy, Object[] inputArgs) {
        this.identityBy = Arrays.asList(identityBy);
        this.inputArgs = Arrays.asList(inputArgs);
    }

    public Object[] filterByIdentity() {
        if (identityBy.isEmpty()) {
            return inputArgs.toArray();
        }

        checkIdentityBySize();

        Iterator<Class> identity = identityBy.iterator();
        Iterator<Object> argument = inputArgs.iterator();
        List<Object> result = new ArrayList<>();
        Class nextIdentity = identity.next();
        Object nextArgument = argument.next();

        while (identity.hasNext() && argument.hasNext()) {
            if (EMPTY_CLASS.equals(nextIdentity)) {
                nextArgument = argument.next();
                nextIdentity = identity.next();
                continue;
            }
            if (nextIdentity.equals(nextArgument.getClass())) {
                result.add(nextArgument);
                nextIdentity = identity.next();
                nextArgument = argument.next();
            } else {
                nextArgument = argument.next();
            }
        }

        if (identity.hasNext()) {
            throw new CacheProxyException(getExceptionMessage("IdentityBy elements don't match arguments: "));
        }

        return result.toArray();
    }

    private void checkIdentityBySize() {
        if (identityBy.size() > inputArgs.size()) {
            throw new CacheProxyException(getExceptionMessage("IdentityBy array greater then input argument: "));
        }
    }

    private String getExceptionMessage(String startMessage) {
        StringBuilder message = new StringBuilder(startMessage);
        List<? extends Class<?>> inputArgsClasses = inputArgs.stream().map(Object::getClass).collect(Collectors.toList());
        return message.append("\n Your identity array: ")
                .append(identityBy)
                .append("\n Your input arguments classes: ")
                .append(inputArgsClasses).toString();
    }
}
