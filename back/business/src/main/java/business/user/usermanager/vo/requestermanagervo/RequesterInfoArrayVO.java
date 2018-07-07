package business.user.usermanager.vo.requestermanagervo;

import java.io.Serializable;
import java.util.Arrays;

public class RequesterInfoArrayVO implements Serializable {

    private RequesterInfoVO[] requesters;

    public RequesterInfoArrayVO(RequesterInfoVO[] requesters) {
        this.requesters = requesters;
    }

    public RequesterInfoVO[] getRequesters() {
        return requesters;
    }

    public void setRequesters(RequesterInfoVO[] requesters) {
        this.requesters = requesters;
    }

    @Override
    public String toString() {
        return "RequesterInfoArrayVO{" +
                "requesters=" + Arrays.toString(requesters) +
                '}';
    }
}
