package business.common.returnmodel;

import java.io.Serializable;

public class ReturnMessage implements Serializable {

    private boolean result;
    private String message;

    public ReturnMessage(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ReturnMessage(){}

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReturnMessage{" +
                "result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
