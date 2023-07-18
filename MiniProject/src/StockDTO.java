
public class StockDTO {

	private String stockName;
	private int beforePrice;
	private int nowPrice;
	private int movePrice;

	public StockDTO(String stockName, int beforePrice, int nowPrice, int movePrice) {
		this.stockName = stockName;
		this.beforePrice = beforePrice;
		this.nowPrice = nowPrice;
		this.movePrice = movePrice;
	}

	public String getStockName() {
		return stockName;
	}

	public int getBeforePrice() {
		return beforePrice;
	}

	public int getNowPrice() {
		return nowPrice;
	}

	public int getMovePrice() {
		return movePrice;
	}

}
