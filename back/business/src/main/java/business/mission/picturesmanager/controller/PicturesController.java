package business.mission.picturesmanager.controller;

import business.common.exception.LogicException;
import business.common.returnmodel.ReturnMessage;
import business.common.returnmodel.ReturnVO;
import business.login.auth.service.AuthService;
import business.mission.picturesmanager.service.*;
import business.mission.picturesmanager.vo.PictureReturnVO;
import business.mission.picturesmanager.vo.parametervo.SaveCheckAnswerVO;
import business.mission.picturesmanager.vo.parametervo.SaveTagAnswerVO;
import business.mission.picturesmanager.vo.parametervo.UpdateCheckAnswerVO;
import business.mission.picturesmanager.vo.parametervo.UpdateTagAnswerVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/")
@Component
public class PicturesController {

    @Autowired
    private AskForPictureService askForPictureService;

    @Autowired
    private SaveCheckAnswerService saveCheckAnswerService;

    @Autowired
    private SaveTagAnswerService saveTagAnswerService;

    @Autowired
    private UpdateCheckAnswerService updateCheckAnswerService;

    @Autowired
    private UpdateTagAnswerService updateTagAnswerService;

    @Autowired
    private AuthService authService;

    ObjectMapper mapper=new ObjectMapper();

    @RequestMapping(value = "/askForPic",method = RequestMethod.GET)
    public ReturnVO askForPictureService(@RequestParam(value = "missionId")Long missionId,
                                         @RequestParam(value="kind")String kind,
                                         @RequestParam(value = "picId")Long pictureId,
                                         HttpServletRequest httpServletRequest) {
//        String missionId = askForPicVO.getMissionId();
//        String kind = askForPicVO.getKind();
//        String workerId = askForPicVO.getWorkerId();
//        String pictureId = askForPicVO.getPicId();

        //deal with the pictureId
//        String realPictureId="";
//        if(!pictureId.equals("")){
//            String[] a=pictureId.split("/");
//            String x=a[a.length-1];
//            realPictureId=x.split("\\.")[0];
//        }
        Long workerId = authService.decodeCookiesForId(httpServletRequest.getCookies());
        PictureReturnVO pictureReturnVO=askForPictureService.askForPicture(missionId,kind,pictureId,workerId);
        return new ReturnVO(true,pictureReturnVO);
    }

    @RequestMapping(value = "/saveCheckAnswer",method = RequestMethod.POST)
    public ReturnMessage saveCheckAnswerService(@RequestBody SaveCheckAnswerVO saveCheckAnswerVO,
                                                HttpServletRequest httpServletRequest){
        Long missionId=saveCheckAnswerVO.getMissionId();
        Long pictureId = saveCheckAnswerVO.getPicId();
        Long authorId = saveCheckAnswerVO.getAuthorId();
        Long checkerId = authService.decodeCookiesForId(httpServletRequest.getCookies());
        Integer checkTime = saveCheckAnswerVO.getPicTime();
        boolean checkAnswer = saveCheckAnswerVO.isCheckAnswer();

        //deal with the pictureId
        //String[] a=pictureId.split("/");
       // String x=a[a.length-1];
       // String realPictureId=x.split("\\.")[0];
        String saveCheckAnswer=saveCheckAnswerService.saveCheckAnswer(missionId, authorId, checkerId, pictureId,checkAnswer,checkTime);
        return new ReturnMessage(true,saveCheckAnswer);
    }

    @RequestMapping(value = "/saveTagAnswer",method = RequestMethod.POST)
    public ReturnMessage saveTagAnswerService(@RequestBody SaveTagAnswerVO saveTagAnswerVO,
                                              HttpServletRequest httpServletRequest){
        Long missionId=saveTagAnswerVO.getMissionId();
        Long pictureId = saveTagAnswerVO.getPicId();
        Long authorId = authService.decodeCookiesForId(httpServletRequest.getCookies());
        String mark = saveTagAnswerVO.getMark();
        String tag = saveTagAnswerVO.getTag();
        Long picTime =saveTagAnswerVO.getPicTime();
        Integer tagTime = saveTagAnswerVO.getTagTime();

        //deal with the pictureId
        //String[] a=pictureId.split("/");
        //String x=a[a.length-1];
        //String realPictureId=x.split("\\.")[0];
        String saveTagAnswer=saveTagAnswerService.saveTagAnswer(missionId,authorId, pictureId, mark, tag,picTime,tagTime);
        return new ReturnMessage(true,saveTagAnswer);
    }

    @RequestMapping(value = "/updateCheckAnswer",method = RequestMethod.POST)
    public ReturnMessage updateCheckAnswerService(@RequestBody UpdateCheckAnswerVO updateCheckAnswerVO,
                                                  HttpServletRequest httpServletRequest){
        Long missionId=updateCheckAnswerVO.getMissionId();
        Long pictureId = updateCheckAnswerVO.getPicId();
        Long authorId = authService.decodeCookiesForId(httpServletRequest.getCookies());
        Long checkerId = updateCheckAnswerVO.getCheckerId();
        String mark = updateCheckAnswerVO.getMark();
        String tag = updateCheckAnswerVO.getTag();
        Integer checkTime = updateCheckAnswerVO.getCheckTime();
        boolean checkAnswer = updateCheckAnswerVO.isCheckAnswer();

        //deal with the pictureId
        //String[] a=pictureId.split("/");
        //String x=a[a.length-1];
        //String realPictureId=x.split("\\.")[0];
        if(updateCheckAnswerService.updateCheckAnswer(missionId, authorId, checkerId, pictureId,mark,tag,checkAnswer,checkTime)){
            return new ReturnMessage(true,"");
        }
        else{
            throw new LogicException("更新失败");
        }
    }

    @RequestMapping(value = "/updateTagAnswer",method = RequestMethod.POST)
    public ReturnMessage updateTagAnswerService(@RequestBody UpdateTagAnswerVO updateTagAnswerVO){
        Long missionId=updateTagAnswerVO.getMissionId();
        Long pictureId = updateTagAnswerVO.getPicId();
        Long authorId = updateTagAnswerVO.getAuthorId();
        String mark = updateTagAnswerVO.getMark();
        String tag = updateTagAnswerVO.getTag();
        Integer tagTime = updateTagAnswerVO.getTagTime();

        //deal with the pictureId
        //String[] a=pictureId.split("/");
        //String x=a[a.length-1];
        //String realPictureId=x.split("\\.")[0];
        if(updateTagAnswerService.updateTagAnswer(missionId,authorId, pictureId, mark, tag,tagTime)){
            return new ReturnMessage(true,"");
        }
        throw new LogicException("更新失败");
    }
}
