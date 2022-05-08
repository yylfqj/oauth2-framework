package oauth.cache.starter.utils;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @ClassName SpelUtil
 * @Author yyl
 * @Date 2021-06-02 13:51
 * @Description SpelUtil spring el表达式的解析工具类
 * @Version 1.0
 */
public class SpelUtil {

    /**
     * 用于SpEL表达式解析.
     */
    private static ExpressionParser parser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private static ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();


    /**
     * 通过spel表达式获取注解方法中的入参数据
     * @param spELString
     * @param args
     * @param method
     * @return
     */
    public static Object getValueBySpEL(String spELString, Object[] args, Method method) {
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(method);
        // 解析过后的Spring表达式对象
        Expression expression = parser.parseExpression(spELString);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 给上下文赋值
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context);
    }
}
