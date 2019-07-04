package com.example.hellomedia.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.hellomedia.R;


public class VideoFragment extends Fragment {



    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewVideo = inflater.inflate(R.layout.fragment_video, container, false);

        // Referenciamos el objeto de nuestra vista
        VideoView mVideoView = (VideoView) viewVideo.findViewById(R.id.vvVideo);

        // Cogemos la referencia hacia nuestro vídeo
        String uriPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video;

        // Parseamos el String
        Uri uri = Uri.parse(uriPath);

        // Asignamos el archivo de vídeo al nuestro componente VideoView
        mVideoView.setVideoURI(uri);

        // Creamos el componente para poder manejar el video
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(mVideoView);

        // Asignamos el controlados de vídeo a nuestro vídeo
        mVideoView.setMediaController(mediaController);
        mediaController.setAnchorView(viewVideo);

        mVideoView.start();


        return viewVideo;
    }

}
