package business.mission.missionmanager.service;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface BuildMissionService {

    boolean buildMission(Long requesterId, String title, Set<Integer> label, String description,
                         String type, String[] pictures, int perPicPoints) throws Exception;
}
