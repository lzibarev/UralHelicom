package ru.uh.web.main.shared;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

@SuppressWarnings("serial")
public class TaskInfoProxy implements Serializable {

	private String id;
	private String taskNumber;
	private String taskTitle;
	private int limitHF;
	private int marginHF;
	private int limitMonth;
	private int marginDay;
	private int lastTaskHF;
	private Date lastTaskDate;
	private String diffHFStr;
	private String diffDaysStr;
	private int priority;

	public String getLimitHFGrid() {
		return limitHF + " ± " + marginHF + " часов";
	}

	public String getLimitMonthGrid() {
		return limitMonth + " ± " + marginDay + " дней";
	}

	public String getLastTaskGrid() {
		DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy/MM/dd");
		return dtf.format(lastTaskDate) + "  (" + lastTaskHF + " часов)";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public int getLimitHF() {
		return limitHF;
	}

	public void setLimitHF(int limitHF) {
		this.limitHF = limitHF;
	}

	public int getMarginHF() {
		return marginHF;
	}

	public void setMarginHF(int marginHF) {
		this.marginHF = marginHF;
	}

	public int getLimitMonth() {
		return limitMonth;
	}

	public void setLimitMonth(int limitMonth) {
		this.limitMonth = limitMonth;
	}

	public int getMarginDay() {
		return marginDay;
	}

	public void setMarginDay(int marginDay) {
		this.marginDay = marginDay;
	}

	public int getLastTaskHF() {
		return lastTaskHF;
	}

	public void setLastTaskHF(int lastTaskHF) {
		this.lastTaskHF = lastTaskHF;
	}

	public Date getLastTaskDate() {
		return lastTaskDate;
	}

	public void setLastTaskDate(Date lastTaskDate) {
		this.lastTaskDate = lastTaskDate;
	}

	public String getDiffHFStr() {
		return diffHFStr;
	}

	public void setDiffHFStr(String diffHFStr) {
		this.diffHFStr = diffHFStr;
	}

	public String getDiffDaysStr() {
		return diffDaysStr;
	}

	public void setDiffDaysStr(String diffDaysStr) {
		this.diffDaysStr = diffDaysStr;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

}
