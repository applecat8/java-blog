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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
        //如果为空创建一个新的,防止空指针异常
        if (types == null) types = new ArrayList<>();
        //记录总数
        int total = types.size();
        model.addAttribute("types", types);
        model.addAttribute("total", total);
        return "admin/types";
    }

    /**
     * 保存新分类
     * @param newTypeName 新分类名称
     * @return
     */
    @GetMapping("/types/save")
    public String saveNewType(String newTypeName,RedirectAttributesModelMap model){
        String[] result =  typeService.saveType(newTypeName);

        if ("true".equals(result[0])) {
            model.addFlashAttribute("status", true);
        }else{
            model.addFlashAttribute("status", false);
        }
        
        model.addFlashAttribute("msg", result[1]);
        return "redirect:/admin/types";
    }


    /**
     * 将前端删除复选框中的id封装为id数组
     * 根据分类的id,来删除多个分类
     * @param delTypeIDs 分类的名称组
     * @return
     */
    @GetMapping("/types/del")
    public String deleteTypes(int[] delTypeIDs, RedirectAttributesModelMap model){
        try {
            typeService.deleteTypes(delTypeIDs); 
        } catch (Exception e) {
            //出现错误提示
            model.addFlashAttribute("status", false);
            model.addFlashAttribute("msg", "有博客正在使用该分类");
        }
        return "redirect:/admin/types";
    }
}