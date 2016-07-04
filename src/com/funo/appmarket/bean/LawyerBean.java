package com.funo.appmarket.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

public class LawyerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long ID;
	private String LAWERID;
	private String USER_ID;
	private String PHOTO;
	private String REL_NAME;
	private String MY_PHONE;
	private String USERNAME;
	private String THIRD_PARTY;
	private String PASSWORD;
	private String EMAIL;
	private String QQ;
	private String WX;
	private String ID_NUMBER;
	private String LICENCE_NUM;
	private String ID_PHOTO_FACE;
	private String ID_PHOTO_OPPO;
	private String LICENCE_PHOTO_FACE;
	private String LICENCE_PHOTO_OPPO;
	private String IS_MANAGE;
	private String WORK_TIME;
	private String GOOD_FIELD;
	private String PROFILE;
	private String LAY_FIRM;
	private String PROVINCE;
	private String CITY;
	private int LAWER_LEVEL;
	private int STATUS;
	private double DISTANCE;
	private String COUNTY;
	private String ADDRESS;
	private String LAWYER_ASSESS;// 评价数|评价星级|专业水平评价|职业素养评价|响应速度评价
	private double BID_PRICE;

	public String getLAWERID() {
		return LAWERID;
	}

	public void setLAWERID(String lAWERID) {
		LAWERID = lAWERID;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getPHOTO() {
		return PHOTO;
	}

	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}

	public String getREL_NAME() {
		return REL_NAME;
	}

	public void setREL_NAME(String cONTENT) {
		REL_NAME = cONTENT;
	}

	public String getMY_PHONE() {
		return MY_PHONE;
	}

	public void setMY_PHONE(String pROVINCE) {
		MY_PHONE = pROVINCE;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String cITY) {
		QQ = cITY;
	}

	public String getWX() {
		return WX;
	}

	public void setWX(String tIP) {
		WX = tIP;
	}

	public String getLICENCE_NUM() {
		return LICENCE_NUM;
	}

	public void setLICENCE_NUME(String cASE_TYPE) {
		LICENCE_NUM = cASE_TYPE;
	}

	public String getWORK_TIME() {
		return WORK_TIME;
	}

	public void setWORK_TIME(String aUTHOR) {
		WORK_TIME = aUTHOR;
	}

	public String getGOOD_FIELD() {
		if (GOOD_FIELD != null && !TextUtils.isEmpty(GOOD_FIELD)) {
			String[] fields = GOOD_FIELD.split(",");
			if (fields.length >= 3) {
				List<String> tempList = new ArrayList<String>();
				for (String field : fields) {
					if (!tempList.contains(field)) {
						tempList.add(field);
						if (tempList.size() == 3) {
							break;
						}
					}
				}
				StringBuffer result = new StringBuffer();
				for (int i = 0; i < tempList.size(); i++) {
					result.append(tempList.get(i));
					if (i != tempList.size() - 1) {
						result.append(",");
					}
				}
				GOOD_FIELD = result.toString();
			}
		}
		return GOOD_FIELD;
	}

	public void setGOOD_FIELD(String tELEPHONE) {
		GOOD_FIELD = tELEPHONE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPROFILE() {
		return PROFILE;
	}

	public void setPROFILE(String sTATUS) {
		PROFILE = sTATUS;
	}

	public String getLAY_FIRM() {
		return LAY_FIRM;
	}

	public void setLAY_FIRM(String lOWER_ID) {
		LAY_FIRM = lOWER_ID;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String nEW_REPLY) {
		CITY = nEW_REPLY;
	}

	public String getCOUNTY() {
		return COUNTY;
	}

	public void setCOUNTY(String nOTES) {
		COUNTY = nOTES;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String nUM) {
		ADDRESS = nUM;
	}

	public int getLAWER_LEVEL() {
		return LAWER_LEVEL;
	}

	public void setLAWER_LEVEL(int cREATE_DATE) {
		LAWER_LEVEL = cREATE_DATE;
	}

	public double getDISTANCE() {
		return DISTANCE;
	}

	public void setDISTANCE(double dISTANCE) {
		DISTANCE = dISTANCE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLAWYER_ASSESS() {
		return LAWYER_ASSESS;
	}

	public void setLAWYER_ASSESS(String lAWYER_ASSESS) {
		LAWYER_ASSESS = lAWYER_ASSESS;
	}

	public void setLICENCE_NUM(String lICENCE_NUM) {
		LICENCE_NUM = lICENCE_NUM;
	}

	public double getBID_PRICE() {
		return BID_PRICE;
	}

	public void setBID_PRICE(double bID_PRICE) {
		BID_PRICE = bID_PRICE;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getTHIRD_PARTY() {
		return THIRD_PARTY;
	}

	public void setTHIRD_PARTY(String tHIRD_PARTY) {
		THIRD_PARTY = tHIRD_PARTY;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getID_NUMBER() {
		return ID_NUMBER;
	}

	public void setID_NUMBER(String iD_NUMBER) {
		ID_NUMBER = iD_NUMBER;
	}

	public String getID_PHOTO_FACE() {
		return ID_PHOTO_FACE;
	}

	public void setID_PHOTO_FACE(String iD_PHOTO_FACE) {
		ID_PHOTO_FACE = iD_PHOTO_FACE;
	}

	public String getID_PHOTO_OPPO() {
		return ID_PHOTO_OPPO;
	}

	public void setID_PHOTO_OPPO(String iD_PHOTO_OPPO) {
		ID_PHOTO_OPPO = iD_PHOTO_OPPO;
	}

	public String getLICENCE_PHOTO_FACE() {
		return LICENCE_PHOTO_FACE;
	}

	public void setLICENCE_PHOTO_FACE(String lICENCE_PHOTO_FACE) {
		LICENCE_PHOTO_FACE = lICENCE_PHOTO_FACE;
	}

	public String getLICENCE_PHOTO_OPPO() {
		return LICENCE_PHOTO_OPPO;
	}

	public void setLICENCE_PHOTO_OPPO(String lICENCE_PHOTO_OPPO) {
		LICENCE_PHOTO_OPPO = lICENCE_PHOTO_OPPO;
	}

	public String getIS_MANAGE() {
		return IS_MANAGE;
	}

	public void setIS_MANAGE(String iS_MANAGE) {
		IS_MANAGE = iS_MANAGE;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

}
