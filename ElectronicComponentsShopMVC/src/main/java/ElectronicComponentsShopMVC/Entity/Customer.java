package ElectronicComponentsShopMVC.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "KHACHHANG")
public class Customer {
	@Id
	private int khachhang_id;
	@NotEmpty(message = "Không được để trống họ và tên!")
	@Column(name = "khachhang_name")
	private String khachhang_name;
	@Pattern(regexp = "[0-9]{9,10}", message = "Số điện thoại không hợp lệ!")
	@Column(name = "khachhang_sdt")
	private String khachhang_sdt;
	@Column(name = "khachhang_gioitinh")
	private boolean khachhang_gioitinh;
	private String khachhang_anh;

	@OneToOne
	@JoinColumn(name = "khachhang_id")
	@MapsId
	private Users user;

	public Customer() {}

	public Customer(int khachhang_id, String khachhang_name, String khachhang_sdt, boolean khachhang_gioitinh,
			String khachhang_anh, Users user) {
		super();
		this.khachhang_id = khachhang_id;
		this.khachhang_name = khachhang_name;
		this.khachhang_sdt = khachhang_sdt;
		this.khachhang_gioitinh = khachhang_gioitinh;
		this.khachhang_anh = khachhang_anh;
		this.user = user;
	}

	public int getKhachhang_id() {
		return khachhang_id;
	}

	public void setKhachhang_id(int khachhang_id) {
		this.khachhang_id = khachhang_id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getKhachhang_name() {
		return khachhang_name;
	}

	public void setKhachhang_name(String khachhang_name) {
		this.khachhang_name = khachhang_name;
	}

	public String getKhachhang_sdt() {
		return khachhang_sdt;
	}

	public void setKhachhang_sdt(String khachhang_sdt) {
		this.khachhang_sdt = khachhang_sdt;
	}

	public boolean isKhachhang_gioitinh() {
		return khachhang_gioitinh;
	}

	public void setKhachhang_gioitinh(boolean khachhang_gioitinh) {
		this.khachhang_gioitinh = khachhang_gioitinh;
	}

	public String getKhachhang_anh() {
		return khachhang_anh;
	}

	public void setKhachhang_anh(String khachhang_anh) {
		this.khachhang_anh = khachhang_anh;
	}

}
