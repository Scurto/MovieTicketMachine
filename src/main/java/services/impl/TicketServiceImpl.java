package services.impl;

import enums.TicketType;
import exceptions.NotCorrectCountException;
import exceptions.NotEnoughAvailablePlacesException;
import services.TicketService;

import java.util.HashMap;
import java.util.TreeMap;

public class TicketServiceImpl implements TicketService {

    private int availablePlaces = 100;
    private double totalPrice = 0.0;
    private TreeMap<TicketType, Integer> orderInfo;

    public TicketServiceImpl() {
        this.orderInfo = new TreeMap<>();;
    }

    public double buy(TicketType type, int count) {
        if (count <= 0) throw new NotCorrectCountException("NotCorrectCount");
        if (availablePlaces - count < 0) throw new NotEnoughAvailablePlacesException("NotEnoughAvailablePlaces");
        setAvailablePlaces(availablePlaces - count);

        double price = type.getPrice() * count;
        setTotalPrice(totalPrice + price);
        orderInfo.computeIfPresent(type, (key, value) -> value + count);
        orderInfo.putIfAbsent(type, count);
        return price;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TreeMap<TicketType, Integer> getOrderInfo() {
        return orderInfo;
    }
}
