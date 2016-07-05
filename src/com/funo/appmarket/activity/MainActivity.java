package com.funo.appmarket.activity;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.util.ToastUtils;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.OpenEffectBridge;
import com.open.androidtvwidget.utils.Utils;
import com.open.androidtvwidget.view.MainLayout;
import com.open.androidtvwidget.view.MainUpView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;

public class MainActivity extends BaseActivity implements OnClickListener {

	MainUpView mainUpView1;
	View test_top_iv;
	OpenEffectBridge mOpenEffectBridge;
	View mOldFocus; // 4.3以下版本需要自己保存.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		test_top_iv = findViewById(R.id.test_top_iv);
		/* MainUpView 设置. */
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		// mainUpView1 = new MainUpView(this); // 手动添加(test)
		// mainUpView1.attach2Window(this); // 手动添加(test)
		mOpenEffectBridge = (OpenEffectBridge) mainUpView1.getEffectBridge();
		// 4.2 绘制有问题，所以不使用绘制边框.
		// 也不支持倒影效果，绘制有问题.
		// 请大家不要按照我这样写.
		// 如果你不想放大小人超出边框(demo，张靓颖的小人)，可以不使用OpenEffectBridge.
		// 我只是测试----DEMO.(建议大家使用 NoDrawBridge)
		if (Utils.getSDKVersion() == 17) { // 测试 android 4.2版本.
			switchNoDrawBridgeVersion();
		} else { // 其它版本（android 4.3以上）.
			mainUpView1.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
			mainUpView1.setShadowResource(R.drawable.item_shadow); // 设置移动边框的阴影.
		}
		// mainUpView1.setUpRectResource(R.drawable.item_highlight); //
		// 设置移动边框的图片.(test)
		// mainUpView1.setDrawUpRectPadding(new Rect(0, 0, 0, -26)); //
		// 设置移动边框的距离.
		// mainUpView1.setDrawShadowPadding(0); // 阴影图片设置距离.
		// mOpenEffectBridge.setTranDurAnimTime(500); // 动画时间.

		MainLayout main_lay11 = (MainLayout) findViewById(R.id.main_lay);
		main_lay11.getViewTreeObserver().addOnGlobalFocusChangeListener(new OnGlobalFocusChangeListener() {

			@Override
			public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {
				if (newFocus != null)
					newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
				float scale = 1.2f;
				mainUpView1.setFocusView(newFocus, mOldFocus, scale);
				mOldFocus = newFocus; // 4.3以下需要自己保存.
				// 测试是否让边框绘制在下面，还是上面. (建议不要使用此函数)
				if (newFocus != null) {
					testTopDemo(newFocus, scale);
				}
			}

		});
		// test demo.
		gridview_lay = findViewById(R.id.gridview_lay);
		gridview_lay.setOnClickListener(this);
		findViewById(R.id.listview_lay).setOnClickListener(this);
		findViewById(R.id.keyboard_lay).setOnClickListener(this);
		findViewById(R.id.viewpager_lay).setOnClickListener(this);
		findViewById(R.id.effect_rlay).setOnClickListener(this);
		findViewById(R.id.menu_rlayt).setOnClickListener(this);
		findViewById(R.id.recyclerview_rlayt).setOnClickListener(this);
		/**
		 * 尽量不要使用鼠标. !!!! 如果使用鼠标，自己要处理好焦点问题.(警告)
		 */
		// main_lay11.setOnHoverListener(new OnHoverListener() {
		// @Override
		// public boolean onHover(View v, MotionEvent event) {
		// mainUpView1.setVisibility(View.INVISIBLE);
		// return true;
		// }
		// });
		//
		for (int i = 0; i < main_lay11.getChildCount(); i++) {
			main_lay11.getChildAt(i).setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						// v.performClick();
						v.requestFocus();
					}
					return false;
				}

			});
		}
	}

	public View gridview_lay;

	/**
	 * 这是一个测试DEMO，希望对API了解下再使用. 这种DEMO是为了实现这个效果:
	 * https://raw.githubusercontent.com/FrozenFreeFall/ImageSaveHttp/master/
	 * chaochupingm%20.jpg
	 */
	public void testTopDemo(View newView, float scale) {
		// 测试第一个小人放大的效果.
		if (newView.getId() == R.id.gridview_lay) { // 小人在外面的测试.
			Rect rect = new Rect(getDimension(R.dimen.px7), -getDimension(R.dimen.px42), getDimension(R.dimen.px7),
					getDimension(R.dimen.px7));
			mOpenEffectBridge.setDrawUpRectPadding(rect); // 设置移动边框间距，不要被挡住了。
			mOpenEffectBridge.setDrawShadowRectPadding(rect); // 设置阴影边框间距，不要被挡住了。
			mOpenEffectBridge.setDrawUpRectEnabled(false); // 让移动边框绘制在小人的下面.
			test_top_iv.animate().scaleX(scale).scaleY(scale).setDuration(100).start(); // 让小人超出控件.
		} else { // 其它的还原.
			mOpenEffectBridge.setDrawUpRectPadding(0);
			mOpenEffectBridge.setDrawShadowPadding(0);
			mOpenEffectBridge.setDrawUpRectEnabled(true);
			test_top_iv.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start(); // 让小人超出控件.
		}
	}

	public int getDimension(int id) {
		return (int) getResources().getDimension(id);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gridview_lay:
			ToastUtils.showShortToast(getContext(), "Gridview demo test");
			break;
		case R.id.listview_lay:
			ToastUtils.showShortToast(getContext(), "Listview demo test");
			break;
		case R.id.keyboard_lay:
			ToastUtils.showShortToast(getContext(), "键盘 demo test");
			break;
		case R.id.viewpager_lay: // viewpager页面切换测试.
			ToastUtils.showShortToast(getContext(), "ViewPager页面切换测试");
			break;
		case R.id.effect_rlay:
			ToastUtils.showShortToast(getContext(), "Effect动画切换测试");
			switchNoDrawBridgeVersion();
			break;
		case R.id.menu_rlayt: // 菜单测试.
			ToastUtils.showShortToast(getContext(), "菜单测试");
			break;
		case R.id.recyclerview_rlayt:
			ToastUtils.showShortToast(getContext(), "recyclerview测试");
			startActivity(new Intent(getContext(), DemoRecyclerviewActivity.class));
		}
	}

	private void switchNoDrawBridgeVersion() {
		EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();
		effectNoDrawBridge.setTranDurAnimTime(200);
		mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.
		mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
		mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距.
	}

}
