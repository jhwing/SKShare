package stark.skshare.tencent;

import android.support.v7.app.AlertDialog;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 2017/3/1.
 */

public class SKTencentContent extends SKShareModel {

    public String imageUrl;

    protected SKTencentContent(Builder builder) {
        super(builder);
        this.imageUrl = builder.imageUrl;
    }

    public static class Builder extends SKShareModel.Builder<SKTencentContent, Builder> {

        String imageUrl;

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        @Override
        public SKTencentContent build() {
            return new SKTencentContent(this);
        }
    }
}
