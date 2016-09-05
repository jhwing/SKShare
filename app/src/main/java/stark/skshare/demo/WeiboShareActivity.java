package stark.skshare.demo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import stark.skshare.ImageCompress;
import stark.skshare.SKShare;
import stark.skshare.utlis.SKShareUtil;
import stark.skshare.weibo.WeiboSKShareContent;
import stark.skshare.weibo.WeiboShare;

/**
 * Created by jihongwen on 16/8/25.
 */

public class WeiboShareActivity extends BaseToolbarActivity {

    ImageView imageView;

    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_share);
        imageView = (ImageView) findViewById(R.id.imageView);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        bitmap = bitmapDrawable.getBitmap();
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("weibo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void textShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareText(new WeiboSKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);

    }

    /**
     * @param view
     */
    public void imageShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareImage(new WeiboSKShareContent.Builder()
                .setContent("新浪分享测试")
                .setImageBitmap(bitmap)
                .setTitle("新浪分享测试 title")
                .build(), this);
    }

    /**
     * @param view
     */
    public void webPageShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareWebpage(new WeiboSKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .setUrl("http://m.budejie.com")
                .setImageBitmap(bitmap)
                .build(), this);
    }

    /**
     * @param view
     */
    public void musicShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareMusic(new WeiboSKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }

    /**
     * @param view
     */
    public void videoShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareVideo(new WeiboSKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }

    /**
     * @param view
     */
    public void voiceShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareVoice(new WeiboSKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }
}
