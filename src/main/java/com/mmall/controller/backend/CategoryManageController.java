package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author gexiao
 * @date 2018/9/20 9:59
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICategoryService categoryService;

    /**
     * 添加品种
     *
     * @param session
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",
            defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验是否是管理员
        if (userService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return categoryService.addCategory(categoryName, parentId);
        }
        return ServerResponse.createByErrorMessage("无权限，不是管理员");
    }

    /**
     * 更新品类名字
     *
     * @param session
     * @param categoryName
     * @param categoryId
     * @return
     */
    @RequestMapping("set_categoryName.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, String categoryName, int categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验是否是管理员
        if (userService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return categoryService.updateCategoryName(categoryName, categoryId);
        }
        return ServerResponse.createByErrorMessage("无权限，不是管理员");
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId",
            defaultValue = "0") int categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验是否是管理员
        if (userService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //查询子节点的category信息，并且不递归，平级
            return categoryService.getChidrenParallelCategory(categoryId);
        }
        return ServerResponse.createByErrorMessage("无权限，不是管理员");
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId",
            defaultValue = "0") int categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验是否是管理员
        if (userService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return categoryService.selectCategoryAndChildrenById(categoryId);
        }
        return ServerResponse.createByErrorMessage("无权限，不是管理员");
    }
}
