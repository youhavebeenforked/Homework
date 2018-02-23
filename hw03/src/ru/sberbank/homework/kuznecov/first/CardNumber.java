package ru.sberbank.homework.kuznecov.first;

public class CardNumber {
    private final Long cardNumber;

    public CardNumber(Long cardNumber) {
        if (cardNumber >= 0L && cardNumber < 1_0000_0000_0000_0000L) {
            this.cardNumber = cardNumber;
        } else {
            throw new IllegalArgumentException("CardNumber must be a positive 16-digits number");
        }
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return cardNumber.equals(((CardNumber) o).getCardNumber());
    }
}
