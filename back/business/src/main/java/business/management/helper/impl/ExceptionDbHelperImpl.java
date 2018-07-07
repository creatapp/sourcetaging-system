package business.management.helper.impl;

import business.common.entity.exceptionentity.ExceptionEntity;
import business.common.repository.ExceptionRepository;
import business.management.helper.ExceptionDbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionDbHelperImpl implements ExceptionDbHelper {

    @Autowired
    private ExceptionRepository exceptionRepository;

    @Override
    public Long add(ExceptionEntity exceptionEntity) {
        return exceptionRepository.save(exceptionEntity).getId();
    }
}
