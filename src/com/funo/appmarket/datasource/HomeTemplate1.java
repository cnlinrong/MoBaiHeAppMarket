package com.funo.appmarket.datasource;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.gridbuilder.GridItem;

import android.support.v7.widget.GridLayout;
import android.view.Gravity;

public class HomeTemplate1 implements IHomeTemplate {

	private int containerHeight = 0;
	private int baseItemHeight = 0;
	private int baseItemWidth = 0;
	
	private int spacing = 12;// 每个子项之间的间隔
	
	private int orientation = GridLayout.HORIZONTAL;
	
	private int rowCount = 2;
	private int columnCount = 0;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	private List<GridItem> gridItemList = new ArrayList<GridItem>();
	
	public HomeTemplate1(List<AppBean> appData, int containerHeight) {
		this.appBeans = appData;
		this.containerHeight = containerHeight;
		
		baseItemHeight = (int) Math.rint((containerHeight - spacing * 2.0) / 3.0);
		baseItemWidth = baseItemHeight + 40;
		
		if (appBeans != null) {
			int size = appBeans.size();
			if (size > 3) {
				columnCount = 2 + (int) Math.ceil((size - 3) / 2.0);
			} else {
				if (size > 0) {
					columnCount = 2;
				}
			}
			
			for (int i = 0; i < size; i++) {
				AppBean appBean = appBeans.get(i);
				if (i == 0) {
					GridItem gridItem = new GridItem();
					gridItem.setView_type(0);
					gridItem.setRowSize(1);
					gridItem.setHeight(baseItemHeight * 2);
					gridItem.setColumnSize(2);
					gridItem.setWidth(baseItemWidth * 2 + spacing);
					gridItem.setRowWeight(2);
					gridItem.setGravity(Gravity.FILL);
					gridItem.setData(appBean);
					gridItemList.add(gridItem);
				} else {
					if ((i + 1) < columnCount && size > 3) {
						GridItem gridItem = new GridItem();
						gridItem.setView_type(0);
						gridItem.setRowSize(1);
						gridItem.setHeight(baseItemHeight * 2);
						gridItem.setColumnSize(1);
						gridItem.setWidth(baseItemWidth);
						gridItem.setRowWeight(2);
						gridItem.setData(appBean);
						gridItemList.add(gridItem);
					} else {
						GridItem gridItem = new GridItem();
						gridItem.setView_type(1);
						gridItem.setRowSize(1);
						gridItem.setHeight(baseItemHeight);
						gridItem.setColumnSize(1);
						gridItem.setWidth(baseItemWidth);
						gridItem.setData(appBean);
						gridItemList.add(gridItem);
					}
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

	@Override
	public int getOrientation() {
		return orientation;
	}
	
}
