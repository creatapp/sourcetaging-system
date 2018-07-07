package business.login.service.serviceimpl;


import business.common.entity.requesterentity.RequesterEntity;
import business.common.entity.workerentity.DoingMissionEntity;
import business.common.entity.workerentity.DoneMissionEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.entity.workerentity.WorkerInfoEntity;
import business.login.auth.service.AesEncryptService;
import business.login.helper.Login_RequesterDbHelper;
import business.login.helper.Login_UserDbHelper;
import business.login.helper.Login_WorkerDbHelper;
import business.login.po.usercommoninfo.UserCommonInfoPO;
import business.login.service.SignUpService;
import business.mail.service.SendSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private Login_UserDbHelper loginUserDbHelper;

    @Autowired
    private Login_WorkerDbHelper loginWorkerDbHelper;

    @Autowired
    private Login_RequesterDbHelper loginRequesterDbHelper;

    @Autowired
    private SendSignUpService sendSignUpService;

    @Autowired
    private AesEncryptService aesEncryptService;

    private String[] label={"1","2","3","4","5"};

    @Override
    public String signUp(String type,
                          String password,
                          String nickName,
                          String email) {

        //TODO 需要先检测邮箱是否已经存在
//        UserEntity userCommonInfoDomain =userDbHelper.findByMail(email);
//        if(userCommonInfoDomain.getEmail() != null){
//            return "该邮箱已经注册";
//        }

                String passAfterEncode = "";
        try {
            passAfterEncode = aesEncryptService.aesEncrypt(password);
        } catch (Exception e) {
            return "密码格式错误";
        }

        UserCommonInfoPO userCommonInfoPO = new UserCommonInfoPO(nickName,type,passAfterEncode,email);
        Long newId  = loginUserDbHelper.add(userCommonInfoPO);

        /*
        * 根据注册类型，worker\requester来选择不同的helper
        * 为每一位用户建立信息档案：workerinfodomian and requesterinfodomain
        *
        * 这里只需要初始化即可
        *
        * */

        /*worker
        *
        * public WorkerEntity(Integer points, Integer rank,
                            Integer doingMissionsNum, DoingMissionEntity[] doingMissionDomains, Integer doneMissionNum,
                            DoneMissionEntity[] doneMissionDomains, Integer totalAnswerNum, Integer rightAnswerNum,
                            Integer wrongAnswerNum, Integer toBeJudgeAnswerNum, Double answerAccuracy, Integer totalCheckNum,
                            Integer rightCheckNum, Integer wrongCheckNum, Integer toBeJudgeCheckNum, Double checkAccuracy)
        * */
        if(type.toLowerCase().equals("worker")){

            DoingMissionEntity[] doingMissionEntities = new DoingMissionEntity[4];//最多只允许有4个
            for(int i = 0; i < 4; i++){
                doingMissionEntities[i] = new DoingMissionEntity(-1,"",0,0,new Date(),new Date(),new Long[1]
                ,0);
            }

            DoneMissionEntity[] doneMissionEntities = new DoneMissionEntity[88];//最多允许512个已经完成的任务
            for(int i = 0; i < 88; i++){
                doneMissionEntities[i] = new DoneMissionEntity(-1,"",0,0,0,0,0,new Date(),new Long[1]);
            }

            Map<Integer,WorkerInfoEntity> workerInfo=new HashMap<>();

            for(int i=0;i<26;i++){
                workerInfo.put(i,new WorkerInfoEntity());
            }
            WorkerEntity workerEntity = new WorkerEntity(-1,0,0,doingMissionEntities,
                    0,doneMissionEntities,0,0,
                    0,0,0.0,workerInfo,
                    0,0,0,0,0.0);

            workerEntity.setWorkerId(newId);

            loginWorkerDbHelper.add(workerEntity);


         /*requester
         *
         * public RequesterEntity(String requesterId, Integer releasedMissionsNum,
                               String[] releasedMissionsList, Integer paidPoints)
         *
         * */
        }else if(type.toLowerCase().equals("requester")){

            Long[] missionList = new Long[88];

            RequesterEntity requesterInfoDomain = new RequesterEntity(0,missionList,0,0);
            requesterInfoDomain.setRequesterId(newId);

            loginRequesterDbHelper.add(requesterInfoDomain);

        }else{

            //管理员暂时不需要进行配置
            return  "未知用户类型";

        }


        //发送注册邮件
//        sendSignUpService = new SendSignUpImpl();
        try {
            sendSignUpService.sendSignUpMail(newId,
                                      password,
                                      userCommonInfoPO.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
