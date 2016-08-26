package com.funo.appmarket.datasource;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.gridbuilder.GridItem;

import android.support.v7.widget.GridLayout;

public class HomeTemplate3 implements IHomeTemplate {

	private int containerHeight = 0;
	private int baseItemHeight = 0;
	private int baseItemWidth = 0;
	
	private int spacing = 12;// 每个子项之间的间隔
	
	private int orientation = GridLayout.VERTICAL;
	
	private int rowCount = 3;
	private int columnCount = 0;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	private List<GridItem> gridItemList = new ArrayList<GridItem>();
	
	public HomeTemplate3(List<AppBean> appData, int containerHeight) {
		this.appBeans = appData;
		this.containerHeight = containerHeight;
		
		baseItemHeight = (int) Math.rint((containerHeight - spacing * 3.0) / 3.0);
		baseItemWidth = baseItemHeight + 40;
		
		if (appBeans != null) {
			int size = appBeans.size();
			AppBean appBean = null;
			if (size > 2) {
				int size1 = size - 2;// 除去前面2个不规则的
				int num = size1 / 5;// 五个为一组，组的个数
				columnCount = num * 2 + 2;
				int surplus = size1 % 5;// 余数
				if (surplus >= 3) {
					columnCount += 2;
				} else {
					columnCount += 1;
				}
				
				appBean = appBeans.get(0);
				GridItem gridItem = new GridItem();
				gridItem.setView_type(0);
				gridItem.setRowSize(2);
				gridItem.setHeight(baseItemHeight * 2 + spacing);
				gridItem.setColumnSize(2);
				gridItem.setWidth(baseItemWidth * 2 + spacing);
				gridItem.setData(appBean);
				gridItemList.add(gridItem);
				
				appBean = appBeans.get(1);
				gridItem = new GridItem();
				gridItem.setView_type(2);
				gridItem.setRowSize(1);
				gridItem.setHeight(baseItemHeight);
				gridItem.setColumnSize(2);
				gridItem.setWidth(baseItemWidth * 2 + spacing);
				gridItem.setData(appBean);
				gridItemList.add(gridItem);
				
				int offset = 2;// 偏移前面两个不规则的
				for (int i = 0; i < num; i++) {
					for (int j = 0; j < 5; j++) {
						appBean = appBeans.get(5 * i + j + offset);
						setup(j, appBean);
					}
				}
				for (int i = 0; i < surplus; i++) {
					appBean = appBeans.get(5 * num + i + offset);
					setup(i, appBean);
				}
			} else if (size > 0) {
				columnCount = 2;
				
				GridItem gridItem = null;
				for (int i = 0; i < size; i++) {
					appBean = appBeans.get(i);
					gridItem = new GridItem();
					gridItem.setData(appBean);
					if (i == 0) {
						gridItem.setView_type(0);
						gridItem.setRowSize(2);
						gridItem.setHeight(baseItemHeight * 2);
					} else if (i == 1) {
						gridItem.setView_type(2);
						gridItem.setRowSize(1);
						gridItem.setHeight(baseItemHeight);
					}
					gridItem.setColumnSize(2);
					gridItem.setWidth(baseItemWidth * 2 + spacing);
					gridItemList.add(gridItem);
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
		case 1:
		case 3:
		case 4:
			GridItem gridItem1 = new GridItem();
			gridItem1.setView_type(1);
			gridItem1.setRowSize(1);
			gridItem1.setHeight(baseItemHeight);
			gridItem1.setColumnSize(1);
			gridItem1.setWidth(baseItemWidth);
			gridItem1.setData(appBean);
			gridItemList.add(gridItem1);
			break;
		case 2:
			GridItem gridItem2 = new GridItem();
			gridItem2.setView_type(2);
			gridItem2.setRowSize(1);
			gridItem2.setHeight(baseItemHeight);
			gridItem2.setColumnSize(2);
			gridItem2.setWidth(baseItemWidth * 2 + spacing);
			gridItem2.setData(appBean);
			gridItemList.add(gridItem2);
			break;
		}
	}
	
	@Override
	public int getOrientation() {
		return orientation;
	}
	
}
