package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.util.ViewHolderUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KeyboardGridViewAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<String> letters = new ArrayList<String>();
	private List<String> numbers = new ArrayList<String>();
	
	private boolean is_letter = true;
	
	public KeyboardGridViewAdapter(Context context) {
		this.mContext = context;
		
		letters.add("A");
		letters.add("B");
		letters.add("C");
		letters.add("D");
		letters.add("E");
		letters.add("F");
		letters.add("G");
		letters.add("H");
		letters.add("I");
		letters.add("J");
		letters.add("K");
		letters.add("L");
		letters.add("M");
		letters.add("N");
		letters.add("O");
		letters.add("P");
		letters.add("Q");
		letters.add("R");
		letters.add("S");
		letters.add("T");
		letters.add("U");
		letters.add("V");
		letters.add("W");
		letters.add("X");
		letters.add("Y");
		letters.add("Z");
		
		numbers.add("1");
		numbers.add("2");
		numbers.add("3");
		numbers.add("4");
		numbers.add("5");
		numbers.add("6");
		numbers.add("7");
		numbers.add("8");
		numbers.add("9");
		numbers.add("0");
	}
	
	public void switch2Letter() {
		is_letter = true;
	}
	
	public void switch2Number() {
		is_letter = false;
	}
	
	@Override
	public int getCount() {
		if (is_letter) {
			return letters.size();
		} else {
			return numbers.size();
		}
	}

	@Override
	public String getItem(int position) {
		if (is_letter) {
			return letters.get(position);
		} else {
			return numbers.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.gridview_item_keyboard, null);
		}
		TextView text = ViewHolderUtils.get(convertView, R.id.text);
		text.setText(getItem(position));
		return convertView;
	}

}
