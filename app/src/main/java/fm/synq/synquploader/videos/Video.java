package fm.synq.synquploader.videos;

/**
 * Created by kjartanvestvik on 07.11.2016.
 */

public class Video {

    private String videoFilePath;
    private String videoThumbnailPath;

    public Video(String filePath, String thumbnailPath) {
        this.videoFilePath = filePath;
        this.videoThumbnailPath = thumbnailPath;
    }

    public String getVideoFilePath() {
        return this.videoFilePath;
    }

    public String getVideoThumbnailPath() {
        return this.videoThumbnailPath;
    }
}
