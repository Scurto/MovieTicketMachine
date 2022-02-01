package services.impl;

import enums.TicketType;
import exceptions.ReceiptServiceException;
import services.ReceiptService;

import java.util.Locale;
import java.util.TreeMap;

public class ReceiptServiceImpl implements ReceiptService {

    public String printTicketRow(TicketType type) {
        switch (type) {
            case ADULT: return "ADULT Ticket:            Price: £" + amountFormatter(TicketType.ADULT.getPrice());
            case SENIOR: return "SENIOR Ticket:           Price: £" + amountFormatter(TicketType.SENIOR.getPrice());
            default: return null;
        }
    }

    public String printTotalRow(double total) {
        return TOTAL_AMOUNT_MESSAGE + amountFormatter(total);
    }

    public String print(TreeMap<TicketType, Integer> orderInfo, double total) {
        if (orderInfo == null) throw new ReceiptServiceException("Order info is empty");
        StringBuilder builder = new StringBuilder();
        builder.append(TOP_MESSAGE).append("\n").append("\n");;

        for (TicketType type : orderInfo.keySet()) {
            for (int i = 0; i < orderInfo.get(type); i++) {
                builder.append(printTicketRow(type)).append("\n");
            }
        }

        builder.append(printTotalRow(total)).append("\n").append("\n");
        builder.append(BOTTOM_MESSAGE);
        return builder.toString();
    }

    private String amountFormatter(double amount) {
        return String.format(Locale.US, "%.2f", amount);
    }
}
