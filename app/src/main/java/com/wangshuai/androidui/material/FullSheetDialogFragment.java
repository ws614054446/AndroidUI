package com.wangshuai.androidui.material;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wangshuai.androidui.R;

import java.util.ArrayList;

/**
 * 全屏显示
 */
public class FullSheetDialogFragment extends BottomSheetDialogFragment{

    private BottomSheetBehavior mBehavior;
    private ArrayList<String> list = new ArrayList<>();

    public FullSheetDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.content_bottom_sheet_fragment_dialog, null);
        initViews(view);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());

        return dialog;
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

    @Override
    public void onStart()
    {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//全屏展开
    }

}
