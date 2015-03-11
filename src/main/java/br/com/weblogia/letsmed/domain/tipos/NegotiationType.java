package br.com.weblogia.letsmed.domain.tipos;


public enum NegotiationType implements Tipo {
	
	  TT_ADVANCE_AND_BALANCE_AGAINST_COPY(1, "T/T xx% advanced xx% balance against B/L copy"),
	  TT_100_AGAINST_COPY(2, "T/T 100 % against copy of B/L"),
	  LC_AT_SIGHT(3, "L/C at sight"),
	  TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT(4, "T/T xx % advanced and xx % balance before shipment"),
	  TT_100_BEFORE_SHIPMENT(5, "T/T 100% before shipment");
	  
	  private final Integer value;   
	  private final String description;
	  
	  NegotiationType(Integer value, String description)
	  {   
	     this.value = value;
	     this.description = description;
	  }   
	  
	  public static NegotiationType getType(Integer value)
	  {
	    for(NegotiationType type: NegotiationType.values())
	    {
	      if (value.equals(type.getValue()))
	      {
	        return type;
	      }
	    }
	    return null;
	  }

	  public NegotiationType[] getValues()
	  {
	    return NegotiationType.values();
	  }

	public String getDescription() {
		return this.description;
	}

	public Integer getValue() {
		return this.value;
	}

}
