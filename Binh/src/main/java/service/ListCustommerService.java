package service;

public interface ListCustommerService {
	Object[][] getListCustommer();

	String getInfor(String idKH);

	void setStatus(String idKH);

	String getCustommer(String id);

	boolean checkStatusCustommer(String id);
}