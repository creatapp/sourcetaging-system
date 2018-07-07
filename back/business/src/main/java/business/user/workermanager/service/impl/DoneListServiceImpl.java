package business.user.workermanager.service.impl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.returnmodel.VO;
import business.user.helper.User_MissionDbHelper;
import business.user.helper.User_WorkerDbHelper;
import business.user.workermanager.service.DoneListService;
import business.user.workermanager.vo.donemission.DoneMissionVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class DoneListServiceImpl implements DoneListService {

    @Autowired
    private User_MissionDbHelper userMissionDbHelper;

    @Autowired
    private User_WorkerDbHelper userWorkerDbHelper;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public VO[] listDoneList(Long workerId) {

        //先拿到workerinfo里的donemission
        WorkerEntity workerEntity = userWorkerDbHelper.findById(workerId);
        DoneMissionEntity[] doneMissionEntities = workerEntity.getDoneMissionEntities();

        int size = workerEntity.getDoneMissionNum();
//        if(size <= 0){
//            throw new LogicException("没有已经完成的任务，请先加入一个");
//        }

        //根据提供的missionid，找到mission的一般描述
        Long id = new Long(-1);
        MissionEntity missionEntity;

        DoneMissionVO[] doneMissionVOS = new DoneMissionVO[size];


        for(int i = 0; i < size; i++){
            id = doneMissionEntities[i].getMissionId();
            missionEntity = userMissionDbHelper.findById(id);
            Integer[] classLabel=missionEntity.getLabel().toArray(new Integer[missionEntity.getLabel().size()]);

            DoneMissionVO doneMissionVO = new DoneMissionVO(id,missionEntity.getTitle()
            ,missionEntity.getDescription(),missionEntity.getType()
            ,doneMissionEntities[i].getMissionKind(),doneMissionEntities[i].getRightNum()
            ,doneMissionEntities[i].getWrongNum(),doneMissionEntities[i].getToBeJudgeNum()
            ,doneMissionEntities[i].getPoint(),classLabel);

            doneMissionVOS[i] = doneMissionVO;
        }

        return doneMissionVOS;
    }
}
