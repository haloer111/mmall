package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * @author gexiao
 * @date 2018/10/13 13:56
 */
public interface ICartService {
    public ServerResponse<CartVo> add(Integer userId, Integer count, Integer productId);

    public ServerResponse<CartVo> update(Integer userId, Integer count, Integer productId);

    public ServerResponse<CartVo> delete(Integer userId, String productIds);

    public ServerResponse<CartVo> list(Integer userId);

    public ServerResponse<Integer> getCartProductCount(Integer userId);

    public ServerResponse<CartVo> selectOrUnselect(Integer userId, Integer productId, Integer checked);
}
