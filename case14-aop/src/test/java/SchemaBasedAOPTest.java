/**
 * Description: SchemaBasedAOPTest
 *
 * @author yushen.ma
 * @version 2015-03-24 23:13
 */

import com.qunar.campus.spring.tutorial.transaction.aop.schema.AnotherWaiter;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:case1-schema.xml")
@Synopsis(name="schema based aop", difficulty = Difficulty.NORMAL)
public class SchemaBasedAOPTest {
    @Resource
    AnotherWaiter anotherWaiter;
    @Test
    public void test () {
        anotherWaiter.greeting();
    }

}
