package fm.synq.synquploader.videos;

/**
 * Created by kjartanvestvik on 07.11.2016.
 */

public class Video {

    private long fileId;
    private String videoFilePath;
    private String videoThumbnailPath;

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
}
