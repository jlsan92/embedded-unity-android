package com.example.reconshot.app;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.unity3d.player.*;

public class UnityActivity extends AppCompatActivity {

    private static UnityPlayer mUnityPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.unityContainer, new PlaceholderFragment())
                    .commit();
        }

        mUnityPlayer = new UnityPlayer(this);
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        mUnityPlayer.init(glesMode, false);
    }

    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    protected void onDestroy ()
    {
        super.onDestroy();
        mUnityPlayer.quit();
    }

    protected void onPause()
    {
        super.onPause();
        mUnityPlayer.pause();
    }

    protected void onResume()
    {
        super.onResume();
        mUnityPlayer.resume();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            FrameLayout layout = (FrameLayout) rootView.findViewById( R.id.framelayout );
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT);
            layout.addView(mUnityPlayer, 0, lp);

            mUnityPlayer.resume();

            return rootView;
        }
    }
}
