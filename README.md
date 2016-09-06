# SKShare
社会化分享sdk，支持微信分享，微博分享，qq分享

# 效果图
![主界面](https://github.com/jhwing/SKShare/blob/master/Screenshots/Screenshot_20160906-084047.png) ![微信分享](https://github.com/jhwing/SKShare/blob/master/Screenshots/Screenshot_20160906-084056.png) ![分享对话](https://github.com/jhwing/SKShare/blob/master/Screenshots/Screenshot_20160906-090647.png) 

# 使用方式
1 配置文件
assets目录下share.properties
```
# 微信
wx_app_key=wxd6e53b1e821b0ec5
wx_app_secret=b01f9e4a8edca4890411bfe598ef57f7
# 微博
weibo_app_key=575596140
weibo_app_secret=b01f9e4a8edca4890411bfe598ef57f7
# qq
qq_app_key=1105530584
qq_app_secret=W27jbzDfc4Bmzh6q
```
2 代码集成
微信
```
public void textShare(View view) {
        share.shareText(this, new WeChatSKShareContent.Builder()
                .setContent("微信分享测试")
                .build(), true);

    }

    public void imageShare(View view) {
        share.shareImage(this, new WeChatSKShareContent.Builder()
                .setContent("微信图片分享测试")
                .setImageBitmap(bitmap)
                .build(), true);

    }

    public void emojiShare(View view) {
        share.shareEmoji(this, new WeChatSKShareContent.Builder()
                .setContent("微信表情分享测试")
                .setImageBitmap(bitmap)
                .build(), true);
    }

    public void musicShare(View view) {
        share.shareMusic(this, new WeChatSKShareContent.Builder()
                .setMusicUrl("http://music.163.com/#/song?id=28768109")
                .build(), true);
    }

    public void webpageShare(View view) {
        share.shareWebPage(this, new WeChatSKShareContent.Builder()
                .setImageBitmap(bitmap)
                .setUrl("http://www.baidu.com")
                .build(), false);
    }
```
微博
```
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
```
qq
```
public void imageShare(View view) {
        SKQQShare share = SKShare.create(SKQQShare.class, this);
        share.shareImageToQQ(this, new SKShareContent.Builder()
                .setUrl("http://m.budejie.com")
                .setImageUrl("http://m.budejie.com")
                .setTitle("qq测试分享title")
                .setContent("qq测试分享content")
                .build(), new DefaultShareCallback());
    }

```
