package com.byr.bbs.meta;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;

public class Section implements Serializable {

	private static final String TAG = Section.class.getSimpleName();
	private static final boolean LOCAL_LOG_V = false;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String IS_ROOT = "is_root";
	public static final String PARENT = "parent";

	public static final String SECTION_NAME_DEFAULT = "0";

	private String name;
	private String description;
	private boolean is_root;
	private String parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isIs_root() {
		return is_root;
	}

	public void setIs_root(boolean is_root) {
		this.is_root = is_root;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(NAME).append(":").append(name);
		builder.append(",");
		builder.append(DESCRIPTION).append(":").append(description);
		builder.append(",");
		builder.append(IS_ROOT).append(":").append(is_root);
		builder.append(",");
		builder.append(PARENT).append(":").append(parent);
		return builder.toString();
	}

	public static Section parse(String json) {
		try {
			Gson gson = new Gson();
			Section section = gson.fromJson(json, Section.class);
			if (LOCAL_LOG_V) {
				Log.i(TAG, section.toString());
			}

			return section;
		} catch (JsonSyntaxException e) {
			Log.e(TAG, e.getMessage());
			return null;
		}
	}
}
