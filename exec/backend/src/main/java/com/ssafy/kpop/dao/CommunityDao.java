package com.ssafy.kpop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kpop.dto.Comm_commentDto;
import com.ssafy.kpop.dto.Comm_likeDto;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.CommunityNickDto;
import com.ssafy.kpop.util.Pagination;

@Mapper
public interface CommunityDao {
	
	int insert_post(CommunityDto community);
	CommunityDto get_old(int cid);
	int update_post(CommunityDto community);
	int update(CommunityDto community);
	int delete(int cid);
	CommunityDto get_community(int cid);
	int up_view(Map<String, Object> map);
	List<Comm_commentDto> get_comment(int cid);
	Comm_likeDto find_like(Comm_likeDto commlike);
	int let_like(Comm_likeDto commlike);
	int let_dislike(Comm_likeDto commlike);
	int cnt_like(int cid);
	int set_like(Map<String, Object> map);
	int total_post();
	List<CommunityDto> all_post(Pagination pagination);
	int insert_nopic(CommunityDto community);
	List<CommunityNickDto> communityInfo(Pagination pagination);
	List<CommunityNickDto> popInfo(Pagination pagination);
	
	int update_nopic(CommunityDto community);
}
