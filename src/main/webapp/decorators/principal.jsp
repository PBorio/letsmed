<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"  prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	
<!-- start: Meta -->
	<meta charset="utf-8">
	<title>Axel Henrich</title>
	<meta name="description" content="Axel Henrich">
	<!-- end: Meta -->
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- end: Mobile Specific -->
	
	
	<!-- start: CSS -->
	<link id="bootstrap-style" href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/resources/css/bootstrap-responsive.min.css'/>" rel="stylesheet">
	<link id="base-style" href="<c:url value='/resources/css/style.css'/>" rel="stylesheet">
	<link href="<c:url value='/resources/js/plugins/select2/select2.css'/>"    rel="stylesheet" />
	<link id="base-style-responsive" href="<c:url value='/resources/css/style-responsive.css'/>" rel="stylesheet">
	<link href="<c:url value='/resources/css/timeline.css'/>" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>

	<decorator:head/>
</head>

<body>
		<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="index.html"><span>Axel Henrich</span></a>
								
				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
				    <ul class="nav">
				  <li class="dropdown hidden-phone">
					  <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						Orders
					  </a>
					  <ul class="dropdown-menu tasks">
					 	 <li 
					 	 >
					  	 		<a class="submenu" href="<c:url value='/timeline'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Timeline</span></a></li>
							<li <c:if test="${controller eq 'orders'}"> class="active" </c:if>>
								<a class="submenu" href="<c:url value='/orders'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Orders</span></a></li>
							<li <c:if test="${controller eq 'timeline'}"> class="active" </c:if>>
								<a class="submenu" href="<c:url value='/orderPayments'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Orders Payment</span></a></li>
							<li <c:if test="${controller eq 'timeline'}"> class="active" </c:if>>
								<a class="submenu" href="<c:url value='/forwardDetails'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Order Forward Detail</span></a></li>
							<li <c:if test="${controller eq 'timeline'}"> class="active" </c:if>>
								<a class="submenu" href="<c:url value='/customers'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Customers</span></a></li>
							<li <c:if test="${controller eq 'timeline'}"> class="active" </c:if>>
								<a class="submenu" href="<c:url value='/products'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Products</span></a></li>
							<li <c:if test="${controller eq 'partner'}"> class="active" </c:if>>
								<a class="submenu" href="<c:url value='/partners'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Partners</span></a></li>
							<li <c:if test="${controller eq 'suppliers'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/suppliers'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Supplier</span></a></li>
							<li><a class="submenu"  href="<c:url value='/unitsOfMeasures'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet">Units</span></a></li>
							<li><a class="submenu"  href="<c:url value='/countries'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet">Countries</span></a></li>  	
                      </ul>
				   </li>
				 <!-- start: Compras Dropdown -->
				 <!-- end: Compras Dropdown -->
             	 <!-- start: Vendas Dropdown -->
				 <li class="dropdown hidden-phone">
					 <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					 	Admin
					 </a>
					 <ul class="dropdown-menu tasks">
						<li <c:if test="${controller eq 'accountDetails'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/accountDetails'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Acount Details</span></a></li>
							<li <c:if test="${controller eq 'statement'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/statement'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Bank Statements</span></a></li>
					  	 	<li <c:if test="${controller eq 'revenues'}"> class="active" </c:if>>
					  	 		<a class="submenu"  href="<c:url value='/revenues'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Revenues</span></a></li>
							<li <c:if test="${controller eq 'timeline'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/expenses'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Expenses</span></a></li>
							<li <c:if test="${controller eq 'expensePayments'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/expensePayments'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Expense Payments</span></a></li>
							<li <c:if test="${controller eq 'revenueAccounts'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/revenueAccounts'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Revenue Accounts</span></a></li>
							<li <c:if test="${controller eq 'expenseAccounts'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/expenseAccounts'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Expense Accounts</span></a></li>
							<li <c:if test="${controller eq 'expenseCategories'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/expenseCategories'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Expense Categories</span></a></li>
							<li <c:if test="${controller eq 'offices'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/offices'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Offices</span></a></li>
							<li <c:if test="${controller eq 'bankAccounts'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/bankAccounts'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Bank Accounts</span></a></li>
							<li <c:if test="${controller eq 'negotiationTerms'}"> class="active" </c:if>>
								<a class="submenu"  href="<c:url value='/negotiationTerms'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Negotiation Terms</span></a></li> 	
					 </ul>
				  </li>
				  
				   <li class="dropdown hidden-phone">
					 <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					 	Statistics
					 </a>
					 <ul class="dropdown-menu tasks">
						<li><a class="submenu"  href="<c:url value='/orderStatistics/orders'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Orders</span></a></li>
						<li><a class="submenu"  href="<c:url value='/orderStatistics/products/categories'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Values By Product Category</span></a></li>
						<li><a class="submenu"  href="<c:url value='/orderStatistics/countries'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Values By Country</span></a></li>
						<li><a class="submenu"  href="<c:url value='/orderStatistics/customers'/>"><i class="fa-icon-align-justify"></i><span class="hidden-tablet"> Values By Customer</span></a></li>
					 </ul>
				  </li>
				  
				  <!-- end: Vendas Dropdown -->
            	  <!-- start: Estoque Dropdown -->
					<!-- end: Calendario Dropdown -->
            		<!-- start: Message Dropdown -->
					<li class="dropdown hidden-phone">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="halflings-icon white envelope"></i>
						</a>
						<ul class="dropdown-menu messages">
						   <li><span class="dropdown-menu-title">You have 9 messages</span></li>
						   <li><a href="#">
						   		 <span class="avatar"></span>
			   				     <span class="header">
			   				       <span class="from">ERP</span>
			      				   <span class="time">6 min</span>
			      			     </span>
                                 <span class="message">
                                   Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                 </span>  
                                </a>
                           </li>
                           <li><a href="#">
								 <span class="avatar"></span>
								 <span class="header">
									 <span class="from">
										Megan Abott
									 </span>
									 <span class="time">
									    56 min
									 </span>
								 </span>
                                 <span class="message">
                                    Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                 </span>  
                                </a>
                            </li>
                             <li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Kate Ross
										     </span>
											<span class="time">
										    	3 hours
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Julie Blank
										     </span>
											<span class="time">
										    	yesterday
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Jane Sanders
										     </span>
											<span class="time">
										    	Jul 25, 2012
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
								<li>
                            		<a class="dropdown-menu-sub-footer">View all messages</a>
								</li>	
							</ul>
						</li>
						<!-- end: Message Dropdown -->
						<li>
							<a class="btn" href="#">
								<i class="halflings-icon white wrench"></i>
							</a>
						</li>
						<!-- start: User Dropdown -->
						<!-- end: User Dropdown -->
					</ul>
					<ul class="nav pull-right">
						<li class="dropdown hidden-phone">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white warning-sign"></i>
							</a>
							<ul class="dropdown-menu notifications">
								<li>
									<span class="dropdown-menu-title">You have 11 notifications</span>
								</li>	
                            	<li>
                                    <a href="#">
										+ <i class="halflings-icon white user"></i> <span class="message">New user registration</span> <span class="time">1 min</span> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										+ <i class="halflings-icon white comment"></i> <span class="message">New comment</span> <span class="time">7 min</span> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										+ <i class="halflings-icon white comment"></i> <span class="message">New comment</span> <span class="time">8 min</span> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										+ <i class="halflings-icon white comment"></i> <span class="message">New comment</span> <span class="time">16 min</span> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										+ <i class="halflings-icon white user"></i> <span class="message">New user registration</span> <span class="time">36 min</span> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										+ <i class="halflings-icon white cart"></i> <span class="message">2 items sold</span> <span class="time">1 hour</span> 
                                    </a>
                                </li>
								<li class="warning">
                                    <a href="#">
										- <i class="halflings-icon white user"></i> <span class="message">User deleted account</span> <span class="time">2 hour</span> 
                                    </a>
                                </li>
								<li class="warning">
                                    <a href="#">
										- <i class="halflings-icon white shopping-cart"></i> <span class="message">Transaction was canceled</span> <span class="time">6 hour</span> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										+ <i class="halflings-icon white comment"></i> <span class="message">New comment</span> <span class="time">yesterday</span> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										+ <i class="halflings-icon white user"></i> <span class="message">New user registration</span> <span class="time">yesterday</span> 
                                    </a>
                                </li>
                                <li>
                            		<a class="dropdown-menu-sub-footer">View all notifications</a>
								</li>	
							</ul>
						</li>
						<!-- start: Notifications Dropdown -->
						<li class="dropdown hidden-phone">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white tasks"></i>
							</a>
							<ul class="dropdown-menu tasks">
								<li>
									<span class="dropdown-menu-title">You have 17 tasks in progress</span>
                            	</li>
								<li>
                                    <a href="#">
										<span class="header">
											<span class="title">iOS Development</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim progressBlue">80</div> 
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="header">
											<span class="title">Android Development</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim progressBlue">47</div> 
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="header">
											<span class="title">Django Project For Google</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim progressBlue">32</div> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										<span class="header">
											<span class="title">SEO for new sites</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim progressBlue">63</div> 
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="header">
											<span class="title">New blog posts</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim progressBlue">80</div> 
                                    </a>
                                </li>
								<li>
                            		<a class="dropdown-menu-sub-footer">View all tasks</a>
								</li>	
							</ul>
						</li>
						<!-- end: Notifications Dropdown -->
						<!-- start: Message Dropdown -->
						<li class="dropdown hidden-phone">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white envelope"></i>
							</a>
							<ul class="dropdown-menu messages">
								<li>
									<span class="dropdown-menu-title">You have 9 messages</span>
								</li>	
                            	<li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Axel Henrich
										     </span>
											<span class="time">
										    	6 min
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Megan Abott
										     </span>
											<span class="time">
										    	56 min
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Kate Ross
										     </span>
											<span class="time">
										    	3 hours
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Julie Blank
										     </span>
											<span class="time">
										    	yesterday
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="avatar"></span>
										<span class="header">
											<span class="from">
										    	Jane Sanders
										     </span>
											<span class="time">
										    	Jul 25, 2012
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
								<li>
                            		<a class="dropdown-menu-sub-footer">View all messages</a>
								</li>	
							</ul>
						</li>
						<!-- end: Message Dropdown -->
						<li>
							<a class="btn" href="#">
								<i class="halflings-icon white wrench"></i>
							</a>
						</li>
						<!-- start: User Dropdown -->
						<li class="dropdown">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white user"></i> Axel Henrich
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="halflings-icon white user"></i> Profile</a></li>
								<li><a href="login.html"><i class="halflings-icon white off"></i> Logout</a></li>
							</ul>
						</li>
						<!-- end: User Dropdown -->
					</ul>
				</div>
				<!-- end: Header Menu -->
				
			</div>
		</div>
	</div>
	<!-- start: Header -->
	
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- start: Main Menu -->
			<!-- end: Main Menu -->
			
			<noscript>
				<div class="alert alert-block span11">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span12">
				<div class="row-fluid sortable">
					<div class="box span12">
					  <decorator:body/>
					</div><!--/span-->
				</div><!--/row-->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
		
		<decorator:getProperty property="page.local_modal"></decorator:getProperty>		
		
		
		<div class="clearfix"></div>
		
		<footer>
			<p>
				<span style="text-align:left;float:left">&copy; <a href="" target="_blank">Weblogia</a> 2015</span>
				<span class="hidden-phone" style="text-align:right;float:right">Powered by: <a href="#">Weblogia</a></span>
			</p>

		</footer>
				
	</div><!--/.fluid-container-->
	
	<!-- start: JavaScript-->

	<script src="<c:url value='/resources/js/jquery-1.9.1.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery-migrate-1.0.0.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.0.custom.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.ui.touch-punch.js'/>"></script>
	<script src="<c:url value='/resources/js/modernizr.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.cookie.js'/>"></script>
	<script src="<c:url value='/resources/js/fullcalendar.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.dataTables.min.js'/>"></script>
	<script src="<c:url value='/resources/js/excanvas.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.flot.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.flot.pie.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.flot.stack.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.flot.resize.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.chosen.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.uniform.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.cleditor.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.noty.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.elfinder.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.raty.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.iphone.toggle.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.uploadify-3.1.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.gritter.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.imagesloaded.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.masonry.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.knob.modified.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.sparkline.min.js'/>"></script>
	<script src="<c:url value='/resources/js/counter.js'/>"></script>
	<script src="<c:url value='/resources/js/retina.js'/>"></script>
	<script src="<c:url value='/resources/js/custom.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.meiomask.1.1.14.js'/>"></script>
	<decorator:getProperty property="page.local_script"></decorator:getProperty>
</body>
</html>
