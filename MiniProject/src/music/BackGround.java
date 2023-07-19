package music;

import java.util.ArrayList;


public class  BackGround{
	
	
	public ArrayList<MusicVO> Sound(){
		MusicVO mp1 = new MusicVO(".\\music\\coins-135571.mp3");  
        ArrayList<MusicVO> musicList = new ArrayList<MusicVO>();
		musicList.add(mp1);
        return musicList;
	}

}
