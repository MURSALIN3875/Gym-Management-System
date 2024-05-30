

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.table.*;

public class GymManagement extends JFrame implements ActionListener
{
   JLabel label,username,password,admin,loginlabel,adminlogo;
   JTextField userfield;
   JPasswordField passfield;
   JButton eye,closeeye,login;
   Connection con;
   Statement st;
   ResultSet rs;

    GymManagement()
    {
        pack();  

      try
     {

      Class.forName("org.postgresql.Driver");
      con=DriverManager.getConnection("jdbc:postgresql://localhost/gym","postgres","mursalin@123");
      st=con.createStatement();

     }catch(Exception e)
     {
      System.out.println(e);
     }  
      
        setSize(1000,700);
         setLocationRelativeTo(null);
         setBackground(Color.WHITE);
         getContentPane().setBackground(Color.WHITE);
        setTitle("GymManagement");
        setLayout(null);
        
        ImageIcon icon=new ImageIcon("logo.png");
        Image resizedImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

LineBorder yellow = new LineBorder(Color.YELLOW, 2); 

  label=new JLabel();
  label.setIcon(resizedIcon);
  label.setBounds(450,0,250,250);
 
    Color darkOrange = new Color(255, 140, 0);
    JPanel panel=new JPanel();
    panel.setLayout(null);
    panel.setBounds(0,0,200,700);
    panel.setBackground(Color.YELLOW);
    
    username=new JLabel("ENTER USERNAME :-");
    username.setBounds(300,240,250,100);
    Font font=new Font("SansSerif",Font.BOLD,20);
    username.setFont(font);

    password=new JLabel("ENTER PASSWORD :-");
    password.setBounds(300,350,250,100);
    password.setFont(font);


    userfield=new JTextField();
    userfield.setBounds(550,270,200,40);
    Font font1=new Font("Arial",Font.BOLD,20);
    userfield.setFont(font1);
 


    passfield=new JPasswordField();
    passfield.setBounds(550,380,200,40);
    passfield.setFont(font1);
    passfield.setEchoChar('\u2022');
     
   ImageIcon visible=new ImageIcon("visible.png");
   Image resizedImage1 = visible.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
   ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);

   eye=new JButton();
   eye.setIcon(resizedIcon1);
   eye.setBorder(null);
   eye.setFocusPainted(false);
   eye.setContentAreaFilled(false);
   eye.setBounds(760,380,40,40);  
   eye.setVisible(false);
 ImageIcon close=new ImageIcon("eye.png");
 Image resizedImage2 = close.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
 ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);

   closeeye=new JButton();
   closeeye.setIcon(resizedIcon2);
   closeeye.setBorder(null);
   closeeye.setFocusPainted(false);
   closeeye.setContentAreaFilled(false);
   closeeye.setBounds(760,380,40,40);  
   
   login=new JButton("LOG-IN");
   login.setBounds(480,510,150,50);  
   login.setBackground(Color.BLACK);
   login.setForeground(Color.YELLOW);
   login.setFont(font1);
   login.setBorder(null);
   login.setFocusPainted(false);

   admin=new JLabel("ADMIN");
   admin.setBounds(50,350,300,100);
   Font adminfont=new Font("Arial",Font.BOLD,30);
   admin.setFont(adminfont);
   panel.add(admin);

   loginlabel=new JLabel("LOGIN");
   loginlabel.setBounds(50,400,300,100);
   loginlabel.setFont(adminfont);
   panel.add(loginlabel);

   ImageIcon adminlogoimage=new ImageIcon("adminlogo.png");
   Image resizedImage4 = adminlogoimage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
   ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);

  adminlogo=new JLabel();
  adminlogo.setIcon(resizedIcon4);
  adminlogo.setBounds(50,250,100,100);
  panel.add(adminlogo);
         add(login);
         add(closeeye);
         add(eye);
         add(passfield);
         add(userfield);
         add(password);
         add(username);
         add(label);
         add(panel);
    
    eye.addActionListener(this);
    closeeye.addActionListener(this);
    login.addActionListener(this);

        setVisible(true);
       
    }

   public void actionPerformed(ActionEvent ae)
   {
    if(ae.getSource()==eye)
    {
        eye.setVisible(false);
        closeeye.setVisible(true);
         passfield.setEchoChar('\u2022');
         
    }
     if(ae.getSource()==closeeye)
    {
         closeeye.setVisible(false);
        eye.setVisible(true);
       passfield.setEchoChar((char)0);
    }
    if(ae.getSource()==login)
    {
      // try{
      // String n,p;
      // n=userfield.getText();
      // p=passfield.getText();
      // String aname="",apassword="";
      //    rs=st.executeQuery("select * from admin");
         
      //    while(rs.next())
      //    {
      //       aname=rs.getString(1);
      //       apassword=rs.getString(2);
      //    }
      //     if(n.equals(aname)&&p.equals(apassword))
      //     {
           dispose();
           new home();

      //     }
      //       else{
                  
      //        JOptionPane.showMessageDialog(null, "Login Failed ivald user name or pass", "Error", JOptionPane.ERROR_MESSAGE);
      //       }

      // }catch(Exception e)
      // {
      //   System.out.println(e);
      // }  
    }
   }

    public static void main(String arg[])
    {
        new GymManagement();
    }
}
 class home extends JFrame implements ActionListener
{
     JLabel home,members,membername,mobile,address,selecttrainer,membership;
     JLabel gender,date,pay;
     JButton trainer,payment,logout,logoutimage,trainerimage,paymentimage;
     JButton add,edit,delete;
     Color darkOrange ;
     JTextField membertext,mobiletext,paytext;
     JComboBox c1,c2,day,month,year;
     JRadioButton male,female;
     JTable table;
     JScrollPane scrollPane,scrollPane1;
        
     ButtonGroup b1;
     Connection con;
     Statement st;
     ResultSet rs;
     ResultSetMetaData rsm;
     DefaultTableModel model;
        String[] columnNames = {
            "Member", "Mobile", "Gender", "Membership", "Date", "Payment", "Trainer"
        };
       ArrayList<String> stringList;
     
    home()
    {
     try
     {

      Class.forName("org.postgresql.Driver");
      con=DriverManager.getConnection("jdbc:postgresql://localhost/gym","postgres","mursalin@123");
      st=con.createStatement();

     }catch(Exception e)
     {
      System.out.println(e);
     }

         setSize(1000,700);
         setLocationRelativeTo(null);
         setBackground(Color.WHITE);
         getContentPane().setBackground(Color.WHITE);
         setTitle("GymManagement");
         setLayout(null);



    darkOrange = new Color(255, 140, 0);
    JPanel panel=new JPanel();
    panel.setLayout(null);
    panel.setBounds(0,0,200,700);
    panel.setBackground(Color.YELLOW);

    
 Font font=new Font("Arial",Font.BOLD,20);
 LineBorder border = new LineBorder(Color.BLACK, 2); 
 LineBorder border2 = new LineBorder(Color.RED, 2); 
 LineBorder yellow = new LineBorder(Color.YELLOW, 2); 
 LineBorder white = new LineBorder(Color.WHITE, 2); 

   

   ImageIcon icon=new ImageIcon("member.png");
   Image resizedImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
   ImageIcon resizedIcon = new ImageIcon(resizedImage);

  home=new JLabel();
  home.setIcon(resizedIcon);
  home.setBounds(60,30,100,80);
  panel.add(home);

  Font font2=new Font("Arial",Font.BOLD,30);

  members=new JLabel("MEMBERS");
  members.setBounds(25,110,200,30);
  members.setFont(font2);
  panel.add(members);



  trainer =new JButton("TRAINERS");
  trainer.setBounds(50,320,130,40);
  trainer.setBackground(Color.YELLOW);
  trainer.setForeground(Color.BLACK);
  trainer.setFont(font);
  trainer.setBorder(border);
  trainer.setFocusPainted(false);
  panel.add(trainer);

ImageIcon trainericon=new ImageIcon("trainer.png");
Image resizedImage6 = trainericon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);

trainerimage=new JButton(resizedIcon6);
trainerimage.setBounds(0,315,50,50);
trainerimage.setBackground(darkOrange);
trainerimage.setBorder(yellow);
trainerimage.setFocusPainted(false);
trainerimage.setContentAreaFilled(false);
panel.add(trainerimage);

  payment =new JButton("PAYMENT");
  payment.setBounds(50,385,130,40);
  payment.setBackground(darkOrange);
  payment.setForeground(Color.BLACK);
  payment.setFont(font);
  payment.setBorder(border);
  payment.setFocusPainted(false);
  payment.setContentAreaFilled(false);
  panel.add(payment);

 
ImageIcon paymenticon=new ImageIcon("coin.png");
Image resizedImage7 = paymenticon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);

paymentimage=new JButton(resizedIcon7);
paymentimage.setBounds(5,385,40,40);
paymentimage.setBackground(darkOrange);
paymentimage.setBorder(yellow);
paymentimage.setFocusPainted(false);
paymentimage.setContentAreaFilled(false);
panel.add(paymentimage);



  logout =new JButton("LOG-OUT");
  logout.setBounds(90,600,110,40);
  logout.setBackground(Color.BLACK);
  logout.setForeground(Color.YELLOW);
  
  logout.setFont(font);
  logout.setBorder(border);
  logout.setFocusPainted(false);
  panel.add(logout);

ImageIcon logimage=new ImageIcon("logout2.png");
Image resizedImage5 = logimage.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);

    
logoutimage=new JButton(resizedIcon5);
logoutimage.setBounds(40,595,45,45);
logoutimage.setBackground(darkOrange);
logoutimage.setBorder(yellow);
logoutimage.setFocusPainted(false);
logoutimage.setContentAreaFilled(false);

 Font font3=new Font("SansSerif",Font.BOLD,18);

 membername =new JLabel("MEMBER NAME :-");
 membername.setBounds(220,15,220,40);
 membername.setFont(font3);
 
 
membertext=new JTextField();
membertext.setBounds(400,20,150,30);
membertext.setFont(font3);
membertext.setBorder(border);

 mobile =new JLabel("MOBILE NO :- ");
 mobile.setBounds(590,15,220,40);
 mobile.setFont(font3);
 
mobiletext=new JTextField();
mobiletext.setBounds(730,20,150,30);
mobiletext.setFont(font3);
mobiletext.setBorder(border);

 selecttrainer =new JLabel("SELECT TRAINER :-");
 selecttrainer.setBounds(220,100,220,40);
 selecttrainer.setFont(font3);

    try{
         rs=st.executeQuery("select * from trainer");
           stringList = new ArrayList<>();
        
         while(rs.next())
         {
          stringList.add(rs.getString(1)); 
         }
    }catch(Exception e)
    {
      System.out.println(e);
    }

  String[] values = stringList.toArray(new String[0]);
 

 c1=new JComboBox(values);
 c1.setBounds(410,105,150,30);
 c1.setBackground(Color.WHITE);
 c1.setFont(font3);
 c1.setBorder(yellow);

 membership =new JLabel("MEMBERSHIP :-");
 membership.setBounds(590,100,220,40);
 membership.setFont(font3);

 String values1[]={"Only Gym","Only Cardio","Gym+Cardio"};

 c2=new JComboBox(values1);
 c2.setBounds(750,105,150,30);
 c2.setBackground(Color.WHITE);
 c2.setFont(font3);
 c2.setBorder(yellow);

 gender =new JLabel("GENDER :-");
 gender.setBounds(220,200,220,40);
 gender.setFont(font3);

 
 male=new JRadioButton("MALE");
 male.setBounds(330,195,100,50);
 male.setFont(font3);
 male.setBackground(Color.WHITE);
 male.setFocusPainted(false);

 female=new JRadioButton("FEMALE");
 female.setBounds(450,195,150,50);
 female.setFont(font3);
 female.setBackground(Color.WHITE);
 female.setFocusPainted(false);
  
  b1=new ButtonGroup();
  b1.add(male);
  b1.add(female);

 

add=new JButton("ADD");
add.setBounds(390,370,100,45);
add.setFont(font3);
add.setBackground(Color.BLACK);
add.setForeground(Color.YELLOW);
add.setFocusPainted(false);
add.setBorder(yellow);

edit=new JButton("EDIT");
edit.setBounds(530,370,100,45);
edit.setFont(font3);
edit.setBackground(Color.BLACK);
edit.setForeground(Color.YELLOW);
edit.setFocusPainted(false);
edit.setBorder(yellow);

delete=new JButton("DELETE");
delete.setBounds(670,370,100,45);
delete.setFont(font3);
delete.setBackground(Color.BLACK);
delete.setForeground(Color.RED);
delete.setFocusPainted(false);
delete.setBorder(border2);

 date=new JLabel("DATE :-");
 date.setBounds(230,280,100,50);
 date.setFont(font3);

String[] days = new String[31];

        for (int i = 0; i < 31; i++) {
            int num = i +1; 
            days[i] = String.valueOf(num); 
        }

 day=new JComboBox(days);
 day.setBounds(320,290,50,30);
 day.setBackground(Color.WHITE);
 day.setFont(font3);
 day.setBorder(yellow);

    String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };


 month=new JComboBox(months);
 month.setBounds(380,290,100,30);
 month.setBackground(Color.WHITE);
 month.setFont(font3);
 month.setBorder(yellow);

   String[] years =new String[21];
    int y=2020;

    for(int i=0;i< years.length;i++)
    {
      
      years[i]=String.valueOf(y+i);

    }
 year=new JComboBox(years);
 year.setBounds(500,290,100,30);
 year.setBackground(Color.WHITE);
 year.setFont(font3);
 year.setBorder(yellow);

 pay=new JLabel("PAYMENT :-");
 pay.setBounds(650,195,150,50);
 pay.setFont(font3);

paytext=new JTextField();
paytext.setBounds(780,205,150,30);
paytext.setFont(font3);
paytext.setBorder(border);

Font tablefont=new Font("Arial",Font.BOLD,15);

    String m="mursalin";
    model = new DefaultTableModel(columnNames, 0);
       

        table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));
        header.setFont(tablefont);
        header.setBackground(Color.YELLOW);
        header.setForeground(Color.BLACK);
        header.setBorder(null);

        table.setRowHeight(30);
        table.setFont(tablefont);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setBorder(null);




        // Put the table in a scroll pane
         scrollPane = new JScrollPane(table);



       
 scrollPane.setBounds(220,450,750,200);
 scrollPane.setBackground(Color.WHITE);
 add(scrollPane);

//  int columnIndexToHide = 7; // Index of the column to hide (0-based index)

//         // Get the column model from the table
//         TableColumnModel columnModel = table.getColumnModel();

//         // Get the TableColumn object for the column to hide
//         TableColumn column = columnModel.getColumn(columnIndexToHide);

//         // Set the width of the column to 0, effectively hiding it
//         column.setMinWidth(0);
//         column.setMaxWidth(0);
//         column.setPreferredWidth(0);
//         column.setWidth(0);

//         // Update the column model
//         columnModel.getColumn(columnIndexToHide).setResizable(false); // Optional: Disable resizing

//         // Optionally, update the table UI
//         table.updateUI();

   payment.addActionListener(this);
   edit.addActionListener(this);
   trainer.addActionListener(this);
   add.addActionListener(this);
   delete.addActionListener(this);
   logout.addActionListener(this);
   panel.add(logoutimage);
    add(panel);
    add(membername);add(membertext);
    add(mobile);add(mobiletext);
    add(selecttrainer);add(c1);
    add(membership);add(c2);
    add(gender);add(male);add(female);
   
    add(date);add(day);add(month);add(year);
    add(pay);add(paytext);
    add(add);add(edit);add(delete);
    
loadtable();
    
        setVisible(true);
    }
    public void loadtable()
    {
      try{
         
   
  rs=st.executeQuery("SELECT * FROM members ORDER BY sort_order");
     rsm=rs.getMetaData();

      List<Object[]> dataList = new ArrayList<>();
      
        while (rs.next()) {
            Object[] row = {
                rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5), rs.getString(6), rs.getString(7)
            };
            dataList.add(row);
        }
        Object[][] data = dataList.toArray(new Object[0][]);

          model.setDataVector(data, columnNames);

           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
for (int i = 0; i < table.getColumnCount(); i++) {
    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
       
      }catch(Exception e)
      {
        System.out.println(e);
      }
    }

    public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource()==payment)
      {
        dispose();
        new payment();
      }
      if (ae.getSource() == edit) {
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();

            if (selectedRow == -1 && selectedColumn == -1) {
                JOptionPane.showMessageDialog(this, "Please select a valid row to edit.");
            }

            else {
                selectedRow = table.getSelectedRow();

                Object[] row = new Object[table.getColumnCount()];
                for (int column = 0; column < table.getColumnCount(); column++) {
                    row[column] = table.getValueAt(selectedRow, column);
                }
                for (int column = 0; column < table.getColumnCount(); column++) {
                    model.setValueAt(row[column], selectedRow, column);

                }

                table.setFont(new Font("arial", Font.BOLD, 16));
                table.getCellEditor().stopCellEditing();
                model.fireTableDataChanged();
                table.revalidate();
                table.repaint();
                table.clearSelection();

                String name = (String) table.getValueAt(selectedRow, 0);
                String mobile = (String) table.getValueAt(selectedRow, 1);
                 String gender = (String) table.getValueAt(selectedRow, 2);
                String membership = (String) table.getValueAt(selectedRow, 3);
                String paydate = (String) table.getValueAt(selectedRow, 4);
                String payment = (String) table.getValueAt(selectedRow, 5);
                 String trainer = (String) table.getValueAt(selectedRow, 6);
                 
// String id1 = (String) table.getValueAt(selectedRow, 7);

 

                try {
                    
                        // selectedRow = selectedRow + 1;
                             if (selectedRow != 0) {
                        selectedRow = selectedRow - 1;
                    }

                   String query = "UPDATE members SET member = '" + name + "',mobile ='" + mobile + "',gender='" + gender + 
                   "',membership='" + membership + "',date='" + paydate
                            + "',payment='" + payment + "',trainer='"
                            + trainer
                            + "' where mobile='"+mobile+"'" ; 
                   
                    st.executeUpdate(query);
                    if (selectedRow != 0) {
                        System.out.println("Row " + (selectedRow + 1) + " updated successfully.");
                    } else {
                        System.out.println("Row " + (selectedRow) + " updated successfully.");

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
          if(ae.getSource()==trainer)
          {
            dispose();
            new trainer();
          }

        if(ae.getSource()==logout)
        {
           
            dispose();
            new GymManagement();
        }

        if(ae.getSource()==add)
        {
          try
          {
           String member,mobile,gender,membership,date,payment,trainer;
           member=membertext.getText();
           mobile=mobiletext.getText();
           if(male.isSelected())
           {
            gender="male";
           }           
           else
           {
            gender="female";
           }
           membership=(String)c2.getSelectedItem();
           trainer=(String)c1.getSelectedItem();
           payment=paytext.getText();
           date=(String)day.getSelectedItem()+"/"+(String)month.getSelectedItem()+"/"+(String)year.getSelectedItem();
          if (member != null && !member.isEmpty() &&
    mobile != null && !mobile.isEmpty() &&
    gender != null && !gender.isEmpty() &&
    membership != null && !membership.isEmpty() &&
    trainer != null && !trainer.isEmpty() &&
    payment != null && !payment.isEmpty() &&
    date != null && !date.isEmpty())
          {
         
 int k= st.executeUpdate("insert into members values('"+member+"','"+mobile+"','"+gender+"','"+membership+"','"+date+"','"+payment+"','"+trainer+"');");
         loadtable();
          }
          else{
             JOptionPane.showMessageDialog(null, "Fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
          }
          }catch(Exception e)
          {
            System.out.println(e);
          } 
        }

        if(ae.getSource()==delete)
        {
          try{
          int selectedrow=table.getSelectedRow();
          if(selectedrow>=0)
          {
            String mem2=(String)table.getValueAt(selectedrow,1);



            int z=st.executeUpdate("delete from members where mobile='"+mem2+"'");

            model.removeRow(selectedrow);
               table.revalidate();
                    table.repaint();
            
          }
          else
          {
             JOptionPane.showMessageDialog(null, "select the row to delete", "Error", JOptionPane.ERROR_MESSAGE);
          }
          }catch(Exception e)
          {
            System.out.println(e);
          }
        }
    }




}
class trainer extends JFrame implements ActionListener
{

     JLabel home,members,membername,mobile,address,selecttrainer,membership;
     JLabel gender,date,pay;
     JButton trainer,payment,logout,logoutimage,trainerimage,paymentimage;
     JButton add,edit,delete;
     Color darkOrange ;
     JTextField membertext,mobiletext,paytext;
     JComboBox c1,c2,day,month,year;
     JRadioButton male,female;
     JTable table;
     JScrollPane scrollPane,scrollPane1;
        
     ButtonGroup b1;
     Connection con;
     Statement st;
     ResultSet rs;
     ResultSetMetaData rsm;
     DefaultTableModel model;
     String[] columnNames = {
            "Trainers", "Mobile", "Gender", "Date", "Payment"
        };
     
     
    trainer()
    {
     try
     {

      Class.forName("org.postgresql.Driver");
      con=DriverManager.getConnection("jdbc:postgresql://localhost/gym","postgres","mursalin@123");
      st=con.createStatement();

     }catch(Exception e)
     {
      System.out.println(e);
     }

        setSize(1000,700);
         setLocationRelativeTo(null);
         setBackground(Color.WHITE);
         getContentPane().setBackground(Color.WHITE);
        setTitle("GymManagement");
        setLayout(null);



    darkOrange = new Color(255, 140, 0);
    JPanel panel=new JPanel();
    panel.setLayout(null);
    panel.setBounds(0,0,200,700);
    panel.setBackground(Color.YELLOW);

    
 Font font=new Font("Arial",Font.BOLD,20);
 LineBorder border = new LineBorder(Color.BLACK, 2); 
 LineBorder border2 = new LineBorder(Color.RED, 2); 
 LineBorder yellow = new LineBorder(Color.YELLOW, 2); 
 LineBorder white = new LineBorder(Color.WHITE, 2); 

   

   ImageIcon icon=new ImageIcon("trainer.png");
   Image resizedImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
   ImageIcon resizedIcon = new ImageIcon(resizedImage);

  home=new JLabel();
  home.setIcon(resizedIcon);
  home.setBounds(60,30,100,80);
  panel.add(home);

  Font font2=new Font("Arial",Font.BOLD,30);

  members=new JLabel("TRAINERS");
  members.setBounds(25,110,200,30);
  members.setFont(font2);
  panel.add(members);



  trainer =new JButton("MEMBERS");
  trainer.setBounds(50,320,130,40);
  trainer.setBackground(Color.YELLOW);
  trainer.setForeground(Color.BLACK);
  trainer.setFont(font);
  trainer.setBorder(border);
  trainer.setFocusPainted(false);
 // trainer.setContentAreaFilled(false);
   panel.add(trainer);

ImageIcon trainericon=new ImageIcon("member.png");
Image resizedImage6 = trainericon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);

trainerimage=new JButton(resizedIcon6);
trainerimage.setBounds(0,315,50,50);
trainerimage.setBackground(darkOrange);
trainerimage.setBorder(yellow);
trainerimage.setFocusPainted(false);
trainerimage.setContentAreaFilled(false);
panel.add(trainerimage);

  payment =new JButton("PAYMENT");
  payment.setBounds(50,385,130,40);
  payment.setBackground(darkOrange);
  payment.setForeground(Color.BLACK);
  payment.setFont(font);
  payment.setBorder(border);
  payment.setFocusPainted(false);
  payment.setContentAreaFilled(false);
  panel.add(payment);

 
ImageIcon paymenticon=new ImageIcon("coin.png");
Image resizedImage7 = paymenticon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);

paymentimage=new JButton(resizedIcon7);
paymentimage.setBounds(5,385,40,40);
paymentimage.setBackground(darkOrange);
paymentimage.setBorder(yellow);
paymentimage.setFocusPainted(false);
paymentimage.setContentAreaFilled(false);
panel.add(paymentimage);



    logout =new JButton("LOG-OUT");
  logout.setBounds(90,600,110,40);
   logout.setBackground(Color.BLACK);
   logout.setForeground(Color.YELLOW);
  
   logout.setFont(font);
  logout.setBorder(border);
logout.setFocusPainted(false);
// logout.setContentAreaFilled(false);
   panel.add(logout);

ImageIcon logimage=new ImageIcon("logout2.png");
        Image resizedImage5 = logimage.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);

    
        logoutimage=new JButton(resizedIcon5);
        logoutimage.setBounds(40,595,45,45);
          logoutimage.setBackground(darkOrange);
         logoutimage.setBorder(yellow);
logoutimage.setFocusPainted(false);
 logoutimage.setContentAreaFilled(false);

 Font font3=new Font("SansSerif",Font.BOLD,18);

 membername =new JLabel("TRAINER NAME :-");
 membername.setBounds(220,15,220,40);
 membername.setFont(font3);
 
 
membertext=new JTextField();
membertext.setBounds(400,20,150,30);
membertext.setFont(font3);
membertext.setBorder(border);

 mobile =new JLabel("MOBILE NO :- ");
 mobile.setBounds(590,15,220,40);
 mobile.setFont(font3);
 
mobiletext=new JTextField();
mobiletext.setBounds(730,20,150,30);
mobiletext.setFont(font3);
mobiletext.setBorder(border);

gender =new JLabel("GENDER :-");
 gender.setBounds(220,100,220,40);
 gender.setFont(font3);

 
 male=new JRadioButton("MALE");
 male.setBounds(330,95,100,50);
 male.setFont(font3);
 male.setBackground(Color.WHITE);
 male.setFocusPainted(false);

 female=new JRadioButton("FEMALE");
 female.setBounds(450,95,150,50);
 female.setFont(font3);
 female.setBackground(Color.WHITE);
 female.setFocusPainted(false);
  
  b1=new ButtonGroup();
  b1.add(male);
  b1.add(female);

 

add=new JButton("ADD");
add.setBounds(390,320,100,45);
add.setFont(font3);
add.setBackground(Color.BLACK);
add.setForeground(Color.YELLOW);
add.setFocusPainted(false);
 add.setBorder(yellow);

edit=new JButton("EDIT");
edit.setBounds(530,320,100,45);
edit.setFont(font3);
edit.setBackground(Color.BLACK);
edit.setForeground(Color.YELLOW);
edit.setFocusPainted(false);
 edit.setBorder(yellow);

delete=new JButton("DELETE");
delete.setBounds(670,320,100,45);
delete.setFont(font3);
delete.setBackground(Color.BLACK);
delete.setForeground(Color.RED);
delete.setFocusPainted(false);
 delete.setBorder(border2);

 date=new JLabel("DATE :-");
 date.setBounds(230,180,100,50);
 date.setFont(font3);

String[] days = new String[31]; // Initialize the array with size 31

        for (int i = 0; i < 31; i++) {
            int num = i +1; // Since array index starts at 0, we use i+1 to get numbers 1 to 31
            days[i] = String.valueOf(num); // Assign the string representation of num to the array
        }

 day=new JComboBox(days);
 day.setBounds(320,190,50,30);
 day.setBackground(Color.WHITE);
 day.setFont(font3);
 day.setBorder(yellow);

    String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };


   month=new JComboBox(months);
 month.setBounds(380,190,100,30);
 month.setBackground(Color.WHITE);
 month.setFont(font3);
 month.setBorder(yellow);

   String[] years =new String[21];
    int y=2020;

    for(int i=0;i< years.length;i++)
    {
      
      years[i]=String.valueOf(y+i);

    }
   year=new JComboBox(years);
 year.setBounds(500,190,100,30);
 year.setBackground(Color.WHITE);
 year.setFont(font3);
 year.setBorder(yellow);

   pay=new JLabel("PAYMENT :-");
 pay.setBounds(650,95,150,50);
 pay.setFont(font3);

paytext=new JTextField();
paytext.setBounds(780,105,150,30);
paytext.setFont(font3);
paytext.setBorder(border);

Font tablefont=new Font("Arial",Font.BOLD,15);

    String m="mursalin";
        // Sample data for the table
    model = new DefaultTableModel(columnNames, 0);
       

         table = new JTable(model);
JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));
        header.setFont(tablefont);
          header.setBackground(Color.YELLOW);
        header.setForeground(Color.BLACK);
        header.setBorder(null);

        table.setRowHeight(30);
      table.setFont(tablefont);
        table.setBackground(Color.WHITE);
      table.setForeground(Color.BLACK);
      table.setBorder(null);
        // Put the table in a scroll pane
         scrollPane = new JScrollPane(table);



       
 scrollPane.setBounds(220,400,750,250);
         scrollPane.setBackground(Color.WHITE);
          add(scrollPane);


payment.addActionListener(this);
 edit.addActionListener(this);
    trainer.addActionListener(this);
    add.addActionListener(this);
     delete.addActionListener(this);
    logout.addActionListener(this);
    panel.add(logoutimage);
    add(panel);
    add(membername);add(membertext);
    add(mobile);add(mobiletext);
    // add(selecttrainer);add(c1);
    // add(membership);add(c2);
    add(gender);add(male);add(female);
   
    add(date);add(day);add(month);add(year);
    add(pay);add(paytext);
    add(add);add(edit);add(delete);
    
loadtable();
    
        setVisible(true);
    }
    public void loadtable()
    {
      try{
         
   
  rs=st.executeQuery("select * from trainer");
     rsm=rs.getMetaData();

    //  int col=rsm.getColumnCount();
    //  System.out.println(col);
      List<Object[]> dataList = new ArrayList<>();
      
        while (rs.next()) {
            Object[] row = {
                rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5)
            };
            dataList.add(row);
        }

        // Convert the list to a 2D array
        Object[][] data = dataList.toArray(new Object[0][]);


        // Create the table model and the table
          model.setDataVector(data, columnNames);

           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
for (int i = 0; i < table.getColumnCount(); i++) {
    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
 
      }catch(Exception e)
      {
        System.out.println(e);
      }
    }
    public void actionPerformed(ActionEvent ae)
    {
         if(ae.getSource()==payment)
      {
        dispose();
        new payment();
      }
     
      if (ae.getSource() == edit) {
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();

            if (selectedRow == -1 && selectedColumn == -1) {
                JOptionPane.showMessageDialog(this, "Please select a valid row to edit.");
            }

            else {
                selectedRow = table.getSelectedRow();

                Object[] row = new Object[table.getColumnCount()];
                for (int column = 0; column < table.getColumnCount(); column++) {
                    row[column] = table.getValueAt(selectedRow, column);
                }
                for (int column = 0; column < table.getColumnCount(); column++) {
                    model.setValueAt(row[column], selectedRow, column);

                }

                table.setFont(new Font("arial", Font.BOLD, 16));
                table.getCellEditor().stopCellEditing();
                model.fireTableDataChanged();
                table.revalidate();
                table.repaint();
                table.clearSelection();

                String name = (String) table.getValueAt(selectedRow, 0);
                String mobile = (String) table.getValueAt(selectedRow, 1);
                String gender = (String) table.getValueAt(selectedRow, 2);
                String date = (String) table.getValueAt(selectedRow, 3);
                String payment = (String) table.getValueAt(selectedRow, 4);

                try {
                    if (selectedRow != 0) {
                        selectedRow = selectedRow - 1;
                    }
                    String query = "UPDATE trainer SET trainers = '" + name + "',mobile ='" + mobile + "',gender='"
                            + gender
                            + "',date='" + date + "',payment='" + payment + "' " +
                            "WHERE ctid = (SELECT ctid FROM trainer ORDER BY ctid LIMIT 1 OFFSET " + selectedRow + ")";
                    st.executeUpdate(query);
                    if (selectedRow != 0) {
                        System.out.println("Row " + (selectedRow + 1) + " updated successfully.");
                    } else {
                        System.out.println("Row " + (selectedRow) + " updated successfully.");

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
      if(ae.getSource()==logout)
      {
        dispose();
        new GymManagement();
      }
      if(ae.getSource()==trainer)
      {
        dispose();
        new home();
      }
              if(ae.getSource()==add)
        {
          try
          {
           String trainer,mobile,gender,date,payment;
           trainer=membertext.getText();
           mobile=mobiletext.getText();
           if(male.isSelected())
           {
            gender="male";
           }           
           else
           {
            gender="female";
           }
           
           
           payment=paytext.getText();
           date=(String)day.getSelectedItem()+"/"+(String)month.getSelectedItem()+"/"+(String)year.getSelectedItem();
          if (trainer != null && !trainer.isEmpty() &&
    mobile != null && !mobile.isEmpty() &&
    gender != null && !gender.isEmpty() &&
    payment != null && !payment.isEmpty() &&
    date != null && !date.isEmpty())
          {
         
 int k= st.executeUpdate("insert into trainer values('"+trainer+"','"+mobile+"','"+gender+"','"+date+"','"+payment+"');");
         loadtable();
          }
          else{
             JOptionPane.showMessageDialog(null, "Fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
          }
          }catch(Exception e)
          {
            System.out.println(e);
          } 
        }
        if(ae.getSource()==delete)
        {
          try{
          int selectedrow=table.getSelectedRow();
          if(selectedrow>=0)
          {
            String mem2=(String)table.getValueAt(selectedrow,0);

            int z=st.executeUpdate("delete from trainer where trainers='"+mem2+"'");

            model.removeRow(selectedrow);
               table.revalidate();
                    table.repaint();
            
          }
          else
          {
             JOptionPane.showMessageDialog(null, "select the row to delete", "Error", JOptionPane.ERROR_MESSAGE);
          }
          }catch(Exception e)
          {
            System.out.println(e);
          }
        }
    }

}
class payment extends JFrame implements ActionListener
{
    JLabel home,members,membername,mobile,address,selecttrainer,membership;
     JLabel gender,date,pay;
     JButton trainer,payment,logout,logoutimage,trainerimage,paymentimage;
     JButton add,edit,delete;
     Color darkOrange ;
     JTextField membertext,mobiletext,paytext;
     JComboBox c1,c2,day,month,year;
     JRadioButton male,female;
       JTable table;
       JScrollPane scrollPane,scrollPane1;
        
     ButtonGroup b1;
     Connection con;
     Statement st;
     ResultSet rs;
     ResultSetMetaData rsm;
      DefaultTableModel model;
        String[] columnNames = {
            "Members", "Amount", "Pay-Date"};
          ArrayList<String> stringList;
    payment()
    {
        try
     {

      Class.forName("org.postgresql.Driver");
      con=DriverManager.getConnection("jdbc:postgresql://localhost/gym","postgres","mursalin@123");
      st=con.createStatement();

     }catch(Exception e)
     {
      System.out.println(e);
     }

        setSize(1000,700);
         setLocationRelativeTo(null);
         setBackground(Color.WHITE);
         getContentPane().setBackground(Color.WHITE);
        setTitle("GymManagement");
        setLayout(null);


        darkOrange = new Color(255, 140, 0);
    JPanel panel=new JPanel();
    panel.setLayout(null);
    panel.setBounds(0,0,200,700);
    panel.setBackground(Color.YELLOW);

    
 Font font=new Font("Arial",Font.BOLD,20);
 LineBorder border = new LineBorder(Color.BLACK, 2); 
 LineBorder border2 = new LineBorder(Color.RED, 2); 
 LineBorder yellow = new LineBorder(Color.YELLOW, 2); 
 LineBorder white = new LineBorder(Color.WHITE, 2); 

   

   ImageIcon icon=new ImageIcon("coin.png");
        Image resizedImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

  home=new JLabel();
  home.setIcon(resizedIcon);
  home.setBounds(60,20,100,80);
  panel.add(home);

  Font font2=new Font("Arial",Font.BOLD,30);

  members=new JLabel("PAYMENT");
  members.setBounds(25,110,200,30);
  members.setFont(font2);
  panel.add(members);



  trainer =new JButton("MEMBERS");
  trainer.setBounds(50,320,130,40);
  trainer.setBackground(Color.YELLOW);
   trainer.setForeground(Color.BLACK);
   trainer.setFont(font);
  trainer.setBorder(border);
  trainer.setFocusPainted(false);
 // trainer.setContentAreaFilled(false);
   panel.add(trainer);

ImageIcon trainericon=new ImageIcon("member.png");
        Image resizedImage6 = trainericon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);

     trainerimage=new JButton(resizedIcon6);
        trainerimage.setBounds(0,315,50,50);
          trainerimage.setBackground(darkOrange);
         trainerimage.setBorder(yellow);
trainerimage.setFocusPainted(false);
 trainerimage.setContentAreaFilled(false);
 panel.add(trainerimage);

   payment =new JButton("TRAINERS");
  payment.setBounds(50,385,130,40);
  payment.setBackground(darkOrange);
   payment.setForeground(Color.BLACK);
   payment.setFont(font);
  payment.setBorder(border);
payment.setFocusPainted(false);
  payment.setContentAreaFilled(false);
   panel.add(payment);

 
ImageIcon paymenticon=new ImageIcon("trainer.png");
        Image resizedImage7 = paymenticon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);

     paymentimage=new JButton(resizedIcon7);
        paymentimage.setBounds(5,385,40,40);
          paymentimage.setBackground(darkOrange);
         paymentimage.setBorder(yellow);
paymentimage.setFocusPainted(false);
 paymentimage.setContentAreaFilled(false);
 panel.add(paymentimage);



    logout =new JButton("LOG-OUT");
  logout.setBounds(90,600,110,40);
   logout.setBackground(Color.BLACK);
   logout.setForeground(Color.YELLOW);
  
logout.setFont(font);
logout.setBorder(border);
logout.setFocusPainted(false);
   panel.add(logout);

ImageIcon logimage=new ImageIcon("logout2.png");
        Image resizedImage5 = logimage.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);

    
        logoutimage=new JButton(resizedIcon5);
        logoutimage.setBounds(40,595,45,45);
          logoutimage.setBackground(darkOrange);
         logoutimage.setBorder(yellow);
logoutimage.setFocusPainted(false);
 logoutimage.setContentAreaFilled(false);

 Font font3=new Font("SansSerif",Font.BOLD,18);

 membername =new JLabel("ENTER AMOUNT :-");
 membername.setBounds(220,80,220,40);
 membername.setFont(font3);
 
 
membertext=new JTextField();
membertext.setBounds(400,85,150,30);
membertext.setFont(font3);
membertext.setBorder(border);

add=new JButton("ADD");
add.setBounds(390,270,100,45);
add.setFont(font3);
add.setBackground(Color.BLACK);
add.setForeground(Color.YELLOW);
add.setFocusPainted(false);
 add.setBorder(yellow);

edit=new JButton("EDIT");
edit.setBounds(530,270,100,45);
edit.setFont(font3);
edit.setBackground(Color.BLACK);
edit.setForeground(Color.YELLOW);
edit.setFocusPainted(false);
 edit.setBorder(yellow);

delete=new JButton("DELETE");
delete.setBounds(670,270,100,45);
delete.setFont(font3);
delete.setBackground(Color.BLACK);
delete.setForeground(Color.RED);
delete.setFocusPainted(false);
 delete.setBorder(border2);

 date=new JLabel("PAID DATE :-");
 date.setBounds(340,165,150,50);
 date.setFont(font3);

String[] days = new String[31]; 

        for (int i = 0; i < 31; i++) {
            int num = i +1; 
            days[i] = String.valueOf(num); 
        }

 day=new JComboBox(days);
 day.setBounds(470,175,50,30);
 day.setBackground(Color.WHITE);
 day.setFont(font3);
 day.setBorder(yellow);

    String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };


   month=new JComboBox(months);
 month.setBounds(535,175,100,30);
 month.setBackground(Color.WHITE);
 month.setFont(font3);
 month.setBorder(yellow);

   String[] years =new String[21];
    int y=2020;

    for(int i=0;i< years.length;i++)
    {
      
      years[i]=String.valueOf(y+i);

    }
   year=new JComboBox(years);
 year.setBounds(650,175,100,30);
 year.setBackground(Color.WHITE);
 year.setFont(font3);
 year.setBorder(yellow);

   pay=new JLabel("MEMBERS:-");
 pay.setBounds(650,75,150,50);
 pay.setFont(font3);

 try{
         rs=st.executeQuery("select * from members");
           stringList = new ArrayList<>();
        
         while(rs.next())
         {
          stringList.add(rs.getString(1)); 
         }
    }catch(Exception e)
    {
      System.out.println(e);
    }

  String[] values = stringList.toArray(new String[0]);
 

 c1=new JComboBox(values);
 c1.setBounds(780,85,150,30);
 c1.setBackground(Color.WHITE);
 c1.setFont(font3);
 c1.setBorder(yellow);

Font tablefont=new Font("Arial",Font.BOLD,15);

    String m="mursalin";
       
    model = new DefaultTableModel(columnNames, 0);
       

         table = new JTable(model);
JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));
        header.setFont(tablefont);
          header.setBackground(Color.YELLOW);
        header.setForeground(Color.BLACK);
        header.setBorder(null);

        table.setRowHeight(30);
      table.setFont(tablefont);
        table.setBackground(Color.WHITE);
      table.setForeground(Color.BLACK);
      table.setBorder(null);
        // Put the table in a scroll pane
         scrollPane = new JScrollPane(table);



       
 scrollPane.setBounds(250,350,700,300);
         scrollPane.setBackground(Color.WHITE);
          add(scrollPane);


payment.addActionListener(this);
 edit.addActionListener(this);
    trainer.addActionListener(this);
    add.addActionListener(this);
     delete.addActionListener(this);
    logout.addActionListener(this);
    panel.add(logoutimage);
    add(panel);
    add(membername);add(membertext);
 
   
    add(date);add(day);add(month);add(year);
     add(pay);//add(paytext);
    add(add);add(edit);add(delete);
     add(c1);

     loadtable();
        setVisible(true);

    }
     public void loadtable()
    {
      try{
         
   
  rs=st.executeQuery("select * from payment");
     rsm=rs.getMetaData();

    //  int col=rsm.getColumnCount();
    //  System.out.println(col);
      List<Object[]> dataList = new ArrayList<>();
      
        while (rs.next()) {
            Object[] row = {
                rs.getString(1), rs.getString(2), rs.getString(3)
            };
            dataList.add(row);
        }

        // Convert the list to a 2D array
        Object[][] data = dataList.toArray(new Object[0][]);


        // Create the table model and the table
          model.setDataVector(data, columnNames);

           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
for (int i = 0; i < table.getColumnCount(); i++) {
    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}
             }catch(Exception e)
      {
        System.out.println(e);
      }
    }
     public void actionPerformed(ActionEvent ae)
     {
         if(ae.getSource()==trainer)
      {
        dispose();
        new home();
      }
       if(ae.getSource()==payment)
      {
        dispose();
        new trainer();
      }
      if(ae.getSource()==add)
      {
        try{
          String amount,member,date;

          amount=membertext.getText();
          date=(String)day.getSelectedItem()+"/"+(String)month.getSelectedItem()+"/"+(String)year.getSelectedItem();
          member=(String)c1.getSelectedItem();

           if (amount != null && !amount.isEmpty() &&
    date != null && !date.isEmpty() &&
    member != null && !member.isEmpty())
          {
         
 int k= st.executeUpdate("insert into payment values('"+member+"','"+amount+"','"+date+"');");
         loadtable();
          }
          else{
             JOptionPane.showMessageDialog(null, "Fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
          }



        }catch(Exception e)
        {
           System.out.println(e);
        }
      }
        if(ae.getSource()==delete)
        {
          try{
          int selectedrow=table.getSelectedRow();
          if(selectedrow>=0)
          {
            String mem2=(String)table.getValueAt(selectedrow,0);

            int z=st.executeUpdate("delete from payment where members='"+mem2+"'");

            model.removeRow(selectedrow);
               table.revalidate();
                    table.repaint();
            
          }
          else
          {
             JOptionPane.showMessageDialog(null, "select the row to delete", "Error", JOptionPane.ERROR_MESSAGE);
          }
          }catch(Exception e)
          {
            System.out.println(e);
          }
        }

if (ae.getSource() == edit) {
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();

            if (selectedRow == -1 && selectedColumn == -1) {
                JOptionPane.showMessageDialog(this, "Please select a valid row to edit.");
            }

            else {
                selectedRow = table.getSelectedRow();

                Object[] row = new Object[table.getColumnCount()];
                for (int column = 0; column < table.getColumnCount(); column++) {
                    row[column] = table.getValueAt(selectedRow, column);
                }
                for (int column = 0; column < table.getColumnCount(); column++) {
                    model.setValueAt(row[column], selectedRow, column);

                }

                table.setFont(new Font("arial", Font.BOLD, 16));
                table.getCellEditor().stopCellEditing();
                model.fireTableDataChanged();
                table.revalidate();
                table.repaint();
                table.clearSelection();

                String name = (String) table.getValueAt(selectedRow, 0);
               
                String payment = (String) table.getValueAt(selectedRow, 1);
                 String date = (String) table.getValueAt(selectedRow, 2);

                try {
                    if (selectedRow != 0) {
                        selectedRow = selectedRow - 1;
                    }
                    String query = "UPDATE payment SET members = '" + name + "',amount ='" + payment + 
                             "',paydate='" + date +"' " +
                            "WHERE ctid = (SELECT ctid FROM payment ORDER BY ctid LIMIT 1 OFFSET " + selectedRow + ")";
                    st.executeUpdate(query);
              
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        if(ae.getSource()==logout)
        {
          dispose();
          new GymManagement();
        }


     }

    public static void main(String arg[])
    {
      new payment();
    }

}