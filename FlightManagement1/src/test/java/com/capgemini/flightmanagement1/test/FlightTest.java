package com.capgemini.flightmanagement1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.flightmanagement1.beans.Flight;
import com.capgemini.flightmanagement1.dao.FlightDao;
import com.capgemini.flightmanagement1.dao.FlightDaoImpl;
import com.capgemini.flightmanagement1.exception.FlightException;

class FlightTest {
FlightDao dao=null;
Flight flight=new Flight(1134,"kingfisher","samreen",45);



@BeforeAll
void addFlight() {
	dao=new FlightDaoImpl();
}

 @Test
	void tsetAddFlight() throws FlightException{
	 int num=dao.addFlight(flight);
	 List l=(List) dao.viewFlight();
	 assertEquals(1134,l.size());
	 
}
	
}



