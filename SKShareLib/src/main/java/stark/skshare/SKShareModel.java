package stark.skshare;

/**
 * Created by jihongwen on 16/8/9.
 */

public abstract class SKShareModel {

    public String title;
    public String content;
    public String appName;
    public String imageUrl;
    public String url;
    public byte[] thumbData;

    protected SKShareModel(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.imageUrl = builder.imageUrl;
        this.appName = builder.appName;
        this.url = builder.url;
    }

    public abstract static class Builder<S extends SKShareModel, B extends SKShareModelBuilder> implements SKShareModelBuilder<S, B> {
        String title;
        String content;
        String imageUrl;
        String url;
        String appName;
        byte[] thumbData;

        public B setTitle(String title) {
            this.title = title;
            return (B) this;
        }

        public B setAppName(String appName) {
            this.appName = appName;
            return (B) this;
        }

        public B setContent(String content) {
            this.content = content;
            return (B) this;
        }

        public B setUrl(String url) {
            if ("".equals(url) || url == null) {
                url = "";
            }
            this.url = url;
            return (B) this;
        }

        public B setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return (B) this;
        }

        public B setThumbData(byte[] thumbData) {
            this.thumbData = thumbData;
            return (B) this;
        }

        public S build(SKShareModel s) {
            this.url = s.url;
            this.title = s.title;
            this.content = s.content;
            this.imageUrl = s.imageUrl;
            this.appName = s.appName;
            return build();
        }

        public abstract S build();
    }


}
