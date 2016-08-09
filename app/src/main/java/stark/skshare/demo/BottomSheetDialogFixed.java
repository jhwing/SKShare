package stark.skshare.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

/**
 * Created by jihongwen on 16/7/30.
 */

public class BottomSheetDialogFixed extends BottomSheetDialog {

    BottomSheetBehavior behavior;

    public BottomSheetDialogFixed(@NonNull Context context) {
        super(context);
    }

    public BottomSheetDialogFixed(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected BottomSheetDialogFixed(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void show() {
        if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        super.show();
    }

    public void setBehavior(BottomSheetBehavior<View> behavior) {
        this.behavior = behavior;
    }
}
