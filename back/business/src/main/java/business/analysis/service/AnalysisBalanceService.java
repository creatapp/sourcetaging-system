package business.analysis.service;

import org.springframework.stereotype.Component;

@Component
public interface AnalysisBalanceService {

    //结算和进行整合
    void planToBalance(Long[] tagIdArray);
}
