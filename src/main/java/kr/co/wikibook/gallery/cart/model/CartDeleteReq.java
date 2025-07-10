package kr.co.wikibook.gallery.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class CartDeleteReq {
    private int cartId;
    private int memberId;
}
