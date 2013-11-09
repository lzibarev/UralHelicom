package ru.uh.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.uh.web.main.shared.TaskInfoProxy;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class TaskInfo {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String taskNumber;
	@Persistent
	private String taskTitle;
	@Persistent
	private int limitHF;
	@Persistent
	private int marginHF;
	@Persistent
	private int limitMonth;
	@Persistent
	private int marginDay;
	@Persistent
	private int lastTaskHF;
	@Persistent
	private Date lastTaskDate;
	@Persistent
	private double diffHF;
	@Persistent
	private double diffMonth;

	public TaskInfo() {
	}

	public TaskInfoProxy asProxy() {
		TaskInfoProxy proxy = new TaskInfoProxy();
		proxy.setId(KeyFactory.keyToString(key));
		proxy.setTaskNumber(taskNumber);
		proxy.setTaskTitle(taskTitle);
		proxy.setLimitHF(limitHF);
		proxy.setLimitMonth(limitMonth);
		proxy.setLastTaskDate(lastTaskDate);
		proxy.setLastTaskHF(lastTaskHF);
		proxy.setMarginDay(marginDay);
		proxy.setMarginHF(marginHF);
		return proxy;
	}

	public Key getKey() {
		return key;
	}
}
