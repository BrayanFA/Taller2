package culturemedia.repository;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.CultureMediaService;
import culturemedia.service.CultureMediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CultureMediaServiceTest {
    private CultureMediaService cultureMediaService;

    private Video exampleVideo1 = new Video("01", "Title 1", "Hello, this is a new video to.....", 4.5);
    private Video exampleVideo2 = new Video("02", "Title 2", "Hello, this is a new video to.....", 5.5);
    private Video exampleVideo3 = new Video("03", "Title 3", "Hello, this is a new video to.....", 4.4);
    private Video exampleVideo4 = new Video("04", "Title 4", "Hello, this is a new video to.....", 3.5);
    private Video exampleVideo5 = new Video("05", "Title 5", "Hello, this is a new video to.....", 5.7);
    private Video exampleVideo6 = new Video("06", "Hilmer Chona", "Hello, this is a new video to.....", 5.1);

    @BeforeEach
    void init() {
        VideoRepository videoRepository = new VideoRepositoryImpl();
        ViewsRepository viewsRepository = new ViewsRepositoryImpl();
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.findAllVideos();
        });
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        List<Video> videos = List.of(
                exampleVideo1,
                exampleVideo2,
                exampleVideo3,
                exampleVideo4,
                exampleVideo5,
                exampleVideo6
        );

        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try {
            List<Video> Videos = cultureMediaService.findAllVideos();
            assertEquals(6, Videos.size());
        } catch (VideoNotFoundException e) {
            assert(false);
        }
    }

    @Test
    void when_find_forTitle_an_VideoNotFoundExeption_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find("any Thing");
        });
    }

    @Test
    void when_find_forDuration_an_VideoNotFoundExeption_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> {
            cultureMediaService.find(0.0, 0.5);
        });
    }

    @Test
    void when_find_forTitle_should_be_returned_succesfully() {
        List<Video> videos = List.of(
                exampleVideo1,
                exampleVideo2,
                exampleVideo3,
                exampleVideo4,
                exampleVideo5,
                exampleVideo6
        );

        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try {
            List<Video> Videos = cultureMediaService.find("Hilmer");
            assertEquals(1, Videos.size());
            assertEquals(exampleVideo6, Videos.get(0));
        } catch (VideoNotFoundException e) {
            assert(false);
        }
    }

    @Test
    void when_find_forDuration_should_be_returned_succesfully() {
        List<Video> videos = List.of(
                exampleVideo1,
                exampleVideo2,
                exampleVideo3,
                exampleVideo4,
                exampleVideo5,
                exampleVideo6
        );

        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try {
            List<Video> Videos = cultureMediaService.find(0.0, 5.5);
            assertEquals(5, Videos.size());
        } catch (VideoNotFoundException e) {
            assert(false);
        }
    }
}
