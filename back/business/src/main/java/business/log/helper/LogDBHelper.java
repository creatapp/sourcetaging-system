package business.log.helper;

import business.common.entity.logentity.LogEntity;
import org.springframework.stereotype.Component;

@Component
public interface LogDBHelper {
    public boolean addLog(LogEntity logEntity);

    public LogEntity[] findByOperatorId(Long operatorId);

    public LogEntity[] getAll();
}
