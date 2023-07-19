package game;

public class Game_QuizDTO {

	String proveb;
	String answer;
	
	public Game_QuizDTO(String proveb, String answer) {
		super();
		this.proveb = proveb;
		this.answer = answer;
	}

	public String getProveb() {
		return proveb;
	}

	public String getAnswer() {
		return answer;
	}
	
}
