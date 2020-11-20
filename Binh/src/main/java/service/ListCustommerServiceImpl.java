package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.ListCustommerDao;
import dao.ListCustommerDaoImpl;
import entity.Custommer;

public class ListCustommerServiceImpl implements ListCustommerService {

	private final ListCustommerDao dao;

	public ListCustommerServiceImpl() {
		dao = new ListCustommerDaoImpl();
	}

	@Override
	public List<Custommer> getListCustommers() {
		return dao.getListCustommers();
	}

	@Override
	public Custommer setStatus(Custommer custommer) {
		if (custommer.getStatus().equals("Chưa thu")) {
			String pattern = "dd-MM-yyyy \n\t                             : HH:mm:ss";
			DateFormat df = new SimpleDateFormat(pattern);
			Date today = Calendar.getInstance().getTime();
			String todayAsString = df.format(today);
			String status = "Đã Thu";

			custommer.setStatus(status);
			dao.setStatusAndDate(custommer.getId(), status, todayAsString);
		}
		return custommer;
	}

	@Override
	public Custommer getCustommer(String id) {
		return dao.getCustommer(id);
	}
}