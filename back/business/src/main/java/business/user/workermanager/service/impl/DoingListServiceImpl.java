package business.user.workermanager.service.impl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.returnmodel.VO;
import business.user.helper.User_MissionDbHelper;
import business.user.helper.User_WorkerDbHelper;
import business.user.workermanager.service.DoingListService;
import business.user.workermanager.vo.doingmission.DoingMissionVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class DoingListServiceImpl implements DoingListService {

    @Autowired
    private User_MissionDbHelper userMissionDbHelper;

    @Autowired
    private User_WorkerDbHelper userWorkerDbHelper;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public VO[] listDoingList(Long workerId) {

        //先拿到workerinfo里的doingmission
        WorkerEntity workerEntity = userWorkerDbHelper.findById(workerId);
        DoingMissionEntity[] doingMissionEntities = workerEntity.getDoingMissionEntities();

        int size = workerEntity.getDoingMissionsNum();
//        if(size <= 0){
//            throw new LogicException("没有正在做的任务，请先加入一个");
//        }

        //根据提供的missionid，找到mission的一般描述
        Long id = new Long(-1);
        MissionEntity missionEntity;

        DoingMissionVO[] doingMissionVOS = new DoingMissionVO[size];


        for(int i = 0; i < size; i++){
            id = doingMissionEntities[i].getMissionId();
            missionEntity = userMissionDbHelper.findById(id);
            Integer[] classLabel=missionEntity.getLabel().toArray(new Integer[missionEntity.getLabel().size()]);
            DoingMissionVO doingMissionVO = new DoingMissionVO(id,missionEntity.getTitle()
            ,missionEntity.getDescription(),missionEntity.getType()
            ,doingMissionEntities[i].getMissionKind(),missionEntity.getTagPrice(),doingMissionEntities[i].getDoneNum()
            ,doingMissionEntities[i].getTotalNum(),classLabel);

            doingMissionVOS[i] = doingMissionVO;
        }

        return doingMissionVOS;
    }
}
