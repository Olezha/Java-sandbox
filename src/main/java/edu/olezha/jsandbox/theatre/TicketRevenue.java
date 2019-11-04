package edu.olezha.jsandbox.theatre;

import java.math.BigDecimal;

public class TicketRevenue {

    public static final int TICKET_PRICE = 30;
    public static final int SEATS_IN_THEATER = 100;

    public BigDecimal estimateTotalRevenue(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Less then zero tickets are sold");
        }
        if (n > SEATS_IN_THEATER) {
            throw new IllegalArgumentException("More than seats in the theater tickets are sold");
        }

        return new BigDecimal(TICKET_PRICE * n);
    }
}
