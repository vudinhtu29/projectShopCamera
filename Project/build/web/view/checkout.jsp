<!DOCTYPE html>
<html lang="en">
<%@page import="java.util.*"  %>
 
<%@page import="model.*" %> 
<head>
    <meta charset="utf-8">
    <title>EShopper - Bootstrap Shop Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Topbar Start -->
   <div class="container-fluid">
            <div class="row bg-secondary py-2 px-xl-5">
                <div class="col-lg-6 d-none d-lg-block">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark" href="">FAQs</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Help</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Support</a>
                    </div>
                </div>
                <div class="col-lg-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-instagram"></i>
                        </a>
                        <a class="text-dark pl-2" href="">
                            <i class="fab fa-youtube"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row align-items-center py-3 px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a href="" class="text-decoration-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">Si</span>Book</h1>
                    </a>
                </div>
                <div class="col-lg-6 col-6 text-left">
                   
                </div>
                <div class="col-lg-3 col-6 text-right">
                    <a href="" class="btn border">
                        <i class="fas fa-heart text-primary"></i>
                        <span class="badge">0</span>
                    </a>
                    <a href="" class="btn border">
                        <i class="fas fa-shopping-cart text-primary"></i>
                        <span class="badge">0</span>
                    </a>
                </div>
            </div>
        </div>
        <!-- Topbar End -->
<form id="logoutForm" action="CheckOut" method="post">

        <!-- Navbar Start -->
       <div class="container-fluid">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <p>Good books, like good friends, are few and chosen; the more select, the more enjoyable</p>
                </div>
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="Home" class="nav-item nav-link active">Home</a>
                                <a href="CheckOut" class="nav-item nav-link">Checkout</a>
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                        <a href="Cart" class="dropdown-item">Shopping Cart</a>
                                    </div>
                                </div>
                              
                            </div>
                               <%
                            User_Info user = (User_Info) request.getSession().getAttribute("account");
                            if(user!=null) {
                          
                            %>
                              <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Hello <%=user.getLastName() +" "+user.getFirstName()%></a>
                                <div class="dropdown-menu rounded-0 m-0">
                                    <a href="" class="dropdown-item">  <a href="Login" class="nav-item nav-link active">Log Out</a> </a>
                                    <a href="" class="dropdown-item">  <a href="Profile" class="nav-item nav-link active">View Profile</a></a>
                                </div>
                            </div>
                            
                            <%
                            
                                }else {
                                %>
                                 <div class="navbar-nav ml-auto py-0">
                                <a href="Login" class="nav-item nav-link">Login</a>
                                <a href="Register" class="nav-item nav-link">Register</a>
                            </div>
                                <%
                                }
                            
                            %>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar End -->
<%   
List<Product> pList = ( List<Product> ) request.getAttribute("pList");
String msg =(String) request.getAttribute("msg"); 

%>


    <!-- Page Header Start -->
    <div class="container-fluid bg-secondary mb-5">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
            <div class="d-inline-flex">
                <p class="m-0"><a href="Home">Home</a></p>
                <p class="m-0 px-2">-</p>
                <p class="m-0">Checkout</p>
            </div>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- Checkout Start -->
    

    <div class="container-fluid pt-5">
        <div class="row px-xl-5">
            <div class="col-lg-8">
                <div class="mb-4">
                    <h4 class="font-weight-semi-bold mb-4">Please enter full information below</h4>
                    <div class="row">
                        <div class="col-md-6 form-group">
                            <label></label>
                         
                        </div>
                        <div class="col-md-6 form-group">
                            <label></label>
                            
                        </div>
                        <div class="col-md-6 form-group">
                            <label></label>
                        </div>
                        <div class="col-md-6 form-group">
                            <label></label>
                        </div>
                        <div class="col-md-6 form-group">
                            <label></label>
                            
                        </div>
                        <div class="col-md-6 form-group">
                            <label></label>
                          
                        </div>
                         <div class="col-md-6 form-group">
                            <label>Shipper</label> 
                            <select name="shipper" class="custom-select">
                               
                                <% 
                       List<Shipper> shipperList = (List<Shipper>) request.getAttribute("shipperList");
                         if(shipperList != null) {

    
                         %>
                        <% for(Shipper i : shipperList )
                        {
                        %>
                       
                           
                                <option selected  value="<%=i.getShipID()%>"><%=i.getShipName()%></option>
                              
                              
                        
                        
                        <%
                        }
                      }
                        
                        %>
                               
                            </select>
                        </div>
                        <div class="col-md-6 form-group">
                            <label>Ship to</label>
                            <input class="form-control" name="shipto" type="text" placeholder="" required="">
                        </div>
                        <div class="col-md-6 form-group">
                            <label></label>
                           
                        </div>
                        <div class="col-md-6 form-group">
                            <label></label>
                           
                        </div>
                        <div class="col-md-12 form-group">
                            
                        </div>
                        <div class="col-md-12 form-group">
                            
                        </div>
                    </div>
                </div>
                
            </div>
                        
            <div class="col-lg-4">
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Order Total</h4>
                    </div>
                    <div class="card-body">
                        <h5 class="font-weight-medium mb-3">Products</h5>
                        
                        <%  if(pList!=null) {
                           for(Product i : pList) {
                           
                            
                            %>
                        <div class="d-flex justify-content-between">
                            <p><%=i.getProductName()%></p>
                            <p><%=i.getPrice()%></p>
                        </div>
                        <%
                            }
                            }%>
                        <hr class="mt-0">
                       
                    </div> 
                          <%
                        Double thanhtien =(Double) request.getAttribute("thanhtien"); 
                        if(thanhtien!=null &&thanhtien!=0) {
                        %>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold"><%=thanhtien%>$</h5>
                        </div>
                    </div>
                        <%}else if (thanhtien==0) {
 %>
 <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                             <h5 class="font-weight-bold">Nothing here</h5>
                            <h5 class="font-weight-bold"><a href="Home">Buy Now</a></h5>
                        </div>
                    </div>
 
 <%

}
 %>
                </div>
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Payment</h4>
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" checked="" name="payment" id="paypal">
                                <label class="custom-control-label" for="paypal">Paypal</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" name="payment" id="directcheck">
                                <label class="custom-control-label" for="directcheck">Direct Check</label>
                            </div>
                        </div>
                        <div class="">
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" name="payment" id="banktransfer">
                                <label class="custom-control-label" for="banktransfer">Bank Transfer</label>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <input type="submit" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3" value="Place Order">
                    </div>
                    <% 
                    if(msg!=null) {
                     %>
                     <h2><%=msg%></h2>
                     <%
                        }
                    %>
                </div>
            </div>
        </div>
                </div>

   </form>

    <!-- Checkout End -->


    <!-- Footer Start -->
     <div class="container-fluid bg-secondary text-dark mt-5 pt-5">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="" class="text-decoration-none">
                        <h1 class="mb-4 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-white px-3 mr-1">E</span>Shopper</h1>
                    </a>
                    <p> Any man who reads too much and uses his own brain too little falls into lazy habits of thinking</p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Binh Yen, Thach That, Ha Noi</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>sinanidesuka12@gmail.com</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>0353385425</p>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="row">
                        
                        <div class="col-md-4 mb-5">
                            <h5 class="font-weight-bold text-dark mb-4">Newsletter</h5>
                            <form action="">
                                <div class="form-group">
                                    <input type="text" class="form-control border-0 py-4" placeholder="Your Name" required="required" />
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control border-0 py-4" placeholder="Your Email"
                                           required="required" />
                                </div>
                                <div>
                                    <button class="btn btn-primary btn-block border-0 py-3" type="submit">Subscribe Now</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row border-top border-light mx-xl-5 py-4">
                <div class="col-md-6 px-xl-0">
                    <p class="mb-md-0 text-center text-md-left text-dark">
                        &copy; <a class="text-dark font-weight-semi-bold" href="#">Your Site Name</a>. All Rights Reserved. Designed
                        by
                        <a class="text-dark font-weight-semi-bold" href="https://htmlcodex.com">HTML Codex</a><br>
                        Distributed By <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
                    </p>
                </div>
                <div class="col-md-6 px-xl-0 text-center text-md-right">
                    <img class="img-fluid" src="img/payments.png" alt="">
                </div>
            </div>
        </div>
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
<script>
    document.getElementById('logoutButton').addEventListener('click', function(e) {
    document.getElementById('logoutForm').submit();
  });
</script>
</html>