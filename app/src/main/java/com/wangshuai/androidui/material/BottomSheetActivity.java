package com.wangshuai.androidui.material;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangshuai.androidui.R;

import java.util.zip.Inflater;

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btnExpand;
    private AppCompatButton btnCollapsed;
    private AppCompatButton btnHide;
    private AppCompatButton btnBottomsheetDialog;
    private AppCompatButton btnBottomsheetDialogFragment;
    private LinearLayout llContentBottomSheet;

    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        initViews();
    }

    private void initViews() {
        btnExpand = (AppCompatButton) findViewById(R.id.btn_expand);
        btnCollapsed = (AppCompatButton) findViewById(R.id.btn_collapsed);
        btnHide = (AppCompatButton) findViewById(R.id.btn_hide);
        btnBottomsheetDialog = (AppCompatButton) findViewById(R.id.btn_bottomsheet_dialog);
        btnBottomsheetDialogFragment = (AppCompatButton) findViewById(R.id.btn_bottomsheet_dialog_fragment);
        llContentBottomSheet = (LinearLayout) findViewById(R.id.ll_content_bottom_sheet);
        btnExpand.setOnClickListener(this);
        btnCollapsed.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        btnBottomsheetDialogFragment.setOnClickListener(this);
        btnBottomsheetDialog.setOnClickListener(this);

        bottomSheetBehavior = BottomSheetBehavior.from(llContentBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_expand://展开
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.btn_collapsed://折叠
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.btn_hide://隐藏
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.btn_bottomsheet_dialog://对话框
                if (dialog == null){
                    dialog = new BottomSheetDialog(this);
                }
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(true);
                View view =LayoutInflater.from(BottomSheetActivity.this).inflate(R.layout.content_bottom_sheet_dialog,null);
                TextView tvWechat = view.findViewById(R.id.tv_wechat);
                tvWechat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(BottomSheetActivity.this,"微信",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                dialog.show();
                break;
            case R.id.btn_bottomsheet_dialog_fragment://fragment
//                CustomFragmentDialog fragmentDialog = new CustomFragmentDialog();
//                fragmentDialog.show(getSupportFragmentManager(),"CustomFragmentDialog");

                FullSheetDialogFragment fullSheetDialogFragment = new FullSheetDialogFragment();
                fullSheetDialogFragment.show(getSupportFragmentManager(),"FullSheetDialogFragment");
                break;
        }
    }
}
