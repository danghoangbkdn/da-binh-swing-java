package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable {

	private String request;
	private Custommer custommer;

	public Request(String requets) {
		this.setRequest(requets);
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Custommer getCustommer() {
		return custommer;
	}

	public void setCustommer(Custommer custommer) {
		this.custommer = custommer;
	}
}