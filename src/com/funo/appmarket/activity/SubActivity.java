package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import org.evilbinary.tv.widget.BorderView;
import org.evilbinary.tv.widget.TvHorizontalScrollView;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.NavListAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.NavItem;
import com.funo.appmarket.business.AppSmallTypeService;
import com.funo.appmarket.business.AppSmallTypeService.AppSmallTypeCallback;
import com.funo.appmarket.business.RecAppInfoService;
import com.funo.appmarket.business.RecAppInfoService.RecAppInfoCallback;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IAppSmallTypeService.AppSmallTypeParam;
import com.funo.appmarket.business.define.IRecAppInfoService.RecAppInfoReqParam;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.datasource.HomeTemplate1;
import com.funo.appmarket.datasource.HomeTemplate2;
import com.funo.appmarket.datasource.HomeTemplate3;
import com.funo.appmarket.datasource.IHomeTemplate;
import com.funo.appmarket.db.NavModelDB;
import com.funo.appmarket.util.AnimationUtils;
import com.funo.appmarket.util.CommonUtils;
import com.funo.appmarket.util.ModelBeanConverter;
import com.gridbuilder.GridBuilder;
import com.gridbuilder.GridItem;
import com.gridbuilder.GridViewHolder;
import com.gridbuilder.listener.OnViewCreateCallBack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SubActivity extends BaseActivity {

	private float originalWidth = 1.0f;
	private float originalHeight = 1.0f;
	private float targetWidth = 1.4f;
	private float targetHeight = 1.4f;
	private long duration = 200;
	
	private AppSmallTypeService appSmallTypeService;
	private RecAppInfoService recAppInfoService;
	
	private TextView parent_label;
	private TvHorizontalScrollView hsv;
	private ListView navList;
	private View search;

	private GridLayout gl_gridlayout;

	private int templateUsedId = 1;// 首页模板
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();

	private NavListAdapter navListAdapter;
	private List<NavItem> navItems = new ArrayList<NavItem>();
	
	private String parentLabel;
	private String parentId;

	private SparseIntArray colorMap = new SparseIntArray();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appSmallTypeService = new AppSmallTypeService(getContext());
		recAppInfoService = new RecAppInfoService(getContext());
		
		setContentView(R.layout.activity_sub);
		
		templateUsedId = sys_sp.getInt("templateUsedId", 1);
//		templateUsedId = 3;
		
		Intent intent = getIntent();
		parentLabel = intent.getStringExtra("parentLabel");
		parentId = intent.getStringExtra("parentId");
		
		initView();

		search = findViewById(R.id.search);
		search.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, originalWidth, originalHeight, targetWidth, targetHeight, duration);
				} else {
					AnimationUtils.scaleAnim(v, targetWidth, targetHeight, originalWidth, originalHeight, duration);
				}
			}
			
		});
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getContext(), SearchActivity.class));
			}

		});

		navList = (ListView) findViewById(R.id.navList);
		navList.post(new Runnable() {

			@Override
			public void run() {
				int itemHeight = navList.getHeight() / 4 + 1;
				navListAdapter = new NavListAdapter(getContext(), navItems, itemHeight);
				navList.setAdapter(navListAdapter);
			}

		});
		navList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NavItem navItem = navListAdapter.getItem(position);
				Intent intent = new Intent(getContext(), AppsActivity.class);
				intent.putExtra("subParentLabel", navItem.getLabel());
				intent.putExtra("subParentId", navItem.getValue());
				startActivity(intent);
			}

		});

		navList.post(new Runnable() {

			@Override
			public void run() {
				search.setFocusable(true);
				search.setFocusableInTouchMode(true);
				
				gl_gridlayout.setFocusable(true);
				gl_gridlayout.setFocusableInTouchMode(true);
				
				BorderView border = new BorderView(getContext());
				border.setBackgroundResource(R.drawable.test_rectangle);
				border.attachTo(gl_gridlayout);
				
				navList.requestFocus();
			}

		});
		
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		refreshGridData();
	}
	
	private void initView() {
		parent_label = (TextView) findViewById(R.id.parent_label);
		hsv = (TvHorizontalScrollView) findViewById(R.id.hsv);
		gl_gridlayout = (GridLayout) findViewById(R.id.gl_gridlayout);

		parent_label.setText(parentLabel);
	}

	private void initData() {
		boolean isTypeChage = sys_sp.getBoolean("isTypeChage", true);
		if (isTypeChage) {
			AppSmallTypeParam appSmallTypeParam = new AppSmallTypeParam();
			appSmallTypeParam.smallTypeValue = parentId;
			appSmallTypeService.app_smallType(appSmallTypeParam, new AppSmallTypeCallback() {
				
				@Override
				public void doCallback(List<NavItem> appSmallTypes) {
					if (appSmallTypes != null) {
						navItems = appSmallTypes;
						
						NavModelDB.batchInsertNavItems(ModelBeanConverter.navBeans2Models(navItems, Integer.parseInt(parentId)));
					}
					if (navItems == null || navItems.isEmpty()) {
						navItems = new ArrayList<NavItem>();
						navItems.add(new NavItem());
					}
					
					if (navListAdapter != null) {
						navListAdapter.setData(navItems);
					}
				}
				
			});
		} else {
			this.navItems = ModelBeanConverter.navModels2Beans(NavModelDB.getNavItemsByParentId(Integer.parseInt(parentId)));
			if (this.navItems == null || this.navItems.isEmpty()) {
				AppSmallTypeParam appSmallTypeParam = new AppSmallTypeParam();
				appSmallTypeParam.smallTypeValue = parentId;
				appSmallTypeService.app_smallType(appSmallTypeParam, new AppSmallTypeCallback() {
					
					@Override
					public void doCallback(List<NavItem> appSmallTypes) {
						if (appSmallTypes != null) {
							navItems = appSmallTypes;
							
							NavModelDB.batchInsertNavItems(ModelBeanConverter.navBeans2Models(navItems, Integer.parseInt(parentId)));
						}
						if (navItems == null || navItems.isEmpty()) {
							navItems = new ArrayList<NavItem>();
							navItems.add(new NavItem());
						}
						
						if (navListAdapter != null) {
							navListAdapter.setData(navItems);
						}
					}
					
				});
				
			}
		}
	}
	
	private void refreshGridData() {
		RecAppInfoReqParam recAppInfoReqParam = new RecAppInfoReqParam();
		recAppInfoReqParam.type = 1;// 0：首页推荐 1：分类页推荐
		recAppInfoReqParam.pageSize = BaseService.PAGE_SIZE;
		recAppInfoReqParam.currentPage = 1;
		recAppInfoService.recAppInfo(recAppInfoReqParam, new RecAppInfoCallback() {

			@Override
			public void doCallback(List<AppBean> appData) {
				if (appData != null) {
					appBeans = appData;
				}
				
				gl_gridlayout.removeAllViews();
				
				hsv.post(new Runnable() {

					@Override
					public void run() {
						GridViewHolder holder = new GridViewHolder(gl_gridlayout);

						IHomeTemplate homeTemplate = null;
						switch (templateUsedId) {
						case 1:
							homeTemplate = new HomeTemplate1(appBeans, hsv.getHeight());
							break;
						case 2:
							homeTemplate = new HomeTemplate2(appBeans, hsv.getHeight());
							break;
						case 3:
							homeTemplate = new HomeTemplate3(appBeans, hsv.getHeight());
							break;
						default:
							homeTemplate = new HomeTemplate1(appBeans, hsv.getHeight());
							break;
						}
						GridBuilder.newInstance(getContext(), gl_gridlayout).setScaleAnimationDuration(200)
								.setOrientation(homeTemplate.getOrientation())
								.setRowCount(homeTemplate.getRowCount())
								.setMargin(5).setColumnCount(homeTemplate.getColumnCount())
								.setGridItemList(homeTemplate.getGridData()).setViewHolder(holder)
								.setOnCreateViewCallBack(new OnViewCreateCallBack() {

							@Override
							public View onViewCreate(LayoutInflater inflater, View convertView, GridItem gridItem) {
								final AppBean appBean = (AppBean) gridItem.getData();

								View v = null;
								if (null == convertView) {
									if (gridItem.getView_type() == 0) {
										v = View.inflate(getContext(), R.layout.gridlayout_item1, null);
									} else if (gridItem.getView_type() == 1) {
										v = View.inflate(getContext(), R.layout.gridlayout_item2, null);
									} else if (gridItem.getView_type() == 2) {
										v = View.inflate(getContext(), R.layout.gridlayout_item3, null);
									}
									v.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											Intent intent = new Intent(getContext(), AppDetailActivity.class);
											intent.putExtra("selectedApp", appBean);
											startActivity(intent);
										}

									});
								} else {
									v = convertView;
								}
								if (appBean != null) {
									ImageView tag_img = (ImageView) v.findViewById(R.id.tag_img);
									int tag = 0;
									try {
										tag = Integer.parseInt(appBean.getTag());
									} catch (NumberFormatException e) {
										e.printStackTrace();
									}
									switch (tag) {// 标签类型 0:无标签 1:精品 2:NEW 3:HOT
									case 0:
										tag_img.setVisibility(View.GONE);
										break;
									case 1:
										tag_img.setVisibility(View.VISIBLE);
										tag_img.setImageResource(R.drawable.recommend_best);
										break;
									case 2:
										tag_img.setVisibility(View.VISIBLE);
										tag_img.setImageResource(R.drawable.recommend_new);
										break;
									case 3:
										tag_img.setVisibility(View.VISIBLE);
										tag_img.setImageResource(R.drawable.recommend_hot);
										break;
									}
									TextView title = (TextView) v.findViewById(R.id.title);
									title.setText(appBean.getAppName());
									ImageView app_logo = (ImageView) v.findViewById(R.id.app_logo);
									Glide.with(getContext()).load(Constants.IMAGE_URL + appBean.getAppLogo()).into(app_logo);
									if (gridItem.getView_type() == 0 || gridItem.getView_type() == 2) {
										((TextView) v.findViewById(R.id.sub_title)).setText(appBean.getAppInfo());
									}
								}
								int appId = (int) appBean.getAppId();
								int color = colorMap.get(appId, -1);
								if (color == -1) {
									int randomColor = CommonUtils.getRandomColor();
									v.findViewById(R.id.content).setBackgroundColor(randomColor);
									colorMap.append(appId, randomColor);
								} else {
									v.findViewById(R.id.content).setBackgroundColor(color);
								}
								v.setFocusable(true);
								return v;
							}

						}).build();
					}

				});
			}

		});
	}
	
}
