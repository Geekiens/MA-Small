package bookReviewer.entity.book;

import java.util.Arrays;

public class BookUserDetails {
    private String[] keywords;
    private String content;

    public BookUserDetails(String[] keywords, String content) {
        this.keywords = keywords;
        this.content = content;
    }

    public BookUserDetails() {
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BookUserDetails{" +
                "keywords=" + Arrays.toString(keywords) +
                ", content='" + content + '\'' +
                '}';
    }
}
