

public class StockDTO extends InfoDTO {
	
	
	private int gold;
	private int techNavi;
	private int energyWave;
	private int globalMobill;
	private int healthWellth;
	private int leaderCoper;
	private int smartGrow;
	private int bioTix;
	private int airFlight;
	private int elitePerformance;
	private int greenFuture;
	
	public StockDTO(String id, String pw, String nick, int gold, int techNavi, int energyWave, int globalMobill,
			int healthWellth, int leaderCoper, int smartGrow, int bioTix, int airFlight, int elitePerformance,
			int greenFuture) {
		super(id, pw, nick);
		this.gold = gold;
		this.techNavi = techNavi;
		this.energyWave = energyWave;
		this.globalMobill = globalMobill;
		this.healthWellth = healthWellth;
		this.leaderCoper = leaderCoper;
		this.smartGrow = smartGrow;
		this.bioTix = bioTix;
		this.airFlight = airFlight;
		this.elitePerformance = elitePerformance;
		this.greenFuture = greenFuture;
	}

	public int getGold() {
		return gold;
	}

	public int getTechNavi() {
		return techNavi;
	}

	public int getEnergyWave() {
		return energyWave;
	}

	public int getGlobalMobill() {
		return globalMobill;
	}

	public int getHealthWellth() {
		return healthWellth;
	}

	public int getLeaderCoper() {
		return leaderCoper;
	}

	public int getSmartGrow() {
		return smartGrow;
	}

	public int getBioTix() {
		return bioTix;
	}

	public int getAirFlight() {
		return airFlight;
	}

	public int getElitePerformance() {
		return elitePerformance;
	}

	public int getGreenFuture() {
		return greenFuture;
	}
	
	
		

}
