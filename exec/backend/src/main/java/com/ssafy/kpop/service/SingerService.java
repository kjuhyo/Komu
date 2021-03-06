package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SingerchatDto;
import com.ssafy.kpop.dto.SingerlikeDto;
import com.ssafy.kpop.dto.SongDto;

public interface SingerService {
	List<SongDto> songlist(String singer_name);
	List<SingerchatDto> chatlist(int singer_id);
	
	int searchSong(String singer_name);
	List<SongDto> all_song(String singer_name, int startList, int listSize);
	
	SingerDto find_singer(String singer_name);
	
	int chat_regist(SingerchatDto chat);
	SingerchatDto check_id(int id);
	
	int do_delete(int id);
	int ami_like(String uid, int singer_id);
	int do_like(SingerlikeDto singerlike);
	int do_dislike(SingerlikeDto singerlike);
	SingerlikeDto find_like(SingerlikeDto singerlike);
	
	int cnt_like(int singer_id);
	int set_like(int singer_id, int singer_like_cnt);
	

}
