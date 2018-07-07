package business.mission.picturesmanager.service.serviceimpl;

import business.analysis.service.AnalysisBalanceService;
import business.common.entity.logentity.LogEntity;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.tagentity.TagEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.log.service.LogService;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.helper.Mission_WorkerDbHelper;
import business.mission.picturesmanager.service.ChangeMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Queue;

@Service
public class ChangeMissionImpl implements ChangeMissionService {

    @Autowired
    private Mission_WorkerDbHelper missionWorkerDbHelper;


    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private Mission_TagDbHelper missionTagDbHelper;

    @Autowired
    private LogService logService;
    @Autowired
    private AnalysisBalanceService analysisBalanceService;

    @Override
    public boolean changeMission(Long missionId, Long workerId,String kind) {
        WorkerEntity workerEntity= missionWorkerDbHelper.findById(workerId);
        DoingMissionEntity[] doingMissionEntities=workerEntity.getDoingMissionEntities();

        DoneMissionEntity doneMissionEntity=null;
        DoingMissionEntity[] doingMissionEntities1=new DoingMissionEntity[4];
        Long[] tagIds=null;
        int count=0;
        int i=0;
        for(;i<doingMissionEntities.length;i++){
            if(doingMissionEntities[i].getMissionId() == (missionId)){
                tagIds=doingMissionEntities[i].getDoingTagId();
                count=doingMissionEntities[i].getDoneNum();
                doneMissionEntity=new DoneMissionEntity(missionId, doingMissionEntities[i].getMissionKind(), 0,
                        0, doingMissionEntities[i].getTotalNum(), doingMissionEntities[i].getTotalNum(), 0, new Date(),
                        doingMissionEntities[i].getDoingTagId());
                break;
            }

        }
        //update doingmissiondomain[]
        for(int j=0,k=0;j<4;j++){
            if(j!=i){
                doingMissionEntities1[k]=doingMissionEntities[j];
                k++;
            }
        }

        //update workerinfodomain
        DoneMissionEntity[] doneMissionEntities=workerEntity.getDoneMissionEntities();
        doneMissionEntities[workerEntity.getDoneMissionNum()]=doneMissionEntity;

        workerEntity.setDoneMissionEntities(doneMissionEntities);
        workerEntity.setDoneMissionNum(workerEntity.getDoneMissionNum()+1);
        workerEntity.setDoingMissionsNum(workerEntity.getDoingMissionsNum()-1);
        workerEntity.setDoingMissionEntities(doingMissionEntities1);
        if(kind.equals("tag")){
            workerEntity.setToBeJudgeAnswerNum(workerEntity.getToBeJudgeAnswerNum()+count);
            workerEntity.setTotalAnswerNum(workerEntity.getTotalAnswerNum()+count);
        }
        else{
            workerEntity.setToBeJudgeCheckNum(workerEntity.getToBeJudgeCheckNum()+count);
            workerEntity.setTotalCheckNum(workerEntity.getTotalCheckNum()+count);
        }
        missionWorkerDbHelper.update(workerEntity);


        //update missioninfodomain
        MissionEntity missionEntity= missionMissionDbHelper.findById(missionId);
        if(kind.equals("tag")){
            Queue<Long> planToCheckQueue=missionEntity.getPlanToCheckQueue();
            for(int j=0;j<count;j++){
                TagEntity tagEntity= missionTagDbHelper.findById(tagIds[j]);
                Long pictureId=tagEntity.getPictureId();
                planToCheckQueue.offer(pictureId);
            }
            missionEntity.setPlanToCheckQueue(planToCheckQueue);
            missionMissionDbHelper.update(missionEntity);
        }
        else{
            Map<Long,Integer> assignedCheckMap=missionEntity.getAssignedCheckMap();
            ArrayList<Long> balanceTag1=new ArrayList<>();
            for(int j=0;j<count;j++){
                TagEntity tagDomain= missionTagDbHelper.findById(tagIds[j]);
                Long pictureId=tagDomain.getPictureId();
                Integer index=assignedCheckMap.get(pictureId);
                assignedCheckMap.put(pictureId,index+1);
                if((index+1)==5){
                    balanceTag1.add(tagIds[j]);
                }
            }
            missionEntity.setAssignedCheckMap(assignedCheckMap);
            missionMissionDbHelper.update(missionEntity);

            //arraylist to array
            Long[] balanceTag2=new Long[balanceTag1.size()];
            for(int m=0;m<balanceTag1.size();m++){
                balanceTag2[m]=balanceTag1.get(m);
            }

            try{
                if(balanceTag1.size()!=0){
                    analysisBalanceService.planToBalance(balanceTag2);
                    LogEntity logEntity=new LogEntity(workerId,"worker",missionId,"结算");
                    logService.addLog(logEntity);
                }
            }catch(Exception e){

            }
        }
        return true;
    }
}