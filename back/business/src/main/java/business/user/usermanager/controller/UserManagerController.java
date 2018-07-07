package business.user.usermanager.controller;

import business.common.exception.LogicException;
import business.common.returnmodel.ReturnArrayVO;
import business.common.returnmodel.ReturnMessage;
import business.common.returnmodel.ReturnVO;
import business.common.returnmodel.VO;
import business.login.auth.service.AuthService;
import business.user.usermanager.service.RequesterInfoService;
import business.user.usermanager.service.UserInfoUpdateService;
import business.user.usermanager.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
@WebAppConfiguration
public class UserManagerController {

    @Autowired
    private UserInfoUpdateService userInfoUpdateService;

    @Autowired
    private RequesterInfoService requesterInfoService;

    @Autowired
    private WorkerInfoService workerInfoService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/changeNickname",method = RequestMethod.GET)
    public ReturnMessage changeNickName(@RequestParam(value = "id")Long id,
                                        @RequestParam(value = "newNickname")String newNickName){

        boolean re = userInfoUpdateService.changeNickName(id,newNickName);
        if(re){
            return new ReturnMessage(true,"昵称修改成功");
        }else {
            throw new LogicException("昵称修改失败，顶层结果为false");
        }
    }

    @RequestMapping(value = "/changeEmail",method = RequestMethod.GET)
    public ReturnMessage changeEmail(@RequestParam(value = "id")Long id,
                                @RequestParam(value = "newEmail")String email){

        boolean re = userInfoUpdateService.changeEmail(id,email);
        if(re){
            return new ReturnMessage(true,"邮箱修改成功");
        }else {
            throw new LogicException("邮箱修改失败，顶层结果为false");
        }
    }



    @RequestMapping(value = "/getWorkerData",method = RequestMethod.GET)
    public ReturnVO getWorkerData(@RequestParam(value = "id")Long workerId){

        VO message = workerInfoService.getWorkerData(workerId);

        return new ReturnVO(true,message);
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public ReturnVO getUserInfo(@RequestParam(value = "id")Long userId){
        VO message = workerInfoService.getUserInfo(userId);

        return new ReturnVO(true,message);
    }


    @RequestMapping(value = "/getRequesterData",method = RequestMethod.GET)
    public ReturnVO getRequesterInfo(@RequestParam(value = "id")Long requesterId){
        VO message = requesterInfoService.getRequesterInfo(requesterId);

        return new ReturnVO(true,message);
    }



    @RequestMapping(value = "/getAllRequesters",method = RequestMethod.GET)
    public ReturnArrayVO getAllRequesters(){

        VO[] message = requesterInfoService.getAllRequester();

        return new ReturnArrayVO(true,message);
    }



    @RequestMapping(value = "/getAllWorkers",method = RequestMethod.GET)
    public ReturnArrayVO getAllWorkers(){

        VO[] message = workerInfoService.getAllWorkers();

        return new ReturnArrayVO(true,message);
    }
}
