package com.qunar.campus.spring.tutorial.application.case3.auto.detect;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: Cache
 *
 * @author yushen.ma
 * @version 2015-03-19 21:33
 */
@Component
public class Cache {

    /**
     * 查询指定酒店的指定房型的某些天的报价
     * @param hotelId hotel id
     * @param roomId room id
     * @param fromDate from date
     * @param toDate to date
     * @return list of daily price
     */
    public List<DailyPrice> findPrice(int hotelId, int roomId,
                                      String fromDate, String toDate) {
        return Lists.newArrayList();
    }
}
