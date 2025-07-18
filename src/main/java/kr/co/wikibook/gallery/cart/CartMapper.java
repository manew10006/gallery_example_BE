package kr.co.wikibook.gallery.cart;

import kr.co.wikibook.gallery.cart.model.CartDeleteReq;
import kr.co.wikibook.gallery.cart.model.CartGetRes;
import kr.co.wikibook.gallery.cart.model.CartPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int save(CartPostReq r);
    List<CartGetRes> findAllWithItemByMemberId(int memberId);
    int deleteByCartIdAndMemberId(CartDeleteReq r);
    int deleteByMemberId(int memberId);

}
