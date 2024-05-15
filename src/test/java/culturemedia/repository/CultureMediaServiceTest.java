package culturemedia.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import culturemedia.service.CultureMediaService;
import culturemedia.service.CultureMediaServiceImpl;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.impl.*;

class CultureMediaServiceTest {
    private CultureMediaService cultureMediaService;
    private VideoRepository videoRepository = Mockito.mock();

    private Video testVideo1 = new Video("01", "Title 1", "Random Description", 3.2);
    private Video testVideo2 = new Video("02", "Title 2", "Random Description", 2.8);
    private Video testVideo3 = new Video("03", "Mark 3", "Random Description", 4.1);
    private Video testVideo4 = new Video("04", "Mark 4", "Random Description", 2.3);
    private Video testVideo5 = new Video("05", "Mark 5", "Random Description", 4.4);
    private Video testVideo6 = new Video("06", "Titanic", "Random Description", 7.7);

    @BeforeEach
    void init() {
        ViewsRepository viewsRepository = new ViewsRepositoryImpl();
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        mockVideoRepositoryFindAll(Collections.emptyList());
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.findAllVideos();
        });
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        mockVideoRepositoryFindAll(List.of(testVideo1, testVideo2, testVideo3, testVideo4, testVideo5, testVideo6));
        List<Video> videos = cultureMediaService.findAllVideos();
        assertEquals(6, videos.size());
    }

    @Test
    void when_find_forTitle_an_VideoNotFoundException_should_be_thrown_successfully() {
        mockVideoRepositoryFind(null, Collections.emptyList());
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find("Candle");
        });
    }

    @Test
    void when_find_forDuration_an_VideoNotFoundException_should_be_thrown_successfully() {
        mockVideoRepositoryFind(null, null, Collections.emptyList());
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find(0.0, 0.5);
        });
    }

    @Test
    void when_find_forTitle_should_be_returned_succesfully() throws VideoNotFoundException{

        mockVideoRepositoryFind("Titanic", List.of(testVideo6));
        List<Video> videos = cultureMediaService.find("Titanic");
        assertEquals(1, videos.size());
        assertEquals(testVideo6, videos.get(0));
    }

    @Test
    void when_find_forDuration_should_be_returned_succesfully() throws VideoNotFoundException{
        mockVideoRepositoryFind(0.0, 5.5, List.of(testVideo1, testVideo2, testVideo3, testVideo4, testVideo6));
        List<Video> videos = cultureMediaService.find(2.0, 4.8);
        assertEquals(5, videos.size());
    }

    private void mockVideoRepositoryFindAll(List <Video> videos){
        doReturn(videos).when(videoRepository).findAll();
    }

    private void mockVideoRepositoryFind(String title, List<Video> videos){
        doReturn(videos).when(videoRepository).find(eq(title));
    }

    private void mockVideoRepositoryFind(Double fromDuration, Double toDuration, List<Video> videos){
        doReturn(videos).when(videoRepository).find(eq(fromDuration), eq(toDuration));
    }
}
