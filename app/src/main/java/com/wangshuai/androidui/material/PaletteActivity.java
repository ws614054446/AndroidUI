package com.wangshuai.androidui.material;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangshuai.androidui.R;

/**
 * 调色板示例
 */
public class PaletteActivity extends AppCompatActivity {
    private ImageView ivImg;
    private TextView tvVibrant;
    private TextView tvVibrantDark;
    private TextView tvVibrantLight;
    private TextView tvMuted;
    private TextView tvMutedDark;
    private TextView tvMutedLight;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        initViews();
    }

    private void initViews() {
        ivImg = (ImageView) findViewById(R.id.iv_img);
        tvVibrant = (TextView) findViewById(R.id.tv_vibrant);
        tvVibrantDark = (TextView) findViewById(R.id.tv_vibrant_dark);
        tvVibrantLight = (TextView) findViewById(R.id.tv_vibrant_light);
        tvMuted = (TextView) findViewById(R.id.tv_muted);
        tvMutedDark = (TextView) findViewById(R.id.tv_muted_dark);
        tvMutedLight = (TextView) findViewById(R.id.tv_muted_light);
        tvTitle = (TextView) findViewById(R.id.tv_trans_title);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivImg.getDrawable();
        final Bitmap bitmap = bitmapDrawable.getBitmap();

        //同步生成
//        Palette palette = Palette.from(bitmap).generate();

        //异步生成 推荐使用
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(Color.RED);//如果分析不出来，则则返回默认颜色Color.BLUE
                int vibrantDarkColor = palette.getDarkVibrantColor(Color.RED);
                int vibrantLightColor = palette.getLightVibrantColor(Color.RED);
                int mutedColor = palette.getMutedColor(Color.RED);
                int mutedDarkColor = palette.getDarkMutedColor(Color.RED);
                int mutedLightColor = palette.getLightMutedColor(Color.RED);

                tvVibrant.setBackgroundColor(vibrantColor);
                tvVibrantDark.setBackgroundColor(vibrantDarkColor);
                tvVibrantLight.setBackgroundColor(vibrantLightColor);
                tvMuted.setBackgroundColor(mutedColor);
                tvMutedDark.setBackgroundColor(mutedDarkColor);
                tvMutedLight.setBackgroundColor(mutedLightColor);

                Palette.Swatch swatch = palette.getVibrantSwatch();
                if (swatch != null){
                    int bodyTextColor = swatch.getBodyTextColor();
                    int titleTextColor = swatch.getTitleTextColor();
                    int rgb = swatch.getRgb();
                    tvTitle.setTextColor(titleTextColor);
                    tvTitle.setBackgroundColor(getTransColor(0.5f,rgb));
                }
                palette.getDarkVibrantSwatch();

            }
        });

//        Target.Builder builder = new Target.Builder();
//        builder.setExclusive(true);
//        builder.setTargetLightness(0.5f);
//
//        Palette.from(bitmap).addTarget(builder.build()).resizeBitmapArea(2).maximumColorCount(8).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int vibrantColor = palette.getVibrantColor(Color.RED);//如果分析不出来，则则返回默认颜色Color.BLUE
//                int vibrantDarkColor = palette.getDarkVibrantColor(Color.RED);
//                int vibrantLightColor = palette.getLightVibrantColor(Color.RED);
//                int mutedColor = palette.getMutedColor(Color.RED);
//                int mutedDarkColor = palette.getDarkMutedColor(Color.RED);
//                int mutedLightColor = palette.getLightMutedColor(Color.RED);
//
//                tvVibrant.setBackgroundColor(vibrantColor);
//                tvVibrantDark.setBackgroundColor(vibrantDarkColor);
//                tvVibrantLight.setBackgroundColor(vibrantLightColor);
//                tvMuted.setBackgroundColor(mutedColor);
//                tvMutedDark.setBackgroundColor(mutedDarkColor);
//                tvMutedLight.setBackgroundColor(mutedLightColor);
//
//                Palette.Swatch swatch = palette.getVibrantSwatch();
//                if (swatch != null){
//                    int bodyTextColor = swatch.getBodyTextColor();
//                    int titleTextColor = swatch.getTitleTextColor();
//                    int rgb = swatch.getRgb();
//                    tvTitle.setTextColor(titleTextColor);
//                    tvTitle.setBackgroundColor(getTransColor(0.5f,rgb));
//                }
//                palette.getDarkVibrantSwatch();
//            }
//        });
    }

    /**
     * rgb为32位二进制数
     * 获取透明颜色值
     * @param percent
     * @param rgb
     */
    private int getTransColor(float percent,int rgb){
        int blue = rgb & 0xFF;//Color.blue(rgb)
        int green = (rgb >> 8) & 0xFF;//Color.green(rgb)
        int red = (rgb >> 16) & 0xFF;//Color.red(rgb)
        int alpha = rgb >>> 24;//Color.alpha(rgb)

        alpha = Math.round(alpha*percent);

        return Color.argb(alpha,red,green,blue);
    }
}
