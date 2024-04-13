package culturemedia.exception;

import java.text.MessageFormat;

public class DurationNotValidException extends CultureMediaException{

    public DurationNotValidException(String title, Double duration){
        super(MessageFormat.format("There is not video with title {0} and duration {1}", title , duration));
    }
}