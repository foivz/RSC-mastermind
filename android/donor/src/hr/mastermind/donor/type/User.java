package hr.mastermind.donor.type;


public class User {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String apiKey;

	private String phone;
	private String city;
	private String sex;
	private String bloodType;
	private String year;
	private int role;
	private boolean pierce = false;
	private boolean tattoo = false;
	private boolean sickness = false;
	private String weight;
	private String message ;
	private int score;
	
	

	public User(String email, String password, String firstName,
			String lastName, String apiKey,  int role) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.apiKey = apiKey;
	
		this.role = role;
	}

	public User(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(String email, String password, String firstName, String lastName, String phone, String city, String year, String sex, String bloodType, String weight) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.city = city;
		this.year = year;
		this.sex = sex;
		this.bloodType = bloodType;		
		this.setWeight(weight);
		
	}
	public User(String email, String password, String firstName, String lastName, String phone, String city, String year, String sex, String bloodType, String weight, String piercing, String tattoo, String sickness) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.city = city;
		this.year = year;
		this.sex = sex;
		this.bloodType = bloodType;		
		this.setWeight(weight);
		this.sickness = Boolean.parseBoolean(sickness);
		this.tattoo = Boolean.parseBoolean(tattoo);
		this.pierce = Boolean.parseBoolean(piercing);
		
	}
	public User(String email, String password, String firstName, String lastName, String phone, String city, String year, String sex, String bloodType, String weight, String piercing, String tattoo, String sickness, int score) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.city = city;
		this.year = year;
		this.sex = sex;
		this.bloodType = bloodType;		
		this.setWeight(weight);
		this.sickness = Boolean.parseBoolean(sickness);
		this.tattoo = Boolean.parseBoolean(tattoo);
		this.pierce = Boolean.parseBoolean(piercing);
		this.setScore(score);
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean isPierce() {
		return pierce;
	}

	public void setPierce(boolean pierce) {
		this.pierce = pierce;
	}

	public boolean isTattoo() {
		return tattoo;
	}

	public void setTattoo(boolean tattoo) {
		this.tattoo = tattoo;
	}

	public boolean isSickness() {
		return sickness;
	}

	public void setSickness(boolean sickness) {
		this.sickness = sickness;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
