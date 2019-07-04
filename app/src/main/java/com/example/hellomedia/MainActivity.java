package com.example.hellomedia;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hellomedia.Fragments.MP3Fragment;
import com.example.hellomedia.Fragments.RadioFragment;
import com.example.hellomedia.Fragments.VideoFragment;
import com.example.hellomedia.Fragments.VideoURLFragment;

public class MainActivity extends AppCompatActivity implements VideoFragment.OnFragmentInteractionListener, MP3Fragment.OnFragmentInteractionListener,VideoURLFragment.OnFragmentInteractionListener,RadioFragment.OnFragmentInteractionListener {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navVideoURL:
                    launchFragment(new VideoURLFragment());
                    return true;
                case R.id.navMp3:
                    launchFragment(new MP3Fragment());
                    return true;
                case R.id.navVideo:
                    launchFragment(new VideoFragment());
                    return true;
                case R.id.navRadio:
                    launchFragment(new RadioFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        launchFragment(new VideoURLFragment());

        volume();

    }

    private void launchFragment(Fragment newfragment) {
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.flContainer,newfragment);
        FT.addToBackStack(null);
        FT.commit();
    }

    private void volume() {
        AudioManager audio;
        audio = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
        audio.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
        // setVolumeControlStream(AudioManager.STREAM_MUSIC);

        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float percent = 0.7f;
        int seventyVolume = (int) (maxVolume*percent);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
