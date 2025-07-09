package kr.co.wikibook.gallery.cart.model;

import kr.co.wikibook.gallery.item.model.ItemGetRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartPostReq {
    //세션에 저장되어있는 id값을 쓸 것. FE로 부터 받지않는다
    private int memberId;
    private int itemId;
}
