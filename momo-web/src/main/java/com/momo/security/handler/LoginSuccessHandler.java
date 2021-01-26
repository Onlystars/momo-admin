package com.momo.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.momo.jwt.JwtUtils;
import com.momo.result.ResultUtils;
import com.momo.system.permission.Vo.MenuVo;
import com.momo.system.permission.entity.SysPermission;
import com.momo.system.user.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录认证成功处理器
 */
@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private JwtUtils jwtUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        MenuVo vo = new MenuVo();
        //1.获取用户信息
        SysUser user = (SysUser)authentication.getPrincipal();
        //2.生成token
        String token = jwtUtils.generateToken(user);
        vo.setToken(token);
        vo.setUserId(user.getId());
        //3.查询用户菜单权限
        List<SysPermission> permissionList = user.getPermissionList();
        if(permissionList.size() > 0){
            //设置用户拥有的权限字段
            List<String> auth = permissionList.stream().filter(item -> item != null).map(item -> item.getCode()).collect(Collectors.toList());
            vo.setAuthList(auth);
            //获取除按钮以外的菜单
            List<SysPermission> collect = permissionList.stream().filter(item -> item != null && !item.getType().equals("2")).collect(Collectors.toList());
            //生成菜单树数据
            List<SysPermission> listMenu = makeTree(collect, 0L);
            vo.setMenuList(listMenu);
            //获取路由数据
            List<SysPermission> routerList = permissionList.stream().filter(item -> item != null && item.getType().equals("1")).collect(Collectors.toList());
            vo.setRouterList(routerList);
        }
        String str = JSONObject.toJSONString(ResultUtils.success("认证成功",vo), SerializerFeature.DisableCircularReferenceDetect);
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
    /**
     * 组装树
     *
     * @param menuList
     * @param pId
     * @return
     */
    private static List<SysPermission> makeTree(List<SysPermission> menuList, Long pId) {
        //顶级菜单
        List<SysPermission> topMenuList = menuList.stream().filter(x -> x.getParentId() == pId).collect(Collectors.toList());
        //附属菜单
        List<SysPermission> childrenMenuList = menuList.stream().filter(x -> x.getParentId() != pId).collect(Collectors.toList());
        if (topMenuList.size() > 0) {
            topMenuList.forEach(x ->
                    {
                        if (childrenMenuList.size() > 0) {
                            makeTree(childrenMenuList, x.getId()).forEach(y -> x.getChildren().add(y));
                        }
                    }
            );
        }
        return topMenuList;
    }
}
