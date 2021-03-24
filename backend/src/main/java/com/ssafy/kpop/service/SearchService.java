package com.ssafy.kpop.service;

import java.util.List;

import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.dto.SongDto;

public interface SearchService {
	public List<SingerDto> getSingerByName(String singer_name);
	public List<SingerDto> getSongByTitle(String song_name);
	public List<SongDto> getSongBySingerName(String singer_name);
}