package ru.sberbank.homework.bedarev;

public class ValueStorage {
    private  Double result;
    private boolean runAtFirstTime = true;
    private String errorInExpression = null;

   public Double getResult () {
       return result;
   }

   public void setResult (Double result) {
       this.result =result;
   }

   public void setRunAtFirstTime(boolean runAtFirstTime) {
       this.runAtFirstTime = runAtFirstTime;
   }

    public boolean getRunAtFirstTime() {
        return runAtFirstTime;
    }

    public String getErrorInExpression() {
        return errorInExpression;
    }

    public void setErrorInExpression(String errorInExpression) {
        this.errorInExpression = errorInExpression;
    }
}

