package com.ssafy.kpop.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.SingerDto;
import com.ssafy.kpop.service.MyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("MyController V1")
@RestController
@RequestMapping("/my")
public class MyController {

	private static final Logger logger = LoggerFactory.getLogger(MyController.class);
	
	@Autowired
	private MyService myService;
	
	/*
	 * 커뮤니티 내가 쓴 글 불러오기 
	 * 
	 * @param uid
	 * @return List<CommunityDto>
	 * */
	@ApiOperation(value="커뮤니티 내가 쓴 글 불러오기", notes = "@param uid </br> @return CommunityDto", response=List.class)
	@GetMapping("/community/{uid}")
	public ResponseEntity<List<CommunityDto>> myPostList(@PathVariable String uid) {
		HttpStatus status=HttpStatus.ACCEPTED;
		
		try {
			List<CommunityDto> list = myService.showMyCommunity(uid);
			Collections.sort(list, new Comparator<CommunityDto>() {
				@Override
				public int compare(CommunityDto o1, CommunityDto o2) {
					return o2.getC_date().compareTo(o1.getC_date());
				}
			});
			return new ResponseEntity<List<CommunityDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	/*
	 * 내가 좋아요한 가수 불러오기 
	 * 
	 * @param uid
	 * @return List<SingerDto>
	 * */
	@ApiOperation(value="내가 좋아요한 가수 불러오기", notes = "@param uid </br> @return SingerDto", response=List.class)
	@GetMapping("/singer/{uid}")
	public ResponseEntity<List<SingerDto>> mySingerList(@PathVariable String uid) {
		HttpStatus status=HttpStatus.ACCEPTED;
		List<SingerDto> l=null;
		
		try {
			l=myService.showMySinger(uid);
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<SingerDto>>(l, status);
	}
}
