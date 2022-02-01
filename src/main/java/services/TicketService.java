package services;

import enums.TicketType;

import java.util.HashMap;
import java.util.TreeMap;

public interface TicketService {

    double buy(TicketType type, int count);
    double getTotalPrice();
    int getAvailablePlaces();
    TreeMap<TicketType, Integer> getOrderInfo();
}
