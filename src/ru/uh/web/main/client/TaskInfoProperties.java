package ru.uh.web.main.client;

import java.text.ParseException;
import java.util.Date;

import ru.uh.web.main.shared.TaskInfoProxy;

import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class TaskInfoProperties extends CommonDirectoryWindow<TaskInfoProxy> {

	private TextField taskNumber;
	private TextField taskTitle;
	private TextField limitHF;
	private TextField marginHF;
	private TextField limitMonth;
	private TextField marginDays;
	private TextField lastTaskHF;
	private DateField lastTaskDate;

	public TaskInfoProperties() {
		super();
		setPixelSize(400, 250);
		setHeadingText("Работа");
	}

	private VerticalLayoutContainer p;

	@Override
	protected Container getInfoContainer() {
		if (p != null)
			return p;
		p = new VerticalLayoutContainer();

		taskNumber = new TextField();
		taskNumber.setAllowBlank(false);
		taskNumber.setEmptyText("Номер работы");
		p.add(new FieldLabel(taskNumber, "Номер"), new VerticalLayoutData(1, -1));

		taskTitle = new TextField();
		taskTitle.setAllowBlank(false);
		taskTitle.setEmptyText("Описание работы");
		p.add(new FieldLabel(taskTitle, "Описание"), new VerticalLayoutData(1, -1));

		HorizontalPanel hp1 = new HorizontalPanel();

		limitHF = new TextField();
		limitHF.setAllowBlank(false);
		limitHF.setEmptyText("в часах");
		limitHF.setWidth(60);
		hp1.add(new FieldLabel(limitHF, "Лимит налета"));

		marginHF = new TextField();
		marginHF.setAllowBlank(false);
		marginHF.setEmptyText("в часах");
		marginHF.setWidth(50);
		hp1.add(new FieldLabel(marginHF, "дельта"));

		p.add(hp1, new VerticalLayoutData(1, -1));

		HorizontalPanel hp2 = new HorizontalPanel();

		limitMonth = new TextField();
		limitMonth.setAllowBlank(false);
		limitMonth.setEmptyText("в месяцах");
		limitMonth.setWidth(60);
		hp2.add(new FieldLabel(limitMonth, "Лимит времени"));

		marginDays = new TextField();
		marginDays.setAllowBlank(false);
		marginDays.setEmptyText("в днях");
		marginDays.setWidth(50);
		hp2.add(new FieldLabel(marginDays, "дельта"));

		p.add(hp2, new VerticalLayoutData(1, -1));

		lastTaskHF = new TextField();
		lastTaskHF.setAllowBlank(false);
		lastTaskHF.setEmptyText("Налет на момент проведения работ");
		p.add(new FieldLabel(lastTaskHF, "Налет"), new VerticalLayoutData(1, -1));

		lastTaskDate = new DateField();
		lastTaskDate.setAllowBlank(false);
		lastTaskDate.setEmptyText("Дата проведения работ");
		p.add(new FieldLabel(lastTaskDate, "Дата"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	public void setData(TaskInfoProxy object) {
		this.object = object;
		if (object == null)
			return;
		taskNumber.setValue(object.getTaskNumber());
		taskTitle.setValue(object.getTaskTitle());

		limitHF.setValue(object.getLimitHF() + "");
		marginHF.setValue(object.getMarginHF() + "");

		limitMonth.setValue(object.getLimitMonth() + "");
		marginDays.setValue(object.getMarginDay() + "");

		lastTaskHF.setValue(object.getLastTaskHF() + "");
		lastTaskDate.setValue(object.getLastTaskDate());
	}

	@Override
	protected void editProcess() {
		if (object == null)
			object = new TaskInfoProxy();
		try {
			object.setTaskNumber(taskNumber.getValue());
			object.setTaskTitle(taskTitle.getValue());

			object.setLimitHF(IntegerParser.instance().parse(limitHF.getValue()));
			object.setMarginHF(IntegerParser.instance().parse(marginHF.getValue()));

			object.setLimitMonth(IntegerParser.instance().parse(limitMonth.getValue()));
			object.setMarginDay(IntegerParser.instance().parse(marginDays.getValue()));

			object.setLastTaskHF(IntegerParser.instance().parse(lastTaskHF.getValue()));
			object.setLastTaskDate(lastTaskDate.getValue());
		} catch (ParseException ex) {
			Info.display("Ошибка", "Неверный числовой формат");
			return;
		}
		UHMainModule.getService().editTask(object, new AsyncCallbackWithErrorMessage<TaskInfoProxy>() {
			@Override
			public void onSuccess(TaskInfoProxy result) {
				Info.display("Данные сохранены", result.getTaskNumber());
				if (updatableForm != null)
					updatableForm.update();
			}
		});
	}

	public void setAsProperties() {
		setEnableProperties(true);
	}

	public void setAsFact() {
		setEnableProperties(false);
		lastTaskDate.setValue(new Date());
	}

	private void setEnableProperties(boolean value) {
		taskNumber.setEnabled(value);
		taskTitle.setEnabled(value);
		limitHF.setEnabled(value);
		marginHF.setEnabled(value);
		limitMonth.setEnabled(value);
		marginDays.setEnabled(value);
	}

}
