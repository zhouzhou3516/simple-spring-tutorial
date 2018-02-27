package com.qunar.campus.spring.meta.data;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: MetaDataTest
 *
 * @author yushen.ma
 * @version 2015-03-23 14:42
 */
@Synopsis(name = "meta data test", difficulty = Difficulty.EASY)
public class MetaDataTest {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        String s = ToStringUtils.toString(new User(1, "admin", "admin", "asldas", 1, true));
        logger.info("{}", s);
    }

}

