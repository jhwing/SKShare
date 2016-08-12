package stark.skshare;

/**
 * Created by jihongwen on 16/6/24.
 */
public class SKShareContent extends SKShareModel {


    public SKShareContent(Builder builder) {
        super(builder);
    }

    @Override
    public String toString() {
        return title + ":" + url;
    }

    public static final class Builder extends SKShareModel.Builder<SKShareContent, Builder> {

        @Override
        public SKShareContent build() {
            return new SKShareContent(this);
        }
    }
}
