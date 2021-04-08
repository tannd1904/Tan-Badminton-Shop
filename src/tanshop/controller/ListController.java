package tanshop.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.bean.cart;
import shop.entity.advertise;
import shop.entity.menu;
import shop.entity.product;

@Transactional
@Controller
@RequestMapping("list")
public class ListController {
	@Autowired

	SessionFactory factory;
	public static String name;
	public static int type1;
	public static List<product> newList = new ArrayList<>();
	@ModelAttribute("menu")
	public List<menu> Nav(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql="FROM menu";
		Query query=session.createQuery(hql);
		List<menu> list = query.list();
		System.out.print(name);
		return list;
	}
	@Autowired
	MainController cc;
	@ModelAttribute("menu")
	public void user(ModelMap model) {
		model.addAttribute("username",cc.username);
	}
	@RequestMapping("pro")
	public String insert(ModelMap model){
		System.out.print(type1);
		model.addAttribute("type", type1);
		model.addAttribute("item", newList);
		return "redirect:/list/"+ name +".htm";
	}
	@RequestMapping("product")
	public String allPro(ModelMap model) {
		name = "product";
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.status = '1'";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			newList = new ArrayList<>();
			//model.addAttribute("products", list);
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("vot-cau-long")
	public String allRacket(ModelMap model) {
		name = "vot";	
		type1 = 1;
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=0)";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			newList = new ArrayList<>();
			//model.addAttribute("products", list);
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("type", type1);
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	
	@RequestMapping("giay-cau-long")
	public String allShoes(ModelMap model) {
		name = "giay";	
		type1 = 2;
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=1)";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("type", type1);
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	
	@RequestMapping("ao-quan-cau-long")
	public String allClothes(ModelMap model) {
		name = "aoquan";	
		type1 = 3;
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=2)";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("type", type1);
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	
	@RequestMapping("phu-kien")
	public String allItems(ModelMap model) {
		name = "phukien";	
		type1 = 4;
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=3)";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("type", type1);
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	
	@RequestMapping("new")
	public String newPro(ModelMap model) {
		name = "new";
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.status = '1' ORDER BY date DESC";
			Query query = session.createQuery(hql).setMaxResults(20);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("sale")
	public String salePro(ModelMap model) {
		name = "sale";
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM product pr where pr.status = '1' and sale != 0";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<product> list = query.list();
			//model.addAttribute("products", list);
			newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			model.addAttribute("item", newList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "list";
	}
	@RequestMapping("/{type}")
	public String typePro(ModelMap model, HttpServletRequest  request, @PathVariable("type") String type){
		if (type.contains("search")) {
			String search = request.getParameter("search_string");
			List<product> newList1 = new ArrayList<>();
//			search= request.getParameter("search_string");
			try {
				Session session = factory.getCurrentSession();
				String hql = "FROM product pr where pr.status = '1'";
				Query query = session.createQuery(hql);
				@SuppressWarnings("unchecked")
				
				List<product> list = query.list();
				
				//model.addAttribute("products", list);
				for (int i = 0; i < list.size(); i++) {
					newList1.add(list.get(i));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			newList = new ArrayList<>();
			for (int i=0; i<newList1.size(); i++) {
				//so sanh dieu kien
				if (newList1.get(i).getId().contains(search) || newList1.get(i).getName().contains(search)) {
					product p = newList1.get(i);
					newList.add(p);
				}
			}
			if (newList.size() == 0) {
				type1 = 0;
			}
			else {
				type1 = 4;
			}
			model.addAttribute("type", type1);
			model.addAttribute("item", newList);
		}
		else {
			try {
				Session session = factory.getCurrentSession();
				String hql1 = "FROM product p WHERE p.GroupProduct.id = :type and p.status = '1'";
				Query query1 = session.createQuery(hql1);
				name = type;
				query1.setParameter("type", type);
				List<product> list1 = query1.list();
				newList = new ArrayList<>();
				for (int i = 0; i < list1.size(); i++) {
					newList.add(list1.get(i));
				}
				
				if (newList.size() == 0) {
					type1 = 0;
				}
				else {
					String s = newList.get(0).getGroupProduct().getId();
					if (s.contains("v")) {
						type1 = 1;
					}
					else if (s.contains("g")) {
						type1 = 2;
					}
					else if (s.contains("aq")) {
						type1 = 3;
					}
					else {
						type1 = 4;
					}
				}
				
				System.out.print(type1);
				
				model.addAttribute("type", type1);
				model.addAttribute("item", newList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "list";
	}
	
	
//	@RequestMapping(value = "search", method = RequestMethod.GET) 
//	public String search() {
//		return "list";
//	}
	
	@Autowired
	MainController mc;
	@ModelAttribute("cart")
	public List<cart> getListGioHang() {
		return MainController.cartlist;
	}
	@ModelAttribute("dem")
	public int dem() {
		return MainController.dem;
	}
	@ModelAttribute("tongTien")
	public Float tien() {
		return MainController.tinhTongtien(MainController.cartlist);
	}
}