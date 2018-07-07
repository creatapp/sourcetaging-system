package business.log.service;

import business.common.entity.logentity.LogEntity;
import business.log.vo.LogVO;
import org.springframework.stereotype.Component;

@Component
public interface LogService {
    public boolean addLog(LogEntity logEntity);

    public LogVO[] findByOperatorId(Long operatorId);

    public LogVO[] getAll();
}
