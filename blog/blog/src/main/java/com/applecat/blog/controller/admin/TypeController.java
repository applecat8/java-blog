package com.applecat.blog.controller.admin;

import java.util.ArrayList;
import java.util.List;

import com.applecat.blog.model.pojo.Type;
import com.applecat.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class TypeController {
    
    @Autowired
    private TypeService typeService;

    /**
     * 分类管理页面
     */
    @GetMapping("/types")
    public String types(Model model){
        List<Type> types =  typeService.listType();
        if (types == null) types = new ArrayList<>();
        model.addAttribute("types", types);
        return "admin/types";
    }

    /**
     * 保存新分类
     * @param newTypeName 新分类名称
     * @return
     */
    @GetMapping("/types/save")
    public String saveNewType(String newTypeName){
        typeService.saveType(newTypeName);
        return "redirect:/admin/types";
    }


    /**
     * 根据分类的名称组(实际是分类的Id),来删除多个分类
     * @param delTypeNames 分类的名称组
     * @return
     */
    @GetMapping("/types/del")
    public String deleteTypes(int[] delTypeNames){
        typeService.deleteTypes(delTypeNames); 
        return "redirect:/admin/types";
    }
}