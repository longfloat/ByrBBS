package com.byr.bbs.meta;

public class Mailbox {

	private static final String TAG = Mailbox.class.getSimpleName();

	public static final String INBOX = "inbox";
	public static final String OUTBOX = "outbox";
	public static final String DELETED = "deleted";

	public static final String NEW_MAIL = "new_mail";
	public static final String FULL_MAIL = "full_mail";
	public static final String SPACE_USED = "space_used";
	public static final String CAN_SEND = "can_send";

	private boolean new_mail;
	private boolean full_mail;
	private String space_used;
	private boolean can_send;

	public boolean isNew_mail() {
		return new_mail;
	}

	public void setNew_mail(boolean new_mail) {
		this.new_mail = new_mail;
	}

	public boolean isFull_mail() {
		return full_mail;
	}

	public void setFull_mail(boolean full_mail) {
		this.full_mail = full_mail;
	}

	public String getSpace_used() {
		return space_used;
	}

	public void setSpace_used(String space_used) {
		this.space_used = space_used;
	}

	public boolean isCan_send() {
		return can_send;
	}

	public void setCan_send(boolean can_send) {
		this.can_send = can_send;
	}

}
