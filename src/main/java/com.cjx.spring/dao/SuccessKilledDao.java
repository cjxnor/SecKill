package com.cjx.spring.dao;

import com.cjx.spring.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * SuccessKilled 类对应的 Dao
 */
public interface SuccessKilledDao {

    /**
     * 根据商品 skId 和用户手机号 userPhone 插入数据，可过滤重复
     * @param skId
     * @param userPhone
     * @return      数据库操作影响的行数
     */
    int insertSuccessKilled(@Param("skId") long skId, @Param("userPhone") long userPhone);

    /**
     * 根据 skId 查询 success_killed 表
     * @param skId
     * @return
     */
    SuccessKilled queryByIdWithStock(@Param("skId") long skId,@Param("userPhone") long userPhone);

}
