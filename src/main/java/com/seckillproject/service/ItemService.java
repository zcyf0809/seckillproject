package com.seckillproject.service;

import com.seckillproject.error.BusinessException;
import com.seckillproject.service.model.ItemModel;

import java.util.List;

/**
 * @author Zhang Yifei
 * @date 2019/12/6 14:12
 */
public interface ItemService {

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param itemModel:商品模型
     * @return ItemModel:商品模型
     * @description 创建商品
     */
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param
     * @return List<ItemModel>:商品List
     * @description 列出商品
     */
    List<ItemModel> listItem();

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param id：商品id
     * @return ItemModel
     * @description 通过id获取商品详情
     */
    ItemModel getItemById(Integer id);

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param itemId
     * @param amount
     * @return boolean
     * @description 扣减库存
     */
    boolean decreaseStock(Integer itemId,Integer amount) throws BusinessException;

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param
     * @return
     * @description 增加商品销量
     */
    void increaseSales(Integer itemId,Integer amount) throws BusinessException;

}
