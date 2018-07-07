package business.mission.missionmanager.controller;


import business.common.dto.RecommendDTO;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.missionentity.RecmdMissionEntity;
import business.common.exception.LogicException;
import business.common.returnmodel.ReturnArrayVO;
import business.common.returnmodel.ReturnMessage;
import business.common.returnmodel.ReturnVO;
import business.login.auth.service.AuthService;
import business.mission.helper.Mission_MissionDbHelper;
import business.mission.missionmanager.service.*;
import business.mission.missionmanager.vo.MissionInfoReturnVO;
import business.mission.missionmanager.vo.NewMissionInfoReturnVO;
import business.mission.missionmanager.vo.ParticipantsReturnVO;
import business.mission.missionmanager.vo.RecmdMissionVO;
import business.mission.missionmanager.vo.parametervo.BuildMissionVO;
import business.recommendation.service.RecommendationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/")
public class MissionController {

    @Autowired
    private BuildMissionService buildMissionService;

    @Autowired
    private GetParticipantsService getParticipantsService;

    @Autowired
    private GetMissionByIdService getMissionByIdService;

    @Autowired
    private GetRequesterMissionsService getRequesterMissionsService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ListAllMissionListService listAllMissionListService;

    @Autowired
    private ListNewMissionListService listNewMissionListService;

    @Autowired
    private SignUpNewMissionService signUpNewMissionService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private Mission_MissionDbHelper mission_missionDbHelper;
    ObjectMapper mapper=new ObjectMapper();

    public MissionController() throws Exception {
    }

    @RequestMapping(value="/buildMission",method = RequestMethod.POST)
    public ReturnMessage buildMissionService(@RequestBody BuildMissionVO buildMissionVO,
                                             HttpServletRequest httpServletRequest) throws Exception {
        Long requesterId = authService.decodeCookiesForId(httpServletRequest.getCookies());
//        Long requesterId=buildMissionVO.getRequesterId();
        String description = buildMissionVO.getDescription();
        Integer[] labels=buildMissionVO.getClassLabel();
        Set<Integer> label=new HashSet<>();
        for(int i=0;i<labels.length;i++){
            label.add(labels[i]);
        }
        String title = buildMissionVO.getTitle();
        String type = buildMissionVO.getType();
        String[] pictures = buildMissionVO.getPics();
        int perPicPoints = buildMissionVO.getPerPoints();
        String message="";
        if(buildMissionService.buildMission(requesterId,title,label,description,type,pictures,perPicPoints)){
            message="建立任务成功";
            return new ReturnMessage(true,message);
        }
        else{
            throw new LogicException("建立失败");
        }
    }

    @RequestMapping(value="/getParticipants",method = RequestMethod.GET)
    public ReturnArrayVO getParticipantsService(@RequestParam(value = "missionId")Long missionId) throws Exception {
        ParticipantsReturnVO[] participantsReturnVOS=getParticipantsService.getParticipants(missionId);
        return new ReturnArrayVO(true,participantsReturnVOS);
    }

    @RequestMapping(value = "/getRequesterMissions",method = RequestMethod.GET)
    public ReturnArrayVO getRequesterMissionsService(HttpServletRequest httpServletRequest) throws Exception {
        Long requesterId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        MissionInfoReturnVO[] missionInfoReturnVOS =getRequesterMissionsService.getRequesterMission(requesterId);
        return new ReturnArrayVO(true,missionInfoReturnVOS);
    }

    @RequestMapping(value = "/getAllMissions",method = RequestMethod.GET)
    public ReturnArrayVO listAllMissionListService() throws Exception {
        MissionInfoReturnVO[] missionInfoReturnVOS=listAllMissionListService.ListAllMissionList();
        if(missionInfoReturnVOS==null){
            throw new LogicException("没有任务");
        }
        return new ReturnArrayVO(true,missionInfoReturnVOS);
    }

    @RequestMapping(value = "/askForNewList",method = RequestMethod.GET)
    public ReturnArrayVO listNewMissionListService(HttpServletRequest httpServletRequest) throws Exception {
        Long workerId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        NewMissionInfoReturnVO[] newMissionInfoReturnVOS =listNewMissionListService.listNewMissionList(workerId);
        if(newMissionInfoReturnVOS==null){
            throw new LogicException("没有新任务");
        }
        return new ReturnArrayVO(true,newMissionInfoReturnVOS);
    }

    @RequestMapping(value = "/askForHotList",method = RequestMethod.GET)
    public ReturnArrayVO listHotMissionListService(HttpServletRequest httpServletRequest) throws Exception {
        Long workerId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        ArrayList<RecmdMissionEntity> recmdMissionEntities=recommendationService.recommendPopular(workerId);
        RecmdMissionEntity[] recmdMissionEntities1=recmdMissionEntities.toArray(new RecmdMissionEntity[recmdMissionEntities.size()]);
        RecmdMissionVO[] recmdMissionVOS=new RecmdMissionVO[recmdMissionEntities.size()];
        for(int i=0;i<recmdMissionVOS.length;i++){
            recmdMissionVOS[i]=new RecmdMissionVO(recmdMissionEntities1[i],getMissionByIdService.getMissionInfo(recmdMissionEntities1[i].getMissionId()));
        }
        return new ReturnArrayVO(true,recmdMissionVOS);
    }

    @RequestMapping(value = "/askForRecommendList",method = RequestMethod.GET)
    public ReturnArrayVO listRecommendMissionListService(HttpServletRequest httpServletRequest) throws Exception {
        Long workerId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        ArrayList<RecmdMissionEntity> recmdMissionEntities=recommendationService.recommendPersonal(workerId);
        for(int i=0;i<recmdMissionEntities.size();i++){
            MissionEntity missionEntity=mission_missionDbHelper.findById(recmdMissionEntities.get(i).getMissionId());
            if(missionEntity.getPictureFinishedNum()==missionEntity.getPictureTotalNum()){
                recmdMissionEntities.remove(i);
            }
        }
        RecmdMissionEntity[] recmdMissionEntities1=recmdMissionEntities.toArray(new RecmdMissionEntity[recmdMissionEntities.size()]);
        RecmdMissionVO[] recmdMissionVOS=new RecmdMissionVO[recmdMissionEntities.size()];
        for(int i=0;i<recmdMissionVOS.length;i++){
            recmdMissionVOS[i]=new RecmdMissionVO(recmdMissionEntities1[i],getMissionByIdService.getMissionInfo(recmdMissionEntities1[i].getMissionId()));
        }
        return new ReturnArrayVO(true,recmdMissionVOS);
    }

    @RequestMapping(value = "/askForMissionInfo",method = RequestMethod.GET)
    public ReturnVO getMissionInfo(@RequestParam(value = "missionId")Long missionId) throws Exception {
        MissionInfoReturnVO missionInfoReturnVO=getMissionByIdService.getMissionInfo(missionId);
        return new ReturnVO(true,missionInfoReturnVO);
    }

    @RequestMapping(value = "/signUpNewMission",method = RequestMethod.GET)
    public ReturnMessage signUpNewMissionService(@RequestParam(value = "missionId")Long missionId,
                                                 @RequestParam(value = "kind")String kind,
                                                 HttpServletRequest httpServletRequest) throws Exception {
        Long workerId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        String signUpNewMission=signUpNewMissionService.signUpMission(workerId, missionId, kind);
        if(signUpNewMission.equals("success")){
            return new ReturnMessage(true,"");
        }
        else{
            return new ReturnMessage(false,signUpNewMission);
        }
    }


}