package ru.uh.web.main.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TaskShowParams implements Serializable {

	private int realHF;
	private int comesHF;
	private int comesDays;

	public int getRealHF() {
		return realHF;
	}

	public void setRealHF(int realHF) {
		this.realHF = realHF;
	}

	public int getComesHF() {
		return comesHF;
	}

	public void setComesHF(int comesHF) {
		this.comesHF = comesHF;
	}

	public int getComesDays() {
		return comesDays;
	}

	public void setComesDays(int comesDays) {
		this.comesDays = comesDays;
	}

}
