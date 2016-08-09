package stark.skshare.demo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stark.skshare.Platform;

/**
 * Created by jihongwen on 16/8/4.
 */

public class ShareLayout extends RelativeLayout {

    public interface IShareView {
        void onShareItemClick(int platform);
    }

    IShareView iShareView;

    RecyclerView shareList;
    ShareAdapter mAdapter;
    LayoutInflater inflater;

    List<ShareItem> shareItems = new ArrayList<>();

    public ShareLayout(Context context) {
        super(context);
    }

    public ShareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        inflater = LayoutInflater.from(getContext());
        View.inflate(getContext(), R.layout.view_share_layout, this);
        shareList = (RecyclerView) findViewById(R.id.shareList);
    }

    public void bindView(IShareView iShareView) {
        this.iShareView = iShareView;
        shareList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new ShareAdapter();
        shareItems.add(new ShareItem(R.drawable.share_weixin_selector, "微信"));
        shareItems.add(new ShareItem(R.drawable.share_qq_selector, "QQ"));
        shareItems.add(new ShareItem(R.drawable.share_pyp_selector, "朋友圈"));
        shareItems.add(new ShareItem(R.drawable.share_qzone_selector, "Qzone"));
        shareItems.add(new ShareItem(R.drawable.share_weibo_selector, "微博"));
        shareList.setAdapter(mAdapter);
    }

    private void onItemClick(int res) {
        switch (res) {
            case R.drawable.share_weixin_selector:
                iShareView.onShareItemClick(Platform.WECHAT);
                break;
            case R.drawable.share_qq_selector:
                iShareView.onShareItemClick(Platform.QQ);
                break;
            case R.drawable.share_pyp_selector:
                iShareView.onShareItemClick(Platform.PYP);
                break;
            case R.drawable.share_qzone_selector:
                iShareView.onShareItemClick(Platform.QZONE);
                break;
            case R.drawable.share_weibo_selector:
                iShareView.onShareItemClick(Platform.WEIBO);
                break;
        }
    }

    private class ShareItem {
        public int sharePlatformIcon;
        public String sharePlatformName;

        ShareItem(int sharePlatformIcon, String sharePlatformName) {
            this.sharePlatformIcon = sharePlatformIcon;
            this.sharePlatformName = sharePlatformName;
        }
    }

    private class ShareAdapter extends RecyclerView.Adapter<ShareViewHolder> {

        @Override
        public ShareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ShareViewHolder(inflater.inflate(R.layout.view_share_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ShareViewHolder holder, int position) {
            holder.sharePlatformName.setText(shareItems.get(position).sharePlatformName);
            holder.sharePlatformIcon.setImageResource(shareItems.get(position).sharePlatformIcon);
        }

        @Override
        public int getItemCount() {
            return shareItems.size();
        }
    }

    private class ShareViewHolder extends RecyclerView.ViewHolder {

        public ImageView sharePlatformIcon;
        public TextView sharePlatformName;

        public ShareViewHolder(View itemView) {
            super(itemView);
            sharePlatformIcon = (ImageView) itemView.findViewById(R.id.sharePlatformIcon);
            sharePlatformName = (TextView) itemView.findViewById(R.id.sharePlatformName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick(shareItems.get(getLayoutPosition()).sharePlatformIcon);
                }
            });
        }
    }
}
