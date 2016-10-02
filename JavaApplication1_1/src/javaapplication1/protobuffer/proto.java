package javaapplication1.protobuffer;

import thuongdc.com.example.tutoria.AddressBookProtos.AddressBook;
import thuongdc.com.example.tutoria.AddressBookProtos.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;


public class proto {

	 // This function fills in a Person message based on user input.
	  static Person PromptForAddress(BufferedReader stdin,
	                                 PrintStream stdout) throws IOException {
	    Person.Builder person = Person.newBuilder();

	    stdout.print("Enter person ID: ");
	    person.setId(Integer.valueOf(stdin.readLine()));

	    stdout.print("Enter name: ");
	    person.setName(stdin.readLine());

	    stdout.print("Enter email address (blank for none): ");
	    String email = stdin.readLine();
	    if (email.length() > 0) {
	      person.setEmail(email);
	    }

	    while (true) {
	      stdout.print("Enter a phone number (or leave blank to finish): ");
	      String number = stdin.readLine();
	      if (number.length() == 0) {
	        break;
	      }

	      Person.PhoneNumber.Builder phoneNumber =
	        Person.PhoneNumber.newBuilder().setNumber(number);

	      stdout.print("Is this a mobile, home, or work phone? ");
	      String type = stdin.readLine();
	      if (type.equals("mobile")) {
	        phoneNumber.setType(Person.PhoneType.MOBILE);
	      } else if (type.equals("home")) {
	        phoneNumber.setType(Person.PhoneType.HOME);
	      } else if (type.equals("work")) {
	        phoneNumber.setType(Person.PhoneType.WORK);
	      } else {
	        stdout.println("Unknown phone type.  Using default.");
	      }

	      person.addPhone(phoneNumber);
	    }

	    return person.build();
	  }

	
	  
	// Main function:  Reads the entire address book from a file,
	  //   adds one person based on user input, then writes it back out to the same
	  
	  public static void main(String[] args) throws Exception {
		            Person.Builder person1 = Person.newBuilder();

              person1.setId(12).setEmail("dasdasd").setName("fff").addPhone(Person.PhoneNumber.newBuilder().setNumber("555-4321")
                      .setType(Person.PhoneType.HOME)).build();
              person1.setEmail("ooo").build();
              int x=9;
              person1.setId(1).build();
              person1.addPhone(Person.PhoneNumber.newBuilder().setNumber("55667768686-4321")
                      .setType(Person.PhoneType.HOME)).build();
              System.err.println(person1.getId());
              System.err.println(person1.getPhone(1));
              
		  
	  }
            public static String protobuff_jetty(){
		            Person.Builder person1 = Person.newBuilder();

              person1.setId(12).setEmail("thuongdc@xct.vn").setName("Thuongdcoiooo").addPhone(Person.PhoneNumber.newBuilder().setNumber("555-4321")
                      .setType(Person.PhoneType.HOME)).build();
              return person1.getName();		  
	  }
	      	 
           //redis
            
            
            
            
            
            
            
            
            
}
