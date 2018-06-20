#### Material Design系列之TextInputLayout详解

[TextInputLayout官方API文档](https://developer.android.com/reference/android/support/design/widget/TextInputLayout)

[Material Design官方文档text-fields的介绍](https://material.io/design/components/text-fields.html)

##### 简介

Layout which wraps an EditText (or descendant) to show a floating label when the hint is hidden due to the user inputting text.

在用户输入的时候能将原来的提示文字浮动在控件上边。

  Also supports showing an error via setErrorEnabled(boolean) and setError(CharSequence), and a character counter via setCounterEnabled(boolean).

  Password visibility toggling is also supported via the setPasswordVisibilityToggleEnabled(boolean) API and related attribute. If enabled, a button is displayed to toggle between the password being displayed as plain-text or disguised, when your EditText is set to display a password.

> Note: When using the password toggle functionality, the 'end' compound drawable of the EditText will be overridden while the toggle is enabled. To ensure that any existing drawables are restored correctly, you should set those compound drawables relatively (start/end), opposed to absolutely (left/right).

> 注意：当使用密码显示开关功能的时候edittext的enddrawable将会被覆盖，为了保证一个已经存在的图片被正确加载，你应该设置那些图片位置在left/right，而不是en/start。

  The TextInputEditText class is provided to be used as a child of this layout. Using TextInputEditText allows TextInputLayout greater control over the visual aspects of any text input. An example usage is as so:

    ```
    <android.support.design.widget.TextInputLayout
               android:layout_width="match_parent"
               android:layout_height="wrap_content">

           <android.support.design.widget.TextInputEditText
                   android:layout_width="match_parent"
                   android:layout_height="wrap_content"
                   android:hint="@string/form_username"/>

       </android.support.design.widget.TextInputLayout>
    ```

> Note: The actual view hierarchy present under TextInputLayout is NOT guaranteed to match the view hierarchy as written in XML. As a result, calls to getParent() on children of the TextInputLayout -- such as an TextInputEditText -- may not return the TextInputLayout itself, but rather an intermediate View. If you need to access a View directly, set an android:id and use findViewById(int).

> 注意：当对TextInputEditText使用getparent()的时候得到的不会是TextInputLayout这个控件，而可能是一个中间的view。如果你需要使用TextInputLayout，请用findViewById来获取.


|属性|说明|
|:---|:--|
|app:hintEnabled|是否使用hint属性,默认true|
|app:hintAnimationEnabled|是否显示hint的动画,默认true|
|app:counterEnabled|是否显示输入长度计数器|
|app:counterMaxLength|设置计数器的最大值|
|app:counterTextAppearance|计数器的字体样式|
|app:counterOverflowTextAppearance|输入字符大于限定个数字符时计数器的字体样式|
|app:errorEnabled|是否显示错误信息|
|app:errorTextAppearance|错误信息的字体样式|
|app:passwordToggleEnabled|是否显示密码开关图片，需设置inputType为password|
|app:passwordToggleTint|设置密码开关图片颜色|
|app:passwordToggleDrawable|设置密码可见开关的图标。通常我们会在不同的情况下设定不同的图标，可通过自定义一个selector，根据state_checked属性来控制图标的切换|
|app:passwordToggleTintMode|控制密码可见开关图标的背景颜色混合模式|

##### 使用

> compile 'com.android.support:design:26.0.0-alpha1'

```
<android.support.design.widget.TextInputLayout
        android:id="@+id/textInputLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:errorEnabled="true"
        app:passwordToggleEnabled="true"
        app:passwordToggleTint="@color/colorPrimary"
        app:passwordToggleTintMode="src_in"
        app:counterEnabled="true"
        app:counterMaxLength="9"
        app:counterTextAppearance="@style/counterTextAppearanceStyle">
        <android.support.design.widget.TextInputEditText
            android:id="@+id/et_passward"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="请输入密码"
            android:inputType="textPassword"/>
    </android.support.design.widget.TextInputLayout>
```
```
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
```
注意：
- 在TextInputLayout下只能放一个TextInputEditText控件；
- 在TextInputLayout下最好使用TextInputEditText，如果使用EditText,会有一些bug。

[Github示例代码](https://github.com/ws614054446/AndroidUI)