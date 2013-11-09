package ru.uh.web.main.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ru.uh.server.model.DateUtils;
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
		Collections.sort(list);
		System.out.println(list.size());
		return list;
	}

	/**
	 * switch (priority) { case 0: return "Запланированно"; case 1: return
	 * "Приближается срок"; case 2: return "Лимит достигнут"; case 3: return
	 * "Лимит прeвышен"; } return ""; *
	 */

	private void updatePriority(TaskInfoProxy proxy, TaskShowParams params) {
		if (proxy.getLimitMonth() != 0) {
			Date now = DateUtils.getDateNoTime(new Date());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtils.getDateNoTime(proxy.getLastTaskDate()));
			calendar.add(Calendar.MONTH, proxy.getLimitMonth());
			calendar.add(Calendar.DAY_OF_YEAR, -proxy.getMarginDay());
			proxy.setDiffDaysStr("Запланированно через " + DateUtils.diffInDays(now, calendar.getTime()) + " дней");
			if (now.after(calendar.getTime()) || now.equals(calendar.getTime())) {
				proxy.setPriority(2);
				calendar.add(Calendar.DAY_OF_YEAR, proxy.getMarginDay() * 2);
				proxy.setDiffDaysStr("Лимит достигнут до окончания " + DateUtils.diffInDays(now, calendar.getTime()) + " дней");
				if (now.after(calendar.getTime()) || now.equals(calendar.getTime()) ) {
					proxy.setPriority(3);
					proxy.setDiffDaysStr("Лимит прeвышен на " + DateUtils.diffInDays(calendar.getTime(), now) + " дней");
				}
			} else {
				calendar.add(Calendar.DAY_OF_YEAR, -params.getComesDays());
				if (now.after(calendar.getTime())) {
					proxy.setPriority(1);
					calendar.add(Calendar.DAY_OF_YEAR, params.getComesDays());
					proxy.setDiffDaysStr("Приближается срок через " + DateUtils.diffInDays(now, calendar.getTime()) + " дней");
				}
			}

		}
		if (proxy.getLimitHF() != 0) {

			int fh = proxy.getLastTaskHF();
			fh += proxy.getLimitHF();
			fh -= proxy.getMarginHF();
			proxy.setDiffHFStr("Запланированно через " + (fh - params.getRealHF()) + " часов");
			if (params.getRealHF() >= fh) {
				proxy.setPriority(2);
				fh += 2 * proxy.getMarginHF();
				proxy.setDiffHFStr("Лимит достигнут до окончания " + (fh - params.getRealHF()) + " часов");
				if (params.getRealHF() >= fh) {
					proxy.setPriority(3);
					proxy.setDiffHFStr("Лимит прeвышен на " + (params.getRealHF() - fh) + " часов");
				}
			} else {
				fh -= params.getComesHF();
				if (params.getRealHF() > fh) {
					proxy.setPriority(1);
					fh += params.getComesHF();
					proxy.setDiffHFStr("Приближается срок через " + (fh - params.getRealHF()) + " часов");
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
