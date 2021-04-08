package tanshop.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shop.entity.groupProduct;
import shop.entity.menu;
import shop.entity.order;
import shop.entity.orderDetail;
import shop.entity.product;
import shop.entity.productDetail;
import shop.entity.user;

@Transactional
@Controller
@RequestMapping("admin")
public class adminController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	
	private static String tb = "Chào mừng bạn đến với trang quản lý của TanShop!";
	public static String userLogin = "Guest";
	
	@RequestMapping(value = "index", method = RequestMethod.GET) 
	public String pr(Model model, @ModelAttribute("prod") product pr) {
		if (!userLogin.equals("Guest")) {
			tb = "Chào mừng bạn đến với trang quản lý của TanShop!";
			model.addAttribute("tb", tb);
			return "admin/index";
		}
		else {
			return "admin/login";
		}
		
	}
	@RequestMapping("donhang")
	public String dh(Model model) {

		return "admin/donhang";
	}
	
	@RequestMapping("logout")
	public String dangxuat(ModelMap model) {
		userLogin = "Guest";
		return "admin/login";
	}
	
	@RequestMapping(value = "login" ,method=RequestMethod.POST)
	public String dangnhapAdmin(Model model,HttpServletRequest re) throws ParseException {
		Session session = factory.getCurrentSession();
		String hql = "FROM admin WHERE username = ? AND password = ?";
		Query query =session.createQuery(hql);
		query.setString(0, re.getParameter("user"));
		query.setString(1, re.getParameter("pass"));
		String username="";
		List<user> list = query.list();
		if(!list.isEmpty()) {
			username = re.getParameter("user");
			model.addAttribute("username", username);
			HttpSession sess = re.getSession();
			userLogin = username;
			sess.setAttribute("username", username);
			tb = "Chào mừng bạn đến với trang quản lý của TanShop!";
			model.addAttribute("tb", tb);
			return "admin/index";
		}else {
			model.addAttribute("tb", "Sai Username hoặc Password!");
			return "admin/login";
		}
		
	}
	@RequestMapping(value = "login" ,method=RequestMethod.GET)
	public String dangnhhapAdmin(ModelMap model,@ModelAttribute("User") user us) {
		return "admin/login";
	}
	
	@RequestMapping(value = "gr-product", method = RequestMethod.POST)
	public String them(Model model, HttpServletRequest re) {
		groupProduct gpr = new groupProduct();
		menu m = new menu();
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		tb="";
		if (re.getParameter("id").trim().length() == 0) {
			tb="Không được để trống ID Nhóm Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else if (re.getParameter("name").trim().length() == 0) {
			tb="Không được để trống Tên Nhóm Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else {
			try {
				if (s.get(groupProduct.class, re.getParameter("id").trim()) == null) {
					gpr.setId(re.getParameter("id"));
					gpr.setName(re.getParameter("name"));
					gpr.setContent(re.getParameter("content"));
					gpr.setStatus(Integer.parseInt(re.getParameter("brands")));
					s.save(gpr);
					m.setName(re.getParameter("name"));
					m.setLink(re.getParameter("id"));
					m.setParentid(Integer.parseInt(re.getParameter("brands")) + 1);
					s.save(m);
					t.commit();
					tb= "Thêm nhóm thành công!";
					model.addAttribute("tb",tb);
				} else {
					tb = "Thêm nhóm sản phẩm thất bại, vui lòng kiểm tra lại!";
					model.addAttribute("tb", tb);
				}
			} catch (Exception e) {
				t.rollback();
				tb="Thêm thất bại, vui lòng kiểm tra lại";
				model.addAttribute("tb", tb);
			} finally {
				s.close();
			}
		}
		return "redirect:/admin/product.htm";
	}

	@RequestMapping(value = "gr-product", method = RequestMethod.GET)
	public String loadthem(Model model, HttpServletRequest re) {
		return "admin/index";
	}

	@RequestMapping(value = "productde", method = RequestMethod.GET)
	public String loadthem1(Model model, HttpServletRequest re) {
		return "admin/index";
	}

	@RequestMapping(value = "product", method = RequestMethod.GET)
	public String loadthem(Model model, HttpServletRequest re, @ModelAttribute("prod") product pr) {
		model.addAttribute("tb",tb);
		return "admin/index";
	}

	@RequestMapping(value = "editpr", method = RequestMethod.GET)
	public String loadthem(Model model, @RequestParam("idSanPham") int idSanPham, HttpServletRequest re,
			@RequestParam("img1") String img1, @RequestParam("img2") String img2, @RequestParam("img3") String img3)
			throws IllegalStateException, IOException {
		Session s = factory.getCurrentSession();
		String hql = "from productDetail ";
		Query query = s.createQuery(hql);
		List<productDetail> list = query.list();

		for (productDetail a : list) {
			if (a.getId() == idSanPham) {
				productDetail prd = (productDetail) s.load(productDetail.class, idSanPham);
				model.addAttribute("prd", prd);
			}
		}

		return "admin/edit";
	}

	@RequestMapping(value = "product", method = RequestMethod.POST)
	public String themsp(Model model, HttpServletRequest re, @RequestParam("img1") MultipartFile img1,
			@RequestParam("img2") MultipartFile img2, @RequestParam("img3") MultipartFile img3)
			throws ServletException, IOException {
		if (userLogin.equals("Guest")) {
			return "admin/login";
		}
		else {
			
		product pr = new product();
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		tb="";
		if (re.getParameter("id").trim().length() == 0) {
			tb = "Không được để trống ID Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else if (re.getParameter("name").trim().length() == 0) {
			tb = "Không được để trống Tên Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else if (re.getParameter("coler").trim().length() == 0) {
			tb = "Không được để trống Màu Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else if (Double.parseDouble(re.getParameter("p")) < 1000) {
			tb = "Vui lòng điền Giá Sản phẩm hợp lệ!";
			model.addAttribute("tb",tb);
		}
		else if (img1.isEmpty()) {
			model.addAttribute("tb", "Vui lòng chọn ít nhất 1 hình ảnh!");
		} else {
			Integer sale;
			int price = Integer.parseInt(re.getParameter("p"));
			sale = Integer.parseInt(re.getParameter("s"));
			
			try {
				pr.setGroupProduct((groupProduct) s.get(groupProduct.class, re.getParameter("grid")));
				pr.setDate(new Date());
				pr.setSold(0);
				pr.setId(re.getParameter("id"));
				pr.setName(re.getParameter("name"));
				pr.setColer(re.getParameter("coler"));
				pr.setContent(re.getParameter("content"));
				pr.setSale(sale);
				pr.setPrice(price);
				pr.setStatus(0);
				String img1Path = context.getRealPath("/resources/img/pro/" + img1.getOriginalFilename());
				img1.transferTo(new File(img1Path));
				String img2Path = context.getRealPath("/resources/img/pro/" + img2.getOriginalFilename());
				img2.transferTo(new File(img2Path));
				String img3Path = context.getRealPath("/resources/img/pro/" + img3.getOriginalFilename());
				img3.transferTo(new File(img3Path));
				model.addAttribute("img1", img1.getOriginalFilename());
				model.addAttribute("img2", img1.getOriginalFilename());
				model.addAttribute("img3", img1.getOriginalFilename());
				pr.setImg1(img1.getOriginalFilename());
				pr.setImg2(img2.getOriginalFilename());
				pr.setImg3(img3.getOriginalFilename());
				s.save(pr);
				t.commit();
				tb="Thêm Sản phẩm thành công!";
				model.addAttribute("tb", tb);
				getpr(model);
			} catch (Exception e) {
				t.rollback();
				tb="Thêm Sản phẩm thất bại!";
				model.addAttribute("tb", tb);
			} finally {
				loadlistgr(model);
				s.close();
			}
		}
		return "admin/index";
		}
	}
	
	public boolean doiTrangThai(String id) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "UPDATE product pr SET pr.status=1 WHERE pr.id= :id";
		try {
			Query query = s.createQuery(hql);
			query.setParameter("id", id);
			query.executeUpdate();
			t.commit();
			return true;
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
		
	}
	
	@RequestMapping(value = "productde", method = RequestMethod.POST)
	public String them2(Model model, HttpServletRequest re) {
		productDetail prd = new productDetail();
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		tb="";
		if (Integer.parseInt(re.getParameter("quanlity")) <= 0) {
			tb = "Số lượng phải lớn hơn 0!";
			model.addAttribute("tb", tb);
		}
		else {
			try {
				prd.setQuanlity(Integer.parseInt(re.getParameter("quanlity")));
				prd.setSize(re.getParameter("size"));
				prd.setProduct((product) s.get(product.class, re.getParameter("prid")));
				prd.setStatus(1);
				//doi trang thai cua san pham co id = prd.id tu 0 sang 1
				if (this.doiTrangThai(prd.getProduct().getId())) {
					s.save(prd);
					t.commit();
					tb="Thêm thành công!";
					model.addAttribute("tb", tb);
					loadlistgr(model);
				}
				else {
					tb = "Thêm Chi tiết thất bại!";
					model.addAttribute("tb",tb);
				}

			} catch (Exception e) {
				t.rollback();
				tb="Thêm thất bại!";
				model.addAttribute("tb", tb);
			} finally {
				s.close();
			}
		}
		
		return "redirect:/admin/product.htm";
	}

	@ModelAttribute("grpr")
	public List<groupProduct> getgr() {
		Session s = factory.getCurrentSession();
		String hql = "from groupProduct";
		Query query = s.createQuery(hql);
		List<groupProduct> list = query.list();
		return list;
	}

	@ModelAttribute("prr")
	public List<product> getgrr() {
		Session s = factory.getCurrentSession();
		String hql = "from product";
		Query query = s.createQuery(hql);
		List<product> list = query.list();
		return list;
	}
	
	
	
	public void getpr(Model model) {
		Session s = factory.getCurrentSession();
		String hql = "from product";
		Query query = s.createQuery(hql);
		List<product> list = query.list();
		model.addAttribute("prr",list);
	}
	@ModelAttribute("listpr")
	public List<productDetail> getlistgr() {
		Session s = factory.getCurrentSession();
		String hql = "from productDetail ";
		Query query = s.createQuery(hql);
		List<productDetail> list = query.list();
		return list;
	}
	@ModelAttribute("listdh")
	public List<orderDetail> getlistdh() {
		Session s = factory.getCurrentSession();
		String hql = "from orderDetail ";
		Query query = s.createQuery(hql);
		List<orderDetail> list = query.list();
		return list;
	}
	public void getdh(Model model) {
		Session s = factory.getCurrentSession();
		String hql = "from orderDetail ";
		Query query = s.createQuery(hql);
		List<orderDetail> list = query.list();
		Session s1 = factory.getCurrentSession();
		String hql1 = "from order";
		Query query1 = s1.createQuery(hql1);
		List<order> list1 = query1.list();
		model.addAttribute("listdh1",list1);
		model.addAttribute("listdh",list);
	}
	public List<productDetail> loadlistgr(Model modela) {

		Session s = factory.getCurrentSession();
		String hql = "from productDetail ";
		Query query = s.createQuery(hql);
		List<productDetail> list = query.list();
		modela.addAttribute("listpr", list);
		return list;
	}
	@RequestMapping("deleteod")
	public String deleteod(Model model, @RequestParam("id") int id) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "from order ";
		Query query = s.createQuery(hql);
		List<order> list = query.list();
		order od = new order();
		for (order a : list) {
			if (a.getId() == id) {
				od = (order) s.load(order.class, id);
				
			}
		}
		try {
			s.delete(od);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			s.close();
			getdh(model);
		}
		return "admin/donhang";
	}
	@RequestMapping("xacnhan")
	public String xacnhanod(Model model, @RequestParam("id") int id) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "from order ";
		Query query = s.createQuery(hql);
		List<order> list = query.list();
		order od = new order();
		for (order a : list) {
			if (a.getId() == id) {
				od = (order) s.load(order.class, id);
				od.setStatus(0);
			}
		}
		try {
			s.update(od);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			s.close();
			getdh(model);
		}
		return "admin/donhang";
	}
	@RequestMapping("deletepr")
	public String deletepr(Model model, @RequestParam("idSanPham") int idSanPham) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "from productDetail ";
		Query query = s.createQuery(hql);
		List<productDetail> list = query.list();
		productDetail prd  = new productDetail();
		tb="";
		for (productDetail a : list) {
			if (a.getId() == idSanPham) {
				prd = (productDetail) s.load(productDetail.class, idSanPham);
				
			}
		}
		try {
			s.delete(prd);
			tb="Xóa Chi tiết Sản phẩm thành công!";
			model.addAttribute("tb", tb);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			tb="Xóa thất bại!";
			model.addAttribute("tb", tb);
		}
		finally {
			s.close();
		}
		return "redirect:/admin/product.htm";
	}
	@RequestMapping("deletepr-all")
	public String deleteall(Model model, @RequestParam("idSanPham") int idSanPham) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "from productDetail ";
		Query query = s.createQuery(hql);
		List<productDetail> list = query.list();
		productDetail prd  = new productDetail();
		product pr = new product();
		tb="";
		for (productDetail a : list) {
			if (a.getId() == idSanPham) {
				prd = (productDetail) s.load(productDetail.class, idSanPham);
				pr = (product) s.load(product.class,prd.getProduct().getId());
				
			}
		}
		try {
			s.delete(prd);
			s.delete(pr);
			tb= "Xóa Sản phẩm thành công!";
			model.addAttribute("tb",tb);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			tb= "Xóa thất bại!";
			model.addAttribute("tb",tb);
		}
		finally {
			s.close();
		}
		return "redirect:/admin/product.htm";
	}
	@RequestMapping(value = "editpr", method = RequestMethod.POST)
	public String editpr(Model model, HttpServletRequest re, @RequestParam("img1") MultipartFile img1,
			@RequestParam("img2") MultipartFile img2, @RequestParam("img3") MultipartFile img3)
			throws IllegalStateException, IOException {
		Session s = factory.openSession();
		Session s1 = factory.openSession();
		String hql = "from productDetail ";
		Query query = s.createQuery(hql);
		Transaction t = s.beginTransaction();
		Transaction t1 = s1.beginTransaction();
		List<productDetail> list = query.list();
		productDetail prd = new productDetail();
		tb="";
		product pr = new product();
		groupProduct gpr = new groupProduct();
		int idSanPham = Integer.parseInt(re.getParameter("idSanPham"));
		for (productDetail a : list) {
			if (a.getId() == idSanPham) {
				prd = (productDetail) s.load(productDetail.class, idSanPham);
				model.addAttribute("prd", prd);
			}
		}
		
		gpr = (groupProduct) s.load(groupProduct.class, re.getParameter("grid"));
		
		try {
			String img1Path = context.getRealPath("/resources/img/pro/" + img1.getOriginalFilename());
			img1.transferTo(new File(img1Path));
			String img2Path = context.getRealPath("/resources/img/pro/" + img2.getOriginalFilename());
			img2.transferTo(new File(img2Path));
			String img3Path = context.getRealPath("/resources/img/pro/" + img3.getOriginalFilename());
			img3.transferTo(new File(img3Path));
			pr.setImg1(img1.getOriginalFilename());
			pr.setImg2(img2.getOriginalFilename());
			pr.setImg3(img3.getOriginalFilename());
		} catch (Exception e) {
			pr.setImg1(re.getParameter("img1"));
			pr.setImg1(re.getParameter("img2"));
			pr.setImg1(re.getParameter("img3"));
		}
		
		if (re.getParameter("id").trim().length() == 0) {
			tb = "Không được để trống ID Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else if (re.getParameter("name").trim().length() == 0) {
			tb = "Không được để trống Tên Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else if (re.getParameter("coler").trim().length() == 0) {
			tb = "Không được để trống Màu Sản phẩm!";
			model.addAttribute("tb",tb);
		}
		else if (Double.parseDouble(re.getParameter("p")) < 1000) {
			tb = "Vui lòng điền Giá Sản phẩm hợp lệ!";
			model.addAttribute("tb",tb);
		}
		else if (img1.isEmpty()) {
			model.addAttribute("tb", "Vui lòng chọn ít nhất 1 hình ảnh!");
		}
		else if (re.getParameter("size").trim().length() == 0) {
			tb = "Không được để trống Đơn vị!";
			model.addAttribute("tb",tb);
		}
		else if ((Integer.parseInt(re.getParameter("status")) == 1) &&  (Integer.parseInt(re.getParameter("quanlity")) <= 0)) {
			tb = "Số lượng hàng đang bán phải lớn hơn 0!";
			model.addAttribute("tb",tb);
		}
		else {
			pr.setId(re.getParameter("id"));
			pr.setGroupProduct(gpr);
			pr.setName(re.getParameter("name"));
			pr.setDate(new Date());
			pr.setColer(re.getParameter("coler"));
			pr.setContent(re.getParameter("content"));
			pr.setSale(Integer.parseInt(re.getParameter("s")));
			pr.setPrice(Integer.parseInt(re.getParameter("p")));
			pr.setSold(Integer.parseInt(re.getParameter("sold")));
			pr.setStatus(Integer.parseInt(re.getParameter("status")));
			
			
			model.addAttribute("img1", img1.getOriginalFilename());
			model.addAttribute("img2", img1.getOriginalFilename());
			model.addAttribute("img3", img1.getOriginalFilename());

			
			prd.setProduct(pr);
			prd.setId(idSanPham);
			prd.setQuanlity(Integer.parseInt(re.getParameter("quanlity")));
			prd.setSize(re.getParameter("size"));
			prd.setStatus(1);
			try {
				s1.update(pr);
				s.update(prd);
				t.commit();
				t1.commit();
				tb="Cập nhật sản phẩm thành công!";
				model.addAttribute("tb",tb );
			} catch (Exception e) {
				t.rollback();
				t1.rollback();
				tb="Cập nhật sản phẩm thất bại! Vui lòng kiểm tra lại";
				model.addAttribute("tb",tb );
			} finally {
				s.close();
				s1.close();
				loadlistgr(model);

			}

		}
		
				return "redirect:/admin/product.htm";
	}
}
