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
 * Created by Liutong on 13-7-26.
 */
public class SectionDetails extends Section implements Serializable {
	private static final String TAG = SectionDetails.class.getSimpleName();
	private static final boolean LOCAL_LOG_V = true;

	public static final String BOARD = "board";
	public static final String SUB_SECTION = "sub_section";

	private ArrayList<Board> boards;
	private ArrayList<String> subsections;

	public SectionDetails(ArrayList<Board> boards, ArrayList<String> subsections) {
		this.boards = boards;
		this.subsections = subsections;
	}

	public ArrayList<Board> getBoards() {
		return boards;
	}

	public void setBoards(ArrayList<Board> boards) {
		this.boards = boards;
	}

	public ArrayList<String> getSubsections() {
		return subsections;
	}

	public void setSubsections(ArrayList<String> subsections) {
		this.subsections = subsections;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(",");
		builder.append(BOARD).append(":").append(boards.size());
		builder.append(",");
		builder.append(SUB_SECTION).append(":").append(subsections.toString());
		return builder.toString();
	}

	public static SectionDetails parse(String json) {
		try {
			Gson gson = new Gson();
			SectionDetails details = gson.fromJson(json, SectionDetails.class);
			JSONObject jo = new JSONObject(json);
			Type type = new TypeToken<ArrayList<String>>() {
			}.getType();
			ArrayList<String> subsections = gson.fromJson(
					jo.getString(SectionDetails.SUB_SECTION), type);

			type = new TypeToken<ArrayList<Board>>() {
			}.getType();
			ArrayList<Board> boards = gson.fromJson(
					jo.getString(SectionDetails.BOARD), type);
			details.setSubsections(subsections);
			details.setBoards(boards);
			if (LOCAL_LOG_V) {
				Log.i(TAG, details.toString());
			}

			return details;
		} catch (JsonSyntaxException e) {
			Log.e(TAG, e.getMessage());
			return null;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
