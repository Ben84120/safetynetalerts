package net.safety.alert.safety.api.controllers;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import net.safety.alert.safety.api.model.Person;

@RestController
public class JsoniterController { 


   /** public static void main(String[] args) throws IOException {        

       String filePath = "C:\\Users\\bvach\\git\\safetynetalerts\\resources\\data.json";

    	byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());

        

    	JsonIterator iter = JsonIterator.parse(bytesFile);

    	Any any = iter.readAny();

    	Any personAny = any.get("persons");

    	List<Person> persons = new ArrayList<>();

    	personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())

    			.address(a.get("address").toString())

    			.city(a.get("city").toString())

    			.lastName(a.get("lastName").toString())

    			.phone(a.get("phone").toString())

    			.zip(a.get("zip").toString())

    			.email(a.get("email").toString())

    			.build()));

    	

    	persons.forEach(p -> System.out.println(p.firstName.concat(p.lastName).concat(p.address).concat(p.city).concat(p.phone).concat(p.zip)));
    	
    }    	
   */ 
}

    	

