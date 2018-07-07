package business.log.controller;

import business.common.returnmodel.ReturnArrayVO;
import business.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/")
@Transactional
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value="/getHistory",method = RequestMethod.GET)
    public ReturnArrayVO mission_save(@RequestParam(value = "id")Long id) throws Exception {
        if(id!=100000000L)
            return new ReturnArrayVO(true,logService.findByOperatorId(id));
        return new ReturnArrayVO(true,logService.getAll());
    }
}
