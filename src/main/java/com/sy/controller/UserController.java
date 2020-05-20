package com.sy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.bean.User;
import com.sy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @类名: com.sy.controller
 * @作者:
 * @创建时间: 2020-05-19 14:57
 * @描述:
 **/
@Controller
public class UserController {
    @Resource
    private UserMapper usersMapper;

    //添加页面
    @RequestMapping("add")
    public String add() {
        return "add";
    }

    //查找(用于查询)
    @RequestMapping("getUser")
    public String getUser(int id, Model model) throws Exception {
        User User = usersMapper.getUser(id);
        model.addAttribute("Users", User);
        return "userShow";
    }
    //添加
    @RequestMapping("addUser")
    public String listUser(User user, BindingResult bindingResult) throws Exception {
        boolean flag = usersMapper.add(user) > 0;
        return "redirect:listUser";
    }

    //删除
    @RequestMapping("deleteUser")
    public String deleteUser(User user) throws Exception {
        usersMapper.del(user.getId());
        return "redirect:listUser";
    }

    //修改
    @RequestMapping("updateUser")
    public String updateUser(User User) throws Exception {
        boolean flag = usersMapper.update(User) > 0;
        return "redirect:listUser";
    }

    //查找(用于修改)
    @RequestMapping("findUser")
    public String findUser(int id, Model model) throws Exception {
        User User = usersMapper.getUser(id);
        model.addAttribute("Users", User);
        return "modify";
    }

    //遍历
    @RequestMapping({"/","listUser"})
    public String listUser(@RequestParam(value = "name",defaultValue = "") String name,
                           Model model, @RequestParam(value = "start", defaultValue = "1") int start,
                           @RequestParam(value = "size", defaultValue = "2") int size) throws Exception {
        PageHelper.startPage(start, size, "id asc");
        List<User> userList = usersMapper.getUsersList(name);
        PageInfo<User> page = new PageInfo<>(userList);
        model.addAttribute("pages", page);
        model.addAttribute("name", name);
        return "index";
    }

}
