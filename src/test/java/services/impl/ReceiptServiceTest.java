package services.impl;

import enums.TicketType;
import exceptions.ReceiptServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.ReceiptService;
import services.TicketService;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {

    @Mock
    private TicketService ticketService;

    @Test
    public void printReceiptWithTopAndBottom() {
        ReceiptService receiptService = new ReceiptServiceImpl();
        String result = receiptService.print(new TreeMap<>(), 0.0);
        assertTrue(result.contains(receiptService.TOP_MESSAGE));
        assertTrue(result.contains(receiptService.BOTTOM_MESSAGE));
    }

    @Test
    public void printReceiptWithTotalAmount() {
        ReceiptService receiptService = new ReceiptServiceImpl();
        String result = receiptService.print(new TreeMap<>(), 10.0);
        assertTrue(result.contains(receiptService.TOTAL_AMOUNT_MESSAGE+10.0));
    }

    @Test
    public void getReceiptLineForAdultTicket() {
        ReceiptService receiptService = new ReceiptServiceImpl();
        assertEquals("ADULT Ticket:            Price: £20.00", receiptService.printTicketRow(TicketType.ADULT));
    }

    @Test
    public void getReceiptLineForSeniorTicket() {
        ReceiptService receiptService = new ReceiptServiceImpl();
        assertEquals("SENIOR Ticket:           Price: £8.00", receiptService.printTicketRow(TicketType.SENIOR));
    }

    @Test
    public void printReceipt() {
        ReceiptService receiptService = new ReceiptServiceImpl();

        TreeMap<TicketType, Integer> orderInfo = new TreeMap<>();
        orderInfo.put(TicketType.ADULT, 1);
        orderInfo.put(TicketType.SENIOR, 2);
        when(ticketService.getOrderInfo()).thenReturn(orderInfo);
        when(ticketService.getTotalPrice()).thenReturn(36.00);

        String result = receiptService.print(ticketService.getOrderInfo(), ticketService.getTotalPrice());
        System.out.println(result);
        assertEquals("Receipt for Willy Wonka Movie Theatre\n" +
                "\n" +
                "ADULT Ticket:            Price: £20.00\n" +
                "SENIOR Ticket:           Price: £8.00\n" +
                "SENIOR Ticket:           Price: £8.00\n" +
                "                         TOTAL: £36.00\n" +
                "\n" +
                "Thank You for your custom, enjoy the movie!", result);
    }

    @Test
    public void orderInfoIsEmpty() {
        ReceiptService receiptService = new ReceiptServiceImpl();
        ReceiptServiceException ex = assertThrows(ReceiptServiceException.class, ()->{
            receiptService.print(null, 0.0);
        });

        assertEquals("Order info is empty", ex.getMessage());
    }

}
