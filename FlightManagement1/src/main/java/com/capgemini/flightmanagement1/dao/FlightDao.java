package com.capgemini.flightmanagement1.dao;

import java.util.List;

import com.capgemini.flightmanagement1.beans.Flight;
import com.capgemini.flightmanagement1.exception.FlightException;

public interface FlightDao {

public int addFlight(Flight flight) throws FlightException;
public Flight modifyFlight(Flight flight) throws FlightException;
public void deleteFlight(int flightNumber) throws FlightException;
public Flight viewFlight(int flightNumber) throws FlightException;
public List<Flight> viewFlight () throws FlightException;
}
