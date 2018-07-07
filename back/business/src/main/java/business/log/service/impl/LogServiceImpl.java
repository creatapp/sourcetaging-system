package business.log.service.impl;

import business.common.entity.logentity.LogEntity;
import business.log.helper.LogDBHelper;
import business.log.service.LogService;
import business.log.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDBHelper logDBHelper;

    public boolean addLog(LogEntity logEntity) {
        if(logDBHelper.addLog(logEntity))
            return true;
        return false;
    }

    @Override
    public LogVO[] findByOperatorId(Long operatorId) {
        LogEntity[] logEntities=logDBHelper.findByOperatorId(operatorId);
        LogVO[] logVOS=new LogVO[logEntities.length];
        for (int i=0;i<logEntities.length;i++){
            logVOS[i]=new LogVO(logEntities[i].getOperatorId(),logEntities[i].getRole(),
                    logEntities[i].getMissionId(),logEntities[i].getOperation(),logEntities[i].getTime());
        }
        return logVOS;
    }

    @Override
    public LogVO[] getAll() {
        LogEntity[] logEntities=logDBHelper.getAll();
        LogVO[] logVOS=new LogVO[logEntities.length];
        for (int i=0;i<logEntities.length;i++){
            logVOS[i]=new LogVO(logEntities[i].getOperatorId(),logEntities[i].getRole(),
                    logEntities[i].getMissionId(),logEntities[i].getOperation(),logEntities[i].getTime());
        }
        return logVOS;
    }
}
