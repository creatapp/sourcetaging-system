package business.user.workermanager.controller;

import business.common.returnmodel.ReturnArrayVO;
import business.common.returnmodel.VO;
import business.login.auth.service.AuthService;
import business.user.workermanager.service.DoingListService;
import business.user.workermanager.service.DoneListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/")
public class WorkManagerController {


    @Autowired
    private DoingListService doingListService;

    @Autowired
    private DoneListService doneListService;

    @Autowired
    private AuthService authService;



    @RequestMapping(value = "/askForDoingList",method = RequestMethod.GET)
    public ReturnArrayVO listDoingList(HttpServletRequest httpServletRequest){
        Long workerId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        VO[] message = doingListService.listDoingList(workerId);
        return new ReturnArrayVO(true,message);
    }


    @RequestMapping(value = "/askForDoneList",method = RequestMethod.GET)
    public ReturnArrayVO listDoneList(HttpServletRequest httpServletRequest){
        Long workerId=authService.decodeCookiesForId(httpServletRequest.getCookies());
        VO[] message = doneListService.listDoneList(workerId);
        return new ReturnArrayVO(true,message);
    }


}
