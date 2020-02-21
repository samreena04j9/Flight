package com.cg.fms.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.cg.fms.bean.Airport;
import com.cg.fms.bean.DateTime;
import com.cg.fms.bean.Flight;
import com.cg.fms.bean.Schedule;
import com.cg.fms.bean.ScheduledFlight;
import com.cg.fms.flightexception.FlightException;

public class ScheduledFlightDaoImpl implements ScheduledFlightDao{

//	List<Flight> flightList=new ArrayList<>();
//	List<Airport> sourceList=new ArrayList<>();
//	List<Airport> destList=new ArrayList<>();
	private Map<Integer,ScheduledFlight> schedflight;
//
	public  ScheduledFlightDaoImpl() {
		schedflight = new HashMap<Integer,ScheduledFlight>();
//		Flight f1= new Flight(1001,"BUSSINESS","INS",100);
//		Flight f2= new Flight(1002,"ECONOMY","INS",80);
//		Flight f3= new Flight(1003,"FIRSTCLASS","INS",50);
//		
//		Airport s1= new Airport("Rajiv Gandhi International Airport","HYD","Hyderabad");
//		Airport s2= new Airport("chathrapathi Shivaji International Airport","MUM","Mumbai");
//		Airport s3= new Airport("Kempoguda Airport","BEN","Bengaluru");
//		
//		Airport d1= new Airport("Rajiv Gandhi International Airport","HYD","Hyderabad");
//		Airport d2= new Airport("chathrapathi Shivaji International Airport","MUM","Mumbai");
//		Airport d3= new Airport("Kempoguda Airport","BEN","Bengaluru");
//
//
//		
//	
//		
//		flightList.add(f1);
//		flightList.add(f2);
//		flightList.add(f3);
//		
//		sourceList.add(s1);
//		sourceList.add(s2);
//		sourceList.add(s3);
//		
//		destList.add(d1);
//		destList.add(d2);
//		destList.add(d3);
//		
//
//		
		
		
	}
	@Override
	public int scheduleFlight(ScheduledFlight scheduledFlight) throws FlightException {
		
		
		if(schedflight.containsKey(scheduledFlight.getFlight().getFlightNumber()))
				{
					throw new FlightException(" Flight is already scheduled");
				}
		schedflight.put(scheduledFlight.getFlight().getFlightNumber(), scheduledFlight);
	
		return scheduledFlight.getFlight().getFlightNumber();

	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(Airport source, Airport destination, DateTime dateTime) throws FlightException {
	List<ScheduledFlight> list1= new ArrayList<ScheduledFlight>();

		String sour=source.getAirportCode();
		String dest =destination.getAirportCode();
		String date2 = dateTime.getDate();
		Collection<ScheduledFlight> col = schedflight.values();
		List<ScheduledFlight> list = new ArrayList<ScheduledFlight>(col);
		for(ScheduledFlight x : list) {

			Schedule s =x.getSchedule();
			DateTime date =s.getArrivalTime();
			String date1 =date.getDate();
			Airport source1 =s.getSourceAirport();
			Airport dest1=s.getDestinationAirport();
			String sourceCode =source1.getAirportCode();
			String destCode = dest1.getAirportCode();
			if(sourceCode.equals(sour)&&destCode.equals(dest)&&date1.equals(date2)) {
				
				list1.add(x);
			}
		}

	
		return list1;
	}

	@Override
	public Flight viewScheduledFlights(int flightNumber) throws FlightException {
		if(!schedflight.containsKey(flightNumber))
		{
			throw new FlightException(" No Flight is Present ");
		}
		Flight f =schedflight.values().stream().map(p->p.getFlight()).filter(p->p.getFlightNumber()==flightNumber).findFirst().get();
		
		return f;
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlight() throws FlightException{
		if(schedflight.isEmpty())
		{
			throw new FlightException(" No Flight is Scheduled ");
		}
		Collection<ScheduledFlight> col = schedflight.values();
		List<ScheduledFlight> list = new ArrayList<ScheduledFlight>(col);
		
		
		schedflight.keySet().stream().forEach(p -> System.out.println(p));
		schedflight.values().stream().forEach(p -> System.out.println(p.getFlight().getFlightNumber()+" "+p.getSchedule().getSourceAirport().getAirportCode()));

		return list;
	}

	@Override
	public ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, int seatnumbers) throws FlightException{

		if(!schedflight.containsKey(flight.getFlightNumber())) {
			throw new FlightException(" Flight number does not exist");
			
		}
		ScheduledFlight obj = new ScheduledFlight(flight,seatnumbers,schedule);
		schedflight.put(obj.getFlight().getFlightNumber(), obj);
		return obj;
		
	}

	@Override
	public void deleteScheduledFlight(int flightNumber) throws FlightException{
		ScheduledFlight scheduledFlight=null;
		
		if(!schedflight.containsKey(flightNumber)) {
			throw new FlightException(" Flight number does not exist");
			
		}
		schedflight.remove(flightNumber);
		
		
	}

}
