package stark.skshare.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import stark.skshare.SKShare;
import stark.skshare.SKShareContent;
import stark.skshare.weibo.WeiboShare;

/**
 * Created by jihongwen on 16/8/25.
 */

public class WeiboShareActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_share);
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("weibo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void textShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareText(new SKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);

    }

    /**
     * @param view
     */
    public void imageShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareImage(new SKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }

    /**
     * @param view
     */
    public void webPageShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareWebpage(new SKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }

    /**
     * @param view
     */
    public void musicShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareMusic(new SKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }

    /**
     * @param view
     */
    public void videoShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareVideo(new SKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }

    /**
     * @param view
     */
    public void voiceShare(View view) {
        WeiboShare share = SKShare.create(WeiboShare.class, this);
        share.shareVoice(new SKShareContent.Builder()
                .setContent("新浪分享测试")
                .setTitle("新浪分享测试 title")
                .build(), this);
    }
}
