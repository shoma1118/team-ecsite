  package jp.co.internous.sky.controller;
  
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
  
  import jp.co.internous.sky.model.domain.MstDestination;
  import jp.co.internous.sky.model.mapper.MstDestinationMapper;
  import jp.co.internous.sky.model.mapper.TblCartMapper;
  import jp.co.internous.sky.model.mapper.TblPurchaseHistoryMapper;
  import jp.co.internous.sky.model.session.LoginSession;
  
	  @Controller
	  @RequestMapping("/sky/settlement") 
	  public class SettlementController {
	  
	  @Autowired 
	  MstDestinationMapper destinationMapper;
	  
	  @Autowired 
	  TblPurchaseHistoryMapper historyMapper;
	  
	  @Autowired 
	  TblCartMapper cartMapper;
	  
	  @Autowired 
	  public LoginSession loginSession;
	  
	  Gson gson = new Gson();
  
  	  @RequestMapping("/") 
  	  public String settlement(Model m) { 
		  int userId = loginSession.getUserId();
  		  List<MstDestination> destinations = destinationMapper.findByUserId(userId);
  		  m.addAttribute("destinations",destinations);
  		  m.addAttribute("loginSession",loginSession);
  		  return "settlement";
	  }
  	  
  	  @SuppressWarnings("unchecked")
  	  @ResponseBody
	  @RequestMapping("/complete") 
	  public boolean complete(@RequestBody String baggage) { 
		int userId = loginSession.getUserId();
	  	Map<String, String> map = gson.fromJson(baggage, Map.class);
  		int destinationId = Integer.valueOf(map.get("destinationId"));
		return historyMapper.insert(userId, destinationId) == true
				&& cartMapper.deleteByUserId(userId) == true;
  	  }
  }
 