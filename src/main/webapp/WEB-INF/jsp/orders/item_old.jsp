<div class="box-content">
	
	  <fieldset>
		<div class="control-group">
		   <label class="control-label col-xs2">Product:</label>			 
	       <div class="controls">
				<input type="text" class="input-xlarge span12" id="product-desc" onkeypress="autoComplete(this);" />
			    <input type="hidden" id="item-index" />
			    <input type="hidden" id="item-id" />
		 	  	<input type="hidden" id="order-id" />
		 	  	
		 	  	<input type="hidden" id="product-id" />
		   </div>
		</div>
		<div class="control-group">
		   <label class="control-label col-xs2">Additional Description:</label>			 
	       <div class="controls">
			  <textarea rows="1" class="input-xlarge span12" id="additional-description"></textarea>
		   </div>
		</div>
		<div class="row-fluid">
		  <div class="span2">
			<div class="control-group">
			  <label class="control-label col-xs2">Quantity:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" id="quantity" onchange="calculateTotalSell(); calculateTotalBuy(); return false;" />
			  </div>
			</div>
			
		 </div>
		  <div class="span6">
		    <div class="control-group">
			  <label class="control-label col-xs2">Units:</label>
			  <div class="controls">
			    <select id="unit" class="input-xlarge span12" >   
			              <option value="-1"> Units...</option>  
			              <c:forEach var="unit" items="${unitsList}">  
			                  <option value="${unit.id}"> 
			                  	${unit.description} 
			                  </option>  
			              </c:forEach>  
			          </select>
			  </div>
			</div>
		  </div>
		</div>
		<div class="row-fluid">
		   <div class="span2">
			<div class="control-group">
			  <label class="control-label col-xs2">Buy Price:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="buy-price" onchange="calculateTotalBuy(); return false;" />
			  </div>
			</div>
		  </div>
		 <div class="span2">
			<div class="control-group">
			  <label class="control-label col-xs2">Sell Price:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="sell-price" onchange="calculateTotalSell(); return false;"/>
			  </div>
			</div>
		</div>
		 <div class="span2">
			<div class="control-group">
			  <label class="control-label col-xs2">Total Buy Price:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" data-behaviour="valor" readonly="readonly" id="total-buy-price" />
			  </div>
			</div>
		</div>
		 <div class="span2">
			<div class="control-group">
			  <label class="control-label col-xs2">Total Sell Price:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" data-behaviour="valor" readonly="readonly" id="total-sell-price"  />
			  </div>
			</div>
		 </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Commision:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" data-behaviour="valor" id="commision" />
		  </div>
		</div>
		<div class="row-fluid">
			<div class="span2">
				<div class="control-group">
				  <label class="control-label col-xs2">Quantity CTN:</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="package-quantity" />
				  </div>
				</div>
			</div>
			<div class="span2">
				<div class="control-group">
				  <label class="control-label col-xs2">G.W KGs:</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="gw"/>
				  </div>
				</div>
			</div>
			<div class="span2">
				<div class="control-group">
				  <label class="control-label col-xs2">N.W KGs:</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="nw" />
				  </div>
				</div>
			</div>
			<div class="span2">
				<div class="control-group">
				  <label class="control-label col-xs2">Volume (CBM):</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="volume" />
				  </div>
				</div>
			</div>
		</div>
	</fieldset>
</div>
