package edu.olezha.jsandbox.theatre;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TicketRevenueTest {

    private TicketRevenue venueRevenue;
    private BigDecimal expectedRevenue;

    @Before
    public void setUp() {
        venueRevenue = new TicketRevenue();
    }

    @Test
    public void oneTicketSoldIsThirtyInRevenue() {
        expectedRevenue = new BigDecimal(TicketRevenue.TICKET_PRICE);
        assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(1));
    }
}
