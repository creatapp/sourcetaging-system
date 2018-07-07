package business.login.controller;

import business.common.exception.LogicException;
import business.common.returnmodel.ReturnMessage;
import business.login.auth.service.AuthService;
import business.login.service.*;
import business.login.vo.ChangePasswordVO;
import business.login.vo.LogInVO;
import business.login.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private FetchPasswordService fetchPasswordService;

    @Autowired
    private ModifyPasswordService modifyPasswordService;

    @Autowired
    private SignInService signInService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ReturnMessage signUp(@RequestBody RegisterVO registerVO)throws Exception{

        String type = registerVO.getType();
        String password = registerVO.getPassword();
        String nickName = registerVO.getNickname();
        String email = registerVO.getEmail();

        if(email == null || !email.endsWith(".com")){
            throw new LogicException("邮箱名不规范,请确认邮箱名以.com结尾");
        }


        String isok = signUpService.signUp(type,password,nickName,email);
        if(!isok.equals("")){
            throw new LogicException(isok);
        }

        ReturnMessage returnMessage = new ReturnMessage(true,"我们发送了一封注册邮件到邮箱"+email);
        return returnMessage;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnMessage signIn(@RequestBody LogInVO logInVO,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse response)throws Exception{
        //匹配密码即可
        String type = logInVO.getType();
        Long id = logInVO.getId();
        String password = logInVO.getPassword();

        String nickname =  signInService.signIn(type,id,password,response);
//        response.setHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        System.out.println(httpServletRequest.getCookies());
//        Cookie writecookie=new Cookie("id",id+"");
//        writecookie.setHttpOnly(true);
//        writecookie.setPath("/");
//        writecookie.setMaxAge(7*24*60*60);   //保留7天
//        response.addCookie(writecookie);
        if(nickname.equals("typeError")){throw new LogicException("用户名或者密码不正确");}
        return new ReturnMessage(true,"");
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public ReturnMessage changePassword(@RequestBody ChangePasswordVO changePasswordVO,
                                        HttpServletRequest httpServletRequest)throws Exception{
        //匹配密码，然后修改密码
        Long id = authService.decodeCookiesForId(httpServletRequest.getCookies());
        String oldPassword = changePasswordVO.getPassword();
        String newPassword = changePasswordVO.getNewPassword();

        boolean isok = modifyPasswordService.modifyPassword(id,oldPassword,newPassword);
        if(!isok){
            throw new LogicException("修改密码失败，请确认原密码输入正确");
        }
        return new ReturnMessage(true,"改密成功");
    }


    @RequestMapping(value = "/findPassword",method = RequestMethod.GET)
    public ReturnMessage findPassword(@RequestParam(value = "email")String email)throws Exception{
        //查找邮箱，然后向该邮箱发送邮件和密码

        boolean isok =  fetchPasswordService.fetchPassword(email);
        if(!isok){
            throw new LogicException("没有查找到该邮箱记录");
        }
        return new ReturnMessage(true, "id和密码已发送到邮箱");
    }


}
