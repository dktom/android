package com.dchan.mylayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;;

public class MyLayout extends LinearLayout {
	/**
	 * LinearLayout.LayoutParams，已经初始化为FILL_PARENT, FILL_PARENT
	 */
	public LinearLayout.LayoutParams layoutParamsFF = null;
	
	/**
	 * LinearLayout.LayoutParams，已经初始化为FILL_PARENT, WRAP_CONTENT
	 */
	public LinearLayout.LayoutParams layoutParamsFW = null;
	
	/**
	 * LinearLayout.LayoutParams，已经初始化为WRAP_CONTENT, FILL_PARENT
	 */
	public LinearLayout.LayoutParams layoutParamsWF = null;
	
	/**
	 * LinearLayout.LayoutParams，已经初始化为WRAP_CONTENT, WRAP_CONTENT
	 */
	public LinearLayout.LayoutParams layoutParamsWW = null;
	
	/** 标题布局. */
	protected LinearLayout titleTextLayout = null;
	
	/** 左侧的Logo图标View. */
	protected ImageView logoView = null;
	
	/** 左侧的Logo图标右边的分割线View. */
	protected ImageView logoLineView = null;
	
	/** 显示标题文字的View. */
	protected Button titleTextBtn = null;
	
	/** 右边的View，可以自定义显示什么. */
	protected LinearLayout rightLayout = null;
	
	/** 标题文本的对齐参数. */
	private LinearLayout.LayoutParams titleTextLayoutParams = null;
	
	/** 右边布局的的对齐参数. */
	private LinearLayout.LayoutParams rightViewLayoutParams = null;
	
	/** 标题栏布局ID. */
	public int mAbTitleBarID = 1;
	
	/** 全局的LayoutInflater对象，已经完成初始化. */
	public LayoutInflater mInflater;
	
	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public MyLayout(Context context) {
		super(context);
		init(context);
		
	}
	/*
	 * 初始化
	 */
	public void init(Context context){
		layoutParamsFF=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layoutParamsFW = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParamsWF = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		layoutParamsWW = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParamsWW.gravity = Gravity.CENTER_VERTICAL;
		
		titleTextLayoutParams=new LinearLayout.LayoutParams(layoutParamsWW);
		rightViewLayoutParams=new LinearLayout.LayoutParams(layoutParamsWW);
		
		titleTextLayout=new LinearLayout(context);
		titleTextLayout.setOrientation(LinearLayout.VERTICAL);
		titleTextLayout.setGravity(Gravity.CENTER_VERTICAL);
		titleTextLayout.setPadding(0, 0, 0, 0);
		
		titleTextBtn = new Button(context);
		titleTextBtn.setTextColor(Color.rgb(255, 255, 255));
		titleTextBtn.setTextSize(20);
		titleTextBtn.setPadding(5, 0, 5, 0);
		titleTextBtn.setGravity(Gravity.CENTER_VERTICAL);
		titleTextBtn.setBackgroundDrawable(null);
		titleTextBtn.setSingleLine();
		titleTextLayout.addView(titleTextBtn,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1));
		
		logoView = new ImageView(context);
		logoView.setVisibility(View.GONE);
		logoLineView = new ImageView(context);
		logoLineView.setVisibility(View.GONE);
		
		// 加右边的布局
		rightLayout = new LinearLayout(context);
		rightLayout.setOrientation(LinearLayout.HORIZONTAL);
		rightLayout.setGravity(Gravity.RIGHT);
		rightLayout.setPadding(0, 0, 0, 0);
		rightLayout.setHorizontalGravity(Gravity.RIGHT);
		rightLayout.setGravity(Gravity.CENTER_VERTICAL);
		rightLayout.setVisibility(View.GONE);
		
		this.addView(logoView,layoutParamsWW);
		this.addView(logoLineView,layoutParamsWW);
		this.addView(titleTextLayout,titleTextLayoutParams);
		this.addView(rightLayout,rightViewLayoutParams);
			
		
	}
	
	/**
	 * 描述：标题栏的背景图.
	 * @param res  背景图资源ID
	 */         
	public void setTitleBarBackground(int res) {
		this.setBackgroundResource(res);
	}
	
	/**
	 * 描述：设置标题背景.
	 * @param d  背景图
	 */
	public void setTitleBarBackgroundDrawable(Drawable d) {
		this.setBackground(d);
	}
	
	/**
	 * 描述：标题栏的背景图.
	 * @param color  背景颜色值
	 */
	/*public void setTitleBarBackgroundColor(int color) {
		this.setBackgroundColor(color);
	}*/
	
	/**
	 * 描述：获取标题文本的Button.
	 * @return the title Button view
	 */
	public Button getTitleTextButton() {
		return titleTextBtn;
	}
	
	/**
	 * 描述：获取标题Logo的View.
	 * @return the logo view
	 */
	public ImageView getLogoView() {
		return logoView;
	}
	
	/**
	 * 描述：设置标题背景.
	 *
	 * @param resId the new title text background resource
	 */
	public void setTitleTextBackgroundResource(int resId){
		titleTextBtn.setBackgroundResource(resId);
	}
	
	/**
     * 描述：设置标题文本.
     * @param text  文本
     */
	public void setTitleText(String text) {
		titleTextBtn.setText(text);
	}
	
	/**
     * 描述：设置标题文本.
     * @param resId  文本的资源ID
     */
	public void setTitleText(int resId) {
		titleTextBtn.setText(resId);
	}
	
	/**
     * 描述：设置Logo的背景资源.
     * @param resId  Logo资源ID
     */
	public void setLogo(int resId) {
		logoView.setVisibility(View.VISIBLE);
		logoView.setBackgroundResource(resId);
	}
	
	/**
     * 描述：设置Logo分隔线的背景资源.
     * @param resId  Logo资源ID
     */
	public void setLogoLine(int resId) {
		logoLineView.setVisibility(View.VISIBLE);
		logoLineView.setBackgroundResource(resId);
	}
	
	/**
     * 描述：把指定的View填加到标题栏右边.
     * @param rightView  指定的View
     */
	public void addRightView(View rightView) {
		rightLayout.setVisibility(View.VISIBLE);
		rightLayout.addView(rightView,layoutParamsFF);
	}
	
	/**
     * 描述：把指定资源ID表示的View填加到标题栏右边.
     * @param resId  指定的View的资源ID
     */
	public void addRightView(int resId) {
		rightLayout.setVisibility(View.VISIBLE);
		rightLayout.addView(mInflater.inflate(resId, null),layoutParamsFF);
	}
	
	/**
     * 描述：清除标题栏右边的View.
     */
	public void clearRightView() {
		rightLayout.removeAllViews();
	}
	
	/**
	 * 获取这个右边的布局,可用来设置位置
	 * @return
	 */
	public LinearLayout getRightLayout() {
		return rightLayout;
	}
	
	/**
	 * 描述：设置Logo按钮的点击事件.
	 * @param mOnClickListener  指定的返回事件
	 */
	public void setLogoOnClickListener(View.OnClickListener mOnClickListener) {
		 logoView.setOnClickListener(mOnClickListener);
	}
	
	/**
	 * 描述：设置标题的点击事件.
	 * @param mOnClickListener  指定的返回事件
	 */
	public void setTitleTextOnClickListener(View.OnClickListener mOnClickListener) {
		titleTextBtn.setOnClickListener(mOnClickListener);
	}
	
	/**
	 * 获取标题的全体布局
	 * @return
	 */
	public LinearLayout getTitleTextLayout() {
		return titleTextLayout;
	}
	
	

}
