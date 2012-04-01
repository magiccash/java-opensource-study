package com.google.protobuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.tutorial.AddressBookProtos.Person;
import com.example.tutorial.AddressBookProtos.Person.PhoneNumber;
import com.example.tutorial.AddressBookProtos.Person.PhoneType;

import junit.framework.TestCase;


public class AddressBookProtosTest extends TestCase {
    
    public void testSerialize() throws Exception{
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        
        PhoneNumber expectedNum = PhoneNumber.newBuilder ().setNumber ("13161642951").setType (PhoneType.HOME).build ();
        
        Person expectedPer = Person.newBuilder ().setId (0).setName ("wuban").addPhone (0, expectedNum).setEmail ("495081611@qq.com").build ();
        
        ObjectOutputStream out  = new ObjectOutputStream(baos);
        
        out.writeObject (expectedPer);
        
        out.close ();
        
        ByteArrayInputStream bais = new ByteArrayInputStream (baos.toByteArray ());
        
        ObjectInputStream in = new ObjectInputStream (bais);
        
        Person actualPer = (Person) in.readObject ();
        
        System.out.println (actualPer.toString ());

    }
    

}
