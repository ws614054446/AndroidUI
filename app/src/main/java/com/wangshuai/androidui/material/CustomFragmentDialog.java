package com.wangshuai.androidui.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangshuai.androidui.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/20.
 */

public class CustomFragmentDialog extends BottomSheetDialogFragment {
    private ArrayList<String> list = new ArrayList<>();

    public CustomFragmentDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_bottom_sheet_fragment_dialog,container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        for (int i = 0;i < 100;i++){
            list.add("条目"+i);
        }
        RecyclerView recyclerView = view.findViewById(R.id.rv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }

}
