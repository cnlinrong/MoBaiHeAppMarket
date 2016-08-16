package com.funo.appmarket.datasource;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.gridbuilder.GridItem;

import android.support.v7.widget.GridLayout;
import android.view.Gravity;

public class HomeTemplate2 implements IHomeTemplate {

	private int containerHeight = 0;
	private int baseItemHeight = 0;
	private int baseItemWidth = 0;
	
	private int spacing = 12;// 每个子项之间的间隔
	
	private int orientation = GridLayout.VERTICAL;
	
	private int rowCount = 3;
	private int columnCount = 0;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	private List<GridItem> gridItemList = new ArrayList<GridItem>();
	
	public HomeTemplate2(List<AppBean> appData, int containerHeight) {
		this.appBeans = appData;
		this.containerHeight = containerHeight;
		
		baseItemHeight = (containerHeight - spacing * 3) / 3;
		baseItemWidth = baseItemHeight + 40;
		
		if (appBeans != null) {
			int size = appBeans.size();
			AppBean appBean = null;
			if (size > 7) {
				int num = size / 7;// 七个为一组，组的个数
				columnCount = num * 4;
				int surplus = size % 7;// 余数
				if (surplus >= 5) {
					columnCount += 4;
				} else if (surplus >= 1) {
					columnCount += 2;
				}
				
				for (int i = 0; i < num; i++) {
					for (int j = 0; j < 7; j++) {
						appBean = appBeans.get(7 * i + j);
						setup(j, appBean);
					}
				}
				for (int i = 0; i < surplus; i++) {
					appBean = appBeans.get(7 * num + i);
					setup(i, appBean);
				}
			} else {
				if (size >= 5) {
					columnCount = 4;
				} else if (size >= 1) {
					columnCount = 2;
				}
				
				for (int i = 0; i < size; i++) {
					appBean = appBeans.get(i);
					setup(i, appBean);
				}
			}
		}
	}
	
	@Override
	public int getRowCount() {
		return rowCount;
	}
	
	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public List<GridItem> getGridData() {
		return gridItemList;
	}
	
	private void setup(int index, AppBean appBean) {
		switch (index) {
		case 0:
			GridItem gridItem1 = new GridItem();
			gridItem1.setView_type(2);
			gridItem1.setGravity(Gravity.FILL);
			gridItem1.setRowSize(1);
			gridItem1.setHeight(baseItemHeight);
			gridItem1.setColumnSize(2);
			gridItem1.setWidth(baseItemWidth * 2 + spacing);
			gridItem1.setData(appBean);
			gridItemList.add(gridItem1);
			break;
		case 1:
			GridItem gridItem2 = new GridItem();
			gridItem2.setView_type(2);
			gridItem2.setGravity(Gravity.FILL);
			gridItem2.setRowSize(1);
			gridItem2.setHeight(baseItemHeight);
			gridItem2.setColumnSize(2);
			gridItem2.setWidth(baseItemWidth * 2 + spacing);
			gridItem2.setData(appBean);
			gridItemList.add(gridItem2);
			break;
		case 2:
			GridItem gridItem3 = new GridItem();
			gridItem3.setView_type(1);
			gridItem3.setRowSize(1);
			gridItem3.setHeight(baseItemHeight);
			gridItem3.setColumnSize(1);
			gridItem3.setWidth(baseItemWidth);
			gridItem3.setData(appBean);
			gridItemList.add(gridItem3);
			break;
		case 3:
			GridItem gridItem4 = new GridItem();
			gridItem4.setView_type(1);
			gridItem4.setRowSize(1);
			gridItem4.setHeight(baseItemHeight);
			gridItem4.setColumnSize(1);
			gridItem4.setWidth(baseItemWidth);
			gridItem4.setData(appBean);
			gridItemList.add(gridItem4);
			break;
		case 4:
			GridItem gridItem5 = new GridItem();
			gridItem5.setView_type(0);
			gridItem5.setGravity(Gravity.FILL);
			gridItem5.setRowSize(2);
			gridItem5.setHeight(baseItemHeight * 2);
			gridItem5.setColumnSize(2);
			gridItem5.setWidth(baseItemWidth * 2 + spacing);
			gridItem5.setData(appBean);
			gridItemList.add(gridItem5);
			break;
		case 5:
			GridItem gridItem6 = new GridItem();
			gridItem6.setView_type(1);
			gridItem6.setRowSize(1);
			gridItem6.setHeight(baseItemHeight);
			gridItem6.setColumnSize(1);
			gridItem6.setWidth(baseItemWidth);
			gridItem6.setData(appBean);
			gridItemList.add(gridItem6);
			break;
		case 6:
			GridItem gridItem7 = new GridItem();
			gridItem7.setView_type(1);
			gridItem7.setRowSize(1);
			gridItem7.setHeight(baseItemHeight);
			gridItem7.setColumnSize(1);
			gridItem7.setWidth(baseItemWidth);
			gridItem7.setData(appBean);
			gridItemList.add(gridItem7);
			break;
		}
	}
	
	@Override
	public int getOrientation() {
		return orientation;
	}
	
}
