package com.byr.bbs.meta;

import java.util.ArrayList;

/**
 * @author Liutong
 * 
 *         <p>
 *         添加或删除收藏夹时的返回数据
 *         </p>
 */
public class FavoriteResult {

	private static final String TAG = FavoriteResult.class.getSimpleName();

	public static final String SUB_FAVORITE = "sub_favorite";
	public static final String SECTION = "section";
	public static final String BOARD = "board";

	/**
	 * <p>
	 * 该层收藏夹包含的自定义目录的数组, 数组元素为收藏夹元数据
	 * </p>
	 */
	private ArrayList<Favorite> sub_favorites;

	/**
	 * <p>
	 * 该层收藏夹包含的分区的数组, 数组元素为分区元数据
	 * </p>
	 */
	private ArrayList<Section> sections;

	/**
	 * <p>
	 * 该层收藏夹包含的版面的数组, 数组元素为版面元数据
	 * </p>
	 */
	private ArrayList<Board> boards;

	public ArrayList<Favorite> getSub_favorites() {
		return sub_favorites;
	}

	public void setSub_favorites(ArrayList<Favorite> sub_favorites) {
		this.sub_favorites = sub_favorites;
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public ArrayList<Board> getBoards() {
		return boards;
	}

	public void setBoards(ArrayList<Board> boards) {
		this.boards = boards;
	}

}
