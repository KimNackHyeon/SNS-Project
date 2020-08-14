package com.web.curation.model;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class FormWrapper {
	private String[] images;
	private MyBoard feedData;
	private Food[] food;
	private String[] tags;
	private String[] contents;

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public MyBoard getFeedData() {
		return feedData;
	}

	public void setFeedData(MyBoard feedData) {
		this.feedData = feedData;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Food[] getFood() {
		return food;
	}

	public void setFood(Food[] food) {
		this.food = food;
	}

	public String[] getContents() {
		return contents;
	}

	public void setContents(String[] contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "FormWrapper [images=" + Arrays.toString(images) + ", feedData=" + feedData + ", food="
				+ Arrays.toString(food) + ", tag=" + Arrays.toString(tags) + ", contents=" + Arrays.toString(contents)
				+ "]";
	}

}