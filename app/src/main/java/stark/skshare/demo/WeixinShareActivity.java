package stark.skshare.demo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import stark.skshare.SKShare;
import stark.skshare.wechat.WeChatSKShareContent;
import stark.skshare.wechat.WeChatShare;
import stark.skshare.weibo.WeiboShare;

/**
 * Created by jihongwen on 16/8/25.
 */

public class WeixinShareActivity extends BaseToolbarActivity {

    WeChatShare share;

    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_share);
        share = SKShare.create(WeChatShare.class, this);
        ImageView shareImg = (ImageView) findViewById(R.id.shareImg);
        bitmap = ((BitmapDrawable) shareImg.getDrawable()).getBitmap();
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("weixin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void textShare(View view) {
        share.shareText(new WeChatSKShareContent.Builder()
                .setContent("微信分享测试")
                .build(), true);

    }

    public void imageShare(View view) {
        share.shareImage(this, new WeChatSKShareContent.Builder()
                .setContent("微信图片分享测试")
                .setThumbImage(bitmap)
                .build(), true);

    }

    public void emojiShare(View view) {
        share.shareEmoji(this, new WeChatSKShareContent.Builder()
                .setContent("微信表情分享测试")
                .setThumbImage(bitmap)
                .build(), true);
    }

    public void musicShare(View view) {
        share.shareMusic(this, new WeChatSKShareContent.Builder()
                .setMusicUrl("")
                .build(), false);
    }

    public void webpageShare(View view) {
        share.shareWebPage(this, new WeChatSKShareContent.Builder().build(), false);
    }

    public void videoShare(View view) {
        share.shareEmoji(this, new WeChatSKShareContent.Builder().build(), false);
    }


    public void fileShare(View view) {
    }
}
