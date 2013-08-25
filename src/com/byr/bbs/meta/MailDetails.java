package com.byr.bbs.meta;

import java.io.Serializable;
import java.util.ArrayList;

public class MailDetails implements Serializable {

	private static final String TAG = MailDetails.class.getSimpleName();

	public static final String DESCRIPTION = "description";
	public static final String MAIL = "mail";
	public static final String PAGINATION = "pagination";

	/**
	 * 信箱类型描述(收件箱|发件箱|废纸篓)
	 */
	private String description;
	private ArrayList<Mail> mails;
	private Pagination pagination;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Mail> getMails() {
		return mails;
	}

	public void setMails(ArrayList<Mail> mails) {
		this.mails = mails;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
