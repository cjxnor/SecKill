package com.cjx.spring.dao;

import com.cjx.spring.entity.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Stock 类对应的Dao
 */
public interface StockDao {

    /**
     * 根据商品 id 减库存操作
     * @param skId
     * @param killTime
     * @return      数据库操作影响的行数
     */
    int reduceNumber(@Param("skId") long skId, @Param("killTime") Date killTime);

    /**
     * 根据商品 id ,查询库存
     * @param skId
     * @return
     */
    Stock queryById(long skId);

    /**
     * 根据偏移量（ offset 和 limit ）查询多条库存数据
     * @param offset
     * @param limit
     * @return
     */
    List<Stock> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
