package com.wangshuai.androidui.material;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangshuai.androidui.R;

public class TabLayoutFragment extends Fragment {

    private static final String PARAM1 = "PARAM1";
    private TextView tvContent;
    private String text = null;

    public TabLayoutFragment() {
    }

    public static TabLayoutFragment newInstance(String text) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        args.putString(PARAM1, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(PARAM1);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tvContent =  view.findViewById(R.id.tv_content);
        tvContent.setText(text);
    }

}
