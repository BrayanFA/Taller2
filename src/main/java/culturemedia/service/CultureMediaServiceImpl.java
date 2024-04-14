package culturemedia.service;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.List;

public class CultureMediaServiceImpl {
    private VideoRepository videofacemedia;
    private ViewsRepository viewfacemedia;
    public CultureMediaServiceImpl(VideoRepository interfacemedia){
        this.videofacemedia = interfacemedia;
    }
    public CultureMediaServiceImpl(ViewsRepository viewfacemedia){ this.viewfacemedia = viewfacemedia;}

    public List<Video> findTitle(String title) throws VideoNotFoundException {
        var videos = videofacemedia.find(title);
        if(videos.isEmpty()){
            throw new VideoNotFoundException(title);
        }
        return videos;
    }
    public List<Video> findDuration(String title, Double duration) throws DurationNotValidException {
        var videos = videofacemedia.find(Double.valueOf(title), duration);
        if(videos.isEmpty()){
            throw new DurationNotValidException(title, duration);
        }
        return videos;
    }

    public List<Video> findAll(){
        return videofacemedia.findAll();
    }
}