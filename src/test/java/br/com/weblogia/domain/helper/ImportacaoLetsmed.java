package br.com.weblogia.domain.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import br.com.weblogia.domain.OrderCommisionPayment;
import br.com.weblogia.domain.OrderSupplierPayment;
import br.com.weblogia.letsmed.domain.BankAccount;
import br.com.weblogia.letsmed.domain.Customer;
import br.com.weblogia.letsmed.domain.ForwardDetail;
import br.com.weblogia.letsmed.domain.NegotiationTerm;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderItem;
import br.com.weblogia.letsmed.domain.OrderPayment;
import br.com.weblogia.letsmed.domain.Product;
import br.com.weblogia.letsmed.domain.Revenue;
import br.com.weblogia.letsmed.domain.RevenuePayment;
import br.com.weblogia.letsmed.domain.Supplier;
import br.com.weblogia.letsmed.domain.TransactionTerm;
import br.com.weblogia.letsmed.domain.UnitOfMeasure;
import br.com.weblogia.letsmed.domain.User;

public class ImportacaoLetsmed {
	
	public static void main(String[] args) throws IOException, ParseException {
		File initialFile = new File("C:\\Borius\\amazon\\importacao.xls");
		InputStream excel = new FileInputStream(initialFile);
		Workbook wb = new HSSFWorkbook(new POIFSFileSystem(excel));
		HSSFSheet sheet = (HSSFSheet) wb.getSheetAt((short)0);
		
		Map<String, Customer> customers = new LinkedHashMap<String,Customer>();
		Map<String, Order> orders = new LinkedHashMap<String, Order>();
		Map<String, Supplier> suppliers = new LinkedHashMap<String, Supplier>();
		Map<String, User> users = new LinkedHashMap<String, User>();
		
		Map<String, Product> products = new LinkedHashMap<String, Product>();
		
		Order order = null;
		String status = null;
		
		for (Row row : sheet){
			if (row.getRowNum() == 0)
				continue;
			
			for (Cell cell : row){
				
				
				//abre nova
				if (cell.getColumnIndex() == 0){
					
					if (order != null){
						//fecha a order velha
						
						if ("Confirmed".endsWith(status)){
							confirmOrder(order);						
						}else if("Finished".equals(status)){
							finishedOrder(order);
						}else if("Proforma".equals(status)){
							proformaOrder(order);
						}else if ("Shipped".equals(status)){
							proformaOrder(order);
							confirmOrder(order);
						}
					}
					
					order = new Order();
					String orderNumber = row.getCell(5).getStringCellValue();
					order.setOrderNumber(orderNumber);
					orders.put(order.getOrderNumber(), order);
					
					NegotiationTerm negotiationTerm = new NegotiationTerm();
					negotiationTerm.setId(1l);
					order.setNegotiationTerm(negotiationTerm);
					
					String customerName = row.getCell(0).getStringCellValue();
					Customer customer = customers.get(customerName);
					if (customer == null){
						customer = new Customer();
						customer.setName(customerName);
						
						String code = orderNumber.split("-")[0]; 
						customer.setCode(code);
						customers.put(customerName, customer);
					}
					order.setCustomer(customer);
					
					String trader = null;
					
					if (row.getCell(1) != null)
						trader = row.getCell(1).getStringCellValue();
					
					TransactionTerm nt = new TransactionTerm();
					if (trader == null){
						nt.setId(1l);
					}else {
						nt.setId(3l);
					}
					order.setTransactionTerm(nt);
					
					String supplierName = row.getCell(2).getStringCellValue();
					Supplier supplier = suppliers.get(supplierName);
					if (supplier == null){
						supplier = new Supplier();
						supplier.setSupplierName(supplierName);
						supplier.setShortName(row.getCell(2).getStringCellValue());
						suppliers.put(supplierName, supplier);
						
						String userName = "axel"; //row.getCell(4).getStringCellValue();
						User user = users.get(userName);
						if (user == null){
							user = new User();
							user.setName(userName);
							user.setLogin(userName);
							users.put(userName, user);
						}
						supplier.setUser(user);
					}
					order.setSupplier(supplier);
					
					
					try{
						order.setOrderDate(row.getCell(6).getDateCellValue());
					}catch (IllegalStateException e){
						order.setOrderDate(new SimpleDateFormat("dd/MM/yyyy").parse(row.getCell(6).getStringCellValue()));
					}
					
					status = row.getCell(8).getStringCellValue();
					
					if (row.getCell(7) != null){
						Date shipDate;
						try{
							shipDate = row.getCell(7).getDateCellValue();
						}catch (IllegalStateException e){
							shipDate = new SimpleDateFormat("dd/MM/yyyy").parse(row.getCell(7).getStringCellValue());
						}
						order.setShipDate(shipDate);
					}
					
				}else if (cell.getColumnIndex() == 13){
					OrderItem oi = new OrderItem();
					oi.setOrder(order);
					oi.setQuantity(row.getCell(14).getNumericCellValue());
					oi.setBuyPrice(row.getCell(15).getNumericCellValue());
					oi.setCommision(row.getCell(16).getNumericCellValue());
					oi.setUnitPrice(row.getCell(17).getNumericCellValue());
					
					String unity = row.getCell(13).getStringCellValue();
					if("bag".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(4l);
						oi.setUnitOfMeasure(unit);
					}else if("box".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(2l);
						oi.setUnitOfMeasure(unit);
					}else if("ctn".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(6l);
						oi.setUnitOfMeasure(unit);
					}else if("do".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(7l);
						oi.setUnitOfMeasure(unit);
					}else if("k".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(8l);
						oi.setUnitOfMeasure(unit);
					}else if("M".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(9l);
						oi.setUnitOfMeasure(unit);
					}else if("m2".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(10l);
						oi.setUnitOfMeasure(unit);
					}else if("pair".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(5l);
						oi.setUnitOfMeasure(unit);
					}else if("pc".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(1l);
						oi.setUnitOfMeasure(unit);
					}else if("pk".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(11l);
						oi.setUnitOfMeasure(unit);
					}else if("RL".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(3l);
						oi.setUnitOfMeasure(unit);
					}else if("un".equals(unity)){
						UnitOfMeasure unit = new UnitOfMeasure();
						unit.setId(12l);
						oi.setUnitOfMeasure(unit);
					}
					
					String productDescription = null;
					try{
						productDescription = row.getCell(12).getStringCellValue();
					}catch(Exception e){
						System.out.println(order.getOrderNumber());
						e.printStackTrace();
					}
					Product product = products.get(productDescription);
					if (product == null){
						product = new Product();
						product.setDescription(productDescription);
						products.put(productDescription, product);
					}
					oi.setProduct(product);
					
					order.getItens().add(oi);
				}
			}
		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		try{
			for (Customer c : customers.values()){
				em.persist(c);
			}
			
			for (Supplier s : suppliers.values()){
				em.persist(s);
			}
			
			for (User u : users.values()){
				em.persist(u);
			}
			
			for (Product p : products.values()){
				em.persist(p);
			}
			
			for (Order o : orders.values()){
				em.persist(o);
				for (OrderItem oi : o.getItens()){
					em.persist(oi);
				}
				for (OrderPayment op : o.getPayments()){
					em.persist(op);
				}
				for (ForwardDetail f : o.getForwardDetails()){
					em.persist(f);
				}
				for (OrderSupplierPayment osp : o.getSupplierPayments()){
					em.persist(osp);
				}
				for (OrderCommisionPayment ocp : o.getCommisionPayments()){
					em.persist(ocp);
				}
				for (Revenue revenue : o.getComissionRevenues()){
					em.persist(revenue);
					for (RevenuePayment rp : revenue.getPayments()){
						em.persist(rp);
					}
				}
			}
			
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		for (Order o : orders.values()){
			System.out.println(o.getOrderNumber()+": "+o.getCustomer().getName()+" - "+o.getCustomer().getCode()+" - "+o.getSupplier().getSupplierName()+" - "+o.getSupplier().getShortName());
			System.out.println(o.getCommisionValue());
		}
	}

	private static void finishedOrder(Order order) {
		proformaOrder(order);
		confirmOrder(order);
		order.setCopyDocumentDate(order.getOrderDate());
		order.setConclusionDate(order.getShipDate());
		
		OrderPayment op = new OrderPayment();
		op.setPaymentDate(order.getOrderDate());
		op.setValue(order.getBalance());
		op.setOrder(order);
		order.getPayments().add(op);
		
		OrderSupplierPayment osp = new OrderSupplierPayment();
		osp.setPaymentDate(order.getOrderDate());
		osp.setValue(order.getBalance());
		osp.setOrder(order);
		order.getSupplierPayments().add(osp);
		
		OrderCommisionPayment ocp = new OrderCommisionPayment();
		ocp.setPaymentDate(order.getOrderDate());
		ocp.setValue(order.getCommisionValue());
		ocp.setOrder(order);
		order.getCommisionPayments().add(ocp);
		
		BankAccount ba = new BankAccount();
		ba.setId(1l);
		RevenuePayment rp = new RevenuePayment();
		rp.setBankAccount(ba);
		rp.setPaymentDate(order.getOrderDate());
		rp.setRevenue(order.getComissionRevenue());
		rp.setValue(order.getRevenueValue());
		order.getComissionRevenue().getPayments().add(rp);
	}

	private static void proformaOrder(Order order) {
		Date date = order.getOrderDate();
		order.setConfirmationDate(date);
		order.setSupplierProformaDate(date);
		order.setProformaConfirmationDate(date);
		order.setArtworkConfirmationDate(date);
		order.setProductionStartDate(date);
		
		if (order.getComissionRevenues().isEmpty()){
			Revenue revenue = new Revenue();
			revenue.setRevenueDate(new Date());
			revenue.setRevenueAccount(order.getCustomer().getRevenueAccount());
			revenue.setOrder(order);
			revenue.setValue(order.getRevenueValue());
			revenue.setAdditionalInfo("Revenue from Order N. "+order.getOrderNumber());
			order.getComissionRevenues().add(revenue);
		}
		
	}

	private static void confirmOrder(Order order) {
		proformaOrder(order);
		ForwardDetail fd = new ForwardDetail();
		fd.setDescription("Details for Order: "+order.getOrderNumber());
		fd.setForwardDetailDate(order.getOrderDate());
		fd.setOrder(order);
		order.getForwardDetails().add(fd);
		
		OrderPayment op = new OrderPayment();
		op.setPaymentDate(order.getOrderDate());
		op.setValue(1.0);
		op.setOrder(order);
		order.getPayments().add(op);
		
		OrderSupplierPayment osp = new OrderSupplierPayment();
		osp.setPaymentDate(order.getOrderDate());
		osp.setValue(1.0);
		osp.setOrder(order);
		order.getSupplierPayments().add(osp);
	}

}
