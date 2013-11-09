package ru.uh.web.main.client;

import java.util.ArrayList;
import java.util.List;

import ru.uh.web.main.shared.TaskInfoProxy;
import ru.uh.web.main.shared.TaskShowParams;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class TasksGrid extends CommonGrid<TaskInfoProxy> {

	private static final TaskInfoProxyProperties props;

	static {
		props = GWT.create(TaskInfoProxyProperties.class);
	}

	public static TasksGrid getCalculatorGrid() {
		ListStore<TaskInfoProxy> store = new ListStore<TaskInfoProxy>(props.key());

		ColumnConfig<TaskInfoProxy, Integer> priorityCol = new ColumnConfig<TaskInfoProxy, Integer>(props.priority(), 50, "Приоритет");

		ColumnConfig<TaskInfoProxy, String> taskNameCol = new ColumnConfig<TaskInfoProxy, String>(props.taskNumber(), 100, "Код работы");
		ColumnConfig<TaskInfoProxy, String> taskTitleCol = new ColumnConfig<TaskInfoProxy, String>(props.taskTitle(), 200, "Описание работы");
		ColumnConfig<TaskInfoProxy, String> limitHFCol = new ColumnConfig<TaskInfoProxy, String>(props.limitHFGrid(), 100, "Лимит налета");
		ColumnConfig<TaskInfoProxy, String> limitMonthCol = new ColumnConfig<TaskInfoProxy, String>(props.limitMonthGrid(), 100, "Лимит времени");
		ColumnConfig<TaskInfoProxy, String> lastTaskCol = new ColumnConfig<TaskInfoProxy, String>(props.lastTaskGrid(), 200, "Последняя работа");
		ColumnConfig<TaskInfoProxy, String> taskDiffHFCol = new ColumnConfig<TaskInfoProxy, String>(props.diffHFStr(), 200, "Интервал налет");
		ColumnConfig<TaskInfoProxy, String> taskDiffDaysCol = new ColumnConfig<TaskInfoProxy, String>(props.diffDaysStr(), 200, "Интервал время");

		AbstractCell<String> c = new AbstractCell<String>() {

			@Override
			public void render(Context context, String value, SafeHtmlBuilder sb) {
				String color = value.startsWith("Лимит прeвышен") ? "red" : value.startsWith("Лимит достигнут") ? "green"
						: value.startsWith("Приближается") ? "blue" : "wight";
				String style = "style='color: " + color + "'";
				sb.appendHtmlConstant("<span " + style + " qtitle='Change' qtip='" + value + "'>" + value + "</span>");
			}

		};

		taskDiffHFCol.setCell(c);
		taskDiffHFCol.setCell(c);

		List<ColumnConfig<TaskInfoProxy, ?>> l = new ArrayList<ColumnConfig<TaskInfoProxy, ?>>();
		l.add(priorityCol);
		l.add(taskDiffDaysCol);
		l.add(taskDiffHFCol);
		l.add(taskNameCol);
		l.add(taskTitleCol);
		l.add(limitHFCol);
		l.add(limitMonthCol);
		l.add(lastTaskCol);

		ColumnModel<TaskInfoProxy> model = new ColumnModel<TaskInfoProxy>(l);
		return new TasksGrid(store, model);
	}

	private TasksGrid(ListStore<TaskInfoProxy> store, ColumnModel<TaskInfoProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(false);
	}

	private TaskShowParams params;

	public void updateData(TaskShowParams params) {
		this.params = params;
		super.updateData();
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<TaskInfoProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<TaskInfoProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<TaskInfoProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<TaskInfoProxy>> callback) {
				UHMainModule.getService().getTasks(params, new AsyncCallbackWithErrorMessage<List<TaskInfoProxy>>() {

					@Override
					public void onSuccess(List<TaskInfoProxy> result) {
						PagingLoadResultBean<TaskInfoProxy> r = new PagingLoadResultBean<TaskInfoProxy>();
						r.setData(result);
						callback.onSuccess(r);
					}
				});
			}
		};
		return new PagingLoader<PagingLoadConfig, PagingLoadResult<TaskInfoProxy>>(proxy);
	}
}
