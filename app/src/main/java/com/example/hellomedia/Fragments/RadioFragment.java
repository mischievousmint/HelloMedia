package com.example.hellomedia.Fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.hellomedia.MainActivity;
import com.example.hellomedia.R;

import java.io.IOException;

import static android.support.constraint.Constraints.TAG;


public class RadioFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static MediaPlayer mp;
    private boolean isPaused = false;
    String radioPath = "http://17873.live.streamtheworld.com/CADENADIAL.mp3";

    public RadioFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RadioFragment newInstance(String param1, String param2) {
        RadioFragment fragment = new RadioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("RadioFragment","onCreate Setting Title");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Radio Online");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_radio, container, false);

        Button btnStart = (Button) v.findViewById(R.id.btnPlay);
        Button btnPause = (Button) v.findViewById(R.id.btnPause);

        Button btnRadio1 = (Button) v.findViewById(R.id.btnRadio1);
        Button btnRadio2 = (Button) v.findViewById(R.id.btnRadio2);
        Button btnRadio3 = (Button) v.findViewById(R.id.btnRadio3);

        try {
            onClick(v);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    launchRadio(radioPath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()){
                    mp.pause();
                    isPaused = true;
                }
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public void onClick(View v) throws IOException {

        switch (v.getId()) {

            case R.id.btnRadio1:
                radioPath = "http://17873.live.streamtheworld.com/CADENADIAL.mp3";
                launchRadio(radioPath);
                break;

            case R.id.btnRadio2:
                radioPath = "http://kissfm.kissfmradio.cires21.com/kissfm.mp3";
                launchRadio(radioPath);
                break;

            case R.id.btnRadio3:
                radioPath = "http://212.83.138.48:8422/stream";
                launchRadio(radioPath);
                break;

            default:
                break;
        }

    }

    public void launchRadio (String path) throws IOException {
        if (mp == null)
            mp = new MediaPlayer();
        if (isPaused){
            mp.start();
            isPaused = false;
        }
        else {
            mp.reset();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(path);
            mp.prepareAsync();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                @Override
                public void onPrepared(MediaPlayer mPlayer){  //Lanza cuando está preparado
                    Log.e("onPrepared","onPrepared");
                    mp.start();
                }
            });
            mp.setOnErrorListener(new MediaPlayer.OnErrorListener(){
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.e("LaunchRadio","onErrorListener");
                    return true;
                }
            });
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()+ " must implement OnFragmentInteractionListener");
        }
        Log.e("RadioFragment","llamando a setTitleToolbar");
        //mListener.setTitleToolbar("Probando 22 11");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause(){
        super.onPause();
        if (mp != null)
            mp.pause();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void setTitleToolbar(String title);
    }
}
