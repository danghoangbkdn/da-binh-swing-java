package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dao.ListCustommerDao;
import dao.ListCustommerDaoImpl;
import entity.Custommer;
import entity.ServiceCharge;

public class ListCustommerServiceImpl implements ListCustommerService {

	private final ListCustommerDao dao;

	public ListCustommerServiceImpl() {
		dao = new ListCustommerDaoImpl();
	}

	@Override
	public Object[][] getListCustommer() {
		List<Custommer> list = dao.getListCustommer();
		Object[][] custommer = new Object[list.size()][5];

		for (int i = 0; i < list.size(); i++) {
			custommer[i][0] = list.get(i).getId();
			custommer[i][1] = list.get(i).getFullname();
			custommer[i][2] = list.get(i).getNumberPhone();
			custommer[i][3] = list.get(i).getAddress();
			custommer[i][4] = list.get(i).getStatus();
		}
		return custommer;
	}

	@Override
	public String getInfor(String idKH) {
		String result = "";
		int index = 1;
		for (Custommer cus : dao.getListCustommer()) {
			if (cus.getId().equals(idKH)) {
				result = "       THÔNG TIN KHÁCH HÀNG:\n\t" + index++ + ". Mã KH: " + cus.getId() + "\n\t" + index++
						+ ". Tên KH: " + cus.getFullname() + "\n\t" + index++ + ". Ngày SD: " + cus.getNgaySD() + "\n\t"
						+ index++ + ". Ngày HH: " + cus.getNgayHH() + "\n       THÔNG TIN THANH TOÁN:\n\t" + index++
						+ ". Trạng Thái: " + cus.getStatus();
				if (cus.getNgayTP() != null) {
					result += "\n\t" + index++ + ". Ngày Thu Phí: " + cus.getNgayTP();
				}
			}
		}
		for (ServiceCharge cus : dao.getListServiceCharge()) {
			if (cus.getIdKH().equals(idKH)) {
				result += "\n       THÔNG TIN DỊCH VỤ:\n\t" + index++ + ". Mã PDV: " + cus.getId() + "\n\t" + index++
						+ ". Tên Loại DV: " + cus.getFullname() + "\n\t" + index++ + ". Tiền Phí DV: " + cus.getPrice();
			}
		}
		return result;
	}

	@Override
	public void setStatus(String idKH) {
		for (Custommer cus : dao.getListCustommer()) {
			if (cus.getId().equals(idKH) && cus.getStatus().equals("Chưa thu")) {
				String pattern = "dd-MM-yyyy \n\t                             : HH:mm:ss";
				DateFormat df = new SimpleDateFormat(pattern);
				Date today = Calendar.getInstance().getTime();
				String todayAsString = df.format(today);
				String status = "Đã Thu";

				cus.setStatus(status);
				dao.setStatusAndDate(idKH, status, todayAsString);
			}
		}
	}

	@Override
	public String getCustommer(String id) {
		return dao.getListCustommer().stream().filter(c -> c.getId().equals(id)).collect(Collectors.toList()).get(0)
				.getStatus();
	}

	@Override
	public boolean checkStatusCustommer(String id) {
		return dao.getListCustommer().stream().filter(c -> c.getId().equals(id)).collect(Collectors.toList()).get(0)
				.getStatus().equals("Đã thu");
	}
}