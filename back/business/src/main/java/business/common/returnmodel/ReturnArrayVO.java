package business.common.returnmodel;

import java.io.Serializable;
import java.util.Arrays;

public class ReturnArrayVO implements Serializable {

    private boolean result;
    private VO[] message;

    public ReturnArrayVO(boolean result, VO[] message) {
        this.result = result;
        this.message = message;
    }

    public ReturnArrayVO(){}

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public VO[] getMessage() {
        return message;
    }

    public void setMessage(VO[] message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReturnArrayVO{" +
                "result=" + result +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
