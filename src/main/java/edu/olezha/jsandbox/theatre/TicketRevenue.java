package edu.olezha.jsandbox.theatre;

import java.math.BigDecimal;

public class TicketRevenue {

    public BigDecimal estimateTotalRevenue(int n) {
        if (n == 1) {
            return new BigDecimal("30");
        }

        return BigDecimal.ZERO;
    }
}
