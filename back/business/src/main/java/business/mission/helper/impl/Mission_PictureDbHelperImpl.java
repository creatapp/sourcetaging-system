package business.mission.helper.impl;

import business.common.entity.pictureentity.PictureEntity;
import business.common.repository.PictureRepository;
import business.mission.helper.Mission_PictureDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class Mission_PictureDbHelperImpl implements Mission_PictureDbHelper {

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Long[] add(ArrayList<PictureEntity> pictureEntityArrayList) {

        Iterable<PictureEntity> pictureEntityIterable = pictureRepository.saveAll(pictureEntityArrayList);
        Iterator<PictureEntity> pictureEntityIterator = pictureEntityIterable.iterator();

        ArrayList<Long> picIds = new ArrayList<>();
        while(pictureEntityIterator.hasNext()){
            picIds.add(pictureEntityIterator.next().getPictureId());
        }
        Long[] temp=new Long[picIds.size()];
        for (int i=0;i<picIds.size();i++){
            temp[i]=picIds.get(i);
        }
        return temp;
    }

    @Override
    public PictureEntity getPicById(Long picId) {
        return pictureRepository.findById(picId).get();
    }
}
