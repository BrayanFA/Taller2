package culturemedia.service;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.List;

public class CultureMediaServiceImpl implements CultureMediaService{
    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;
    public CultureMediaServiceImpl(VideoRepository videoRepository){
        this.videoRepository = videoRepository;
    }
    public CultureMediaServiceImpl(ViewsRepository viewsRepository){
        this.viewsRepository = viewsRepository;
    }
    @Override
    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if(videos.isEmpty()){
            throw new VideoNotFoundException();
        }
        else{
            return videos;
        }
    }

    @Override
    public Video add(Video video) {
        Video videoAdd = videoRepository.save(video);
        return videoAdd;
    }

    @Override
    public View add(View view) {
        View viewAdd = viewsRepository.save(view);
        return viewAdd;
    }
}