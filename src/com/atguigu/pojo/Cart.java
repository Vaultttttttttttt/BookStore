package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wxj27
 * @version 1.0
 * @description: 购物车对象
 * @date 2023-03-25 13:33
 */

public class Cart {

    private Integer totalCount;
    private BigDecimal totalPrice;
    /**
    * @description: key是商品编码，value是商品信息
    * @param: null
    * @return:
    * @author wxj27
    * @date: 2023-03-25 13:44
    */
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * @description: 添加商品项
     * @param: cartItem
     * @return: void
     * @author wxj27
     * @date: 2023-03-25 13:36
     */
    public void addItem(CartItem cartItem) {
        CartItem item=items.get(cartItem.getId());

        if(item==null){
            items.put(cartItem.getId(),cartItem);
        }else{
            item.setCount(item.getCount()+cartItem.getCount());
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * @description: 删除商品项
     * @param: id
     * @return: void
     * @author wxj27
     * @date: 2023-03-25 13:38
     */
    public void delete(Integer id) {
        items.remove(id);
    }

    /**
     * @description: 清空购物车
     * @param:
     * @return: void
     * @author wxj27
     * @date: 2023-03-25 13:38
     */
    public void clean() {
        items.clear();
    }

    /**
     * @description: 更新商品数量
     * @param: id
     * @param: count
     * @return: void
     * @author wxj27
     * @date: 2023-03-25 13:39
     */
    public void updateCount(Integer id, Integer count) {
        //先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem item=items.get(id);

        if(item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Integer getTotalCount() {
        totalCount=0;

        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        totalPrice=new BigDecimal(0);

        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
