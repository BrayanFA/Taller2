package culturemedia.exception;

public class VideoNoFoundException extends  CultureMediaException{

    public VideoNoFoundException(){

    }
    public  VideoNoFoundException(String title){
        super(title)
    }
}