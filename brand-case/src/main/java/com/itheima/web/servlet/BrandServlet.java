package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService=new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1调用service查询
        List<Brand> brands = brandService.selectAll();
        //2转为JSON
        String jsonString = JSON.toJSONString(brands);
        //3写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1接受数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//JSON字符串

        //2转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService.add(brand);

        //3响应成功的标识
        response.getWriter().write("success");
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1接受数据


        String id = request.getParameter("id");

        brandService.delete(Integer.parseInt(id));
        //3响应成功的标识

        response.getWriter().write("deletesuccess");
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1接受数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//JSON字符串

        //2转为brand对象
        String _id = request.getParameter("id");
        int id=Integer.parseInt(_id);

        Brand _brand = JSON.parseObject(params, Brand.class);
        Brand brand = new Brand();
        brand.setId(id);
        brand.setBrandName(_brand.getBrandName());
        brand.setCompanyName(_brand.getCompanyName());
        brand.setOrdered(_brand.getOrdered());
        brand.setDescription(_brand.getDescription());
        brand.setStatus(_brand.getStatus());
        brandService.update(brand);

        //3响应成功的标识
        response.getWriter().write("updatesuccess");
    }


    //批量删除
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1接受数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//JSON字符串

        //2转为int[]
        int[] ids = JSON.parseObject(params, int[].class);
        brandService.deleteByIds(ids);

        //3响应成功的标识
        response.getWriter().write("deleteByIdssuccess");
    }

    //分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1接受当且页码和每页展示条数url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //2调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        //3转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //4写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1接受当且页码和每页展示条数url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询对象条件
        BufferedReader br = request.getReader();
        String params = br.readLine();//JSON字符串

        //2转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);
        //3转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //4写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
