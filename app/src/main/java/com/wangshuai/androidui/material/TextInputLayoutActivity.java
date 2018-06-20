package com.wangshuai.androidui.material;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.wangshuai.androidui.R;

public class TextInputLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        TextInputEditText et_passward = (TextInputEditText) findViewById(R.id.et_passward);
        et_passward.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > textInputLayout.getCounterMaxLength()){
                    textInputLayout.setError("输入超过限制");
                }
            }
        });


    }
}
