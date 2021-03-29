package com.ssafy.kpop.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.kpop.dto.CommunityDto;
import com.ssafy.kpop.dto.NamuwikiDto;
import com.ssafy.kpop.service.CommunityService;
import com.ssafy.kpop.util.S3Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CommunityController V1")
@RestController
@RequestMapping(value = "/community")
public class CommunityController {

	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	CommunityService cservice;

	@Autowired
	private S3Util s3util;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	// 게시물 등록하기
	@ApiOperation(value = "Community Post Insert", notes = "커뮤니티 글 등록")
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestPart MultipartFile file,
			@RequestPart CommunityDto community) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("=====> 커뮤니티 글 등록 시작");
			String originName = file.getOriginalFilename(); //파일 이름 가져오기
			
			String ext = originName.substring(originName.lastIndexOf('.')); //파일 확장명 가져오기
			String saveFileName = UUID.randomUUID().toString() + ext; //암호화해서 파일확장넣어주기
			String path = System.getProperty("user.dir"); //경로설정해주고

			File tempfile = new File(path, saveFileName); //경로에 파일만들어주고

			String line = "community/";

			saveFileName = line + saveFileName;

			file.transferTo(tempfile);
			s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, tempfile)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			String url = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
			tempfile.delete();

			community.setC_img(url);

			int result = cservice.insert_post(community);

			if (result == 1) {
				logger.info("=====> 커뮤니티 글 등록 성공");
				resultMap.put("message", "게시글 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("=====> 커뮤니티 글 등록 실패");
				resultMap.put("message", "게시글 등록에 실패하였습니다.");
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

}
