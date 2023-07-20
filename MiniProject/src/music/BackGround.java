package music;

import java.util.ArrayList;


public class  BackGround{
	
	
	public ArrayList<MusicVO> Sound(){
		MusicVO mp1 = new MusicVO(".\\music\\coins-135571.mp3");  
		MusicVO mp2 = new MusicVO(".\\music\\잭팟2.mp3");  
		MusicVO mp3 = new MusicVO(".\\music\\335. 브금의 숲.mp3");    
		MusicVO mp4 = new MusicVO(".\\music\\마마무마마.mp3");  
        ArrayList<MusicVO> musicList = new ArrayList<MusicVO>();
		musicList.add(mp1);
		musicList.add(mp2);
		musicList.add(mp3);
		musicList.add(mp4);
        return musicList;
	}

}
