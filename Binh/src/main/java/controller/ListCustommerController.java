package controller;

public interface ListCustommerController {
	Object[][] getListCustommer();

	String getInfor(String idKH);

	void setStatus(String idKH);

	String getCustommer(String id);

	boolean checkStatusCustommer(String id);
}