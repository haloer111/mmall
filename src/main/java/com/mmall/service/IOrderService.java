package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVo;

import java.util.Map;

/**
 * @author gexiao
 * @date 2018/10/22 16:20
 */
public interface IOrderService {

    public ServerResponse pay(Long orderNo, Integer userId, String path);

    public ServerResponse alipayCallback(Map<String, String> params);

    public ServerResponse queryOrderPayStatus(Long orderNo, Integer userId);

    public ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse cancel(Integer id, Long orderNo);

    ServerResponse getOrderCartProduct(Integer id);

    ServerResponse getOrderDetail(Integer id, Long orderNo);

    ServerResponse getOrderList(Integer id, int pageNum, int pageSize);

    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);


    public ServerResponse<String> manageSendGoods(Long orderNo);

    public ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);

    public ServerResponse<OrderVo> manageDetail(Long orderNo);


}
