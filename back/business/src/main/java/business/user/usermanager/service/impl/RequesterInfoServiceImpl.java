package business.user.usermanager.service.impl;

import business.common.entity.requesterentity.RequesterEntity;
import business.common.entity.userentity.UserEntity;
import business.common.exception.LogicException;
import business.common.returnmodel.VO;
import business.user.helper.User_RequesterDbHelper;
import business.user.helper.User_UserDbHelper;
import business.user.usermanager.service.RequesterInfoService;
import business.user.usermanager.vo.requestermanagervo.RequesterInfoVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequesterInfoServiceImpl implements RequesterInfoService {

    @Autowired
    private User_RequesterDbHelper userRequesterDbHelper;

    @Autowired
    private User_UserDbHelper userUserDbHelper;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public VO getRequesterInfo(Long id) {

        RequesterEntity requesterEntity = userRequesterDbHelper.findById(id);

        UserEntity userEntity = userUserDbHelper.findById(id);

        //转换为需要的类型
        RequesterInfoVO requesterInfoVO = new RequesterInfoVO(id
        ,userEntity.getNickName(),userEntity.getEmail()
        ,requesterEntity.getReleasedMissionsNum()
        ,requesterEntity.getPaidPoints());

        return requesterInfoVO;
    }

    @Override
    public VO[] getAllRequester() {

        RequesterEntity[] requesterEntities = userRequesterDbHelper.findAll();
        int size = requesterEntities.length;

        if(size <= 0){
            throw new LogicException("当前没有发起者");
        }

        RequesterInfoVO[] requesterInfoVOS = new RequesterInfoVO[size];
        UserEntity userCommonInfoDomain;
        Long id;

        for(int i = 0; i < size; i++){
            id = requesterEntities[i].getRequesterId();

            userCommonInfoDomain = userUserDbHelper.findById(id);

            RequesterInfoVO requesterInfoVO = new RequesterInfoVO(id
            ,userCommonInfoDomain.getNickName(),userCommonInfoDomain.getEmail()
            ,requesterEntities[i].getReleasedMissionsNum()
            ,requesterEntities[i].getPaidPoints());

            requesterInfoVOS[i] = requesterInfoVO;
        }

        return requesterInfoVOS;
    }
}
