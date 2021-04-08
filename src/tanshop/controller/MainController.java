package tanshop.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.bean.cart;
import shop.entity.advertise;
import shop.entity.customer;
import shop.entity.menu;
import shop.entity.orderDetail;
import shop.entity.product;
import shop.entity.productDetail;
import shop.entity.user;

@Transactional
@Controller
@RequestMapping("/home")
public class MainController {
	
	 public static	List<cart> cartlist = new ArrayList<>();
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(ModelMap model) {
		return "NewFile";
	}
	
	@Autowired
	SessionFactory factory;
	public static int dem =0;
	@RequestMapping("index")
	public String Index(ModelMap model) {
		model.addAttribute("cart", cartlist);
		model.addAttribute("tongTien",this.tinhTongtien(cartlist));
		return "index";
	}
	@ModelAttribute("menu")
	public List<menu> Nav(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql="FROM menu";
		Query query=session.createQuery(hql);
		List<menu> list = query.list();
		model.addAttribute("username", username);
		return list;
	}
	
	@ModelAttribute("banner")
	public List<advertise> banner(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM advertise";
		Query query =session.createQuery(hql);
		List<advertise> list = query.list();
		return list;
	}
	@ModelAttribute("newproduct")
	public List<product> newpro(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM product pr where pr.status = '1' ORDER BY date DESC";
		Query query =session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}
	@ModelAttribute("bestproduct")
	public List<product> bestpro(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM product pr where pr.status = '1' ORDER BY sold DESC";
		Query query =session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}
	
	@ModelAttribute("vot")
	public List<product> vot(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=0)";
		Query query =session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}
	
	@ModelAttribute("giay")
	public List<product> giay(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=1)";
		Query query =session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}
	
	@ModelAttribute("aoquan")
	public List<product> aoquan(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=2)";
		Query query =session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}
	
	@ModelAttribute("phukien")
	public List<product> phukien(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM product pr where pr.GroupProduct in (select id from groupProduct gp where gp.brands=3)";
		Query query =session.createQuery(hql).setMaxResults(10);
		List<product> list = query.list();
		return list;
	}
	@ModelAttribute("subbanner")
	public List<advertise> Subbanner(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM advertise";
		Query query =session.createQuery(hql);
		List<advertise> list = query.list();
		return list;
	}
	@ModelAttribute("listGioHang")
	public List<cart> getListGioHang() {
		return cartlist;
	}
	
	@ModelAttribute("prode")
	public List<productDetail> ProductDetail(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM productDetail";
		Query query =session.createQuery(hql);
		List<productDetail> list = query.list();
		return list;
	}
	
	@RequestMapping("shopping")
	public String shopping(Model model,@RequestParam("id") String id, @RequestParam("name") String name,@RequestParam("coler") String coler, @RequestParam("gia") float gia,
			@RequestParam("image") String image) {
		boolean isExist = false;
		for (cart item : cartlist) {
			if (item.getIdSanPham().equals(id) ) {
				item.setSoLuong(item.getSoLuong() + 1);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			dem++;
			cart ghi = new cart();
			ghi.setIdSanPham(id);
			ghi.setTenSanPham(name);
			ghi.setGiaSanPham(gia);
			ghi.setSoLuong(1);
			ghi.setImage(image);
			cartlist.add(ghi);
		}
		System.out.print(cartlist.toString());
		model.addAttribute("dem",dem);
		model.addAttribute("cart",cartlist);
      model.addAttribute("tongTien",this.tinhTongtien(cartlist));
		return "index";
	}
	@RequestMapping("shopping-list")
	public String shoppingl(Model model,@RequestParam("id") String id, @RequestParam("name") String name,@RequestParam("coler") String coler, @RequestParam("gia") float gia,
			@RequestParam("image") String image) {
		boolean isExist = false;
		for (cart item : cartlist) {
			if (item.getIdSanPham().equals(id) ) {
				item.setSoLuong(item.getSoLuong() + 1);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			dem++;
			cart ghi = new cart();
			ghi.setIdSanPham(id);
			ghi.setTenSanPham(name);
			ghi.setGiaSanPham(gia);
			ghi.setSoLuong(1);
			ghi.setImage(image);
			cartlist.add(ghi);
		}
		System.out.print(cartlist.toString());
		model.addAttribute("dem",dem);
		model.addAttribute("cart",cartlist);
      model.addAttribute("tongTien",this.tinhTongtien(cartlist));
      return "redirect:/list/pro.htm";
	}
	@RequestMapping("shopping-now")
	public String shoppingnow(Model model,@RequestParam("id") String id, @RequestParam("name") String name,@RequestParam("coler") String coler, @RequestParam("gia") float gia,
			@RequestParam("image") String image) {
		boolean isExist = false;
		for (cart item : cartlist) {
			if (item.getIdSanPham().equals(id) ) {
				item.setSoLuong(item.getSoLuong() + 1);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			dem++;
			cart ghi = new cart();
			ghi.setIdSanPham(id);
			ghi.setTenSanPham(name);
			ghi.setGiaSanPham(gia);
			ghi.setSoLuong(1);
			ghi.setImage(image);
			cartlist.add(ghi);
		}
		System.out.print(cartlist.toString());
		model.addAttribute("dem",dem);
		model.addAttribute("cart",cartlist);
      model.addAttribute("tongTien",this.tinhTongtien(cartlist));
		return "redirect:/home/order.htm";
	}
	@RequestMapping("deletecart")
	public String deletecart(Model model,@RequestParam("idSanPham") String idSanPham){
		int k=0;
		for(int i=0; i<cartlist.size();i++){
			if(cartlist.get(i).getIdSanPham().equals(idSanPham)){
				k=i;
			}
		}
		dem--;
		cartlist.remove(k);
		model.addAttribute("dem",dem);
		model.addAttribute("cart",cartlist);
		 model.addAttribute("tongTien",this.tinhTongtien(cartlist));
		return "index";
	}
	@RequestMapping("deletecart-list")
	public String deletecartl(Model model,@RequestParam("idSanPham") String idSanPham){
		int k=0;
		for(int i=0; i<cartlist.size();i++){
			if(cartlist.get(i).getIdSanPham().equals(idSanPham)){
				k=i;
			}
		}
		dem--;
		cartlist.remove(k);
		model.addAttribute("dem",dem);
		model.addAttribute("cart",cartlist);
		 model.addAttribute("tongTien",this.tinhTongtien(cartlist));
		return "redirect:/list/pro.htm";	
	}
	@RequestMapping("deletecart-od")
	public String deletecarto(Model model,@RequestParam("idSanPham") String idSanPham){
		int k=0;
		for(int i=0; i<cartlist.size();i++){
			if(cartlist.get(i).getIdSanPham().equals(idSanPham)){
				k=i;
			}
		}
		dem--;
		cartlist.remove(k);
		model.addAttribute("dem",dem);
		model.addAttribute("cart",cartlist);
		 model.addAttribute("tongTien",this.tinhTongtien(cartlist));
		return "redirect:/home/order.htm";	
	}
	public static Float tinhTongtien (List<cart> list) {
		Float tongTien=(float) 0;
		for(cart item1: list) {
			tongTien=(tongTien+item1.getGiaSanPham()*item1.getSoLuong());
		}
		return tongTien;
	}
	public static int tinhTongSP (List<cart> list) {
		int tongSP=0;
		for(cart item1: list) {
			tongSP=tongSP+item1.getSoLuong();
		}
		return tongSP;
	}
	private List<productDetail> list1; 
	
	@RequestMapping(value="order")
	public String order(ModelMap model){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail";
		Query query=s.createQuery(hql2);
		list1 = query.list();
		for (productDetail pd : list1) {
			for (cart item : cartlist ) {
				if(pd.getProduct().getId().equals(item.getIdSanPham())) {
					item.setSize(pd.getSize());
					break;
				}
			}
		}
		model.addAttribute("customer", new customer());
		model.addAttribute("cart", cartlist);
		model.addAttribute("prode", list1);
		model.addAttribute("tongTien",this.tinhTongtien(cartlist));
		return "order";
	}
	
	
	@RequestMapping(value = "order", method=RequestMethod.POST)
	public String order(Model model,HttpServletRequest re,@ModelAttribute("customer") customer cus, BindingResult err) {
		String size="";
		int sl = 0;
		ArrayList<String> listid = new ArrayList<>();
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		shop.entity.order o = new shop.entity.order();
		boolean temp = true;
		if(cus.getName().trim().length()==0) {
			model.addAttribute("message", "Vui lòng nhập Tên Khách hàng!");
			model.addAttribute("customer", new customer());
			model.addAttribute("cart", cartlist);
			model.addAttribute("prode", list1);
			model.addAttribute("tongTien",this.tinhTongtien(cartlist));
			temp = false;
		}
		else if(cus.getPhone() == null) {
			model.addAttribute("message", "Vui lòng nhập Số điện thoại!");
			model.addAttribute("customer", new customer());
			model.addAttribute("cart", cartlist);
			model.addAttribute("prode", list1);
			model.addAttribute("tongTien",this.tinhTongtien(cartlist));
			temp = false;
		}
		else if(cus.getEmail().trim().length() == 0) {
			model.addAttribute("message", "Vui lòng nhập Email!");
			model.addAttribute("customer", new customer());
			model.addAttribute("cart", cartlist);
			model.addAttribute("prode", list1);
			model.addAttribute("tongTien",this.tinhTongtien(cartlist));
			temp = false;
		}
		else if(cus.getAddress().trim().length()==0) {
			model.addAttribute("message", "Vui lòng nhập Địa chỉ!");
			model.addAttribute("customer", new customer());
			model.addAttribute("cart", cartlist);
			model.addAttribute("prode", list1);
			model.addAttribute("tongTien",this.tinhTongtien(cartlist));
			temp = false;
		}
		
		else if (temp == true) {
			try{
				cus.setName(re.getParameter("name"));
				cus.setEmail(re.getParameter("email"));
				cus.setPhone( Integer.parseInt(re.getParameter("phone")));
				cus.setAddress(re.getParameter("address"));
				cus.setCode(re.getParameter("code"));
				
				o.setStatus(1);
				o.setUser(userlogin);
				o.setDate(new Date());
				o.setCustomer(cus);
				o.setTotal(tinhTongtien(cartlist));
				s.save(cus);
				s.save(o);
				int madon = 0;
				for (cart item : cartlist) {
					orderDetail od = new orderDetail();
					product pro = (product)s.get(product.class, item.getIdSanPham());
					sl = Integer.parseInt(re.getParameter("sl" + item.getIdSanPham()));
					size = (re.getParameter("size" + item.getIdSanPham()));
					item.setSoLuong(sl);
					item.setSize(size);
					if (!check(pro.getId(), item.getSize(), item.getSoLuong())) {
						t.rollback();
						model.addAttribute("customer", new customer());
						model.addAttribute("cart", cartlist);
						model.addAttribute("prode", list1);
						model.addAttribute("tongTien",this.tinhTongtien(cartlist));
						model.addAttribute("message", "Số lượng lớn hơn số còn lại! Đặt hàng thất bại! ");
						s.close();
						return "order";
					}
					pro.setSold(pro.getSold()+1);
					s.update(pro);
					updatesl(pro.getId(), item.getSize(), item.getSoLuong());
					od.setSize(size);
					System.out.println(pro.toString());
					od.setProduct(pro);
					od.setOrder(o);
					od.setQuanlity(item.getSoLuong());
					s.save(od);
					madon=od.getId();
				}
//				
//				for (String string : listid) {
//					if (!check(string, size, sl)) {
//						t.rollback();
//						model.addAttribute("customer", new customer());
//						model.addAttribute("cart", cartlist);
//						model.addAttribute("prode", list1);
//						model.addAttribute("tongTien",this.tinhTongtien(cartlist));
//						model.addAttribute("message", "Số lượng lớn hơn số còn lại! Đặt hàng thất bại! ");
//						s.close();
//						return "order";
//					}
//				}
//				
//				System.out.println("thÃªm");
//				for (String string : listid) {
//					updatesl(string, size, sl);
//				}

				t.commit();
				thongbao(madon,re.getParameter("email"),re.getParameter("name"));
				model.addAttribute("message", "Đặt hàng thành công !");
			}
			catch(Exception e){
				t.rollback();
				model.addAttribute("customer", new customer());
				model.addAttribute("cart", cartlist);
				model.addAttribute("prode", list1);
				model.addAttribute("tongTien",this.tinhTongtien(cartlist));
				model.addAttribute("message", "Đặt hàng thất bại ! ");
				System.out.println("faile");
			}
			finally{
				s.close();
			}
		}
		
		return "order";
	}
	@RequestMapping("order-pr")
	public String themsl(ModelMap model,HttpServletRequest re) {
		for(cart item : cartlist) {
			int sl = Integer.parseInt(re.getParameter("sl" + item.getIdSanPham()));
			String size = (re.getParameter("size" + item.getIdSanPham()));
			item.setSoLuong(sl);
			item.setSize(size);
		}
		order1(model);
		return "order";
	}
	public void order1(ModelMap model){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail";
		Query query=s.createQuery(hql2);
		List<productDetail> list1 = query.list();
		model.addAttribute("customer", new customer());
		model.addAttribute("cart", cartlist);
		model.addAttribute("prode", list1);
		model.addAttribute("tongTien",this.tinhTongtien(cartlist));
	}
	public static String username = "Guest";
	public static user userlogin;
	@RequestMapping(value = "sign-up" ,method=RequestMethod.POST)
	public String dangki(Model model,HttpServletRequest re,@ModelAttribute("User") user us,BindingResult err) throws ParseException {
		
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		boolean temp = true;
		if(us.getUsername().trim().length()==0) {
			err.rejectValue("username","User","Vui lòng nhập Tên tài khoản!");
			temp = false;
		}
		if(us.getAddress().trim().length()==0) {
			err.rejectValue("address","User","Vui lòng nhập Địa chỉ!");
			temp = false;
		}
		if(us.getEmail().trim().length()==0) {
			err.rejectValue("email","User","Vui lòng nhập Email!");
			temp = false;
		}
		if(us.getPhone()==0) {
			err.rejectValue("phone","User","Vui lòng nhập SĐT!");
			temp = false;
		}
		if(us.getFullname().trim().length()==0) {
			err.rejectValue("fullname","User","Vui lòng nhập Họ và tên!");
			temp = false;
		}
		if(us.getGender()==null) {
			err.rejectValue("gender","User","Vui lòng nhập Giới tính!");
			temp = false;
		}
		if(us.getPassword().trim().length()==0) {
			err.rejectValue("password","User","Vui lòng nhập Mật khẩu!");
			temp = false;
		}
		if(us.getPassword().trim().equals(re.getParameter("rpass").trim())==false) {
			err.rejectValue("password","User","Mật khẩu không giống nhau!");
			temp = false;
		}
		if(us.getBirthday()==null) {
			err.rejectValue("birthday","User","Vui lòng nhập Ngày sinh!");
			temp = false;
		}
		if(temp==true) {
			try{
				if(s.get(user.class,us.getUsername().trim())==null) {
					s.save(us);
					t.commit();
					model.addAttribute("dk",1);
					model.addAttribute("tb", "Đăng kí thành công!");
				}
				else {
					model.addAttribute("tb", "Tài khoản đã tồn tại, vui lòng đăng nhập!");
					System.out.println("faile");
				}
			}
			catch(Exception e){
				t.rollback();
				model.addAttribute("tb", "Đăng kí thất bại, vui lòng kiểm tra lại!");
				System.out.println("faile");
			}
			finally{
				s.close();
			}			
		}
		return "user-signup";
	}
	@RequestMapping(value = "sign-up" ,method=RequestMethod.GET)
	public String dangki1(ModelMap model,@ModelAttribute("User") user us) {
		return "user-signup";
	}
	@RequestMapping(value = "forget-pass" ,method = RequestMethod.GET)
	public String quen(ModelMap model) {
		return "forgetpass";
	}
	@Autowired
	JavaMailSender mailer;
	@RequestMapping(value = "forget-pass" ,method = RequestMethod.POST)
	public String quen1(ModelMap model,HttpServletRequest re) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "FROM user";
		Query query =s.createQuery(hql);
		List<user> list = query.list();
		boolean kiemtra = true;
			for (user kt : list) {
				if(kt.getUsername().equals(re.getParameter("username"))) {
					int random = (int) Math.floor(((Math.random() * 899999) + 100000));/// random sinh sá»‘ cÃ³ 6 chá»¯ sá»‘
					String matkhaumoi=String.valueOf(random);
					String mailgui;
					mailgui=kt.getEmail();
					kt.setPassword(matkhaumoi);
					s.update(kt);
					kiemtra= false;
					t.commit();
					Date date = new Date();
					String from="tannd1904@gmail.com";
					String to=mailgui;
					String subject="Lấy lại mật khẩu";
					String body="Bạn đã báo quên mật khẩu tại TanShop vào "+date+". \nMật khẩu mới của bạn là:   "+matkhaumoi+"\n Nếu bạn không thực hiện yêu cầu trên, vui lòng liên hệ QTV ngay!";
					try {
						
						MimeMessage mail = mailer.createMimeMessage();
						
						
						MimeMessageHelper helper= new MimeMessageHelper(mail);
						helper.setFrom(from,from);
						helper.setTo(to);
						helper.setReplyTo(from,from);
						helper.setSubject(subject);
						helper.setText(body,true);
						mailer.send(mail);
						model.addAttribute("tinnhan", "Mật khẩu mới đã được gửi qua Email của bạn!");
					}
					catch (Exception ex){
						model.addAttribute("tinnhan","Gửi mail thất bại!");
						t.rollback();
					}
					finally {
						s.close();
					}
				}
			}
			if(kiemtra==true) {
				model.addAttribute("tinnhan", "Tài khoản không tồn tại!");
			}
		
			return "forgetpass";
	}
	@RequestMapping(value = "login" ,method=RequestMethod.POST)
	public String dangnhap(Model model,HttpServletRequest re) throws ParseException {
		Session session = factory.getCurrentSession();
		String hql = "FROM user WHERE Username = ? AND Password = ?";
		Query query =session.createQuery(hql);
		query.setString(0, re.getParameter("user"));
		query.setString(1, re.getParameter("pass"));
		List<user> list = query.list();
		if(!list.isEmpty()) {
			username = re.getParameter("user");
			model.addAttribute("username", username);
			HttpSession sess = re.getSession();
//			userlogin = user;
			sess.setAttribute("username", username);
			return "index";
		}else {
			model.addAttribute("tb", "Sai Username hoặc Password!");
			return "user-login";
		}
		
	}
	
	@RequestMapping(value = "log-in" ,method=RequestMethod.POST)
	public String dangnhap2(Model model,HttpServletRequest re) throws ParseException {
		Session session = factory.getCurrentSession();
		String hql = "FROM user WHERE Username = ? AND Password = ?";
		Query query =session.createQuery(hql);
		query.setString(0, re.getParameter("uname"));
		query.setString(1, re.getParameter("psw"));
		List<user> list = query.list();
		if(!list.isEmpty()) {
			username = re.getParameter("uname");
			model.addAttribute("username", username);
			HttpSession sess = re.getSession();
//			userlogin = user;
			sess.setAttribute("username", username);
			return "index";
		}else {
			int flag=0;
			model.addAttribute("flag", flag);
			model.addAttribute("tb", "Sai Username hoặc Password!");
			return "index";
		}
		
	}
	
	@RequestMapping(value = "login" ,method=RequestMethod.GET)
	public String dangnhhap1(ModelMap model,@ModelAttribute("User") user us) {
		return "user-login";
	}
	
	@RequestMapping(value ="logout", method = RequestMethod.GET)
	public String dangxuat(ModelMap model) {
		username = "Guest";
		model.addAttribute("username", username);
		return "index";
	}
	
	public boolean check(String id ,String size ,int sl) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail pd where pd.Product.id = :id and pd.size = :size";
		Query query=s.createQuery(hql2);
		query.setParameter("id",id);
		query.setParameter("size",size);
		List<productDetail> list1 = query.list();
		ArrayList<productDetail> ls = new ArrayList<productDetail>();
		for (productDetail a : list1) {
			ls.add(a);
		}
		for (productDetail a : ls) {
			if (a.getQuanlity() <  sl) {
				return false;
			}
		}
		return true;
	}

	public void updatesl(String id ,String size ,int sl) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql2 = "FROM productDetail pd where pd.Product.id = :id and pd.size = :size";
		Query query=s.createQuery(hql2);
		query.setParameter("id",id);
		query.setParameter("size",size);
		List<productDetail> list1 = query.list();
		ArrayList<productDetail> ls = new ArrayList<productDetail>();
		for (productDetail a : list1) {
			ls.add(a);
		}
		for (productDetail a : ls) {
			a.setQuanlity(a.getQuanlity()-sl);
			s.update(a);
			t.commit();
			s.close();
		}
	}
	@Autowired
	JavaMailSender mailer1;
	public void thongbao(int madonhang, String email,String tenkh) {
					Date date = new Date();
					String from="tannd1904@gmail.com";
					String to= email;
					String subject="Đặt hàng thành công!";
					String body="Xin chào "+tenkh+"! \n Đơn hàng mã: "+madonhang+" của bạn đã được tạo thành công vào lúc "+date+" . \nNhân viên của cửa hàng sẽ gửi hàng cho bạn trong thời gian sớm nhất. \n Cám ơn bạn đã ủng hộ shop!";
					try {
						MimeMessage mail = mailer.createMimeMessage();
						MimeMessageHelper helper= new MimeMessageHelper(mail);
						System.out.println("new");
						helper.setFrom(from,from);
						helper.setTo(to);
						helper.setReplyTo(from,from);
						helper.setSubject(subject);
						helper.setText(body,true);
						mailer.send(mail);
					}
					catch (Exception ex){
					}
	}
	
	@RequestMapping("test")
	public String test() {
		return "NewFile";
	}
}
