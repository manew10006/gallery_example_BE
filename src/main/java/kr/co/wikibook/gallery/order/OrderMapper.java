package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.order.model.OrderPostDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto r);


}
