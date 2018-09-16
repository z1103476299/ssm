package com.ssm.web;

import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping(value = "goLogin")
    public String toLogin(){
        return "UserLogin";
    }


    /**
     * 跳转到添加用户界面
     * @return
     */
    @RequestMapping(value = "toAdd")
    public String toAddUser(){
        return "addUser";
    }

    /**
     * 添加用户并重定向
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "addUser")
    public String addUser(User user){
        if (user!=null){
            userService.saveUser(user);
        }
        return "redirect:/user/goLogin";
    }

    /**
     * 修改用户
     * @param model
     * @param user1
     * @return
     */
    @RequestMapping(value = "/update")
    public String updateUser(User user1,Model model){
        /**
         * 这一块 userService.updateUser(user1) 数据就是null ...?
         */
        if (userService.updateUser(user1)){
            user1 = userService.findById(user1.getId()) ;
            /*model.addAttribute("user",user1);*/
            return "redirect:/user/findAll";
        }
           return "error";
    }

    /**
     * 登录验证界面
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String check(String username, String password, Model model, HttpServletRequest request){
        User user = userService.checkUser(username,password);
        System.out.println("用户登录:"+username+" "+password);
        request.getSession().setAttribute("User",username);
        if (user!=null) {
            model.addAttribute("user", user);
            return "redirect:/user/findAll";
        }
        return "redirect:/user/goLogin";
    }

    /**
     * 查询所有
     * @param model
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("list",userList);
        return "allUser";
    }

    /**
     * 查询单个用户
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUser")
    public String getUser(Model model,int id){
        model.addAttribute("user",userService.findById(id));
        return "editUser";
    }

    /**
     * 根据id删除用户
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/delUser")
    public String deleteUser(int id,Model model){
        model.addAttribute("user",userService.deleteUser(id));
        return "redirect:/user/findAll";
    }



}
