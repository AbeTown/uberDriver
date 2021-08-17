package hw1;
/**
 * Model of the money earned by an Uber driver over the course of one driving session
 * @author abesteph
 *
 */

public class UberDriver {
	/**
	 * Maximum number of passengers allowed in the vehicle at one time.
	 */
	public static final int MAX_PASSENGERS = 4;
	
	/**
	 * Cost to operate the vehicle per mile
	 */
	public static final double OPERATING_COST = 0.5;
	
	/**
	 * Minutes per hour
	 */
	//Created to avoid using a "magic number" twice, in averageProfit and driveAtSpeed
	private static final int MINUTES_PER_HOUR = 60;
	
	/**
	 * The amount Uber driver earns per mile driven
	 */
	private double perMileRate;
	
	/**
	 * The amount the uber driver earns per minute driven
	 */
	private double perMinuteRate;
	
	/**
	 * total miles driven
	 */
	private int milesDriven;
	
	/**
	 * total minutes driven
	 */
	private int minutesDriven;
	
	/**
	 * current number of passengers in the car
	 */
	private int passengerCount;
	
	/**
	 * current number of credits gained
	 */
	private double credits;
	
	/**
	 * Constructs an Uberdriver object with the given per mile and per minute rates
	 * @param givenPerMileRate
	 * 		the amount the driver will earn per mile driven
	 * @param givenPerMinuteRate
	 * 		the amount the driver will earn per minute driven
	 */
	public UberDriver(double givenPerMileRate, double givenPerMinuteRate) {
		perMileRate = givenPerMileRate;
		perMinuteRate = givenPerMinuteRate;
		milesDriven = 0;
		minutesDriven = 0;
		passengerCount = 0;
		credits = 0;
	}
	
	/**
	 * Returns the total miles driven since this UberDriver was constructed
	 * @return
	 * 		total number of miles driver has driven
	 */
	public int getTotalMiles() {
		return milesDriven;
	}
	
	/**
	 * Returns the total minutes driven since this UberDriver was constructed
	 * @return
	 * 		number of minutes driver has currently been driving
	 */
	public int getTotalMinutes() {
		return minutesDriven;
	}
	
	/**
	 * Drives the vehicle for the given number of miles over the given number of minutes
	 * @param miles
	 * 		Miles to drive
	 * @param minutes
	 * 		Minutes it takes to drive 
	 */
	public void drive(int miles, int minutes) {
		addCredits(miles, minutes);
		milesDriven += miles;
		minutesDriven += minutes;
	}
	
	/**
	 * Simulates sitting in the vehicle without moving for the given number of mintutes. 
	 * equivalent to drive(0,minutes)
	 * @param minutes
	 * 		the number of minutes spent waiting
	 */
	public void waitAround(int minutes) {
		addCredits(0, minutes);
		minutesDriven += minutes;
	}
	
	/**
	 * Drives the vehicle for the given number of miles at the given speed. Equivalent to
	 * drive (miles, m) where m is the actual number of minutes required, rounded to the
	 * nearest integer. Caller of method must ensure that average speed is positive.
	 * @param miles
	 * 		the number of miles driven
	 * @param averageSpeed 
	 * 		the average speed driven
	 */
	public void driveAtSpeed(int miles, double averageSpeed) {
		int mins = (int)Math.round((miles / averageSpeed) * MINUTES_PER_HOUR);
		addCredits(miles, mins);
		milesDriven += miles;
		minutesDriven += mins;
	}
	
	/**
	 * Returns the number of passengers currently in the vehicle
	 * @return 
	 * 		number of passengers in car
	 */
	public int getPassengerCount() {
		return passengerCount;
	}
	
	/**
	 * Increases the passenger count by 1, not exceeding MAX_PASSENGERS
	 */
	public void pickUp() {
		passengerCount = Math.min(MAX_PASSENGERS, passengerCount+1);
	}
	
	/**
	 * Decreases the passenger count by one, not going below zero
	 */
	public void dropOff() {
		passengerCount = Math.max(0, passengerCount - 1);
	}
	
	/**
	 * returns this uberDriver's total credits (money earned before deducting 
	 * operating costs
	 * @return 
	 * 		total credits earned so far
	 */
	public double getTotalCredits() {
		return credits;
	}
	
	/**
	 * returns this uberdriver's profit (total credits less operating costs)
	 * @return 
	 * 		credits less operating costs
	 */
	public double getProfit() {
		return credits - milesDriven * OPERATING_COST;
	}
	
	/**
	 * Returns this UberDriver's average profit per hour worked. Caller of method
	 * must ensure that it is only called when the value of getTotalMinutes()
	 * is nonzero
	 * @return 
	 * 		profits averaged over amount of time driven
	 */
	public double getAverageProfitPerHour() {
		return getProfit() / minutesDriven * MINUTES_PER_HOUR;
	}
	
	/**
	 * Helper method to add credits to the credit total depending on miles
	 * and minutes driven 
	 * @param miles
	 * 		miles driven, can be zero, caller to make sure value is positive
	 * @param minutes
	 * 		minutes driven, caller to make sure value is positive
	 */		
	//Created to avoid repeating credit calculation code in 3 methods 
	private void addCredits(int miles, int minutes) {
		credits += passengerCount * (miles * perMileRate);
		credits += passengerCount * (minutes * perMinuteRate);
	}
}











