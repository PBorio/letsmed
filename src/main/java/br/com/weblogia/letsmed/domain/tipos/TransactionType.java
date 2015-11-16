package br.com.weblogia.letsmed.domain.tipos;


public enum TransactionType implements Tipo {
	
	  SUPPLIER_PAYS_COMMISION(1, "Supplier Pays Commision"),
	  CUSTOMER_PAYS_COMMISION(2, "Customer Pays Commision"),
	  PROFIT(3, "PROFIT");
	  
	  private final Integer value;   
	  private final String description;
	  
	  TransactionType(Integer value, String description)
	  {   
	     this.value = value;
	     this.description = description;
	  }   
	  
	  public static TransactionType getType(Integer value)
	  {
	    for(TransactionType type: TransactionType.values())
	    {
	      if (value.equals(type.getValue()))
	      {
	        return type;
	      }
	    }
	    return null;
	  }

	  public TransactionType[] getValues()
	  {
	    return TransactionType.values();
	  }

	public String getDescription() {
		return this.description;
	}

	public Integer getValue() {
		return this.value;
	}

}
