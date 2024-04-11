package culturemedia.service;

import culturemedia.repository.VideoRepository;
import culturemedia.exception.VideoNotFoundException.java;
import culturemedia.exception.DurationNotValidException.java;
public class CultureMediaServiceImpl {
    private VideoRepository interfacemedia;

    public CultureMediaServiceImpl(VideoRepository interfacemedia){
        this.interfacemedia = interfacemedia;
    }

    public List<Video> findTitle(String title){
        var videos = interfacemedia.find(title);
        if(videos.isEmpty()){
            throw new VideoNotFoundException(title);
        }
        return videos;
    }
    public List<Video> findDuration(String title, Double duration){
        var videos = interfacemedia.find(title, duration);
        if(videos.isEmpty()){
            throw new DurationNotValidException(title, duration);
        }
        return videos;
    }

    public List<Video> findAll(){
        return interfacemedia.findAll();
    }
}