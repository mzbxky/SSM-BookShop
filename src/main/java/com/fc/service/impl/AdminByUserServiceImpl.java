package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.service.AdminByUserService;
import com.fc.vo.PageVO;
import com.fc.vo.UserVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class AdminByUserServiceImpl implements AdminByUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ModelAndView userAdd(ModelAndView mv, HttpSession session, User user) {
        user.setUmark("普通用户");
        user.setUrole(1);
        if (user.getUredgt() == null){
            user.setUredgt(new Date());
        }

        int affectedRows = userMapper.insert(user);
        if (affectedRows>0){
            System.out.println("进入跳转");
            mv.setViewName("forward:user_list?pageNumber=1");
        }else {
            session.setAttribute("msg","用户名重复");

            mv.setViewName("/admin/user_add");

        }

        return mv;
    }

    @Override
    public ModelAndView serEditShow(Integer uid, HttpSession session, ModelAndView mv) {
        User user = userMapper.selectByPrimaryKey(uid);
        if (user!=null){
            session.setAttribute("u",user);
            mv.setViewName("/admin/user_edit");
        }else {
            mv.setViewName("/user_list?pageNumber=1");
        }
        return mv;
    }

    @Override
    public ModelAndView user_list(ModelAndView mv, Integer pageNumber, HttpSession session) {
        PageHelper.startPage(pageNumber, 8);
        List<User> users = userMapper.findAll();
//        PageInfo<User> pageInfo = new PageInfo<>(users);
        PageVO<User> pageVO = new PageVO<>(users);

        mv.setViewName("admin/user_list");
        session.setAttribute("p",pageVO);
        return mv;
    }

    @Override
    public ModelAndView delete(Integer uid, ModelAndView mv) {
        int affectedRows = userMapper.delete(uid);
        mv.setViewName("redirect:user_list?pageNumber=1");

        return mv;
    }

    @Override
    public ModelAndView update(User user, HttpSession session, ModelAndView mv) {
        int affectedRows = userMapper.update(user);
        UserVO user1 = (UserVO) session.getAttribute("user");
        if (user1.getUid() == user.getUid()){
            user1.setUphone(user.getUphone());
            user1.setUaddress(user.getUaddress());
            session.setAttribute("user",user1);
        }
        mv.setViewName("redirect:user_list?pageNumber=1");
        return mv;
    }

    @Override
    public ModelAndView updatePassword(User user, ModelAndView mv, HttpSession session) {
        int affectedRows = userMapper.updatePassword(user.getUid(),user.getUpwd());
        if (affectedRows>0){
            session.setAttribute("msg","修改成功");
        }else {
            session.setAttribute("failMsg","修改失败");
        }
        mv.setViewName("redirect:user_list?pageNumber=1");
        return mv;
    }
}
