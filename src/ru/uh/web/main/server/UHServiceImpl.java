package ru.uh.web.main.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.uh.server.model.PMF;
import ru.uh.server.model.TaskInfo;
import ru.uh.web.main.client.UHService;
import ru.uh.web.main.shared.TaskInfoProxy;
import ru.uh.web.main.shared.TaskShowParams;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class UHServiceImpl extends RemoteServiceServlet implements UHService {

	@SuppressWarnings("unchecked")
	public List<TaskInfoProxy> getTasks(TaskShowParams params) {
		List<TaskInfoProxy> list = new ArrayList<TaskInfoProxy>();
		List<TaskInfo> tasks = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(TaskInfo.class);
			tasks = (List<TaskInfo>) query.execute();
		} finally {
			em.close();
		}

		for (TaskInfo taskInfo : tasks) {
			TaskInfoProxy proxy = taskInfo.asProxy();
			updatePriority(proxy, params);
			list.add(proxy);
		}
		System.out.println(list.size());
		return list;
	}

	private void updatePriority(TaskInfoProxy proxy, TaskShowParams params) {
		Date now = new Date();
		if (proxy.getLimitMonth() != 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(proxy.getLastTaskDate());
			calendar.add(Calendar.MONTH, proxy.getLimitMonth());
			calendar.add(Calendar.DAY_OF_YEAR, -proxy.getMarginDay());
			if (now.after(calendar.getTime())) {
				proxy.setPriority(2);
				calendar.add(Calendar.DAY_OF_YEAR, proxy.getMarginDay() * 2);
				if (now.after(calendar.getTime())) {
					proxy.setPriority(3);
				}
			} else {
				calendar.add(Calendar.DAY_OF_YEAR, -params.getComesDays());
				if (now.after(calendar.getTime())) {
					proxy.setPriority(1);
				}
			}
		}
		if (proxy.getLimitHF() != 0) {

			int fh = proxy.getLastTaskHF();
			fh += proxy.getLimitHF();
			fh -= proxy.getMarginHF();
			if (params.getRealHF() > fh) {
				proxy.setPriority(2);
				fh += 2 * proxy.getMarginHF();
				if (params.getRealHF() > fh) {
					proxy.setPriority(3);
				}
			} else {
				fh -= params.getComesHF();
				if (params.getRealHF() > fh) {
					proxy.setPriority(1);
				}
			}
		}

	}

	@Override
	public TaskInfoProxy editTask(TaskInfoProxy proxy) {
		TaskInfo task = new TaskInfo(proxy);
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			em.makePersistent(task);
		} finally {
			em.close();
		}
		System.out.println(task.getKey().toString());
		return task.asProxy();
	}
}
