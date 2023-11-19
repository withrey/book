package com.book.service;

import com.book.dao.*;
import com.book.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AttachDao attachDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private BookDao bookDao;


    // 주문 정보
    public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders) {

        List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();

        for(OrderPageItemDTO ord : orders) {

            OrderPageItemDTO goodsInfo = orderDao.getGoodsInfo(ord.getBookId());

            goodsInfo.setBookCount(ord.getBookCount());

            goodsInfo.initSaleTotal();

            List<AttachImageVO> imageList = attachDao.getAttachList(goodsInfo.getBookId());

            goodsInfo.setImageList(imageList);

            result.add(goodsInfo);

        }

        return result;

    }


    // 주문
    @Override
    @Transactional
    public void order(OrderDTO ord) {

        /* 사용할 데이터 가져오기 */
        // 회원 정보
        MemberVO member = memberDao.getMemberInfo(ord.getMemId());

        // 주문 정보
        List<OrderItemDTO> ords = new ArrayList<>();
        for(OrderItemDTO oit : ord.getOrders()) {
            OrderItemDTO orderItem = orderDao.getOrderInfo(oit.getBookId());
            // 수량 세팅
            orderItem.setBookCount(oit.getBookCount());
            // 기본정보 세팅
            orderItem.initSaleTotal();
            // list 객체 추가
            ords.add(orderItem);
        }

        // OrderDTO
        ord.setOrders(ords);
        ord.getOrderPriceInfo();


        /* DB 주문, 주문상품, 배송정보 넣기 */
        //orderId 만들기 및 OrderDTO객체 orderId에 저장
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
        String orderId = member.getMemId() + format.format(date);
        ord.setOrderId(orderId);

        /* DB 넣기 */
        // book_order 등록
        orderDao.enrollOrder(ord);
        // book_orderItem 등록
        for(OrderItemDTO oit : ord.getOrders()) {
            oit.setOrderId(orderId);
            orderDao.enrollOrderItem(oit);
        }

        /* 재고 변동 적용 */
        for(OrderItemDTO oit : ord.getOrders()) {
            // 변동 재고 값 구하기
            BookVO book = bookDao.getGoodsInfo(oit.getBookId());
            book.setBookStock(book.getBookStock() - oit.getBookCount());
            // 변동 값 DB 적용
            orderDao.deductStock(book);
        }

        /* 장바구니 제거 */
        for(OrderItemDTO oit : ord.getOrders()) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setMemId(ord.getMemId());
            cartDTO.setBookId(oit.getBookId());

            cartDao.deleteOrderCart(cartDTO);
        }

    }

    // 주문 취소
    @Override
    @Transactional
    public void orderCancel(OrderCancelDTO dto) {

        /* 주문, 주문상품 객체 */
        // 회원
        MemberVO member = memberDao.getMemberInfo(dto.getMemId());

        // 주문상품
        List<OrderItemDTO> ords = orderDao.getOrderItemInfo(dto.getOrderId());
        for(OrderItemDTO ord : ords) {
            ord.initSaleTotal();
        }

        // 주문
        OrderDTO orw = orderDao.getOrder(dto.getOrderId());
        orw.setOrders(ords);

        orw.getOrderPriceInfo();

        // 주문상품 취소 DB
        orderDao.orderCancel(dto.getOrderId());

        // 재고
        for(OrderItemDTO ord : orw.getOrders()) {
            BookVO book = bookDao.getGoodsInfo(ord.getBookId());
            book.setBookStock(book.getBookStock() + ord.getBookCount());
            orderDao.deductStock(book);
        }

    }

}
