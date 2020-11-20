package entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("serial")
public class Custommer implements Serializable {
	private String id;
	private String fullname;
	private String address;
	private String numberPhone;
	private String NgaySD;
	private String NgayHH;
	private String status;
	private String NgayTP;
	private String tenPDV;
	private Float price;

	public Custommer() {
	}

	public Custommer(String id, String fullname, String numberPhone, String address, String NgaySD, String NgayHH,
			Boolean status, String NgayTP, String idPDV, String tenPDV, Float price) {
		this.id = id;
		this.fullname = fullname;
		this.address = address;
		this.numberPhone = numberPhone;
		this.NgaySD = NgaySD;
		this.NgayHH = NgayHH;
		this.status = status ? "Đã thu" : "Chưa thu";
		this.NgayTP = NgayTP;
		this.tenPDV = tenPDV;
		this.price = price;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getNgaySD() {
		return NgaySD;
	}

	public void setNgaySD(String NgaySD) {
		this.NgaySD = NgaySD;
	}

	public String getNgayHH() {
		return NgayHH;
	}

	public void setNgayHH(String NgayHH) {
		this.NgayHH = NgayHH;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNgayTP() {
		return NgayTP;
	}

	public void setNgayTP(Date ngayTP) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy\n  		: HH:mm:ss");
		this.NgayTP = df.format(ngayTP);
	}

	public String getTenPDV() {
		return tenPDV;
	}

	public void setTenPDV(String tenPDV) {
		this.tenPDV = tenPDV;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
	public String toString() {
		return "Custommer [id=" + id + ", fullname=" + fullname + ", address=" + address + ", numberPhone="
				+ numberPhone + ", NgaySD=" + NgaySD + ", NgayHH=" + NgayHH + ", status=" + status + ", NgayTP="
				+ NgayTP + ", tenPDV=" + tenPDV + ", price=" + price + "]";
	}
}