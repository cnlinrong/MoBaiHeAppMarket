package com.funo.appmarket.datasource;

import java.util.List;

import com.gridbuilder.GridItem;

public interface IHomeTemplate {

	int getOrientation();

	int getRowCount();

	int getColumnCount();

	List<? extends GridItem> getGridData();

}
