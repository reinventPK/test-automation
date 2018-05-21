package com.hellofresh.authenticationtests;

/* ==============================================================
 * POJO to store new user details by reading it from NewUser.json 
 * file.
 * ==============================================================*/

public class NewUser {
	private String firstName;
	private String lastName;
	private String password;
	private String birthDate;
	private String company;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String otherInfo;
	private String phone;
	private String mobilePhone;
	private String alias;
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getCompany() {
		return company;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public String getPhone() {
		return phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getAlias() {
		return alias;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "NewUser [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", birthDate="
				+ birthDate + ", company=" + company + ", address1=" + address1 + ", address2=" + address2 + ", city="
				+ city + ", state=" + state + ", postalCode=" + postalCode + ", otherInfo=" + otherInfo + ", phone="
				+ phone + ", mobilePhone=" + mobilePhone + ", alias=" + alias + "]";
	}

}
