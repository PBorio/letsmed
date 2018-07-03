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

public class ImportProducts {
	
	public static void main(String[] args) throws IOException, ParseException, SQLException {
		File initialFile = new File("C:\\Borius\\backup\\ProductsList.xls");
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
		
		int supplier = 0;
		
		try {		
				for (Row row : sheet){
		
					System.out.println("Row: "+row.getRowNum()+1);
					if (row.getRowNum() == 0)
						continue;
					
					for (Cell cell : row){
											
						//abre nova
						if (cell.getColumnIndex() == 0){
							
							
							String productCode = null;
							if (row.getCell(1) != null) productCode = row.getCell(1).getStringCellValue();
							
							String customerProductCode = null;
							if (row.getCell(2) != null) customerProductCode = row.getCell(2).getStringCellValue();
							
							String supplierProductCode = null;
							if (row.getCell(3) != null) supplierProductCode = row.getCell(3).getStringCellValue();
							
							Integer supplierId = (int) row.getCell(5).getNumericCellValue();
							supplier = supplierId;
							int userId = (int) row.getCell(6).getNumericCellValue();
							
							Integer categoryId = null;
							if (row.getCell(7) != null) categoryId = (int) row.getCell(7).getNumericCellValue();
							
							String productDescription = row.getCell(8).getStringCellValue();
							
							
							
							PreparedStatement stmt = connection.prepareStatement("insert into products (description, productCode, supplierProductCode, customerProductCode, supplier_id, productCategory_id) values (?,?,?,?,?,?)");
							stmt.setString(1, productDescription);
							stmt.setString(2, productCode);
							stmt.setString(3, supplierProductCode);
							stmt.setString(4, customerProductCode);
							stmt.setInt(5, supplierId);
							stmt.setInt(6, categoryId);
							stmt.execute();
							System.out.println(stmt.toString());
						}
					}
				}
				System.out.println("Commited");
			}catch(Exception e){
				System.err.println("Erro no supplier: "+supplier);
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
