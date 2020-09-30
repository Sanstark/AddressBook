package com;
import java.util.Scanner; 
class address_update 
{
	String first_name,last_name,age,address,city,zip,phone_no,email;
	String[]database = new String[8];
	public void display() 
    { 
		System.out.println("Emplyee details are: ");
		System.out.println("Name: "+first_name+" "+last_name+"   Age:"+age+"  Address:"+address+" "+city+" "+zip+"  Contact: "+phone_no+" "+email);	
    }
	public void f_name()
	{
		Scanner myObj= new Scanner(System.in);
		System.out.println("Enter your first name: ");
		first_name = myObj.nextLine();
		database[0]=first_name;
		System.out.println("Enter your last name:");
		last_name=myObj.nextLine();
		database[1]=last_name;
	}
	
	public void input()
	{
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter age: ");
		age=myObj.nextLine();
		System.out.println("Enter address: ");
		address=myObj.nextLine();
		System.out.println("Enter city");
		city= myObj.nextLine();
		System.out.println("Enter zip code: ");
		zip=myObj.nextLine();
		System.out.println("Enter contact no:  ");
		phone_no=myObj.nextLine();
		System.out.println("Enter email_id: ");
		email=myObj.nextLine();
		
		database[2]=age;
		database[3]=address;
		database[4]=city;
		database[5]=zip;
		database[6]=phone_no;
		database[7]=email;
  
	}
	
	public int search (String name)
	{
		if(database[0].equals(name))
	    {
	    	System.out.println("Name found");
	    	return 1;
	    }
	    else 
	    	System.out.println("Name not found");
	    	return 0;
	    
	}
	public void erase()
	{
	    for(int j=0;j<8;j++)
	    {
	    	database[j]=" ";
	    }
	    
	}

	
}
public class address
{
	public static void main(String[] args)
	{	
		Scanner obj3 = new Scanner(System.in);
		address_update person=new address_update();
		int end=1;
		while(end!=0)
		{
			System.out.println("Enter your choice: 1.New Contact"+" 2.Edit existing contact"+ " 3.Delete a contact ");
			int choice=obj3.nextInt();
			switch(choice)
		   {
	        case 1:
	        	
	        	System.out.println("Enter details");
	        	person.f_name();
	    		person.input();
	    		person.display();	
	        	break;
	        case 2:
	        	System.out.println("Enter first name of the person for editing info");
	        	Scanner obj1 = new Scanner(System.in);
	        	String name=obj1.nextLine();
	        	int x=person.search(name);
	        	if(x==1)
	        	{	
	        	  System.out.println("Edit");
	        	  person.input();
	        	} 
	        	break;
	        	       	
	        case 3:
	        	System.out.println("Enter first name of the person for deleting");
	        	Scanner obj2 = new Scanner(System.in);
	        	String f_name=obj2.nextLine();
	        	int y=person.search(f_name);
	        	if(y==1)
	        	{	
	        	  person.erase();	
	        	  System.out.println("Deleted.");
	        	}
	        	break;
             } 	       
		  }
			
	}
}
