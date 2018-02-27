package ru.sberbank.homework.solovyov.task1;

public class PinVerificationInfoStorage {
    private static final int PIN_HASH_CODE = "1234".hashCode();
    private long accountUnblockTime = 0;
    private int userAttemptsNumber = 0;

    public int getUserAttemptsNumber() {
        return userAttemptsNumber;
    }

    public void incUserAttemptsNumber() {
        this.userAttemptsNumber++;
    }

    public void setUserAttemptsNumber(int userAttemptsNumber) {
        this.userAttemptsNumber = userAttemptsNumber;
    }


    public long getAccountUnblockTime() {
        return accountUnblockTime;
    }

    public void setAccountUnblockTime(long accountUnblockTime) {
        this.accountUnblockTime = accountUnblockTime;
    }

    public int getVerificationInfo(){
        return PIN_HASH_CODE;
    }


}
