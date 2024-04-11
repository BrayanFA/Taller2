package culturemedia.model;

import java.time.LocalDataTime;
public record View(String userFullName, LocalDataTime startPlayingTime, Integer age, Video video) {
}