package business.analysis.service.utils;

import business.analysis.helper.Analysis_MissionDbHelper;
import business.analysis.helper.Analysis_TagDbHelper;
import business.analysis.helper.Analysis_WorkerDbHelper;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.tagentity.Check;
import business.common.entity.tagentity.TagEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.entity.workerentity.WorkerInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class AnalysisBalance {

    @Autowired
    private Analysis_WorkerDbHelper workerDbHelper;

    @Autowired
    private Analysis_MissionDbHelper missionDbHelper;

    @Autowired
    private Analysis_TagDbHelper tagDbHelper;

    /*
    * 结算模块：
    * 1、需要修改工人能力指数
    * 2、修改任务完成情况
    * 3、修改工人薪酬
    * */
    public void balance(boolean result, MissionEntity missionEntity, TagEntity tagEntity, WorkerEntity[] workerEntities){

        /*mission模块在doneMission已经完成的情况下请求这个服务
         *
         *
         * 一、如果判断其标注正确：
         *   1、为标注者和检查者分配奖励
         *   2、根据标注还是检查更改各个工人的标注指标WorkerInfoDomain
         *   3、修改标注工人的DoneMissionDomain指标
         *   4、修改MissionInfoDomain
         *   5、从map中移除
         *
         * 二、如果错误
         *   1、为判断错误的检查者分配奖励
         *   2、修改每个工人的标注指标WorkerInfoDomain
         *   3、修改标注工人的DoneMissionDomain
         *   4、从map中移除，并且修改picId:tagId中的tagId为新的Id，其picId加入待标注队列。
         *   5、该标注作废，仅参与工人在DoneMissionDomain持有它
         *   6、修改tagDomain中的available
         *
         * */

        Integer[] missionLabelArray = missionEntity.getLabel().toArray(new Integer[3]);

        /*整合结果正确时*/
        if (result){

            //更新tagEntity
            tagEntity.setAvailable(true);
            tagEntity.setHasFinished(true);
            tagDbHelper.update(tagEntity);

            WorkerEntity workerEntity;
            if(missionEntity.getType() == null){return; }

            Long[] participants = missionEntity.getParticipants();
            if(participants == null){participants = new Long[missionEntity.getPictureTotalNum() * 6];}

            /*开始分配奖励*/

            //标注者
            workerEntity = workerEntities[0];
            //if(workerEntity.getRank() == null){return; }

            workerEntity.setRightAnswerNum(workerEntity.getRightAnswerNum() + 1);
            double accuracy = new BigDecimal((double)workerEntity.getRightAnswerNum() / (workerEntity.getRightAnswerNum()
                    +workerEntity.getWrongAnswerNum()) )
                    .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            workerEntity.setAnswerAccuracy(accuracy);
            workerEntity.setToBeJudgeAnswerNum(workerEntity.getToBeJudgeAnswerNum() - 1);
            workerEntity.setPoints(workerEntity.getPoints() + missionEntity.getTagPrice());

            //WorkerInfoEntity
            Map<Integer,WorkerInfoEntity> workerEntityMap = workerEntity.getWorkerInfo();
            for (int i = 0; i < missionLabelArray.length; i++){
                if (missionLabelArray[i] == null)continue;
                WorkerInfoEntity workerInfoEntity = workerEntityMap.get(missionLabelArray[i]);
                workerInfoEntity.setTotalTagNum(workerInfoEntity.getTotalTagNum() + 1);
                workerInfoEntity.setRightTagNum(workerInfoEntity.getRightTagNum() + 1);
                workerInfoEntity.setTagAccuracy(new BigDecimal((double)workerInfoEntity.getRightTagNum() / workerInfoEntity.getTotalTagNum())
                        .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                //根据正确率增加能力值  10 * 正确率  取整
                workerInfoEntity.setTraitRefer(workerInfoEntity.getTraitRefer() + (int)(10 * workerInfoEntity.getTagAccuracy()));
                //workerEntityMap.replace(missionLabelArray[i],workerInfoEntity);
            }
            //workerEntity.setWorkerInfo(workerEntityMap);

            //寻找DoneMissionDomain
            DoneMissionEntity[] doneMissionEntities1 = workerEntity.getDoneMissionEntities();
            int doneMissionSize = workerEntity.getDoneMissionNum();

            for(int i = 0; i < doneMissionSize; i++){
                DoneMissionEntity doneMissionEntity = doneMissionEntities1[i];
                if(doneMissionEntity.getMissionId() == missionEntity.getMissionId()){

                    doneMissionEntity.setToBeJudgeNum(doneMissionEntity.getToBeJudgeNum() - 1);
                    doneMissionEntity.setRightNum(doneMissionEntity.getRightNum() + 1);
                    doneMissionEntity.setPoint(doneMissionEntity.getPoint() + missionEntity.getTagPrice());
                    break;
                }
            }
            workerEntity.setDoneMissionEntities(doneMissionEntities1);
            workerDbHelper.update(workerEntity);
            //participants[participants.length - 1 >= 0 ? participants.length - 1 : 0] = workerEntity.getWorkerId();

            //检查者
            Check[] checks = tagEntity.getChecks();
            int checkSize = checks.length;
            Long checkId;

            for(int i = 0; i < checkSize; i++){
                //checkId = checks[i].getWorkerId();
                //if(checks[i].getWorkerId() < 0){continue;}
                workerEntity = workerEntities[i+1];
                //participants[participants.length - 1 >= 0 ? participants.length - 1 : 0] = workerEntity.getWorkerId();

                //开始对检查者的信息进行更改
                if(checks[i].isJudge()){

                    workerEntity.setPoints(workerEntity.getPoints() + missionEntity.getCheckPrice());
                    workerEntity.setRightCheckNum(workerEntity.getRightCheckNum() + 1);
                    workerEntity.setToBeJudgeCheckNum(workerEntity.getToBeJudgeCheckNum() - 1);
                    double checkAccuracy = new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum()
                            +workerEntity.getWrongAnswerNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    workerEntity.setCheckAccuracy(checkAccuracy);

                    //workerInfoEntity
                    Map<Integer,WorkerInfoEntity> checkEntityMap = workerEntity.getWorkerInfo();
                    for (int j = 0; j < missionLabelArray.length; j++){
                        if (missionLabelArray[j] == null)continue;
                        WorkerInfoEntity workerInfoEntity = checkEntityMap.get(missionLabelArray[j]);
                        workerInfoEntity.setTotalCheckNum(workerInfoEntity.getTotalCheckNum() + 1);
                        workerInfoEntity.setRightCheckNum(workerInfoEntity.getRightCheckNum() + 1);
                        workerInfoEntity.setCheckAccuracy(new BigDecimal((double)workerInfoEntity.getRightCheckNum() / workerInfoEntity.getTotalCheckNum())
                                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                        //根据正确率增加能力值  10 * 正确率  取整
                        workerInfoEntity.setTraitRefer(workerInfoEntity.getTraitRefer() + (int)(10 * workerInfoEntity.getCheckAccuracy()));
                        //checkEntityMap.replace(missionLabelArray[i],workerInfoEntity);
                    }
                    //workerEntity.setWorkerInfo(checkEntityMap);

                    //doneMissionEntity
                    DoneMissionEntity[] doneMissionEntities2 = workerEntity.getDoneMissionEntities();
                    int doneMissionSize2 = workerEntity.getDoneMissionNum();
                    for(int j = 0; j < doneMissionSize2; j++){
                        DoneMissionEntity doneMissionEntity = doneMissionEntities2[j];

                        if(doneMissionEntity.getMissionId() == missionEntity.getMissionId()){

                            doneMissionEntity.setToBeJudgeNum(doneMissionEntity.getToBeJudgeNum() - 1);
                            doneMissionEntity.setRightNum(doneMissionEntity.getRightNum() + 1);
                            doneMissionEntity.setPoint(doneMissionEntity.getPoint() + missionEntity.getCheckPrice());

                            break;
                        }
                    }
                    workerEntity.setDoneMissionEntities(doneMissionEntities2);
                    workerEntity.setCheckAccuracy(new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum() + workerEntity.getWrongCheckNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

                }else{  //判错

                    workerEntity.setWrongCheckNum(workerEntity.getWrongCheckNum() + 1);
                    workerEntity.setToBeJudgeCheckNum(workerEntity.getToBeJudgeCheckNum() - 1);
                    double checkAccuracy = new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum()
                            +workerEntity.getWrongCheckNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    workerEntity.setCheckAccuracy(checkAccuracy);

                    //能力
                    Map<Integer,WorkerInfoEntity> checkEntityMap = workerEntity.getWorkerInfo();
                    for (int j = 0; j < missionLabelArray.length; j++){
                        if (missionLabelArray[j] == null)continue;
                        WorkerInfoEntity workerInfoEntity = checkEntityMap.get(missionLabelArray[j]);
                        workerInfoEntity.setTotalCheckNum(workerInfoEntity.getTotalCheckNum() + 1);
                        workerInfoEntity.setCheckAccuracy(new BigDecimal((double)workerInfoEntity.getRightCheckNum() / workerInfoEntity.getTotalCheckNum())
                                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                        //根据错误率减少能力值  10 *  (1 - 正确率)  取整
                        workerInfoEntity.setTraitRefer(workerInfoEntity.getTraitRefer() - (int)(10 - 10 * workerInfoEntity.getCheckAccuracy()));
                        //checkEntityMap.replace(missionLabelArray[i],workerInfoEntity);
                    }
                    //workerEntity.setWorkerInfo(checkEntityMap);

                    DoneMissionEntity[] doneMissionEntities3 = workerEntity.getDoneMissionEntities();
                    int doneMissionSize2 = workerEntity.getDoneMissionNum();
                    for(int j = 0; j < doneMissionSize2; j++){
                        DoneMissionEntity doneMissionEntity = doneMissionEntities3[j];

                        if(doneMissionEntity.getMissionId() == missionEntity.getMissionId()){

                            doneMissionEntity.setToBeJudgeNum(doneMissionEntity.getToBeJudgeNum() - 1);
                            doneMissionEntity.setWrongNum(doneMissionEntity.getWrongNum() + 1);

                            break;
                        }
                    }
                    workerEntity.setDoneMissionEntities(doneMissionEntities3);
                    workerEntity.setCheckAccuracy(new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum() + workerEntity.getWrongCheckNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

                }

                workerDbHelper.update(workerEntity);
            }

            //修改missionInfo
            missionEntity.setPictureFinishedNum(missionEntity.getPictureFinishedNum() + 1);
            missionEntity.setPictureLeftNum(missionEntity.getPictureLeftNum() - 1);
            missionEntity.getAssignedCheckMap().remove(tagEntity.getPictureId());

            missionEntity.setParticipants(participants);
            missionDbHelper.udpate(missionEntity);

        /*整合结果不正确时*/
        }else{

            //更新tagDomain配置
            tagEntity.setAvailable(false);
            tagEntity.setHasFinished(false);
            tagDbHelper.update(tagEntity);

            WorkerEntity workerEntity;
            if(missionEntity.getType() == null){return; }

            Long workerId;
            Long[] participants = missionEntity.getParticipants();
            if(participants == null){participants = new Long[missionEntity.getPictureTotalNum() * 6];}

            /*开始分配奖励*/

            //标注者
            workerEntity = workerEntities[0];
            if(workerEntity.getRank() == null){return;}

            workerEntity.setWrongAnswerNum(workerEntity.getWrongAnswerNum() + 1);
            double accuracy = new BigDecimal((double)workerEntity.getRightAnswerNum() / (workerEntity.getWrongAnswerNum()
                    + workerEntity.getRightAnswerNum()))
                    .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            workerEntity.setAnswerAccuracy(accuracy);
            workerEntity.setToBeJudgeAnswerNum(workerEntity.getToBeJudgeAnswerNum() - 1);

            //WorkerInfoEntity
            Map<Integer,WorkerInfoEntity> workerEntityMap = workerEntity.getWorkerInfo();
            for (int i = 0; i < missionLabelArray.length; i++){
                if (missionLabelArray[i] == null)continue;
                WorkerInfoEntity workerInfoEntity = workerEntityMap.get(missionLabelArray[i]);
                workerInfoEntity.setTotalTagNum(workerInfoEntity.getTotalTagNum() + 1);
                workerInfoEntity.setTagAccuracy(new BigDecimal((double)workerInfoEntity.getRightTagNum() / workerInfoEntity.getTotalTagNum())
                        .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                //根据错误率减少能力值  10 *  （1 - 正确率）  取整
                workerInfoEntity.setTraitRefer(workerInfoEntity.getTraitRefer() -  (int)(10 - 10 * workerInfoEntity.getTagAccuracy()));
                //workerEntityMap.replace(missionLabelArray[i],workerInfoEntity);
            }
            //workerEntity.setWorkerInfo(workerEntityMap);

            //寻找DoneMissionDomain
            DoneMissionEntity[] doneMissionEntities1 = workerEntity.getDoneMissionEntities();
            int doneMissionSize = workerEntity.getDoneMissionNum();

            for(int i = 0; i < doneMissionSize; i++){
                DoneMissionEntity doneMissionEntity = doneMissionEntities1[i];
                if(doneMissionEntity.getMissionId() == missionEntity.getMissionId()){

                    doneMissionEntity.setToBeJudgeNum(doneMissionEntity.getToBeJudgeNum() - 1);
                    doneMissionEntity.setWrongNum(doneMissionEntity.getWrongNum() + 1);
                    break;
                }
            }
            workerEntity.setDoneMissionEntities(doneMissionEntities1);
            workerDbHelper.update(workerEntity);
            //participants[participants.length - 1 >= 0 ? participants.length - 1 : 0] = workerEntity.getWorkerId();

            //检查者
            Check[] checks = tagEntity.getChecks();
            int checkSize = checks.length;

            for(int i = 0; i < checkSize; i++){
                workerId = checks[i].getWorkerId();
                workerEntity = workerEntities[i+1];
                //if(workerEntity.getRank() == null){return;}
                //participants[participants.length - 1 >= 0 ? participants.length - 1 : 0] = workerEntity.getWorkerId();

                //开始对检查者的信息进行更改
                if(checks[i].isJudge()){

                    workerEntity.setWrongCheckNum(workerEntity.getWrongCheckNum() + 1);
                    workerEntity.setToBeJudgeCheckNum(workerEntity.getToBeJudgeCheckNum() - 1);
                    double checkAccuracy = new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum()
                            +workerEntity.getWrongAnswerNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    workerEntity.setCheckAccuracy(checkAccuracy);

                    //WorkerInfoEntity
                    Map<Integer,WorkerInfoEntity> checkEntityMap = workerEntity.getWorkerInfo();
                    for (int j = 0; j < missionLabelArray.length; j++){
                        if (missionLabelArray[j] == null)continue;
                        WorkerInfoEntity workerInfoEntity = checkEntityMap.get(missionLabelArray[j]);
                        workerInfoEntity.setTotalCheckNum(workerInfoEntity.getTotalCheckNum() + 1);
                        workerInfoEntity.setCheckAccuracy(new BigDecimal((double)workerInfoEntity.getRightCheckNum() / workerInfoEntity.getTotalCheckNum())
                                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                        //根据错误率减少能力值  10 *  （1 - 正确率）  取整
                        workerInfoEntity.setTraitRefer(workerInfoEntity.getTraitRefer() -  (int)(10 - 10 * workerInfoEntity.getCheckAccuracy()));
                        //workerEntityMap.replace(missionLabelArray[i],workerInfoEntity);
                    }
                    //workerEntity.setWorkerInfo(workerEntityMap);

                    DoneMissionEntity[] doneMissionEntities2 = workerEntity.getDoneMissionEntities();
                    int doneMissionSize2 = workerEntity.getDoneMissionNum();
                    for(int j = 0; j < doneMissionSize2; j++){
                        DoneMissionEntity doneMissionEntity = doneMissionEntities2[j];

                        if(doneMissionEntity.getMissionId() == missionEntity.getMissionId()){

                            doneMissionEntity.setToBeJudgeNum(doneMissionEntity.getToBeJudgeNum() - 1);
                            doneMissionEntity.setWrongNum(doneMissionEntity.getWrongNum() + 1);

                            break;
                        }
                    }
                    workerEntity.setDoneMissionEntities(doneMissionEntities2);
                    workerEntity.setCheckAccuracy(new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum() + workerEntity.getWrongCheckNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

                }else{ //工人判错

                    workerEntity.setPoints(workerEntity.getPoints() + missionEntity.getCheckPrice());
                    workerEntity.setRightCheckNum(workerEntity.getRightCheckNum() + 1);
                    workerEntity.setToBeJudgeCheckNum(workerEntity.getToBeJudgeCheckNum() - 1);
                    double checkAccuracy = new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum()
                            +workerEntity.getWrongCheckNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    workerEntity.setCheckAccuracy(checkAccuracy);

                    //WorkerInfoEntity
                    Map<Integer,WorkerInfoEntity> checkEntityMap = workerEntity.getWorkerInfo();
                    for (int j = 0; j < missionLabelArray.length; j++){
                        if (missionLabelArray[j] == null)continue;
                        WorkerInfoEntity workerInfoEntity = checkEntityMap.get(missionLabelArray[j]);
                        workerInfoEntity.setTotalCheckNum(workerInfoEntity.getTotalCheckNum() + 1);
                        workerInfoEntity.setRightCheckNum(workerInfoEntity.getRightCheckNum() + 1);
                        workerInfoEntity.setCheckAccuracy(new BigDecimal((double)workerInfoEntity.getRightCheckNum() / workerInfoEntity.getTotalCheckNum())
                                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                        //根据正确率增加能力值  10 *  （1 + 正确率）  取整
                        workerInfoEntity.setTraitRefer(workerInfoEntity.getTraitRefer() + (int)(10 * workerInfoEntity.getCheckAccuracy()));
                        //workerEntityMap.replace(missionLabelArray[i],workerInfoEntity);
                    }
                    //workerEntity.setWorkerInfo(workerEntityMap);

                    DoneMissionEntity[] doneMissionEntities3 = workerEntity.getDoneMissionEntities();
                    int doneMissionSize2 = workerEntity.getDoneMissionNum();
                    for(int j = 0; j < doneMissionSize2; j++){
                        DoneMissionEntity doneMissionEntity = doneMissionEntities3[j];

                        if(doneMissionEntity.getMissionId() == missionEntity.getMissionId()){

                            doneMissionEntity.setToBeJudgeNum(doneMissionEntity.getToBeJudgeNum() - 1);
                            doneMissionEntity.setRightNum(doneMissionEntity.getRightNum() + 1);
                            doneMissionEntity.setPoint(doneMissionEntity.getPoint() + missionEntity.getCheckPrice());

                            break;
                        }
                    }
                    workerEntity.setDoneMissionEntities(doneMissionEntities3);
                    workerEntity.setCheckAccuracy(new BigDecimal((double)workerEntity.getRightCheckNum() / (workerEntity.getRightCheckNum() + workerEntity.getWrongCheckNum()))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                }

                workerDbHelper.update(workerEntity);
            }

            //修改missionInfo
            //原来的tagDomain无效，新生成一个可以使用的tagId，难题是如何保证tagId的唯一性
            try{
                tagDbHelper.delete(tagEntity.getTagId());
            }catch (Exception e){
                //
            }
            //生成新的tagEntity
            TagEntity tagEntity1 = new TagEntity();
            long newTagId = tagDbHelper.add(tagEntity1).getTagId();

            missionEntity.getPlanToTagQueue().offer(tagEntity.getPictureId());//加入待标注队列
            missionEntity.getPic2TagMap().put(tagEntity.getPictureId(),newTagId);//使用新的标注信息
            missionEntity.getAssignedCheckMap().put(tagEntity.getPictureId(),0);//不移除

            missionEntity.setParticipants(participants);
            missionDbHelper.udpate(missionEntity);
        }
    }
}
