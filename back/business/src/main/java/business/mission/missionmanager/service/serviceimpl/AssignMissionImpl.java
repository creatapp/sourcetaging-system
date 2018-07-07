package business.mission.missionmanager.service.serviceimpl;

import business.common.entity.missionentity.MissionEntity;
import business.common.entity.tagentity.Check;
import business.common.entity.tagentity.Tag;
import business.common.entity.tagentity.TagEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.helper.Mission_TagDbHelper;
import business.mission.helper.Mission_WorkerDbHelper;
import business.mission.missionmanager.service.AssignMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Transactional
@Service
public class AssignMissionImpl implements AssignMissionService {

    @Autowired
    private Mission_MissionDbHelper missionMissionDbHelper;

    @Autowired
    private Mission_WorkerDbHelper missionWorkerDbHelper;

    @Autowired
    private Mission_TagDbHelper missionTagDbHelper;

    @Override
    public boolean assignMission(Long workerId, String kind, Long missionId,String type) throws Exception {
        MissionEntity missionEntity = missionMissionDbHelper.findById(missionId);
        WorkerEntity workerEntity= missionWorkerDbHelper.findById(workerId);

        //assign the pictures
        Queue<Long> queue;
        Long[] doingTag=new Long[10];
        int judge=0;
        DoingMissionEntity doingMissionEntity =new DoingMissionEntity(missionId, kind, 0, 0,
                new Date(), new Date(), doingTag, 0);
        Map<Long,Long> pic2TagMap= missionEntity.getPic2TagMap();
        if(kind.equals("check")){
            queue= missionEntity.getPlanToCheckQueue();
            int lengt=queue.size();
            Queue<Long> lineQueue=new LinkedList<>();
            for(int c=0;c<lengt;c++){
                Long temp=queue.element();
                lineQueue.add(temp);
                queue.poll();
                queue.add(temp);
            }
            while(!lineQueue.isEmpty()&&judge<=10&&judge<lengt){

                doingMissionEntity.setTotalNum(doingMissionEntity.getTotalNum()+1);
                //update tagdomain
                Long tagId=pic2TagMap.get(lineQueue.element());
                TagEntity tagEntity = missionTagDbHelper.findById(tagId);
                Check[] checks= tagEntity.getChecks();
                for(int i=0;i<checks.length;i++){
                    if(checks[i].getWorkerId().equals(new Long(-1))){
                        checks[i].setWorkerId(workerId);
                        break;
                    }
                }
                tagEntity.setChecks(checks);
                tagEntity.setCheckLeftTimes(tagEntity.getCheckLeftTimes()+1);
                missionTagDbHelper.update(tagEntity);
                doingTag[judge]=tagId;
                //judge whether moving from queue
                if(tagEntity.getCheckLeftTimes()==5){
                    queue.poll();
                }
                lineQueue.poll();
                judge++;
            }
            missionEntity.setPlanToCheckQueue(queue);

        }
        else{
            queue= missionEntity.getPlanToTagQueue();
            while(!queue.isEmpty()&&judge<=10){
                //生成tagdomain
                Long tagId=pic2TagMap.get(queue.element());

                Tag tag1=new Tag(workerId,"","",0);

                Check[] checks=new Check[5];
                for(int m=0;m<5;m++){
                    checks[m]=new Check(new Long(-1),false,0);
                }

                TagEntity tagEntity =new TagEntity(missionId,queue.element(),workerId,tag1,checks,-1L,
                        0,false,true);
                tagEntity.setTagId(tagId);

                missionTagDbHelper.update(tagEntity);
                doingTag[judge]=pic2TagMap.get(queue.poll());
                judge++;
            }
            missionEntity.setPlanToTagQueue(queue);
        }

        if(judge==0){

        }

        //生成新doingmissiondomain
        doingMissionEntity.setDoingTagId(doingTag);
        doingMissionEntity.setTotalNum(judge);

        //修改workerInfoDoMain并更新
        /*
        include: DoingMissionsNum, DoingMissionDomains
         */
        workerEntity.setDoingMissionsNum(workerEntity.getDoingMissionsNum()+1);
        DoingMissionEntity[] doingMissionEntities =workerEntity.getDoingMissionEntities();
        doingMissionEntities[workerEntity.getDoingMissionsNum()-1]= doingMissionEntity;
        workerEntity.setDoingMissionEntities(doingMissionEntities);
        missionWorkerDbHelper.update(workerEntity);

        //更新missionInfoDoMain
        /*
        include: Participants, queue
         */
        Long[] partners=new Long[missionEntity.getParticipants().length+1];
        for(int i=0;i<partners.length-1;i++){
            partners[i]= missionEntity.getParticipants()[i];
        }
        partners[missionEntity.getParticipants().length]=workerId;
        missionEntity.setParticipants(partners);
        missionMissionDbHelper.update(missionEntity);

        //use missionsender
       // RecycleDTO recycleDTO=new RecycleDTO(workerId, missionId, missionEntity.getType(), new Date());
        try{
            //analysisHelper.recycle(workerId,missionId,missionEntity.getType());
        }catch(Exception e){

        }

        return true;
    }
}
