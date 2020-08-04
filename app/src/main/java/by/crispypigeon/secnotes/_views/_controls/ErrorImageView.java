package by.crispypigeon.secnotes._views._controls;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import by.crispypigeon.secnotes.R;

public class ErrorImageView extends androidx.appcompat.widget.AppCompatImageView {

    public ErrorImageView(Context context) {
        super(context);
    }

    public ErrorImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrorImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showErrorAnimation(Activity activity)
    {
        Animation shake = AnimationUtils.loadAnimation(activity, R.anim.shake);
        startAnimation(shake);
    }
}
