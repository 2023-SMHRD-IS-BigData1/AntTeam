
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
	public StockDTO(String stockName) {
		this.stockName = stockName;
	}
	public StockDTO(int beforePrice, int nowPrice, int movePrice) {
		this.beforePrice = beforePrice;
		this.nowPrice = nowPrice;
		this.movePrice = movePrice;
	}
	public StockDTO(int nowPrice) {
		this.nowPrice = nowPrice;
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
