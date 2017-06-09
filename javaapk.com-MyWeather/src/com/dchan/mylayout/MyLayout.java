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
	 * LinearLayout.LayoutParams���Ѿ���ʼ��ΪFILL_PARENT, FILL_PARENT
	 */
	public LinearLayout.LayoutParams layoutParamsFF = null;
	
	/**
	 * LinearLayout.LayoutParams���Ѿ���ʼ��ΪFILL_PARENT, WRAP_CONTENT
	 */
	public LinearLayout.LayoutParams layoutParamsFW = null;
	
	/**
	 * LinearLayout.LayoutParams���Ѿ���ʼ��ΪWRAP_CONTENT, FILL_PARENT
	 */
	public LinearLayout.LayoutParams layoutParamsWF = null;
	
	/**
	 * LinearLayout.LayoutParams���Ѿ���ʼ��ΪWRAP_CONTENT, WRAP_CONTENT
	 */
	public LinearLayout.LayoutParams layoutParamsWW = null;
	
	/** ���Ⲽ��. */
	protected LinearLayout titleTextLayout = null;
	
	/** ����Logoͼ��View. */
	protected ImageView logoView = null;
	
	/** ����Logoͼ���ұߵķָ���View. */
	protected ImageView logoLineView = null;
	
	/** ��ʾ�������ֵ�View. */
	protected Button titleTextBtn = null;
	
	/** �ұߵ�View�������Զ�����ʾʲô. */
	protected LinearLayout rightLayout = null;
	
	/** �����ı��Ķ������. */
	private LinearLayout.LayoutParams titleTextLayoutParams = null;
	
	/** �ұ߲��ֵĵĶ������. */
	private LinearLayout.LayoutParams rightViewLayoutParams = null;
	
	/** ����������ID. */
	public int mAbTitleBarID = 1;
	
	/** ȫ�ֵ�LayoutInflater�����Ѿ���ɳ�ʼ��. */
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
	 * ��ʼ��
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
		
		// ���ұߵĲ���
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
	 * �������������ı���ͼ.
	 * @param res  ����ͼ��ԴID
	 */         
	public void setTitleBarBackground(int res) {
		this.setBackgroundResource(res);
	}
	
	/**
	 * ���������ñ��ⱳ��.
	 * @param d  ����ͼ
	 */
	public void setTitleBarBackgroundDrawable(Drawable d) {
		this.setBackground(d);
	}
	
	/**
	 * �������������ı���ͼ.
	 * @param color  ������ɫֵ
	 */
	/*public void setTitleBarBackgroundColor(int color) {
		this.setBackgroundColor(color);
	}*/
	
	/**
	 * ��������ȡ�����ı���Button.
	 * @return the title Button view
	 */
	public Button getTitleTextButton() {
		return titleTextBtn;
	}
	
	/**
	 * ��������ȡ����Logo��View.
	 * @return the logo view
	 */
	public ImageView getLogoView() {
		return logoView;
	}
	
	/**
	 * ���������ñ��ⱳ��.
	 *
	 * @param resId the new title text background resource
	 */
	public void setTitleTextBackgroundResource(int resId){
		titleTextBtn.setBackgroundResource(resId);
	}
	
	/**
     * ���������ñ����ı�.
     * @param text  �ı�
     */
	public void setTitleText(String text) {
		titleTextBtn.setText(text);
	}
	
	/**
     * ���������ñ����ı�.
     * @param resId  �ı�����ԴID
     */
	public void setTitleText(int resId) {
		titleTextBtn.setText(resId);
	}
	
	/**
     * ����������Logo�ı�����Դ.
     * @param resId  Logo��ԴID
     */
	public void setLogo(int resId) {
		logoView.setVisibility(View.VISIBLE);
		logoView.setBackgroundResource(resId);
	}
	
	/**
     * ����������Logo�ָ��ߵı�����Դ.
     * @param resId  Logo��ԴID
     */
	public void setLogoLine(int resId) {
		logoLineView.setVisibility(View.VISIBLE);
		logoLineView.setBackgroundResource(resId);
	}
	
	/**
     * ��������ָ����View��ӵ��������ұ�.
     * @param rightView  ָ����View
     */
	public void addRightView(View rightView) {
		rightLayout.setVisibility(View.VISIBLE);
		rightLayout.addView(rightView,layoutParamsFF);
	}
	
	/**
     * ��������ָ����ԴID��ʾ��View��ӵ��������ұ�.
     * @param resId  ָ����View����ԴID
     */
	public void addRightView(int resId) {
		rightLayout.setVisibility(View.VISIBLE);
		rightLayout.addView(mInflater.inflate(resId, null),layoutParamsFF);
	}
	
	/**
     * ����������������ұߵ�View.
     */
	public void clearRightView() {
		rightLayout.removeAllViews();
	}
	
	/**
	 * ��ȡ����ұߵĲ���,����������λ��
	 * @return
	 */
	public LinearLayout getRightLayout() {
		return rightLayout;
	}
	
	/**
	 * ����������Logo��ť�ĵ���¼�.
	 * @param mOnClickListener  ָ���ķ����¼�
	 */
	public void setLogoOnClickListener(View.OnClickListener mOnClickListener) {
		 logoView.setOnClickListener(mOnClickListener);
	}
	
	/**
	 * ���������ñ���ĵ���¼�.
	 * @param mOnClickListener  ָ���ķ����¼�
	 */
	public void setTitleTextOnClickListener(View.OnClickListener mOnClickListener) {
		titleTextBtn.setOnClickListener(mOnClickListener);
	}
	
	/**
	 * ��ȡ�����ȫ�岼��
	 * @return
	 */
	public LinearLayout getTitleTextLayout() {
		return titleTextLayout;
	}
	
	

}
