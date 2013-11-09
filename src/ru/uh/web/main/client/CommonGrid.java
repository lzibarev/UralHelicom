package ru.uh.web.main.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public abstract class CommonGrid<T> extends Grid<T> {

	protected final PagingLoader<PagingLoadConfig, PagingLoadResult<T>> loader;

	private boolean loadOnInit;

	protected CommonGrid(ListStore<T> store, ColumnModel<T> model) {
		super(store, model);

		loader = createLoader();
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, T, PagingLoadResult<T>>(store));

		this.getView().setForceFit(true);
		this.setLoadMask(true);
		this.setLoader(loader);

		this.setLoadOnInit(true);
	}

	protected abstract PagingLoader<PagingLoadConfig, PagingLoadResult<T>> createLoader();

	public void updateData() {
		loader.load();
	}

	protected void setLoadOnInit(boolean loadOnInit) {
		this.loadOnInit = loadOnInit;
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		if (loadOnInit)
			inOnAfterFirstAttach();
	}

	protected void inOnAfterFirstAttach() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				loader.load();
			}
		});
	}
}
