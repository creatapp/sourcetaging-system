package business.user.helper.impl;

import business.common.entity.pictureentity.PictureEntity;
import business.common.repository.PictureRepository;
import business.user.helper.User_PictureDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class User_PictureDbHelperImpl implements User_PictureDbHelper {

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
        return (Long[])picIds.toArray();
    }
}
