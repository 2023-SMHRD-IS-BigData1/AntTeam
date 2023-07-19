

public class InfoDTO {
	
	private String id;
	private String pw;
	private String nick;
	private int gold;
	
	public InfoDTO(String id, String pw, String nick, int gold) {
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.gold = gold;
	}
	public InfoDTO(String id, String pw, String nick) {
		this.id = id;
		this.pw = pw;
		this.nick = nick;
	}
	
	public InfoDTO(String id, String nick, int gold) {
		this.id = id;
		this.nick = nick;
		this.gold = gold;
	}
	public InfoDTO(String nick, int gold) {
		this.nick = nick;
		this.gold = gold;
	}

	public InfoDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}
	
	public String getNick() {
		return nick;
	}
	
	public int getGold() {
		return gold;
	}
	
	
	
	
	

}
