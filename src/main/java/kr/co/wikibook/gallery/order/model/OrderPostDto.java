package kr.co.wikibook.gallery.order.model;

import lombok.*;

@Getter
@Setter
@ToString
public class OrderPostDto {
    private int orderId;
    private int memberId;
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private long amount;
}
