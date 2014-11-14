package com.codepath.instagram;

import java.util.Date;

public class Photo {

	private Date timestamp;

	private int imageHeight;
	private int likeCount;

	private String caption;
	private String imageURL;
	private String userName;
	private String userPicURL;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPicURL() {
		return userPicURL;
	}

	public void setUserPicURL(String userPicURL) {
		this.userPicURL = userPicURL;
	}
}
