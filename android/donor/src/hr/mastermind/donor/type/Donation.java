package hr.mastermind.donor.type;

public class Donation {
	private int numberOfDonations;
	private String lastDonation;
	private int nextDonation;
	private int points;
	
	public Donation(int numberOfDonations, String lastDonation, int nextDonation, int points){
		this.numberOfDonations = numberOfDonations;
		this.lastDonation = lastDonation;
		this.nextDonation = nextDonation;
		this.points = points;
	}
	
	
	public int getNumberOfDonations() {
		return numberOfDonations;
	}
	public void setNumberOfDonations(int numberOfDonations) {
		this.numberOfDonations = numberOfDonations;
	}
	public String getLastDonation() {
		return lastDonation;
	}
	public void setLastDonation(String lastDonation) {
		this.lastDonation = lastDonation;
	}
	public int getNextDonation() {
		return nextDonation;
	}
	public void setNextDonation(int nextDonation) {
		this.nextDonation = nextDonation;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
