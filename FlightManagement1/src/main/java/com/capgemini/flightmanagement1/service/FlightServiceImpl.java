package com.capgemini.flightmanagement1.service;

import java.math.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.capgemini.flightmanagement1.exception.FlightException;



import com.capgemini.flightmanagement1.beans.Flight;
import com.capgemini.flightmanagement1.dao.FlightDao;
import com.capgemini.flightmanagement1.dao.FlightDaoImpl;
public class FlightServiceImpl implements FlightService {

private FlightDao flightDao;

public FlightServiceImpl()
{
super();
flightDao = new FlightDaoImpl();
}

public void validateFlight(Flight flight) {

boolean f=flight.getCarrierName().matches("[a-zA-Z]+");
   f=flight.getFlightModel().matches("[a-zA-Z]+");
String flightnum=String.valueOf(flight.getFlightNumber());
f=flightnum.matches("[0-9]{4}");
String seatCapacity=String.valueOf(flight.getSeatCapacity());
f=flightnum.matches("[0-9]{2}");



}

public int addFlight(Flight flight) throws FlightException {
/*String flightnum=String.valueOf(flight.getFlightNumber());
boolean flag1=flightnum.matches("[0-9]{4}");
if(!flag1)
{
throw new FlightException("flight number should be");
}*/
//String name=flight.getCarrierName();
//boolean flag=validateFlight(name);
boolean flag1=false,flag2=false;
flag1=flight.getCarrierName().matches("[a-zA-Z]+");
String seatCapacity=String.valueOf(flight.getSeatCapacity());
flag2= seatCapacity.matches("[0-9]{2}");
if(flag1==false || flag2==false)
{
throw new FlightException("Invalid carrier name or Seatcapacity");
}
       Random random=new Random(); 
int flightnum1=random.nextInt(1000)+1000;
flight.setFlightNumber(flightnum1);
flightnum1=flightDao.addFlight(flight);
return flightnum1;
}

public Flight modifyFlight(Flight flight) throws FlightException {

String flightnum=String.valueOf(flight.getFlightNumber());
boolean flag=flightnum.matches("[0-9]{4}");
if(!flag)
{
throw new FlightException("Invalid flight number ");
}
return flightDao.modifyFlight(flight);
}

public void deleteFlight(int flightNumber) throws FlightException {

String flightnum=String.valueOf(flightNumber);
boolean flag=flightnum.matches("[0-9]{4}");
if(!flag)
{
throw new FlightException("Invalid flight number  ");
}
flightDao.deleteFlight(flightNumber);
}

public Flight viewFlight(int flightNumber) throws FlightException {

String flightnum=String.valueOf(flightNumber);
boolean flag=flightnum.matches("[0-9]{4}");
if(!flag)
{
throw new FlightException("Invalid flight number ");
}
return flightDao.viewFlight(flightNumber);
}

public List<Flight> viewFlight() throws FlightException {

return flightDao.viewFlight();
}
}
