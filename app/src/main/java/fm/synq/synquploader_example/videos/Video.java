package fm.synq.synquploader_example.videos;

/**
 * Created by kjartanvestvik on 07.11.2016.
 */

public class Video {

    private long fileId;
    private String videoFilePath;
    private String videoThumbnailPath;
    // Synq API parameters:
    private String apiVideoId;


    public Video(long fileId, String filePath) {
        this.fileId = fileId;
        this.videoFilePath = filePath;
    }


    public long getFileId() {
        return this.fileId;
    }

    public String getVideoFilePath() {
        return this.videoFilePath;
    }

    public String getVideoThumbnailPath() {
        return this.videoThumbnailPath;
    }

    public void setVideoThumbnailPath(String path) {
        this.videoThumbnailPath = path;
    }

    public String getApiVideoId() {
        return this.apiVideoId;
    }

    public void setApiVideoId(String videoID) {
        this.apiVideoId = videoID;
    }
}
