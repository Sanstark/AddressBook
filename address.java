package com;
import java.util.Scanner; 
class address_update 
{
	String first_name,last_name,age,address,city,zip,phone_no,email;
	public void display() 
    { 
		System.out.println("Emplyee details are: ");
		System.out.println("Name: "+first_name+" "+last_name+"   Age:"+age+"  Address:"+address+" "+city+" "+zip+"  Contact: "+phone_no+" "+email);	
    }
	public void input()
	{
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter your first name: ");
		first_name = myObj.nextLine();
		System.out.println("Enter your last name:");
		last_name=myObj.nextLine();
		System.out.println("Enter your age: ");
		age=myObj.nextLine();
		System.out.println("Enter your address: ");
		address=myObj.nextLine();
		System.out.println("Enter your city");
		city= myObj.nextLine();
		System.out.println("Enter your zip code: ");
		zip=myObj.nextLine();
		System.out.println("Enter your contact no:  ");
		phone_no=myObj.nextLine();
		System.out.println("Enter your email_id: ");
		email=myObj.nextLine();
	}
	
}
public class address
{
	public static void main(String[] args)
	{	
		address_update obj1=new address_update();
		obj1.input();
		obj1.display();	
	}
}
