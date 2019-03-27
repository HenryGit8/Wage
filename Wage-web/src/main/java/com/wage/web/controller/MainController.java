package com.wage.web.controller;

import com.wage.util.RandomValidateCodeUtil;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.constant.ParamConstants;
import com.wage.web.handler.LoginHandler;
import com.wage.web.handler.MainHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController extends MultiActionController {

    @Autowired
    LoginHandler loginHandler;

    @Autowired
    MainHandler mainHandler;

    /**
     * 跳转至主界面
     * @param request
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView main(HttpServletRequest request) {
        return new ModelAndView("login");
    }

    /**
     * 跳转到管理员主页面
     *
     * @return
     */
    @RequestMapping(value = "/gotoMain", method = RequestMethod.GET)
    public ModelAndView gotoMain(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", mainHandler.getLoginInfo());
        mv.setViewName("main/main");
        loginHandler.recordLogin(null,request);
        return mv;
    }

    /**
     * 跳转到员工主页面
     *
     * @return
     */
    @RequestMapping(value = "/gotoEmpMain", method = RequestMethod.GET)
    public ModelAndView gotoEmpMain(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", mainHandler.getLoginInfo());
        mv.addObject("userid", mainHandler.getLoginId());
        mv.addObject("noticeCount", mainHandler.getNotice());
        mv.setViewName("emp-main/main");
        loginHandler.recordLogin(null,request);
        return mv;
    }

    /**
     * 获取菜单
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/menu")
    @ResponseBody
    public JsonResult menu() {
        return new JsonResult(DescribableEnum.SUCCESS, mainHandler.menu());
    }

    /**
     * 登录页面生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        try {
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录页面校验验证码
     */
    @RequestMapping(value = "/checkVerify")
    @ResponseBody
    public Integer checkVerify(String inputStr, HttpSession session){
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if(random.equals(inputStr)){
            return 1;//验证码正确
        }else{
            return 0;//验证码错误
        }
    }
}
