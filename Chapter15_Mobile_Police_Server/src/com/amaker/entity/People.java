package com.amaker.entity;

/**
 * 
 * @author ������
 * �������O�A����escapedpeopletbl��ƪ��c
 */
public class People {
	private int id;
	private String name;
	private int age;
	private String gender;
	private String address;
	private String idno;
	private String crimerecord;
	private String pic;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCrimerecord() {
		return crimerecord;
	}

	public void setCrimerecord(String crimerecord) {
		this.crimerecord = crimerecord;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	/**
	 * �л\toString()��k��^People���r��
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		
		sb.append("�����Ҧr���G");
		sb.append(idno);
		sb.append("\n");
		
		sb.append("�m�W�G");
		sb.append(name);
		sb.append("\n");
		
		sb.append("�ʧO�G");
		sb.append(gender);
		sb.append("\n");
		
		sb.append("�~�֡G");
		sb.append(age);
		sb.append("\n");
		
		sb.append("�y�e�G");
		sb.append(address);
		sb.append("\n");
		
		sb.append("�Ǹo�����G");
		sb.append(crimerecord);
		sb.append("\n");
		
		return sb.toString();
	}

}
