package com;
import java.util.ArrayList;
import java.util.Scanner; 
class address_update 
{
	String first_name,last_name,age,address,city,zip,phone_no,email;
	int i=0;
	String[][]database = new String[100][8];
	public void display() 
    { 
		System.out.println("Emplyee details are: ");
		System.out.println("Name: "+first_name+" "+last_name+"   Age:"+age+"  Address:"+address+" "+city+" "+zip+"  Contact: "+phone_no+" "+email);	
    }
	public void f_name(int posi)
	{
		Scanner myObj= new Scanner(System.in);
		System.out.println("Enter your first name: ");
		first_name = myObj.nextLine();
		database[posi][0]=first_name;
		System.out.println("Enter your last name:");
		last_name=myObj.nextLine();
		database[posi][1]=last_name;
	}
	
	public void input(int pos)
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
		
		database[pos][2]=age;
		database[pos][3]=address;
		database[pos][4]=city;
		database[pos][5]=zip;
		database[pos][6]=phone_no;
		database[pos][7]=email;
		System.out.println("  "+database);

	}
	
	public int search (String name,int count)
	{
		
		int i=count-1;
		while(i>=0)
		{	
		   if(database[i][0].equals(name))
	      {
	    	System.out.println("Name found");
	    	return i;
	      }
		   i=i-1;
		}
	    System.out.println("Name not found");
	    return 2000;
	}
	
	public void erase(int pos)
	{
		 for(int j=0;j<8;j++)
	     {
	       	database[pos][j]=" ";
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
		int total=0;
		while(end!=0)
		{
			System.out.println("Enter your choice: 1.New Contact"+" 2.Edit existing contact"+ " 3.Delete a contact ");
			int choice=obj3.nextInt();
			
			switch(choice)
		   {
	        case 1:
	        	System.out.println("Enter details");
	        	person.f_name(total);
	    		person.input(total);
	    		total++;
	    		person.display();
	    		System.out.println("Total "+total);
	        	break;
	        	
	        case 2:
	        	System.out.println("Enter first name of the person for editing info");
	        	Scanner obj1 = new Scanner(System.in);
	        	String name=obj1.nextLine();
	        	int x=person.search(name,total);
	        	if(x!=2000)
	        	{	
	        	  System.out.println("Editting now.....");
	        	  person.input(x);
	        	} 
	        	break;
	        	       	
	        case 3:
	        	System.out.println("Enter first name of the person for deleting");
	        	Scanner obj2 = new Scanner(System.in);
	        	String f_name=obj2.nextLine();
	        	int y=person.search(f_name,total);
	        	if(y!=2000)
	        	{	
	        	  person.erase(y);	
	        	  System.out.println("Deleted.");
	        	}
	        	break;
             } 	       
		  }
			
	}
}
