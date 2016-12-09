package com.scau.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scau.dao.service.PointPraiseService;
import com.scau.dao.service.ShareService;
import com.scau.entity.Share;

@Controller
@RequestMapping("/PointPraise")
public class PointPraiseController {
	
	@Autowired
	private PointPraiseService pointPraiseService;
	@Autowired
	private ShareService shareService;
	
	@RequestMapping("getPointpraiseByUserId.do")
	@ResponseBody
	public Map<String,Object> getPointpraiseByUserId(@RequestParam(value = "id")int user_id){
		HashMap<String,Object> map =new HashMap<String, Object>();
		ArrayList<Share> list = new ArrayList<Share>();
		List<Long> pointIds =  pointPraiseService.getPointPraiseByUserId(user_id);
		for(long share_id:pointIds){
			Share share = shareService.findShareById(share_id); 
			list.add(share);
		}
		map.put("shares", list);
		return map;
		
	}
	
	@RequestMapping("addPointPraise.do")
	@ResponseBody
	public Map<String,Object> addPointPraise(@RequestParam(value="userId")int user_id,
			@RequestParam(value = "shareId")int share_id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean hasPointed = pointPraiseService.checkPointPraise(share_id, user_id);
		if(hasPointed){
			map.put("flag", "fail");
			return map;
		}
		pointPraiseService.addPointPraise(share_id, user_id);
		map.put("flag","true" );
		return map;
		
		
	}

}
