package services;

import enums.TicketType;

import java.util.HashMap;
import java.util.TreeMap;

public interface ReceiptService {

    String TOP_MESSAGE = "Receipt for Willy Wonka Movie Theatre";
    String BOTTOM_MESSAGE = "Thank You for your custom, enjoy the movie!";
    String TOTAL_AMOUNT_MESSAGE = "                         TOTAL: £";

    String printTicketRow(TicketType type);
    String print(TreeMap<TicketType, Integer> orderInfo, double total);
}
