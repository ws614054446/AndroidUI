### Material Design系列之Palette详解

[官方文档链接(自备梯子)](https://developer.android.com/reference/android/support/v7/graphics/package-summary)

Palette:可以在一张图片里面分析出一些色彩特性：主色调、鲜艳的颜色、柔和颜色等

palette可以从图像中提取的颜色：

- Vibrant (鲜艳的)
- Vibrant Dark (鲜艳的暗)
- Vibrant Light (鲜艳的亮)
- Muted (柔和的)
- Muted Dark (柔和的暗)
- Muted Light (柔和的亮)

共有两种创建对象的方式：
```
    // Synchronous 同步
    Palette p = Palette.from(bitmap).generate();

    // Asynchronous 异步 推荐使用  可能分析的图片会比较大或者颜色分布比较复杂，会耗时比较久，防止卡死主线程
    Palette.from(bitmap).generate(new PaletteAsyncListener() {
          public void onGenerated(Palette p) {
                // Use generated instance
          }
    });
```
同时也支持Palette.Builder方式构建

![](https://github.com/ws614054446/AndroidUI/blob/master/imgs/palette.png)

#### Palette.Swatch
从palette可以获取到google推荐的颜色样本

```
palette.getDarkMutedSwatch();
palette.getDarkVibrantSwatch();
...
```

对应的方法有：
- public int getBodyTextColor():返回一个合适的颜色，用于显示在这个调色板上的任何“正文”文本。这种颜色保证有足够的对比度。
- public float[] getHsl():返回这个样本的HSL值. hsv[0]为色相 [0 .. 360); hsv[1]为饱和度 [0...1]; hsv[2]为明度 [0...1]
- public int getPopulation():这个样本所代表的像素数
- public int getRgb():这个样本的RGB颜色值
- public int getTitleTextColor():返回一个适当的颜色用于任何“标题”文本，显示在这个调色板上。这种颜色保证有足够的对比度。

#### Palette.Builder
构造方法：
```
Palette.Builder(Bitmap bitmap)
Palette.Builder(List<Palette.Swatch> swatches)//通常只用于测试
```

对应的其他方法：

- addFilter(Palette.Filter filter)：添加一个过滤器，以便能够对所得到的调色板中允许的颜色进行细粒度控制。
- clearFilters()：清除所有添加的过滤器。这包括任何由调色板自动添加的默认过滤器。
- generate()：同步生成并返回调色板。
- generate (Palette.PaletteAsyncListener listener)：异步生成并返回调色板
- maximumColorCount (int colors)：当使用位图作为源时，设置量化步骤中使用的最大颜色数。
良好的值取决于源图像类型。对于风景来说，一般在10-16的范围内。对于大部分由人脸构成的图像，这个值应该增加到24或以上。
- resizeBitmapSize (int maxDimension)：调整位图大小，对图片进行缩放。
这个值对处理时间有很大的影响。调整大小的图像越大，生成调色板所需的时间就越长。图像越小，结果图像中的细节越少，因此颜色选择的精度越低。
该方法在API级别24.1.0中被剔除，推荐使用resizeBitmapArea(int area)
- resizeBitmapArea(int area)：作用同上一个方法。
- setRegion(int left, int top, int right, int bottom):在计算调色板时设置选取的位图区域
- clearRegion()
- addTarget(Target target)：在调色板中添加一个颜色配置文件配置文件
- clearTargets()

#### Target

在调色板的自定义颜色的类。通过Target.Builder生成实例。

To use the target, use the addTarget(Target) API when building a Palette。

- getLightnessWeight()：返回目标区域颜色的明亮度所占的权重。
- getMaximumLightness()：最大亮度值
- getMinimumLightness()：最小亮度值
- getMaximumSaturation()：最大饱和度
- getMinimumSaturation()：最小饱和度
- getPopulationWeight()：Returns the weight of importance that this target places on a color's population within the image.(无法准确翻译，见谅)
- getSaturationWeight()：获取饱和度所占的比重
- getTargetLightness()：获取目标明度
- getTargetSaturation()：获取目标饱和度
- isExclusive()：Returns whether any color selected for this target is exclusive for this target only.

#### Target.Builder
对应上面都有set方法

[Github示例代码,欢迎star](https://github.com/ws614054446/AndroidUI)




