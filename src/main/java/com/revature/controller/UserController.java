package com.revature.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.dao.BookDAO;
import com.revature.dao.UserDAO;
import com.revature.model.Book;
import com.revature.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	private BookDAO bookDAO;

	@GetMapping("/list")
	public String list(ModelMap modelMap) {

		System.out.println("UserController -> list");
		List<User> userList = userDAO.findAll();
		modelMap.addAttribute("USER_LIST", userList);

		return "user_list";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/alogin")
	public String alogin() {
		return "user_login";
	}

	@PostMapping("/validate")
	public String showLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap map) throws SQLException {
		String rereturn;
		if (userDAO.login(email, password) == true) {
			rereturn = "user_list";
		} else {
			rereturn = "user_list";
		}

		return rereturn;
	}

	@PostMapping("/user_validate")
	public String user_Login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap map) throws SQLException {
		String rereturn;
		if (userDAO.login(email, password) == true) {
			rereturn = "user_index";
		} else {
			rereturn = "user_login";
		}

		return rereturn;
	}
	
	@GetMapping("/avalidate")
	public String AdminLogin(@RequestParam("name") String name, @RequestParam("password") String password, ModelMap map)
			throws SQLException {
		String rereturn;
		if (name.equals("admin") && password.equals("1234")) {
			rereturn = "admin";
		} else {
			rereturn = "admin_login";
		}

		return rereturn;
	}

	@GetMapping("/create")
	public String create() {
		return "add_user";
	}

	@GetMapping("/save")
	public String save(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password, ModelMap map) {

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		System.out.println(user);

		userDAO.save(user);

		return "redirect:/users/list";
	}
	
	

	
	@GetMapping("/edit")
	public String edit(@RequestParam("id") Integer id, ModelMap model) {

		User user = userDAO.findOne(id);
		model.addAttribute("USER_DETAIL", user);
		return "update_user";
	}

	@GetMapping("/activate")
	public String activateAccount(@RequestParam("id") Integer id) {

		userDAO.activateAccount(id);
		return "redirect:users/users/list";
	}

	@GetMapping("/deactivate")
	public String deActivateAccount(@RequestParam("id") Integer id) {

		userDAO.deActivateAccount(id);
		return "redirect:users/users/list";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("password") String password) {

		User user = userDAO.findOne(id);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);

		userDAO.update(user);

		return "redirect:/users/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id) {
		userDAO.delete(id);
		return "redirect:users/users/list";
	}

	
}
