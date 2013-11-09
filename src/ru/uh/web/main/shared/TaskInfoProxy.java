package ru.uh.web.main.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TaskInfoProxy  implements Serializable {

	private String id;
	private String taskNumber;
	private String taskTitle;
	private int limitHF;
	private int marginHF;
	private int limitMonth;
	private int marginDay;
	private int lastTaskHF;
	private Date lastTaskDate;
	private double diffHF;
	private double diffDays;

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

	public double getDiffHF() {
		return diffHF;
	}

	public void setDiffHF(double diffHF) {
		this.diffHF = diffHF;
	}

	public double getDiffDays() {
		return diffDays;
	}

	public void setDiffDays(double diffDays) {
		this.diffDays = diffDays;
	}

}