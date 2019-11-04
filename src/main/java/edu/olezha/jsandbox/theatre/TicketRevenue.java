package edu.olezha.jsandbox.theatre;

import java.math.BigDecimal;

public class TicketRevenue {

    public static final int TICKET_PRICE = 30;

    public BigDecimal estimateTotalRevenue(int n) {
        return new BigDecimal(TICKET_PRICE * n);
    }
}
