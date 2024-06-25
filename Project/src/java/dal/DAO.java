/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import model.Category;
import model.Product;
import model.Role;
import model.Shipper;
import model.User_Info;

/**
 *
 * @author sinan
 */
public class DAO extends DBContext {

    public static final DAO Instance = new DAO();

    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM Product";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getInt("Quantity"),
                        getCategoryByProductID(rs.getString("ProductID")),
                        rs.getString("Author")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Category> getAllCategory() {
        String sql = "SELECT * FROM Category";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getString("CategoryID"), rs.getString("CategoryName")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Shipper> getAllShipper() {
        String sql = "SELECT * FROM Shippers";
        List<Shipper> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Shipper(rs.getInt("ShipID"), rs.getString("ShipName")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return list;

    }

    public List<Product> getAllProductByCategoryName(String cate) {
        String sql = "SELECT p.ProductID,p.ProductName,p.CategoryID,c.CategoryName,Price,Image,Quantity,Author "
                + "FROM Product p INNER JOIN Category c ON  p.CategoryID=c.CategoryID where CategoryName= ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getInt("Quantity"),
                        getCategoryByProductID(rs.getString("ProductID")),
                        rs.getString("Author")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Category getCategoryByProductID(String id) {
        String sql = "SELECT * FROM Product p INNER JOIN Category c ON c.CategoryID=p.CategoryID where ProductID=?";
        Category c = null;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                c = new Category(rs.getString("CategoryID"), rs.getString("CategoryName"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return c;
    }

    
    public List<Product> getProductByCategoryName(String name) {
        String sql = "SELECT * FROM Product p INNER JOIN Category c ON c.CategoryID=p.CategoryID where CategoryName=?";
        List<Product> listProduct = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                 listProduct.add(new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getInt("Quantity"),
                        getCategoryByProductID(rs.getString("ProductID")),
                        rs.getString("Author")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listProduct;
    }
    
    public List<Product> getAllProductByProductName(String name) {
        String sql = "SELECT * FROM Product where ProductName LIKE ?";
        name+="%";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getInt("Quantity"),
                        getCategoryByProductID(rs.getString("ProductID")),
                        rs.getString("Author")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
    public boolean Register(String firstname,String lastname,String phone,String dob,String cccd,String address,String username, String password) {
        String sql ="INSERT INTO User_Info VALUES(?,?,?,?,?,?,?,?,'CUS')";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, firstname);
            stm.setString(4, lastname);
            stm.setString(5,phone);
            stm.setString(6, dob);
            stm.setString(7, cccd);
            stm.setString(8, address);
            
            return stm.executeUpdate()>0?true:false;
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
   public boolean checkUsernameExist(String Username) {
       String sql="SELECT count(*) as count FROM User_Info Where username=?";
       try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Username);
            ResultSet rs= stm.executeQuery();
            int t=0;
            if(rs.next()){
                 t =rs.getInt("count");
            }
            return t>0?true:false;
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
   } 
   public boolean checkUsernameandPassword(String Username,String Password) {
       String sql="SELECT count(*) as count FROM User_Info Where username=? and Password =?";
       try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Username);
            stm.setString(2,Password);
            ResultSet rs= stm.executeQuery();
            int t=0;
            if(rs.next()){
                 t =rs.getInt("count");
            }
            return t>0?true:false;
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
   }
   
   public Product getProductByID(String id) {
        String sql = "SELECT * FROM Product where ProductID=?";
        Product a=null;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
               a=new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getInt("Quantity"),
                        getCategoryByProductID(rs.getString("ProductID")),
                        rs.getString("Author"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return a;
    }
   public boolean InsertIntoOrder(int userID,int shipID,String ship_address,String product,double total ) {
       String sql = "INSERT INTO Orders VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setDate(2, getCurrentDate());
            stm.setInt(3, shipID);
            stm.setString(4,ship_address);
            stm.setString(5, product);
            stm.setDouble(6, total);
            return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
       return false;
   }
    public Date getCurrentDate() {
         Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        Date currentDate = new Date(year - 1900, month, day);
        return  currentDate;
    }
    public User_Info getUserbyPassAndUser(String user, String pass) {
        String sql ="Select * FROM User_Info where Username =? and Password=?";
        User_Info a = null;
          try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                a= new User_Info(rs.getInt("UserID"),
                        rs.getString("Username"), 
                        rs.getString("Password"), 
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
                        rs.getString("Phone"), 
                        rs.getString("DateOfBirth"),
                        rs.getString("CCCD"), 
                        rs.getString("Address"), 
                        getRolebyUserID(rs.getInt("UserID")));
            }
           
          
        } catch (Exception ex) {
            System.out.println(ex);
        }
          return a;
    }
    public Role getRolebyUserID(int userID) {
        Role a =null;
        String sql ="Select r.RoleId,[Role Career] From User_Info u Inner join Role r  ON  u.RoleID = r.RoleID where u.UserID=?";
          try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
           
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                a= new Role(rs.getString("RoleID"), rs.getString("Role Career"));
            }
           
          
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return a;
    }
    public boolean updateProfile(String fName, String lName, String phone, String dob, String CCCD,String Address,int UserId) {
        String sql="Update User_Info Set FirstName=? , LastName=? , Phone=? ,DateOfBirth=?\n" +
",CCCD=? ,Address=? where UserID=?";
         try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,fName );
            stm.setString(2,lName );
            stm.setString(3,phone );
            stm.setString(4,dob );
            stm.setString(5,CCCD );
            stm.setString(6,Address);
            stm.setInt(7,UserId);
            
            return stm.executeUpdate()>0 ? true:false;
          
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
        return false;
    }
    public List<User_Info> getAllUSer_Info() {
        List<User_Info> listUser = new ArrayList<>();
        String sql="Select * FROM User_Info";
         try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                listUser.add(new User_Info(rs.getInt("UserID"),
                        rs.getString("Username"), 
                        rs.getString("Password"), 
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
                        rs.getString("Phone"), 
                        rs.getString("DateOfBirth"),
                        rs.getString("CCCD"), 
                        rs.getString("Address"), 
                        getRolebyUserID(rs.getInt("UserID"))));
            }
           
          
        } catch (Exception ex) {
            System.out.println(ex);
        }
         return listUser;
    }
    public boolean deleteUser(int id) {
        String sql="DELETE User_Info where UserID=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
           
          return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
    public boolean deleteProduct(String pID) {
        
         String sql="DELETE Product where ProductID=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pID);
           
          return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return false;
    }
     public boolean deleteCategory(String cateID) {
        
         String sql="DELETE Category where CategoryID=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cateID);
           
          return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return false;
    }
     
     public boolean UpdateProduct(String name,double price,String img,
             int quantity,String categoryID,String author,String pID) {
         String sql="UPDATE Product SET ProductName=?,  Price=?, Image=?,Quantity=?,CategoryID=?, Author=?\n" +
"WHERE ProductID =?";
          try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setDouble(2, price);
            stm.setString(3, img);
            stm.setInt(4,quantity);
            stm.setString(5, categoryID);
            stm.setString(6, author);
            stm.setString(7, pID);
            
           
          return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
         
         return false;
     }
     public Category getCategoryByCateID(String id) {
         String sql="SELECT * FROM Category where CategoryID=?";
          Category c = null;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                c = new Category(rs.getString("CategoryID"), rs.getString("CategoryName"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return c;
     }
    
      public boolean UpdateCategory(String cateupdateID,String name,String cateidnow) {
        
         String sql="Update Category set CategoryID=? , CategoryName=? where CategoryID=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cateupdateID);
             stm.setString(2, name);
                        stm.setString(3, cateidnow);

          return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return false;
    }
      public boolean InsertProduct(String id ,String name,double price,
              String file,int quantity,String category,String author) {
          String sql="INSERT INTO Product Values(?,?,?,?,?,?,?)";
          
           try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2,name);
            stm.setDouble(3, price);
            stm.setString(4,file);
            stm.setInt(5, quantity);
            stm.setString(6,category );
            stm.setString(7, author);
            
           
          return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
          
          return false;
      }
      public boolean InsertCategory(String id,String name) {
          String sql="INSERT INTO Category Values(?,?)";
          
           try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2,name);
            
            
           
          return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
          
          return false;
      }
      public List<Order> getAllOrder() {
          List<Order> list = new ArrayList<>();
          String sql="SELECT * FROM Orders";
           try {
            PreparedStatement stm = connection.prepareStatement(sql);
          ResultSet rs=stm.executeQuery();
             while(rs.next()) {
            list.add(new  Order(rs.getInt("OrderID"),
                    Instance.getUser_InfoByID(rs.getInt("UserID")),
                    rs.getString("OrderDate"), 
                    Instance.getShipperByShipID(rs.getInt("ShipID")),
                    rs.getString("ship_address"), rs.getString("Product"),
                    rs.getDouble("Total")));
             }
            
           
        } catch (Exception ex) {
            System.out.println(ex);
        }
          return list;
      }
      public User_Info getUser_InfoByID(int id) {
          User_Info a = null;
          String sql="Select * FROM User_Info where Userid=?";
             try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
          ResultSet rs=stm.executeQuery();
               if(rs.next()) {
                a= new User_Info(rs.getInt("UserID"),
                        rs.getString("Username"), 
                        rs.getString("Password"), 
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
                        rs.getString("Phone"), 
                        rs.getString("DateOfBirth"),
                        rs.getString("CCCD"), 
                        rs.getString("Address"), 
                        getRolebyUserID(rs.getInt("UserID")));
            }
           
            
           
        } catch (Exception ex) {
            System.out.println(ex);
        }
             return a;
      }
      public Shipper getShipperByShipID(int id) {
          Shipper a = null;
              String sql = "SELECT * FROM Shippers where ShipID=?";
        
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                a=(new Shipper(rs.getInt("ShipID"), rs.getString("ShipName")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return a;

            
           
      }
      public boolean checkProductinStore(String productID) {
          List<String> listPID= new ArrayList<>();
          String sql="SELECT ProductID FROM Product";
           try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listPID.add(rs.getString("ProductID"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
           for (String i : listPID) {
              if(i.equals(productID)) {
                  return true;
              }
          }
           
          return false;
      }
      
        public boolean checkQuantity(String id,int quantity) {
          int getQuan=0;
          String sql="SELECT Quantity FROM Product where ProductID=?";
           try {
            PreparedStatement stm = connection.prepareStatement(sql);
          
            stm.setString(1, id);
              ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                 getQuan=rs.getInt("Quantity");
              
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
          if(getQuan>quantity) {
              return true;
          }
          return false;
           
         // return false;
      }
  public boolean isProduct(String id) {
         int getQuan=0;
          String sql="SELECT count(*) as pNum  FROM Product where ProductID=?";
           try {
            PreparedStatement stm = connection.prepareStatement(sql);
          
            stm.setString(1, id);
              ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                 getQuan=rs.getInt("pNum");
              
            }
            return getQuan>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
          
          return false;
  }
  public double getSumOfOrder() {
      double tong=0;
          String sql="SELECT sum(Total) as tong   FROM Orders";
           try {
            PreparedStatement stm = connection.prepareStatement(sql);
          
            
              ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                 tong=rs.getInt("tong");
              
            }
            return tong;
        } catch (Exception ex) {
            System.out.println(ex);
        }
          
          return 0;
  }
  public boolean updateQuantity(int quanBuy, String id) {
      String sql="Update product set Quantity = Quantity-? where ProductID=?";
      try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quanBuy);
            stm.setString(2, id);
        
           return stm.executeUpdate()>0?true:false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
          
          return false;
  }
      
    public static void main(String[] args) {
        //System.out.println(Instance.InsertIntoOrder(12, 1, "HCM city", "sach 1 + sach 2", 100));
       // System.out.println(Instance.updateProfile("Si", "Nguyen", "0012345678", "12/12/2024","123456789000" , "Da Nang", 16));
        
//        for (User_Info i : Instance.getAllUSer_Info()) {
//            System.out.println(i.getFirstName()+" "+i.getLastName());
//        }
       // System.out.println(Instance.UpdateProduct("MMM", 1000, "LLL", 100, "HOR", "Si", "RMC11"));
       // System.out.println(Instance.UpdateCategory("DDD","Categirryrr","DDD" ));
       // System.out.println(Instance.InsertProduct("MMM", "MMM", 210, " ", 4, "RMC", "SI"));
       // System.out.println(Instance.InsertCategory("SSS", "SSS"));
      //  System.out.println(Instance.getUser_InfoByID(18).getFirstName());
        //System.out.println(Instance.getShipperByShipID(1).getShipName());
//        for (Order i : Instance.getAllOrder()) {
//            System.out.println(i.getUserID().getUserID());
//        }
// 
        //System.out.println(Instance.checkProductinStore("ADV01"));
       // System.out.println(Instance.tachChuoi("9!123"));
     
        //System.out.println(Instance.checkUsernameExist("sideptrai"));
        //System.out.println(Instance.isProduct("ADV"));
        //System.out.println(Instance.getSumOfOrder());
        //System.out.println(Instance.updateQuantity(2, "ADV01"));
    }
     
}

    

