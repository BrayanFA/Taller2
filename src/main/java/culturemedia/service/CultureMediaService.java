package culturemedia.service;

public interface CultureMediaService {

    List<Video> findAll();
    Video save(Video save);
    Player save(Player save);
}