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

import ElectronicComponentsShopMVC.Service.Algorithm.AES256;

@Entity
@Table(name = "NHANVIEN")
public class Emloyee {
	@Id
	private int nhanvien_id;
	@NotEmpty(message = "Không được để trống họ và tên!")
	@Column(name = "nhanvien_name")
	private String nhanvien_name;
	@Pattern(regexp = "[0-9]{9,10}", message = "Số điện thoại không hợp lệ!")
	@Column(name = "nhanvien_sdt")
	private String nhanvien_sdt;
	
	@Column(name = "nhanvien_gioitinh")
	private boolean nhanvien_gioitinh;

	@Column(name = "nhanvien_luong")
	private String nhanvien_luong;
	@OneToOne
	@JoinColumn(name = "nhanvien_id")
	@MapsId
	private Users usernv;

	public Emloyee() {
		super();
	}

	public Emloyee(int nhanvien_id, String nhanvien_name, String nhanvien_sdt, boolean nhanvien_gioitinh,
			String nhanvien_luong, Users usernv) {
		super();
		this.nhanvien_id = nhanvien_id;
		this.nhanvien_name = nhanvien_name;
		this.nhanvien_sdt = nhanvien_sdt;
		this.nhanvien_gioitinh = nhanvien_gioitinh;
		this.nhanvien_luong = nhanvien_luong;
		this.usernv = usernv;
	}

	public String getNhanvien_luong() {
		return nhanvien_luong;
	}

	public void setNhanvien_luong(String nhanvien_luong) {
		this.nhanvien_luong = nhanvien_luong;
	}

	public int getNhanvien_id() {
		return nhanvien_id;
	}

	public void setNhanvien_id(int nhanvien_id) {
		this.nhanvien_id = nhanvien_id;
	}

	public String getNhanvien_name() {
		return nhanvien_name;
	}

	public void setNhanvien_name(String nhanvien_name) {
		this.nhanvien_name = nhanvien_name;
	}

	public String getNhanvien_sdt() {
		return nhanvien_sdt;
	}

	public void setNhanvien_sdt(String nhanvien_sdt) {
		this.nhanvien_sdt = nhanvien_sdt;
	}

	public boolean isNhanvien_gioitinh() {
		return nhanvien_gioitinh;
	}

	public void setNhanvien_gioitinh(boolean nhanvien_gioitinh) {
		this.nhanvien_gioitinh = nhanvien_gioitinh;
	}

	public Users getUsernv() {
		return usernv;
	}

	public void setUsernv(Users usernv) {
		this.usernv = usernv;
	}

	public String getNhanvien_luongd() {
		return AES256.decrypt2(nhanvien_luong, "123");
	}


}
