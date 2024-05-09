package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import java.util.List;

public interface CultureMediaService {

    List<Video> findAllVideos() throws VideoNotFoundException;
    Video add(Video video);
    View add(View view);
}