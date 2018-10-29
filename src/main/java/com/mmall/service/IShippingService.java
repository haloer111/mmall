package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * @author gexiao
 * @date 2018/10/19 10:29
 */
public interface IShippingService {
    public ServerResponse add(Integer userId, Shipping shipping);

    public ServerResponse del(Integer userId, Integer shippingId);

    public ServerResponse update(Integer userId, Shipping shipping);

    public ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    public ServerResponse<PageInfo> list(Integer userId, Integer pageNum, Integer pageSize);
}

