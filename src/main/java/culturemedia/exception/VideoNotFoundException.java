package culturemedia.exception;

import java.text.MessageFormat;

public class VideoNotFoundException extends  CultureMediaException{

    public  VideoNotFoundException(String title){
        super(MessageFormat.format("There is not video with title {0}", title));
    }

    public VideoNotFoundException(){
        super("Video not found");
    }
}
