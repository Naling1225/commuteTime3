package commuteTime;

import java.io.IOException;

public class CommuteModel {
	private double hourlyWage;
	private String departureLocation;
	private String destinationLocation;
	private double commuteTime;
	String duration;
	String wageType;
	TransitAPI transitAPI;
	String departureName, destinationName;
	int fare, nDay, nTime;

	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
	
	public void setWageType(String wageType) {
		this.wageType = wageType;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	
	public void setCommuteDay(int nDay) {
		this.nDay = nDay;
	}
	
	public void setWorkingTime(int nTime) {
		this.nTime = nTime;
	}

	public String calculateCommuteTime() throws IOException, InterruptedException {
		System.out.println("hourlyWage: " + hourlyWage);
		System.out.println("wageType  : " + wageType);
		System.out.println("departureLocation: " + departureLocation);
		System.out.println("destinationLocation: " + destinationLocation);
		// 이동 시간 계산 로직
		// API를 사용하여 출발지에서 도착지까지의 이동 시간을 가져온다.
		// 가져온 이동 시간을 commuteTime 변수에 저장한다.

		transitAPI = new TransitAPI(departureLocation, destinationLocation);
		commuteTime = transitAPI.time / 3600;
		duration = transitAPI.commuteTime;
		departureName = transitAPI.departureName;
		destinationName = transitAPI.destinationName;
		fare = transitAPI.fare;
		return duration;
	}

	public String getDepartureName() {
		return this.departureName;
	}

	public String getDestinationName() {
		return this.destinationName;
	}

	public int getFare() {
		return this.fare;
	}

	public int calculateCommuteCost() {
//		1. 시급을 선택했을 경우 : 임금 / 60 * 출퇴근시간 = commuteCost
//		2. 일급을 선택했을 경우 : 임금 / 60 / 하루근무시간 * 출퇴근시간 = commuteCost
//		3. 월급을 선택했을 경우 : 임금 / 60 / 하루근무시간 / 한달근로일 * 출퇴근시간 = commuteCost
//		4. 월급을 선택했을 경우 : 임금 / 60 / 하루근무시간 / 한달근로일 / 12 * 출퇴근시간 = commuteCost
		int commuteCost = 0;
		if("시급".equals(wageType)) {
			commuteCost = (int)(hourlyWage * commuteTime);
		}else if("일급".equals(wageType)) {
			commuteCost = (int)(hourlyWage  / nTime * commuteTime);
		}else if("월급".equals(wageType)) {
			commuteCost = (int)(hourlyWage / nTime / nDay * commuteTime);
		}else { //연봉
			commuteCost = (int)(hourlyWage / nTime / nDay / 12 * commuteTime);
		}
		return commuteCost;
	}
}
