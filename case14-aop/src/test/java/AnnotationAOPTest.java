import com.qunar.campus.spring.tutorial.transaction.aop.annotation.Waiter;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Description: PointCutSynopsis
 *
 * @author yushen.ma
 * @version 2015-03-23 22:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:case0-annotation.xml")
@Synopsis(name="aop annotation test", difficulty = Difficulty.NORMAL)
public class AnnotationAOPTest {

    @Resource
    Waiter waiter;

    @Test
    public void testWaiterGreet() {
        waiter.greetTo();
    }

    @Test
    public void testWaiterServe() {
        waiter.serveTo();
    }

    @Test
    public void testParams() {
        waiter.iHaveSomeParams(1, "admin");
    }
}
