package com.example.hellomedia.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.hellomedia.MyPager;
import com.example.hellomedia.R;
import me.relex.circleindicator.CircleIndicator;


public class GalleryFragment extends Fragment implements MyPager.OnInteractionListener {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private MyPager myPager;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGallery = inflater.inflate(R.layout.fragment_gallery, container, false);
        viewPager = viewGallery.findViewById(R.id.view_pager);
        circleIndicator = viewGallery.findViewById(R.id.circle);



        myPager = new MyPager(getActivity(), this);
        viewPager.setAdapter(myPager);
        circleIndicator.setViewPager(viewPager);
        

        return viewGallery;
    }

    @Override
    public void onClickImage(int position) {
        Toast.makeText(getContext(), "position " + position, Toast.LENGTH_LONG).show();
    }
}
