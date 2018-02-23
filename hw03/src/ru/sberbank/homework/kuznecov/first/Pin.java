package ru.sberbank.homework.kuznecov.first;

public class Pin {
    private final Integer pin;

    public Pin(Integer pin) {
        if (pin >= 0 && pin < 10000) {
            this.pin = pin;
        } else {
            throw new IllegalArgumentException("Pin must be a positive 4-digits number");
        }
    }

    public Integer getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return pin.equals(((Pin) o).getPin());
    }
}
