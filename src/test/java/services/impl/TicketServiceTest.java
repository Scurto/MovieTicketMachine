package services.impl;

import enums.TicketType;
import exceptions.NotCorrectCountException;
import exceptions.NotEnoughAvailablePlacesException;
import org.junit.jupiter.api.Test;
import services.TicketService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TicketServiceTest {

    @Test
    public void getPriceForOneAdultTicket() {
        TicketService ticketService = new TicketServiceImpl();
        assertEquals(20.00, ticketService.buy(TicketType.ADULT, 1));
    }

    @Test
    public void getPriceForOneSeniorTicket() {
        TicketService ticketService = new TicketServiceImpl();
        assertEquals(8.00, ticketService.buy(TicketType.SENIOR, 1));
    }

    @Test
    public void getPriceForFewAdultTickets() {
        TicketService ticketService = new TicketServiceImpl();
        assertEquals(40.00, ticketService.buy(TicketType.ADULT, 2));
    }

    @Test
    public void getPriceForFewSeniorTickets() {
        TicketService ticketService = new TicketServiceImpl();
        assertEquals(16.00, ticketService.buy(TicketType.SENIOR, 2));
    }

    @Test
    public void getTotalPriceForMultiOrder() {
        TicketService ticketService = new TicketServiceImpl();
        ticketService.buy(TicketType.ADULT, 1);
        ticketService.buy(TicketType.SENIOR, 1);
        assertEquals(28.00, ticketService.getTotalPrice());
    }

    @Test
    public void countForAvailablePlaces() {
        TicketService ticketService = new TicketServiceImpl();
        ticketService.buy(TicketType.SENIOR, 3);
        assertEquals(97, ticketService.getAvailablePlaces());
    }

    @Test
    public void notEnoughAvailablePlaces() {
        TicketService ticketService = new TicketServiceImpl();
        NotEnoughAvailablePlacesException ex = assertThrows(NotEnoughAvailablePlacesException.class, ()->{
            ticketService.buy(TicketType.ADULT, 50);
            ticketService.buy(TicketType.SENIOR, 51);
        });

        assertEquals("NotEnoughAvailablePlaces", ex.getMessage());
    }

    @Test
    public void notCorrectCount() {
        TicketService ticketService = new TicketServiceImpl();
        NotCorrectCountException ex = assertThrows(NotCorrectCountException.class, ()->{
            ticketService.buy(TicketType.ADULT, 0);
        });

        assertEquals("NotCorrectCount", ex.getMessage());
    }

    @Test
    public void addTicketToOrderInfo() {
        TicketService ticketService = new TicketServiceImpl();
        ticketService.buy(TicketType.ADULT, 1);
        assertEquals(1, ticketService.getOrderInfo().get(TicketType.ADULT));
    }

    @Test
    public void updateCountInOrderInfoByTicketType() {
        TicketService ticketService = new TicketServiceImpl();
        ticketService.buy(TicketType.ADULT, 1);
        ticketService.buy(TicketType.SENIOR, 2);
        ticketService.buy(TicketType.ADULT, 2);
        assertEquals(3, ticketService.getOrderInfo().get(TicketType.ADULT));
        assertEquals(2, ticketService.getOrderInfo().get(TicketType.SENIOR));
    }
}
