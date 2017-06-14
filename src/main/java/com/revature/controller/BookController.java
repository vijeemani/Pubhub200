package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.dao.BookDAO;
import com.revature.dao.UserDAO;
import com.revature.model.Book;
import com.revature.model.User;

@Controller
@RequestMapping("/book")

public class BookController {
	@Autowired
	private BookDAO bookDAO;
	
	
	@GetMapping("/addbooks")
	public String add_book(){
	return"addbook";
	}
	
	@GetMapping("/books")
	public String book(@RequestParam("name") String bname, @RequestParam("aname") String auname,
			@RequestParam("description") String des, @RequestParam("price") Integer amount) {
		Book book = new Book();
		book.setTitle(bname);
		book.setAuthorName(auname);
		book.setDescrption(des);
		book.setPrice(amount);
		book.toString();
		bookDAO.save(book);
		//System.out.println(bname+auname+des+amount);
		return "redirect:/users/list";
	}
	
	@GetMapping("/booklist")
	public String list(ModelMap modelMap) {

		System.out.println("BookController -> list");
		List<Book> booksList = bookDAO.findAll();
		modelMap.addAttribute("BOOK_LIST",booksList);

		return "list_books";
	}
	
	@GetMapping("/bookview")
	public String ulist(ModelMap modelMap) {

		System.out.println("BookController -> list");
		List<Book> booksList = bookDAO.findAll();
		modelMap.addAttribute("BOOK_LIST",booksList);

		return "user_view";
	}

@GetMapping("/ordered")
public String Olist(){
	return"orders_list";
}
	

}
