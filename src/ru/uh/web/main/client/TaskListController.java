package ru.uh.web.main.client;

import java.text.ParseException;
import java.util.List;

import ru.uh.web.main.shared.TaskInfoProxy;
import ru.uh.web.main.shared.TaskShowParams;

import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class TaskListController implements IsWidget, IUpdatable {

	private TasksGrid grid;
	private TaskInfoProperties window;
	private TextField realHF;
	private TextField comesHF;
	private TextField comesDays;

	@Override
	public Widget asWidget() {
		VerticalLayoutContainer container = new VerticalLayoutContainer();

		window = new TaskInfoProperties();
		window.setUpdatable(TaskListController.this);

		container.add(createToolBar());

		grid = TasksGrid.getCalculatorGrid();
		container.add(grid);
		return container;
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton addButton = new TextButton("Добавить новый тип работы");
		addButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				window.setData(null);
				window.show();
			}
		});
		TextButton editButton = new TextButton("Редактировать");
		editButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				List<TaskInfoProxy> list = grid.getSelectionModel().getSelectedItems();
				if (list == null || list.size() != 1) {
					Info.display("Предупреждение", "Невозможно произвести редактирование");
					return;
				}
				TaskInfoProxy object = list.get(0);
				window.setData(object);
				window.show();
			}
		});
		TextButton refreshButton = new TextButton("Обновить");
		refreshButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		});

		realHF = new TextField();
		realHF.setAllowBlank(false);
		realHF.setWidth(50);
		realHF.setEmptyText("часов");

		comesHF = new TextField();
		comesHF.setAllowBlank(false);
		comesHF.setWidth(50);
		comesHF.setEmptyText("часов");

		comesDays = new TextField();
		comesDays.setAllowBlank(false);
		comesDays.setWidth(50);
		comesDays.setEmptyText("деней");

		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(refreshButton);

		toolBar.add(new FieldLabel(realHF, "Текущий налет"));
		toolBar.add(new FieldLabel(comesHF, "Предупредить за"));
		toolBar.add(comesDays);

		return toolBar;
	}

	@Override
	public void update() {
		TaskShowParams params = new TaskShowParams();
		try {
			if (realHF.getValue() == null)
				throw new ParseException("", 0);
			params.setRealHF(IntegerParser.instance().parse(realHF.getValue()));
			if (comesDays.getValue() != null) {
				params.setComesDays(IntegerParser.instance().parse(comesDays.getValue()));
			}
			if (comesHF.getValue() != null) {
				params.setComesHF(IntegerParser.instance().parse(comesHF.getValue()));
			}
		} catch (ParseException ex) {
			Info.display("Ошибка", "Неверный числовой формат");
			return;
		}
		grid.updateData(params);

	}

}
