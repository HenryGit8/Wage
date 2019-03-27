/**
 * JumpController.java 2017/12/15 8:05
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * File：JumpController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/jump")
public class JumpController{

    /**
     * 跳到注册界面
     * @param request
     * @param response
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/goRegister")
    public ModelAndView goRegister(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("register");
        return mv;

    }

    /**
     * 跳转至主界面
     * @param request
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/main", method = {RequestMethod.GET})
    public ModelAndView main(HttpServletRequest request) {
        return new ModelAndView("login");
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/empInfoManager")
    public ModelAndView empInfoManager() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/empinfo_manager");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/empMonDeductSalManager")
    public ModelAndView empMonDeductSalManager() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/empmondeductsal_manager");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/allowanceHistory")
    public ModelAndView allowanceHistory() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/allowance_history");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/empMonOtherSalManager")
    public ModelAndView empMonOtherSalManager() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/empmonothersal_manager");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/empMonSalManager")
    public ModelAndView empMonSalManager() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/empmonsal_manager");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/overWorkHistory")
    public ModelAndView overWorkHistory() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/overwork_history");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/restWorkHistory")
    public ModelAndView restWorkHistory() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/restwork_history");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/salaryChanceHistory")
    public ModelAndView salaryChanceHistory() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/salarychance_history");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/salaryPayHistory")
    public ModelAndView salaryPayHistory() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/salarypay_history");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/departmentManager")
    public ModelAndView departmentManager() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/department_manager");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/systemSet")
    public ModelAndView systemSet() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/system_set");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/welcome")
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView("/includejsp/admin/welcome");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/empWelcome")
    public ModelAndView empWelcome() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_welcome");
        return mav;
    }

    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/empWageQuery")
    public ModelAndView empWageQuery() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_wage_query");
        return mav;
    }
    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/restApply")
    public ModelAndView restApply() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_rest_apply");
        return mav;
    }
    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/restQuery")
    public ModelAndView restQuery() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_rest_query");
        return mav;
    }
    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/overApply")
    public ModelAndView overApply() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_over_apply");
        return mav;
    }
    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/overQuery")
    public ModelAndView overQuery() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_over_query");
        return mav;
    }
    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/welfareApply")
    public ModelAndView welfareApply() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_welfare_apply");
        return mav;
    }
    /**
     *
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/welfareQuery")
    public ModelAndView welfareQuery() {
        ModelAndView mav = new ModelAndView("/includejsp/emp/emp_welfare_query");
        return mav;
    }
}
