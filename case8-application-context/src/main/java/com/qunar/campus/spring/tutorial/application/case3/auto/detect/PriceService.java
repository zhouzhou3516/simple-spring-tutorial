package com.qunar.campus.spring.tutorial.application.case3.auto.detect;

import com.google.common.base.Optional;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: PriceService
 *
 * 这是一个简单的酒店报价的例子
 *
 * 1. 先从缓存中查报价，命中则返回结果
 * 2. 如果缓存miss，则直接查数据库
 *
 * @Service
 * @Component
 * 有什么差异
 *
 * @author yushen.ma
 * @version 2015-03-19 21:32
 */
@Service
public class PriceService {

    @Resource
    private Cache cache;

    @Resource
    private HotelStorage hotelStorage;

    public List<DailyPrice> getDailyPrice(int hotelId, int roomId,
                                          String fromDate, String toDate) {
        List<DailyPrice> price = cache.findPrice(hotelId, roomId, fromDate, toDate);
        if (CollectionUtils.isEmpty(price)) {
            return Optional.fromNullable(
                    hotelStorage.findPrice(hotelId, roomId, fromDate, toDate))
                    .or(new ArrayList<DailyPrice>());
        }
        return price;
    }
}
