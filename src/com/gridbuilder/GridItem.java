package com.gridbuilder;

import java.io.Serializable;

import android.view.Gravity;

public class GridItem implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public static final String TAG_FIRST_ITEM = "first_grid_item";

    public GridItem() {
    }

    private int rowSize = 1;

    private int columnSize = 1;

    /**
     * 行
     */
    private int row;

    /**
     * 列
     */
    private int column;

    private int rowWeight = 1;
    private int columnWeight = 1;
    
    private int gravity = Gravity.NO_GRAVITY;
    
    private int width;

    private int height;

    private Object data;
    
    private int view_type = 0;
    
    public int getView_type() {
		return view_type;
	}

	public void setView_type(int view_type) {
		this.view_type = view_type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public void setColumn(int column) {
        this.column = column;
    }

    public int getRowWeight() {
		return rowWeight;
	}

	public void setRowWeight(int rowWeight) {
		this.rowWeight = rowWeight;
	}

	public int getColumnWeight() {
		return columnWeight;
	}

	public void setColumnWeight(int columnWeight) {
		this.columnWeight = columnWeight;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	/**
     * 获取列
     */
    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    /**
     * 获取行
     */
    public int getRow() {
        return row;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "GridItem{" +
                "rowSpec=" + rowSize +
                ", columnSpec=" + columnSize +
                ", row=" + row +
                ", column=" + column +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
