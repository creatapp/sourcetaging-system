package business.log.helper.impl;

import business.common.entity.logentity.LogEntity;
import business.common.repository.LogRepository;
import business.log.helper.LogDBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

@Service
public class LogDBHelperImpl implements LogDBHelper {

    @Autowired
    private LogRepository logRepository;

    public boolean addLog(LogEntity logEntity) {
        logRepository.save(logEntity);
        return true;
    }

    @Override
    public LogEntity[] findByOperatorId(Long operatorId) {
        return logRepository.findByOperatorId(operatorId);
    }

    @Override
    public LogEntity[] getAll() {
        return logRepository.getAll();
    }
}
