package com.example.exchangeforzhku.view;

import com.example.exchangeforzhku.R;
import com.nineoldandroids.view.ViewHelper;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class ExchangeforZhku extends HorizontalScrollView {

	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;
	private int mMenuWidth;
	private int mMenuRightPadding = 50;// dp
	private boolean once;
	private boolean isOpen;
	
	/**
	 * δʹ���Զ�������ʱ������
	 * @param context
	 * @param attrs
	 */
	public ExchangeforZhku(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		
	}

	/**
	 * ��ʹ�����Զ�������ʱ������ô˹��췽��
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ExchangeforZhku(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		// ��ȡ���Ƕ��������
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.ExchangeforZhku, defStyle, 0);// �õ�����
		int n = a.getIndexCount();// �Զ������
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.ExchangeforZhku_rightPadding:
				mMenuRightPadding = a.getDimensionPixelOffset(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50, context
										.getResources().getDisplayMetrics()));// û�иı�ֵʱ��Ĭ����50dp
				break;
			}
		}
		a.recycle();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;// �����Ļ���
	}

	public ExchangeforZhku(Context context) {
		this(context, null,0);
	}

	/**
	 * ������View�Ŀ�͸� �����Լ��Ŀ�͸�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!once) {
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			once = true;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * ͨ������ƫ��������menu����
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			// ��������ߵĿ��
			int scrollX = getScrollX();
			if (scrollX >= mMenuWidth / 2) {// �жϲ˵����ػ�����ʾ
				this.smoothScrollTo(mMenuWidth, 0);// ����scrollBy(int,
													// int)�����ǳ���ƽ������������˲�����
													// ������ע��˲���������ָ����ʾ�������̣�ֱ����ʾ������ﵽ��λ�ã���
				isOpen = false;
			} else {
				this.smoothScrollTo(0, 0);// �����ʵ��ȴ���1/2ʱ����ȫ��ʾ
				isOpen = true;
			}
			return true;

		}
		return super.onTouchEvent(ev);
	}

	/**
	 * �򿪲˵�
	 */
	public void openMenu() {
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}

	public void closeMenu() {
		if (!isOpen)
			return;
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;
	}

	/**
	 * �л��˵�
	 */
	public void toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
		}
	}

	/**
	 * ��������ʱ
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO �Զ����ɵķ������
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mMenuWidth;// 1~0
		// �������Զ���������TranslationX
		ViewHelper.setTranslationX(mMenu, mMenuWidth * scale);

	}

}
