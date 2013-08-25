package com.byr.bbs.meta;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Liutong
 * 
 *         <p>
 *         附件的元数据为用户空间的附件状态或是单篇文章/信件的附件状态
 *         </p>
 */
public class Attachment implements Serializable {

	private static final String TAG = Attachment.class.getSimpleName();

	/**
	 * @author Liutong
	 * 
	 * @see <a
	 *      href="https://github.com/xw2423/nForum/wiki/nForum-API-Meta-Attachment">
	 *      https://github.com/xw2423/nForum/wiki/nForum-API-Meta-Attachment</a>
	 */
	public static class FileInfo implements Serializable {

		public static final String NAME = "name";
		public static final String URL = "url";
		public static final String SIZE = "size";
		public static final String THUMBNAIL_SMALL = "thumbnail_small";
		public static final String THUMBNAIL_MIDDLE = "thumbnail_middle";

		private String name;
		private String url;
		private String size;
		private String thumbnail_small;
		private String thumbnail_middle;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getThumbnail_small() {
			return thumbnail_small;
		}

		public void setThumbnail_small(String thumbnail_small) {
			this.thumbnail_small = thumbnail_small;
		}

		public String getThumbnail_middle() {
			return thumbnail_middle;
		}

		public void setThumbnail_middle(String thumbnail_middle) {
			this.thumbnail_middle = thumbnail_middle;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append(NAME).append("=").append(name).append(", ");
			builder.append(URL).append("=").append(url).append(", ");
			builder.append(SIZE).append("=").append(size).append(", ");
			builder.append("}");
			return builder.toString();
		}

	}

	public static final String FILE = "file";
	public static final String REMAIN_SPACE = "remain_space";
	public static final String REMAIN_COUNT = "remain_count";

	private ArrayList<FileInfo> files;
	private String remain_space;
	private int remain_count;

	public ArrayList<FileInfo> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<FileInfo> files) {
		this.files = files;
	}

	public String getRemain_space() {
		return remain_space;
	}

	public void setRemain_space(String remain_space) {
		this.remain_space = remain_space;
	}

	public int getRemain_count() {
		return remain_count;
	}

	public void setRemain_count(int remain_count) {
		this.remain_count = remain_count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(FILE);
		builder.append(":");
		builder.append(files.toString()).append(", ");
		builder.append(REMAIN_COUNT).append(":").append(remain_count)
				.append(", ");
		builder.append(REMAIN_SPACE).append(":").append(remain_space);
		return builder.toString();
	}

	public static Attachment parse(String json) {
		try {
			JSONObject jo = new JSONObject(json);
			Attachment attachment = new Attachment();
			attachment.setRemain_count(jo.optInt(Attachment.REMAIN_COUNT));
			attachment.setRemain_space(jo.optString(Attachment.REMAIN_SPACE));

			String fileJson = jo.getString(Attachment.FILE);
			Type type = new TypeToken<ArrayList<FileInfo>>() {
			}.getType();
			attachment.setFiles(new Gson().<ArrayList<FileInfo>> fromJson(
					fileJson, type));

			return attachment;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
