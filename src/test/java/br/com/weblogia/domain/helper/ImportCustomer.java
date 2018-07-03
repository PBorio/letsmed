package br.com.weblogia.domain.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class ImportCustomer {
	
	public static void main(String[] args) throws IOException, ParseException, SQLException {
		File initialFile = new File("C:\\Borius\\backup\\customers.xls");
		InputStream excel = new FileInputStream(initialFile);
		Workbook wb = new HSSFWorkbook(new POIFSFileSystem(excel));
		HSSFSheet sheet = (HSSFSheet) wb.getSheetAt((short)0);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		
		Session session = (Session) em.getDelegate();
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection connection = cp.getConnection();
		connection.setAutoCommit(true);
		
		
		try {		
				for (Row row : sheet){
		
					System.out.println("Row: "+row.getRowNum()+1);
					if (row.getRowNum() == 0)
						continue;
					
					for (Cell cell : row){
											
						//abre nova
						if (cell.getColumnIndex() == 0){
							
							long id = (long) row.getCell(0).getNumericCellValue();
							String name = row.getCell(1).getStringCellValue();
							
							String address = null;
							if (row.getCell(2) != null) address = row.getCell(2).getStringCellValue();
							
							String phoneNumber = null;
							if (row.getCell(3) != null) phoneNumber = row.getCell(3).getStringCellValue();
							
							String phoneNumber2 = null;
							if (row.getCell(4) != null)	phoneNumber2 = row.getCell(4).getStringCellValue();
							
							String email = null; 
							if (row.getCell(5) != null) email = row.getCell(5).getStringCellValue();
							
							String site = null; 
							if (row.getCell(6) != null) site = row.getCell(6).getStringCellValue();
							
							String contact = null;
							if (row.getCell(7) != null) contact = row.getCell(7).getStringCellValue();
							
							Integer officeId = null;
							if (row.getCell(9) != null) officeId = (int) row.getCell(9).getNumericCellValue();
							
							Integer bankId = null;
							if (row.getCell(10) != null) bankId = (int) row.getCell(10).getNumericCellValue();
							
							Integer transactionId = null;
							if (row.getCell(11) != null) transactionId = (int) row.getCell(11).getNumericCellValue();
							
							String code = null;
							if (row.getCell(15) != null) contact = row.getCell(15).getStringCellValue();
							
							Integer countryId = null;
							if (row.getCell(16) != null) countryId = (int) row.getCell(16).getNumericCellValue();
							
							PreparedStatement stmt = connection.prepareStatement("insert into customers (id, name, address, phoneNumber, phoneNumber2, email, site, contact, office_id, bank_id, transactionTerm_id, code, country_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
							stmt.setLong(1, id);
							stmt.setString(2, name);
							stmt.setString(3, address);
							stmt.setString(4, phoneNumber);
							stmt.setString(5, phoneNumber2);
							stmt.setString(6, email);
							stmt.setString(7, site);
							stmt.setString(8, contact);
							stmt.setInt(9, officeId);
							stmt.setInt(10, bankId);
							stmt.setInt(11, transactionId);
							stmt.setString(12, code);
							stmt.setInt(13, countryId);
							stmt.execute();
							System.out.println(stmt.toString());
						}
					}
				}
				System.out.println("Commited");
			}catch(Exception e){
					e.printStackTrace();
			}
		System.out.println("Completed");
//		for (Order o : orders.values()){
//			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
//			System.out.println(o.getOrderNumber()+": "+o.getCustomer().getName()+" - "+o.getCustomer().getCode()+" - "+o.getSupplier().getSupplierName()+" - "+o.getSupplier().getShortName());
//			for (OrderItem i : o.getItens()){
//				System.out.println("------------------------------------------");
//				System.out.println("Item Order: "+i.getOrder().getOrderNumber());
//				System.out.println("Produto: "+i.getProduct().getDescription());
//				System.out.println("quantidade: "+i.getQuantity());
//				System.out.println("valor compra: "+i.getBuyPrice());
//				System.out.println("Comissão: "+i.getCommision());
//				System.out.println("Valor Venda: "+i.getUnitPrice());
//				System.out.println("------------------------------------------");
//			}
//			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
//		}
	}

}
