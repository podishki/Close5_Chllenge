package com.example.kiran.close5_chllenge.model;

import android.os.Bundle;

public class Districts {
	public Districts() {
	}
	//	constants for field references
	public static final String ADDRESS = "address";
	public static final String DATE = "date";
	public static final String CATEGORY = "category";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";

	//	private fields
	private String date;
	private String address;
	private String category;
	private String latitude;
	private String longitude;
	private String pddistrict;
	private String descript;

	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getPddistrict() {
		return pddistrict;
	}
	public void setPddistrict(String pddistrict) {
		this.pddistrict = pddistrict;
	}
	public String getDate() {
		return date;
	}

	public String getAddress() {
		return address;
	}

	public String getCategory() {
		return category;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


}
