package business.user.usermanager.service.impl;

import business.common.entity.requesterentity.RequesterEntity;
import business.common.entity.userentity.UserEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.exception.LogicException;
import business.common.returnmodel.VO;
import business.user.helper.User_RequesterDbHelper;
import business.user.helper.User_UserDbHelper;
import business.user.helper.User_WorkerDbHelper;
import business.user.usermanager.service.WorkerInfoService;
import business.user.usermanager.vo.workermanagervo.UserInfoVO;
import business.user.usermanager.vo.workermanagervo.WorkerDataVO;
import business.user.usermanager.vo.workermanagervo.WorkerInfoVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class WorkerInfoServiceImpl implements WorkerInfoService {

    @Autowired
    private User_UserDbHelper userUserDbHelper;

    @Autowired
    private User_WorkerDbHelper userWorkerDbHelper;

    @Autowired
    private User_RequesterDbHelper user_requesterDbHelper;
    ObjectMapper mapper = new ObjectMapper();

    public VO getWorkerInfo(Long id) {

        WorkerEntity workerEntity = userWorkerDbHelper.findById(id);

        UserEntity userEntity = userUserDbHelper.findById(id);

        WorkerInfoVO workerInfoVO = new WorkerInfoVO(id,userEntity.getNickName()
        ,userEntity.getEmail(),workerEntity.getPoints(),workerEntity.getRank()
        ,workerEntity.getDoingMissionsNum(),workerEntity.getDoneMissionNum()
        ,workerEntity.getRightAnswerNum() + workerEntity.getRightCheckNum(),workerEntity.getWrongAnswerNum()
                +workerEntity.getWrongCheckNum()
        ,workerEntity.getToBeJudgeAnswerNum(),workerEntity.getRightAnswerNum() + workerEntity.getWrongAnswerNum()
                +workerEntity.getRightCheckNum() + workerEntity.getWrongCheckNum()
        ,workerEntity.getAnswerAccuracy());


        return workerInfoVO;
    }

    @Override
    public VO getUserInfo(Long userId) {
        UserInfoVO userInfoVO=new UserInfoVO();
        if(userWorkerDbHelper.exists(userId)){
            WorkerEntity workerEntity = userWorkerDbHelper.findById(userId);
            UserEntity userEntity = userUserDbHelper.findById(userId);
            userInfoVO=new UserInfoVO(userEntity.getId(),userEntity.getNickName(),userEntity.getEmail()
                    ,workerEntity.getPoints());
        }else if(user_requesterDbHelper.exists(userId)){
            RequesterEntity requesterEntity = user_requesterDbHelper.findById(userId);
            UserEntity userEntity = userUserDbHelper.findById(userId);
            userInfoVO=new UserInfoVO(userEntity.getId(),userEntity.getNickName(),userEntity.getEmail()
                    ,requesterEntity.getBalance());
        }

        return userInfoVO;
    }

    @Override
    public VO getWorkerData(Long workerId) {
        WorkerEntity workerEntity = userWorkerDbHelper.findById(workerId);
        WorkerDataVO workerDataVO=new WorkerDataVO(workerEntity);
        return workerDataVO;
    }

    @Override
    public VO[] getAllWorkers() {

        WorkerEntity[] workerEntities = userWorkerDbHelper.findAll();
        int size = workerEntities.length;

        if(size <= 0){
            throw new LogicException("当前没有工人");
        }

        WorkerInfoVO[] workerInfoVOS = new WorkerInfoVO[size];
        Long id;
        UserEntity userEntity;

        for(int i = 0; i < size; i++){
            id = workerEntities[i].getWorkerId();
            userEntity = userUserDbHelper.findById(id);

            WorkerInfoVO workerInfoVO = new WorkerInfoVO(id,userEntity.getNickName()
                    ,userEntity.getEmail(),workerEntities[i].getPoints(),workerEntities[i].getRank()
                    ,workerEntities[i].getDoingMissionsNum(),workerEntities[i].getDoneMissionNum()
                    ,workerEntities[i].getRightAnswerNum(),workerEntities[i].getWrongAnswerNum()
                    ,workerEntities[i].getToBeJudgeAnswerNum(),workerEntities[i].getTotalAnswerNum()
                    ,workerEntities[i].getAnswerAccuracy());

            workerInfoVOS[i] = workerInfoVO;
        }


        return workerInfoVOS;
    }
}
