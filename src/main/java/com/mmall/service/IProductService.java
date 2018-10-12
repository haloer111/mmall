package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * @author gexiao
 * @date 2018/9/21 15:55
 */
public interface IProductService {
    ServerResponse InsertOrUpdateProduct(Product product);

    ServerResponse<String> SetSaltStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int
            pageSize, String orderBy);
}
