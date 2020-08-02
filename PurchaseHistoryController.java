  package jp.co.internous.sky.controller;
  
  import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.ResponseBody;
  import jp.co.internous.sky.model.domain.dto.PurchaseHistoryDto;
  import jp.co.internous.sky.model.mapper.TblPurchaseHistoryMapper;
  import jp.co.internous.sky.model.session.LoginSession;
  
  @Controller
  @RequestMapping("/sky/history") 
  public class PurchaseHistoryController {
	  @Autowired 
	  TblPurchaseHistoryMapper historyMapper;
	  
	  @Autowired
	  private LoginSession loginSession;
	  
	  @RequestMapping("/")
	  public String index(Model m) { 
		  int userId = loginSession.getUserId();
		  List<PurchaseHistoryDto> historys = historyMapper.findByUserId(userId);
		  m.addAttribute("historys",historys);
  		  m.addAttribute("loginSession",loginSession);
		  return "purchase_history";
	  }
	  
	  @ResponseBody
	  @RequestMapping("/delete") 
	  public boolean delete() { 
		  int userId = loginSession.getUserId();
		  return historyMapper.deleteByUserId(userId);
	}
  
 }
 