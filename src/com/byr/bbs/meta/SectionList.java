package com.byr.bbs.meta;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Liutong on 13-7-27.
 */
public class SectionList implements Serializable {
	private static final String TAG = SectionList.class.getSimpleName();

	public static final String SECTION_COUNT = "section_count";
	public static final String SECTION = "section";

	private int section_count;
	private ArrayList<Section> sections;

	public SectionList() {

	}

	public SectionList(int section_count, ArrayList<Section> sections) {
		this.section_count = section_count;
		this.sections = sections;
	}

	public int getSection_count() {
		return section_count;
	}

	public void setSection_count(int section_count) {
		this.section_count = section_count;
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public static SectionList parse(String json) {
		try {
			JSONObject jo = new JSONObject(json);
			int count = jo.getInt(SectionList.SECTION_COUNT);
			String secs = jo.getString(SectionList.SECTION);

			Type type = new TypeToken<ArrayList<Section>>() {
			}.getType();
			ArrayList<Section> sections = new Gson().fromJson(secs, type);
			if (sections != null) {
				SectionList sectionList = new SectionList(count, sections);
				return sectionList;
			}
			return null;
		} catch (JsonSyntaxException e) {
			Log.e(TAG, e.getMessage());
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
