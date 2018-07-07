package business.common.exception;

import business.common.returnmodel.ReturnMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class BusinessExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = LogicException.class)
    public ReturnMessage exceptionHandler(LogicException le){
        ReturnMessage returnMessage = new ReturnMessage(false,le.getMessage());

        return returnMessage;
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ReturnMessage allExceptionHandler( Exception e){
        ReturnMessage returnMessage = new ReturnMessage(false,"未捕捉错误："+e.getMessage());

        return returnMessage;
    }
}
