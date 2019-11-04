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
    public void oneTicketSoldIsTicketPriceInRevenue() {
        expectedRevenue = new BigDecimal(TicketRevenue.TICKET_PRICE);
        assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIfLessThenZeroTicketsAreSold() {
        venueRevenue.estimateTotalRevenue(-1);
    }

    @Test
    public void zeroSalesEqZeroRevenur() {
        assertEquals(BigDecimal.ZERO, venueRevenue.estimateTotalRevenue(0));
    }

    @Test
    public void tenTicketsSoldIsTenPricesInRevenue() {
        expectedRevenue = new BigDecimal(TicketRevenue.TICKET_PRICE * 10);
        assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIfMoreThanSeatsInTheTheaterTicketsAreSold() {
        venueRevenue.estimateTotalRevenue(101);
    }
}
