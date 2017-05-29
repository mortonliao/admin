package com.longhum.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longhum.admin.entity.ResultSimpleDate;
import com.longhum.admin.model.SysRole;
import com.longhum.admin.model.SysUser;
import com.longhum.admin.service.SysUserRoleService;
import com.longhum.admin.service.SysUserService;

/**
 * @author liaoxiaohu
 * @date 2017年5月14日
 * @info
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysUserRoleService userRoleService;
	
	@GetMapping("/login.do")
	public String loginForm() {
		return "usercenter/login";
	}
	
	@PostMapping("/login.do")
	public String login(@Valid SysUser user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "/user/login";
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
			return "redirect:/index.do";
		} catch (LockedAccountException lae) {
			redirectAttributes.addFlashAttribute("msg", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			redirectAttributes.addFlashAttribute("msg", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			redirectAttributes.addFlashAttribute("msg", "用户名或密码不正确");
		}
		return "redirect:/user/login.do";
	}

	@GetMapping("/logout.do")
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/user/login.do";
	}
	
	@GetMapping("/userList.do")
	public String userList(ModelMap map){
		List<SysUser> list = userService.findAll();
		List<SysRole> roleList = userRoleService.findAllRole();
		map.put("list", list);
		map.put("roleList", roleList);
		return "usercenter/userList";
	}
	
	@PostMapping("/saveUser.do")
	public String saveUser(SysUser user,ModelMap map){
		user.setPassword("1234qwer");
		userService.saveUser(user);
		System.out.println(user);
		List<SysUser> list = userService.findAll();
		List<SysRole> roleList = userRoleService.findAllRole();
		map.put("list", list);
		map.put("roleList", roleList);
		return "redirect:/user/userList.do";
	}
	
	@GetMapping("/findUserById.do")
	@ResponseBody
	public ResultSimpleDate findUserById(Long userId){
		SysUser user = userService.findById(userId);
		return ResultSimpleDate.ok("查询成功", user);
	}
	
	@GetMapping("/deleteUser.do")
	public String deleteUser(Long userId){
		userService.deleteUser(userId);
		return "redirect:/user/userList.do";
	}
	
}
