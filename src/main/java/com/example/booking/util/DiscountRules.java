package com.example.booking.util;

import java.time.LocalTime;

import com.example.booking.core.ShowsSeatsEntity;
import com.example.booking.core.TicketEntity;
import com.example.booking.exception.BookingTechException;

public class DiscountRules {

	public static Object applyRateRule(ShowsSeatsEntity s, int count, TicketEntity ticketEntity) {

		try
		{
			//Rule1 - discount of 50% from 3rd ticket
			if( ticketEntity.getSeats().size() >3 && count >=3)
			{
				s.setRate((int) (s.getRate()*(50/100)));
			}
					
			//Rule2 - ticket booked for afternoon shows gets 
			if( ticketEntity.getShow().getShowTime().equals( LocalTime.NOON ) )
			{
				s.setRate((int) (s.getRate()*(20/100)));
			}
		
		}
		catch(Exception ex)
		{
			throw new BookingTechException("Exception while applying discount rule 1 on rates " + ex.getMessage());
		}
	
		return s;
	}

}
