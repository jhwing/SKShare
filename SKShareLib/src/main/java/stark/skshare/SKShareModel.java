package stark.skshare;

/**
 * Created by jihongwen on 16/8/9.
 */

public abstract class SKShareModel {

    /**
     * 公共分享字段
     */
    public String title;
    public String content;
    public String appName;
    public String url;

    protected SKShareModel(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.appName = builder.appName;
        this.url = builder.url;
    }

    public abstract static class Builder<S extends SKShareModel, B extends SKShareModelBuilder> implements SKShareModelBuilder<S, B> {
        String title;
        String content;
        String url;
        String appName;

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

        public S build(SKShareModel s) {
            this.url = s.url;
            this.title = s.title;
            this.content = s.content;
            this.appName = s.appName;
            return build();
        }

        public abstract S build();
    }
}
