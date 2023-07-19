
public class UserStockDTO {

	private String id;
	private String stockName;
	private int buyPrice;
	private int stockNum;
	
	public UserStockDTO(String id, String stockName, int buyPrice, int stockNum) {
		this.id = id;
		this.stockName = stockName;
		this.buyPrice = buyPrice;
		this.stockNum = stockNum;
	}
	
	public UserStockDTO(String stockName) {
		this.stockName = stockName;
	}


	public String getId() {
		return id;
	}

	public String getStockName() {
		return stockName;
	}

	public int getBuyPrice() {
		return buyPrice;
	}
	
	public int getStockNum() {
		return stockNum;
	}
	
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
	
	
}
