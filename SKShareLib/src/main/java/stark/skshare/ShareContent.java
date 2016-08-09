package stark.skshare;

/**
 * Created by jihongwen on 16/6/24.
 */
public class ShareContent {

    public String title;
    public String content;
    public String appName;
    public String imageUrl;
    public String url;

    private ShareContent(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.imageUrl = builder.imageUrl;
        this.url = builder.url;
    }

    @Override
    public String toString() {
        return title + ":" + url;
    }

    public static class Builder {
        String title;
        String content;
        String imageUrl;
        String url;
        String appName;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAppName(String appName) {
            this.appName = appName;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setUrl(String url) {
            if ("".equals(url) || url == null) {
                url = "";
            }
            this.url = url;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ShareContent build() {
            return new ShareContent(this);
        }
    }
}
