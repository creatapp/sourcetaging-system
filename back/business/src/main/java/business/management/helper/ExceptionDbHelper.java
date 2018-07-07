package business.management.helper;

import business.common.entity.exceptionentity.ExceptionEntity;
import org.springframework.stereotype.Component;

@Component
public interface ExceptionDbHelper {

    Long add(ExceptionEntity exceptionEntity);
}
