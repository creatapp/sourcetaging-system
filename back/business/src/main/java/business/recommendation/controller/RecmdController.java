package business.recommendation.controller;

import business.common.exception.LogicException;
import business.common.returnmodel.ReturnArrayVO;
import business.common.returnmodel.ReturnMessage;
import business.common.returnmodel.ReturnVO;
import business.login.auth.service.AuthService;
import business.recommendation.service.*;
import business.recommendation.vo.AssociationRuleVO;
import business.recommendation.vo.FeedbackVO;
import business.recommendation.vo.WorkerRecmdInfoVO;
import business.recommendation.vo.WorkerSimilarityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class RecmdController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RecommendationFeedbackService recommendationFeedbackService;

    @Autowired
    private RecommendationAssociationRuleService recommendationAssociationRuleService;

    @Autowired
    private RecommendationWorkerSimilarityService recommendationWorkerSimilarityService;

    @Autowired
    private RecommendationWorkerRecmdInfoService recommendationWorkerRecmdInfoService;

    @Autowired
    private RecommendationReasonService recommendationReasonService;


    @RequestMapping(value = "/feedback",method = RequestMethod.GET)
    public ReturnMessage recmdFeedback(@RequestParam(value = "missionId")Long missionId,
                                       @RequestParam(value = "event")int event,
                                       HttpServletRequest httpServletRequest){
        Long workerId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        try{
            recommendationFeedbackService.adjust(workerId,missionId,event);
            return new ReturnMessage(true,"");
        }catch (Exception e){
            throw  new LogicException("反馈系统失效");
        }
    }

    @RequestMapping(value = "/workerRecmdInfo",method = RequestMethod.GET)
    public ReturnArrayVO listWorkerRecmdInfo(@RequestParam(value = "id")Long workerId){
        try{
            WorkerRecmdInfoVO[] workerRecmdInfoVOS=recommendationWorkerRecmdInfoService.listWorkerRecmdInfo(workerId);
            return new ReturnArrayVO(true,workerRecmdInfoVOS);
        }catch (Exception e){
            throw new LogicException("无法显示");
        }
    }

    @RequestMapping(value = "/workerSimilarity",method = RequestMethod.GET)
    public ReturnArrayVO listWorkerSimilarity(@RequestParam(value = "id")Long workerId){
        try{
            WorkerSimilarityVO[] workerSimilarityVOS=recommendationWorkerSimilarityService.listWorkerSimilarity(workerId);
            return new ReturnArrayVO(true,workerSimilarityVOS);
        }catch (Exception e){
            throw new LogicException("无法显示");
        }
    }

//    @RequestMapping(value = "/recmdReason",method = RequestMethod.GET)
//    public ReturnMessage getReason(@RequestParam(value = "workerId")Long workerId,
//                                   @RequestParam(value = "missionId")Long missionId){
//        try{
//            String reason=recommendationReasonService.getReason(workerId,missionId);
//            return new ReturnMessage(true,reason);
//        }catch (Exception e){
//            throw new LogicException("系统故障");
//        }
//    }

    @RequestMapping(value = "/associationRule",method = RequestMethod.GET)
    public ReturnArrayVO listAssociationRule(@RequestParam(value = "workerId")Long workerId) {
        try {
            AssociationRuleVO[] associationRuleVOS = recommendationAssociationRuleService.getAssociationRule(workerId);
            return new ReturnArrayVO(true, associationRuleVOS);
        } catch (Exception e) {
            throw new LogicException("系统故障");
        }
    }
}
