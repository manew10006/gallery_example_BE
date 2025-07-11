package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.cart.CartMapper;
import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartMapper cartMapper;

    //여러개의 작업을 하나로 묶는 것, 같은 이름의 작업중(여기선 save) 둘 중 하나가 에러터지면 하나는 원래대로 되돌리게해줌
    @Transactional
    public int saveOrder(OrderPostReq req, int logginedMemberId) {
        //주문하고자하는 상품정보 DB로 부터 가져오기
        List<ItemGetRes> itemList = itemMapper.findAllByIdIn(req.getItemIds());
        log.info("itemList={}",itemList);

        //총 구매가격 콘솔에 출력!!!
            int amount=0;
            for(ItemGetRes item:itemList){
            int price = item.getPrice()-(item.getPrice()*item.getDiscountPer() / 100);
            amount+=price;
        }
        log.info("AllPrice={}",amount);

        // OrderPostDto 객체화하시고 데이터 넣기
        OrderPostDto orderPostDto = new OrderPostDto();
        orderPostDto.setMemberId(logginedMemberId);
        orderPostDto.setName(req.getName());
        orderPostDto.setAddress(req.getAddress());
        orderPostDto.setPayment(req.getPayment());
        orderPostDto.setCardNumber(req.getCardNumber());
        orderPostDto.setAmount(amount);
        log.info("before-orderPostDto={}",orderPostDto);

        //int affectedRows = orderMapper.save(orderPostDto); // 리턴메소드지만 값(영향받은행수) 쓸때가없어서 메소드호출만 함
        orderMapper.save(orderPostDto);
        log.info("after-orderPostDto={}",orderPostDto);

        //OrderItemPostDto 객체화 하면서 데이터 넣어주기
        OrderItemPostDto orderItemPostDto = new OrderItemPostDto(orderPostDto.getOrderId(),req.getItemIds());

        orderItemMapper.save(orderItemPostDto);
        cartMapper.deleteByMemberId(logginedMemberId);

        return 1;
    }

    public List<OrderGetRes> findAllByMemberIdOrderByIdDesc(int memberId) {
        return orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
    }

    public OrderDetailGetRes detail(OrderDetailGetReq r) {
        OrderDetailGetRes result = orderMapper.findByOrderIdAndMemberId(r);
        log.info("result={}",result);
        return result;
    }


}
