import java.util.ArrayList;
import java.util.List;

public class AmbigiousProductException extends Exception {
    List<String> messagesToStackTrace;
    public AmbigiousProductException(List<String> messagesToStackTrace) {
        this.messagesToStackTrace = messagesToStackTrace;
    }

    @Override
    public String getMessage() {
        return messagesToStackTrace.toString();
    }
}
