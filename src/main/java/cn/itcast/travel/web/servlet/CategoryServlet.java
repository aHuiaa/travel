package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseSevlet {
    /**
     * 查找所有的分类信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void findAll( HttpServletRequest request, HttpServletResponse response ) throws IOException {
        //1. 调用secice
        //创建service对象
        CategoryService service = new CategoryServiceImpl();
        List<Category> categories = service.findCategory();

        //将返回的数据序列化为Json数据并响应数据
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),categories);

    }
}
