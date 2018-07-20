package site.qipeng.wxapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.qipeng.wxapi.entity.Category;
import site.qipeng.wxapi.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "list")
    public List<Category> getAll(){
        return categoryService.findAll();
    }


}
