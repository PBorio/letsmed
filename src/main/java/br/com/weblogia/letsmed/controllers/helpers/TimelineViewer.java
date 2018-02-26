package br.com.weblogia.letsmed.controllers.helpers;

import java.util.ArrayList;
import java.util.List;

import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.Supplier;

public class TimelineViewer {

	private List<TimelineViewerColumn> columns = new ArrayList<>();
	
	
	public TimelineViewer(List<Order> orderList) {
		for (Order o : orderList) {
			
			Supplier s = o.getSupplier();
			
			TimelineViewerColumn selectedColumn = null;
			columnfor: for (TimelineViewerColumn column : this.columns) {
				if (column.getSupplier().equals(s)) {
					selectedColumn = column;
					break columnfor;
				}
			}
			
			if (selectedColumn == null) {
				selectedColumn = new TimelineViewerColumn(s);
				this.columns.add(selectedColumn);
			}
			
			selectedColumn.addOrder(o);
		};
	}


	public List<TimelineViewerColumn> getColumns() {
		return columns;
	}


	public void setColumns(List<TimelineViewerColumn> columns) {
		this.columns = columns;
	}
	
	public int getColumnNumber() {
		return this.columns.size();
	}

}
