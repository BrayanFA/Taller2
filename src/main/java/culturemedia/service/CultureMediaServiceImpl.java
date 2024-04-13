package culturemedia.service;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

import java.util.List;

public class CultureMediaServiceImpl {
    private VideoRepository interfacemedia;

    public CultureMediaServiceImpl(VideoRepository interfacemedia){
        this.interfacemedia = interfacemedia;
    }

    public List<Video> findTitle(String title) throws VideoNotFoundException {
        var videos = interfacemedia.find(title);
        if(videos.isEmpty()){
            throw new VideoNotFoundException(title);
        }
        return videos;
    }
    public List<Video> findDuration(String title, Double duration) throws DurationNotValidException {
        var videos = interfacemedia.find(Double.valueOf(title), duration);
        if(videos.isEmpty()){
            throw new DurationNotValidException(title, duration);
        }
        return videos;
    }

    public List<Video> findAll(){
        return interfacemedia.findAll();
    }
}