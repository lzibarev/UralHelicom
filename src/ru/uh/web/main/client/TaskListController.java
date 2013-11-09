package ru.uh.web.main.client;

import java.util.List;

import ru.uh.web.main.shared.TaskInfoProxy;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class TaskListController implements IsWidget, IUpdatable {

	private TasksGrid grid;

	@Override
	public Widget asWidget() {
		VerticalLayoutContainer container = new VerticalLayoutContainer();

		container.add(createToolBar());

		grid = TasksGrid.getCalculatorGrid();
		container.add(grid);
		return container;
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();

		TextButton addButton = new TextButton("Добавить");
		addButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TaskInfoProperties window = new TaskInfoProperties();
				window.setUpdatable(TaskListController.this);
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
				TaskInfoProperties window = new TaskInfoProperties();
				window.setUpdatable(TaskListController.this);
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
		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(refreshButton);

		return toolBar;
	}

	@Override
	public void update() {
		grid.updateData();
	}

}
