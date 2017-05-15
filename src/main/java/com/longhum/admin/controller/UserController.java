package com.longhum.admin.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longhum.admin.model.TUser;
import com.longhum.admin.service.UUserService;
import com.longhum.admin.service.UserService;

/**
 * @author liaoxiaohu
 * @date 2017年5月14日
 * @info
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/*
	 * @Autowired private UserService userService;
	 * 
	 * @Autowired private UUserService uuserService;
	 */

	@GetMapping("/login")
	public String loginForm() {
		return "usercenter/login";
	}

	@PostMapping("/login")
	public String login(@Valid TUser user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			ModelMap map) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		String loginName = user.getUsername();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, user.getPassword());
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			redirectAttributes.addFlashAttribute("message", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			return "redirect:/index";
		} else {
			token.clear();
			return "redirect:/user/login";
		}
		/*
		 * user = userService.findById(1);
		 * System.out.println(user.getUsername()); UUser uuser =
		 * uuserService.findById(1); System.out.println(uuser.getNickName());
		 * List<ResourceMenu> list = new ArrayList<ResourceMenu>(); ResourceMenu
		 * menu1 = new ResourceMenu(1, 0, "会员管理"); ResourceMenu menu2 = new
		 * ResourceMenu(2, 0, "系统管理"); ResourceMenu menu3 = new ResourceMenu(3,
		 * 0, "报表管理"); ResourceMenu menu4 = new ResourceMenu(4, 0, "监控管理");
		 * 
		 * list.add(menu1); list.add(menu2); list.add(menu3); list.add(menu4);
		 * 
		 * map.put("menuList", list);
		 */
		// return "index";
	}

	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/user/login";
	}

}
