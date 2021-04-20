package com.applecat.blog.controller.admin;

import java.util.ArrayList;
import java.util.List;

import com.applecat.blog.model.pojo.Tag;
import com.applecat.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
@RequestMapping(path = "/admin")
public class TagController {
    
    @Autowired
    private TagService tagService;

    /**
     * 标签管理页面
     */
    @GetMapping("/tags")
    public String tags(Model model){
        List<Tag> tags =  tagService.listTag();
        //如果为空创建一个新的,防止空指针异常
        if (tags == null) tags = new ArrayList<>();
        //记录总数
        int total = tags.size();
        model.addAttribute("tags", tags);
        model.addAttribute("total", total);
        return "admin/tags";
    }

    /**
     * 保存新标签
     * @param newTagName 新标签名称
     * @return
     */
    @GetMapping("/tags/save")
    public String saveNewTag(String newTagName,RedirectAttributesModelMap model){
        String[] result =  tagService.saveTag(newTagName);

        if ("true".equals(result[0])) {
            model.addFlashAttribute("status", true);
        }else{
            model.addFlashAttribute("status", false);
        }
        
        model.addFlashAttribute("msg", result[1]);
        return "redirect:/admin/tags";
    }


    /**
     * 将前端删除复选框中的id封装为id数组
     * 根据分类的id,来删除多个分类
     * @param delTagIDs 分类的名称组
     * @return
     */
    @GetMapping("/tags/del")
    public String deleteTags(int[] delTagIDs, RedirectAttributesModelMap model){
        try {
            tagService.deleteTags(delTagIDs); 
        } catch (Exception e) {
            model.addFlashAttribute("status", false);
            model.addFlashAttribute("msg", "有博客正在使用该标签!");
        }
        return "redirect:/admin/tags";
    }
}