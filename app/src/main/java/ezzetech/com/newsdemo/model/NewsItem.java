package ezzetech.com.newsdemo.model;

/**
 * Created by ETL-1605 on 5/27/2017.
 */

public class NewsItem {
//    private String newsID;
    private String newsTitle;
    private String newsDetails;
    private String mediaType;
    private String sourceLink;
    private String providerName;
    private String imageofNews;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDetails() {
        return newsDetails;
    }

    public void setNewsDetails(String newsDetails) {
        this.newsDetails = newsDetails;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getImageofNews() {
        return imageofNews;
    }

    public void setImageofNews(String imageofNews) {
        this.imageofNews = imageofNews;
    }
}
