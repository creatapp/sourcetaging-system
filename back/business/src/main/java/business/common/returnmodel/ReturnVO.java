package business.common.returnmodel;

import java.io.Serializable;

public class ReturnVO implements Serializable {

    private boolean result;

    private VO message;

    public ReturnVO(boolean result, VO message) {
        this.result = result;
        this.message = message;
    }

    public ReturnVO(){}

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public VO getMessage() {
        return message;
    }

    public void setMessage(VO message) {
        this.message = message;
    }
}
