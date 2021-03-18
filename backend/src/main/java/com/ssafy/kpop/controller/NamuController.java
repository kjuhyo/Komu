package com.ssafy.kpop.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.service.NamuService;
import com.ssafy.kpop.util.Pagination;
import com.ssafy.kpop.util.S3Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("NamuController V1")
@RestController
@RequestMapping(value = "/namu")
public class NamuController {
	public static final Logger logger = LoggerFactory.getLogger(NamuController.class);
	
	@Autowired
	NamuService namuservice;
	
	@Autowired
	private S3Util s3util;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket; // 버킷이름
	
	// 게시물 등록하기
	@ApiOperation(value="Namu Insert", notes="나무위키 단어 등록")
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestPart MultipartFile file, 
			@RequestPart NamuwikiDto namu
//			@RequestPart String uid, @RequestPart int song_id, @RequestPart int singer_id, @RequestPart String namu_title, @RequestPart String namu_content
			){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
//		NamuwikiDto namu = new NamuwikiDto();
//		namu.setUid(uid);
//		namu.setSong_id(song_id);
//		namu.setSinger_id(singer_id);
//		namu.setNamu_title(namu_title);
//		namu.setNamu_content(namu_content);
	
		try {	
			
			logger.info("=====> 나무위키 글 등록 시작");
			String originName = file.getOriginalFilename();
			
			String ext = originName.substring(originName.lastIndexOf('.'));
			String saveFileName=UUID.randomUUID().toString()+ext;
			String path = System.getProperty("user.dir");
			
			File tempfile = new File(path,saveFileName);
			
			String line = "namu/";
			
			saveFileName = line+saveFileName;
			
			file.transferTo(tempfile);
			s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, tempfile).withCannedAcl(CannedAccessControlList.PublicRead));
			String url = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
			tempfile.delete();
			
			namu.setNamu_img(url);
			
			int result = namuservice.insert(namu);
			
			if(result==1) {
				logger.info("=====> 나무위키 글 등록 성공");
				resultMap.put("message", "단어 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("=====> 나무위키 글 등록 실패");
				resultMap.put("message", "단어 등록에 실패하였습니다.");
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status); 
	}
	
	// 게시물 수정하기
	@ApiOperation(value="Namu Update", notes="나무위키 등록 단어 수정")
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody NamuwikiDto namu) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			logger.info("=====> 내용 수정");
			int result = namuservice.update(namu);
			
			if(result == 1) {
				System.out.println("=====> 수정 성공");
				resultMap.put("message", "글 수정에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 수정에 실패하였습니다.");
				status = HttpStatus.NOT_FOUND;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 게시물 삭제하기
	@ApiOperation(value="Namu Delete", notes="나무위키 등록 단어 삭제")
	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody NamuwikiDto namu) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			logger.info("=====> 단어 삭제");
			int result = namuservice.delete(namu);
		
			if(result == 1) {
				logger.info("=====> 삭제 성공");
				resultMap.put("message", "글 삭제에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 삭제에 실패하였습니다.");
				status = HttpStatus.NOT_FOUND;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 게시물 확인하기
	@ApiOperation(value="Namu single word", notes="나무 단어 하나 보기")
	@GetMapping("/word/{namu_id}")
	public ResponseEntity<Map<String, Object>> getboard(@PathVariable int namu_id, @RequestParam String uid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		boolean isAuth = false;
		
		// 수정, 삭제 버튼에 대한 권한 체크
		try {
			logger.info("=====> 버튼 권한 체크");
			int check = namuservice.checkAuth(namu_id, uid);
			System.out.println(check);
			
			if(check > 0) {
				logger.info("=====> 작성자");
				isAuth = true;
			} else {
				logger.info("=====> 비 작성자");
			}

			System.out.println("check : "+ check);
			resultMap.put("isAuth", isAuth);
			
			logger.info("=====> 단어 부르기");
			NamuwikiDto dto = namuservice.callnamu(namu_id);
			
			if(dto != null) {
				logger.info("=====> 글 부르기 성공");

				resultMap.put("board", dto);
				resultMap.put("message", "단어 가져오기 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "단어 가져오기 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("실행 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	//나무위키 단어 전체다 가져오기
	@ApiOperation(value="NamuWiki page list", notes="나무위키 페이지 단어 목록")
	@GetMapping("/list/{page}")
	public ResponseEntity<Map<String, Object>> allList(@PathVariable int page) {
		Map<String, Object> resultMap = new HashMap<>();
	    HttpStatus status = null;
	    
	    int range = (page / 10) + 1;
	    int listCnt = 0;
	    List<NamuwikiDto> list = null;
	    
	    try {
	        logger.info("=====> 단어목록 확인");

	        // 총 게시물 개수
	        listCnt = namuservice.get_total();
	        
	        // 페이지 처리
	        logger.info("=====> 페이징");
	        Pagination pagination = new Pagination();
	        pagination.pageInfo(page, range, listCnt);
	        
	        logger.info("=====> 글 목록 받기");
	        list = namuservice.get_list(pagination);
	        
	        resultMap.put("pagination", pagination);
	        resultMap.put("list", list);
	        status = HttpStatus.ACCEPTED;
	        
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        logger.error("리스트업 실패 : {}", e);
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	        e.printStackTrace();
	    }
	    
	    return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 검색에 해당하는 리스트만 가져오기
	@ApiOperation(value="NamuWiki page list", notes="나무위키 페이지 단어 목록")
	@GetMapping("/search/{page}")
	public ResponseEntity<Map<String, Object>> allList(@PathVariable int page, @RequestParam String word) {
		Map<String, Object> resultMap = new HashMap<>();
	    HttpStatus status = null;
	    
	    int range = (page / 10) + 1;
	    int listCnt = 0;
	    List<NamuwikiDto> list = null;
	    
	    try {
	        logger.info("=====> 단어목록 확인");

	        // 총 게시물 개수
	        listCnt = namuservice.search_total(word);
	        
	        // 페이지 처리
	        logger.info("=====> 페이징");
	        Pagination pagination = new Pagination();
	        pagination.pageInfo(page, range, listCnt);
	        
	        int startList = pagination.getStartList();
	        int listSize = pagination.getListSize();
	        
	        
	        logger.info("=====> 단어 목록 받기");
	        list = namuservice.search_list(word, startList, listSize);
	        
	        resultMap.put("pagination", pagination);
	        resultMap.put("list", list);
	        status = HttpStatus.ACCEPTED;
	        
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        logger.error("리스트업 실패 : {}", e);
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	        e.printStackTrace();
	    }
	    
	    return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	
	
	
	



}
