package ru.uh.web.main.client;

import java.util.ArrayList;
import java.util.List;

import ru.uh.web.main.shared.TaskInfoProxy;

import com.google.gwt.core.client.GWT;
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

		ColumnConfig<TaskInfoProxy, String> nameCol = new ColumnConfig<TaskInfoProxy, String>(props.taskNumber(), 300, "Код работы");
		ColumnConfig<TaskInfoProxy, String> customerCol = new ColumnConfig<TaskInfoProxy, String>(props.taskTitle(), 200, "Описание работы");
		ColumnConfig<TaskInfoProxy, String> legalNumberCol = new ColumnConfig<TaskInfoProxy, String>(props.limitHFGrid(), 200, "Лимит часов");

		List<ColumnConfig<TaskInfoProxy, ?>> l = new ArrayList<ColumnConfig<TaskInfoProxy, ?>>();
		l.add(nameCol);
		l.add(customerCol);
		l.add(legalNumberCol);

		ColumnModel<TaskInfoProxy> model = new ColumnModel<TaskInfoProxy>(l);
		return new TasksGrid(store, model);
	}

	private TasksGrid(ListStore<TaskInfoProxy> store, ColumnModel<TaskInfoProxy> model) {
		super(store, model);

		setHeight(500);
		setLoadOnInit(true);
	}

	@Override
	protected PagingLoader<PagingLoadConfig, PagingLoadResult<TaskInfoProxy>> createLoader() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<TaskInfoProxy>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<TaskInfoProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final AsyncCallback<PagingLoadResult<TaskInfoProxy>> callback) {
				UHMainModule.getService().getTasks(new AsyncCallbackWithErrorMessage<List<TaskInfoProxy>>() {

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
