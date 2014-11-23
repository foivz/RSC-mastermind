package hr.mastermind.donor.type;

public class Institution {
	private String name;
	private String adress;
	private String phone;
	private String city;
	private int Aplus;
	private int Aminus;
	private int Bplus;
	private int Bminus;
	private int ABplus;
	private int ABminus;
	private int ZeroMinus;
	private int ZeroPlus;
	
	
	
	public Institution(String name, String adress, String phone, String city,
			int aplus, int aminus, int bplus, int bminus, int aBplus,
			int aBminus, int zeroMinus, int zeroPlus) {
		super();
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.city = city;
		Aplus = aplus;
		Aminus = aminus;
		Bplus = bplus;
		Bminus = bminus;
		ABplus = aBplus;
		ABminus = aBminus;
		ZeroMinus = zeroMinus;
		ZeroPlus = zeroPlus;
	}
	public int getZeroPlus() {
		return ZeroPlus;
	}
	public void setZeroPlus(int zeroPlus) {
		ZeroPlus = zeroPlus;
	}
	public int getZeroMinus() {
		return ZeroMinus;
	}
	public void setZeroMinus(int zeroMinus) {
		ZeroMinus = zeroMinus;
	}
	public int getABminus() {
		return ABminus;
	}
	public void setABminus(int aBminus) {
		ABminus = aBminus;
	}
	public int getABplus() {
		return ABplus;
	}
	public void setABplus(int aBplus) {
		ABplus = aBplus;
	}
	public int getBminus() {
		return Bminus;
	}
	public void setBminus(int bminus) {
		Bminus = bminus;
	}
	public int getBplus() {
		return Bplus;
	}
	public void setBplus(int bplus) {
		Bplus = bplus;
	}
	public int getAminus() {
		return Aminus;
	}
	public void setAminus(int aminus) {
		Aminus = aminus;
	}
	public int getAplus() {
		return Aplus;
	}
	public void setAplus(int aplus) {
		Aplus = aplus;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
