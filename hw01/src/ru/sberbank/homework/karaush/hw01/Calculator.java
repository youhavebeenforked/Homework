public class Calculator {

    public <T extends Number> Number add(T first, T second){
        if(first instanceof Double)
            return first.doubleValue() + second.doubleValue();
        else
            return first.intValue() + second.intValue();
    }

    public <T extends Number> Number subtract(T first, T second){
        if(first instanceof Double)
            return first.doubleValue() - second.doubleValue();
        else
            return first.intValue() - second.intValue();
    }

    public <T extends Number> Number multiply(T first, T second){
        if(first instanceof Double)
            return first.doubleValue() * second.doubleValue();
        else
            return first.intValue() * second.intValue();
    }

    public <T extends Number> Number divide(T first, T second){
        if(first instanceof Double)
            return first.doubleValue() / second.doubleValue();
        else
            return first.intValue() / second.intValue();
    }


}


