package dennis.testdemos.a01_materialdesign.bean;

import java.io.Serializable;
/**
 * Created by Dennis Can on 2017/2/8.
 */
public class ItemBean implements Serializable {
	private final int imageId;
	private final String title;

	public ItemBean(int imageId, String title) {
		this.imageId = imageId;
		this.title = title;
	}

	public int getImageId() { return imageId; }

	public String getTitle() { return title; }
}