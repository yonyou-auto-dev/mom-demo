package com.yonyou.gtmc.demo_f4.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Paginator;
import org.javalite.activejdbc.RowListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yonyou.cloud.mom.client.MqSender;
import com.yonyou.f4.mvc.annotation.Conn;
import com.yonyou.f4.mvc.annotation.Txn;
import com.yonyou.f4.mvc.annotation.TxnConn;
import com.yonyou.f4.mvc.context.ActionContext;
import com.yonyou.f4.mvc.controller.BaseController;
import com.yonyou.gtmc.demo_f4.message.LoginMsg;
import com.yonyou.gtmc.demo_f4.model.Emp;
import com.yonyou.gtmc.demo_f4.model.Pig;
import com.yonyou.gtmc.demo_f4.service.BizService;

@Controller
@TxnConn
@RequestMapping(value = "/Test")
public class TestCtrl extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TestCtrl.class);
	private static AtomicInteger pk = new AtomicInteger();

	@Autowired
	private MqSender mqSender;
	
	@Autowired
	private BizService bizService;
	
	@RequestMapping("/sendmqMsgCollection")
//	@Txn(id = "DbTxnManager")
	@ResponseBody
	public String sendmqMsgCollection() throws Exception{
		mqSender.justSend("mqMsgCollection", null, "{\"lib\":{\"$lib\":\"Java\",\"$lib_method\":\"code\",\"$lib_version\":\"1.0.0\",\"$lib_detail\":\"org.ben.logstash.demo.TrackLogstashDemoApplication##testTrack##TrackLogstashDemoApplication.java##37\"},\"time\":"+System.currentTimeMillis()+",\"type\":\"send\",\"host\":\"localhost\",\"event\":\"test\",\"properties\":{\"$lib\":\"Java\",\"data\":{\"column1\":\"b8f4a234-28e4-4040-8c38-72ae92501047\",\"column5\":\"65092cd1-8407-4dfc-aab0-c2cd7502aae1\",\"column4\":\"9cfdda81-fb77-42e0-a35c-ebc472175ff7\",\"column3\":\"b8d0c957-20ff-43ab-8f5e-4aee7629ef6b\",\"$lib_version\":\"1.0.0\",\"column2\":\"acce9576-995d-4220-8b7b-27c83ff7bdd6\",\"column8\":\"bd8502a1-2909-4647-b2ce-6ff4ecfcb222\",\"column7\":\"30637278-9022-45fa-8b26-563c2f63841c\",\"column6\":\"0dcddb65-27d2-4682-9fa1-c5a592e8998d\"},\"produceFailTimes\":0,\"occurTime\":1513245497633,\"consumeSuccessTime\":1513245497444,\"produceSuccessTime\":1513245497633,\"sender\":\"moxoe\",\"success\":\"true\",\"host\":\"localhost\",\"exchangeName\":\"mqMsgCollection\",\"msgKey\":\""+UUID.randomUUID().toString()+"\",\"consumeFailTimes\":0,\"routingKey\":\"\",\"status\":\"PRODUCED\"}}");
		return "done";
	}
	
	@RequestMapping("/login/{name}")
//	@Txn(id = "DbTxnManager")
	@ResponseBody
	public String login(@PathVariable("name") String name) throws Exception{
		bizService.saveLoginUser(name);
		return "done";
	}
	
	@RequestMapping("/login2/{name}")
//	@Txn(id = "DbTxnManager")
	@ResponseBody
	public String login2(@PathVariable("name") String name) throws Exception{
		bizService.saveLoginUser2(name);
		return "done";
	}
	
	@RequestMapping("/testBatch")
	@ResponseBody
	public String testBatch() {
		int size = 1000;
		long timeMillis = System.currentTimeMillis();
		for(int i = 0; i<size; i++){
			LoginMsg msg = new LoginMsg();
			msg.setLoginName(UUID.randomUUID().toString());
			msg.setLoginTime(new Date().getTime());
			mqSender.send("mqMsgF4Exchange", "login", msg);
		}
		long currentTimeMillis = System.currentTimeMillis();
		long cost = currentTimeMillis - timeMillis;
		return "done! cost" + cost;
	}
	
	@RequestMapping(value="/test/{message}")  
	@ResponseBody
    public void test(@PathVariable("message") String message){ 
		logger.error(message);
		logger.debug(message);
		logger.info(message);
		logger.warn(message);
    }  
	
	@RequestMapping(value = "/emps")
	public ModelAndView emps(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ModelAndView mav = new ModelAndView();
		ActionContext ac = ActionContext.getActionContext(request, response);
		
		Emp emp = new Emp();
		emp.findAll();
		
		//TODO: Base.findAll() 如何分页

		try {
			String sql = "select A.ENAME name, A.JOB, B.DNAME from emp A, dept B where A.DEPTNO = B.DEPTNO and A.DEPTNO = ?";
			List<Map> emps = Base.findAll(sql, 30);
			
			final List list = new ArrayList();
			Base.find(sql, 30).with(new RowListenerAdapter() {
				public void onNext(Map row) {
					// write your code here
					row.put("dname", row.get("dname")+"acd12");
					list.add(row);
				}
			});
			
			
			String empSql = "select ename from emp";
			Paginator p = new Paginator(Emp.class, 2, empSql);
			
			mav.addObject("data", emps);
			mav.addObject("list", list);
			mav.addObject("p", p.getPage(2));
			
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		return mav;
	}

	@RequestMapping(value = "/addPig")
	public ModelAndView addPig(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ModelAndView mav = new ModelAndView();

		try {
			Pig pig = new Pig();
			// ID
			int id = pk.addAndGet(1);
			pig.setId(id);
			// Name
			ActionContext ac = ActionContext.getActionContext(request, response);
			String name = ac.getValue("name", String.class, "Pig" + id);
			pig.set("name", name);
			// Age
			Integer age = ac.getValue("age", Integer.class, id);
			pig.setInteger("age", age);

			boolean result = pig.insert();
			if (result) {
				mav.addObject("data", id);
			}
			mav.setViewName("pig/addResult");
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		return mav;
	}

	@RequestMapping(value = "/killPig")
	@Txn(id = "DbTxnManager")
	@Conn(ids = "dataSource", names = "default")
	public ModelAndView killPig(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		try {
			Integer id = ActionContext.getActionContext(request, response).getValue("id", Integer.class, null);
			if (id != null) {
				// Pig pig = Pig.findFirst("id = ?",
				// Integer.parseInt(id.trim()));
				// boolean result = pig.delete();
				// if(result) {
				// mav.addObject("data", id);
				// }
				int cnt = Pig.delete("id = ?", id);
				if (cnt > 0) {
					mav.addObject("data", id);
				}
			} else {
				Pig.deleteAll();
			}

			mav.setViewName("pig/killResult");
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);
			//
			mav.addObject("data", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}

	@RequestMapping(value = "/getPig")
	@Txn(id = "DbTxnManager")
	public ModelAndView getPig(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		try {
			Integer id = ActionContext.getActionContext(request, response).getValue("id", Integer.class, null);
			if (id != null) {
				Pig pig = Pig.findById(id);
				if (pig != null) {
					mav.addObject("data", pig);
				}
			} else {
				LazyList<Pig> pigs = Pig.findAll();
				pigs.size();

				mav.addObject("data", pigs);
			}

			mav.setViewName("pig/getResult");
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);

			mav.addObject("data", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}

	@RequestMapping(value = "/setPig")
	@Conn(ids = "dataSource", names = "default")
	public ModelAndView setPig(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			ActionContext ac = ActionContext.getActionContext(request, response);
			Integer id = ac.getValue("id", Integer.class, null);
			if (id != null) {
				Pig thePig = Pig.findFirst("id = ?", id);
				if (thePig != null) {
					String name = ac.getValue("name", String.class, null);
					if (name != null) {
						thePig.set("name", name);
					}
					Integer age = ac.getValue("age", Integer.class, null);
					if (age != null) {
						thePig.setInteger("age", age);
					}
					thePig.saveIt();

					mav.addObject("data", thePig);
				}
			}

			mav.setViewName("pig/setResult");
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);

			mav.addObject("data", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}
}