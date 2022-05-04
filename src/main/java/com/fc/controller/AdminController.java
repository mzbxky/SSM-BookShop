package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.AdminByUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    private AdminByUserService adminByUserService;
    @PostMapping("user_add")
    public ModelAndView userAdd(ModelAndView mv, HttpSession session, User user){
        mv = adminByUserService.userAdd(mv,session,user);
        return mv;
    }
    @GetMapping("user_edit_show")
    public ModelAndView userEditShow(Integer uid,HttpSession session,ModelAndView mv){
        mv = adminByUserService.serEditShow(uid,session,mv);
        return mv;
    }
    @RequestMapping("user_list")
    public ModelAndView user_list(@RequestParam("pageNumber") Integer pageNumber,ModelAndView mv,HttpSession session){
        mv = adminByUserService.user_list(mv,pageNumber,session);
        return mv;
    }
    @GetMapping("user_delete")
    public ModelAndView delete(Integer uid,ModelAndView mv){
        mv = adminByUserService.delete(uid,mv);
        return mv;
    }
    @PostMapping("user_update")
    public ModelAndView userUpdate(User user,HttpSession session,ModelAndView mv){
        mv = adminByUserService.update(user,session,mv);

        return mv;
    }
    @PostMapping("change_password")
    public ModelAndView changePassword(User user,ModelAndView mv,HttpSession session){
        mv = adminByUserService.updatePassword(user,mv,session);
        return mv;
    }
    @GetMapping("logout")
    public ModelAndView logout(ModelAndView mv,HttpSession session){
        session.removeAttribute("user");
        mv.setViewName("redirect:index.jsp");
        return mv;
    }
}
