package business.common.controller;

import business.common.entity.logentity.LogEntity;
import business.common.entity.missionentity.HotMissionEntity;
import business.common.entity.missionentity.MissionEntity;
import business.common.entity.requesterentity.RequesterEntity;
import business.common.entity.workerentity.RecmdWeighEntity;
import business.common.entity.workerentity.SimWorkerEntity;
import business.common.entity.workerentity.WorkerEntity;
import business.common.repository.HotMissionRepository;
import business.common.repository.MissionRepository;
import business.common.repository.RequesterRepository;
import business.common.repository.WorkerRepository;
import business.common.returnmodel.ReturnArrayVO;
import business.common.returnmodel.ReturnVO;
import business.log.service.LogService;
import business.log.vo.LogVO;
import business.user.usermanager.vo.workermanagervo.WorkerDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping(value = "/")
@Transactional
public class CommonController {
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private RequesterRepository requesterRepository;
    @Autowired
    private HotMissionRepository hotMissionRepository;

    public CommonController() throws Exception {
    }
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public ReturnVO mission_save() throws Exception {
        List<SimWorkerEntity> simWorkerEntities=workerRepository.findById(100000001L).get().getRecmdInterestSimWorker();
        simWorkerEntities.add(new SimWorkerEntity(100000001L,1.0));
        WorkerEntity workerEntity=workerRepository.findById(100000002L).get();
        workerEntity.setRecmdTraitSimWorker(simWorkerEntities);
        workerRepository.save(workerEntity);
        return null;
    }

}
