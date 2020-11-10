package entity;

import java.util.Objects;

public class ServiceCharge {
	private String id;
	private String fullname;
	private Float price;
	private String idKH;

	public ServiceCharge() {
	}

	public ServiceCharge(String id, String fullname, Float price, String idKH) {
		this.id = id;
		this.fullname = fullname;
		this.price = price;
		this.idKH = idKH;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getIdKH() {
		return idKH;
	}

	public void setIdKH(String idKH) {
		this.idKH = idKH;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Custommer)) {
			return false;
		}
		Custommer that = (Custommer) obj;
		return getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ServiceCharge [id=" + id + ", fullname=" + fullname + ", price=" + price + ", idKH=" + idKH + "]";
	}
}