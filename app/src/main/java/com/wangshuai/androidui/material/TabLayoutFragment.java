package com.wangshuai.androidui.material;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangshuai.androidui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabLayoutFragment extends Fragment {
    private TextView tvContent;

    public TabLayoutFragment() {
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
    }

}
