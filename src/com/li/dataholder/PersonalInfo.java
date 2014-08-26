package com.li.dataholder;

public class PersonalInfo {
	private String Name;
	private String dateOfBirth;
	private int Age;
	private String Contact;
	private String Address;
	private double Hight;
	private double Weight;
	public PersonalInfo(){
		
	}
	
	public void setName(String name){
		Name = name;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return Name;
	}
	
	public void setDate(String date){
		dateOfBirth = date;
	}
	public String getDate() {
		// TODO Auto-generated method stub
		return dateOfBirth;
	}
	public void setAge(String age){
		Age = Integer.parseInt(age);
	}
	
	public String getAge() {
		// TODO Auto-generated method stub
		return String.valueOf(Age);
	}
	
	public void setAddress(String address){
		Address = address;
	}
	
	public String getAddress(){
		return Address;
	}
	
	public void setContact(String contact){
		Contact = contact;
	}
	
	public String getContact(){
		return Contact;
	}
	
	public void setHight(String hight){
		Hight = Double.parseDouble(hight);
	}
	
	public String getHight(){
		return String.valueOf(Hight);
	}
	
	public void setWeight(String weight){
		Weight = Double.parseDouble(weight);
	}
	
	public String getWeight(){
		return String.valueOf(Weight);
	}
	

}
