package business.management.aspect;

import business.common.entity.exceptionentity.ExceptionEntity;
import business.management.helper.ExceptionDbHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;

@Aspect
@Component
public class ExceptionAspect {

    @Autowired
    private ExceptionDbHelper exceptionDbHelper;
    //@Pointcut("execution(public * business..controller..*(..))")
//    @Pointcut("execution(public * business..*(..)) && !execution(public * business.management..*(.. ))" +
//            "&& !execution(* business.common..*(.. )) && !execution(* business..controller..*(.. ))")
    @Pointcut("execution(public * business..service..*(..))")
    //@Pointcut("execution(public * business..*(..))")
    public void publicMethod(){}

    @AfterThrowing(pointcut = "publicMethod()",throwing="throwable")
    private void doPublicMethod(JoinPoint joinPoint,Throwable throwable){

        String exception = "null";
        if (throwable.getLocalizedMessage() != null){
            exception = throwable.getLocalizedMessage().substring(0,throwable.getLocalizedMessage().length() > 100 ? 100 : throwable.getLocalizedMessage().length());
        }

        /*
        * public ExceptionEntity(String className, String methodName, String args, Date date, String exception)
        * */
        ExceptionEntity exceptionEntity = new ExceptionEntity(joinPoint.getSignature().getDeclaringTypeName()
        ,joinPoint.getSignature().getName()
        , Arrays.toString(joinPoint.getArgs()).substring(0,Arrays.toString(joinPoint.getArgs()).length() > 200 ? 200 : Arrays.toString(joinPoint.getArgs()).length())
        ,new Date(System.currentTimeMillis())
        ,exception);

        exceptionDbHelper.add(exceptionEntity);
    }
}
