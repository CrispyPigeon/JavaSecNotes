package by.crispypigeon.secnotes._views._controls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class LoadingProgressBar extends ProgressBar {

    public LoadingProgressBar(Context context) {
        super(context);
    }

    public LoadingProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadingProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void startLoadingCommand(){
        //TODO AsyncLoading method
    }
}
