
import java.time.*;
import java.time.chrono.ChronoLocalDate;


public class Date {
	private LocalDate date;
	private int month;
	private int day;
	private int year; 

	public Date(int month, int day , int year) {

		LocalDate dateTransaction= LocalDate.of(year, month, day);
		
		if (year < LocalDate.now().getYear()-2000) { 
			throw new DateOutOfBoundsException();
		}
		this.month= month;
		this.year=year;
		this.day=day;
		date= dateTransaction;
	}
	public Date (int month, int year) { 
		LocalDate dateTransaction= LocalDate.of(year, month, 01); 
		this.month=month;
		this.year=year;
		this.day=01;
		date=dateTransaction;
	}


	@Override
	public String toString() {
		return (this.month +"/"+ this.day + "/" +this.year);
	}

}
