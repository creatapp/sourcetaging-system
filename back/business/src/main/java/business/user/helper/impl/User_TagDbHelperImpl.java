package business.user.helper.impl;

import business.common.entity.tagentity.TagEntity;
import business.common.exception.LogicException;
import business.common.repository.TagRepository;
import business.user.helper.User_TagDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class User_TagDbHelperImpl implements User_TagDbHelper {

    @Autowired
    private TagRepository tagRepository;
    
    public TagEntity findById(long id){
        Optional<TagEntity> tagEntityOptional = tagRepository.findById(id);
        if(tagEntityOptional.isPresent())return tagEntityOptional.get();
        else throw new LogicException("未找到该标注");
    }

    public boolean update(TagEntity tagEntity){
        tagRepository.save(tagEntity);
        return true;
    }

    @Override
    public void delete(long tagId) {
        tagRepository.deleteById(tagId);
    }

    @Override
    public TagEntity add(TagEntity tagEntity) {
        return tagRepository.save(tagEntity);
    }

}
