// Jeffrey W GARIP  JAVA AND WEBDESIGN N# 18881932 NetID jwg327
package jg.midtermquestion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static jg.midtermquestion.midtermQuestion.Left;
import static jg.midtermquestion.midtermQuestion.Rbox;

class Question
{
   String question;
   String left;
   String right;
   Question(){ question="";left="True"; right="False";}
   Question(String Q1, String L, String R)
   {
    question=Q1;
    left=L;
    right=R;
    }
  
    public void Display(){System.out.println(this.getQuestion());}
    public String getQuestion(){return question;}
    public String getleft(){return left;}
    public String getRight(){return question;}
}



class Timer extends Thread
{
   
      @Override
public void run(){
     //while(true){     
    //for(int i=0; i<midtermQuestion.Qbox.length;i++)
    //{       //midtermQuestion.txt.setText(midtermQuestion.Qbox[i].question);
            //midtermQuestion.Left.setText(midtermQuestion.Qbox[i].left);
            //midtermQuestion.Right.setText(midtermQuestion.Qbox[i].right);
            //midtermQuestion.Left.setText(midtermQuestion.Qbox[i].left);
            //midtermQuestion.Right.setText(midtermQuestion.Qbox[i].right);
            //System.out.println(midtermQuestion.Qbox[i].getQuestion());
            //System.out.println(midtermQuestion.Qbox[i].getleft());
            //System.out.println(midtermQuestion.Qbox[i].getRight());
         //   if(midtermQuestion.LeftClicked==true){
           ///   midtermQuestion.Rbox[i]=midtermQuestion.Qbox[i].left;
            //}
            //if(midtermQuestion.RightClicked==true){
              //midtermQuestion.Rbox[i]=midtermQuestion.Qbox[i].right;
            
            //} 
            
      try{ Thread.sleep(5000);}
      catch( InterruptedException e){}
       if (midtermQuestion.mytimer==this){
         String Answers=""; 
        for(int j=0;j<midtermQuestion.Rbox.length;j++)
         {
             //System.out.println("WORKING");
             System.out.println(midtermQuestion.Rbox[j]);
             Answers+=midtermQuestion.Rbox[j]+",";}
  
             midtermQuestion.txt.setText(Answers);
            }
    
      }   
}
//}/// 


 
/// TIMER 

/// button timer 

  class ButtonListener implements ActionListener{
  
    @Override
    public void actionPerformed(ActionEvent aevt  )
    {
          JButton dpbutton= (JButton) aevt.getSource();
         if( dpbutton== midtermQuestion.Left)
         {    midtermQuestion.LeftClicked=true;
            midtermQuestion.Rbox[midtermQuestion.clickedCTR-1]=midtermQuestion.Qbox[midtermQuestion.clickedCTR-1].left;
           
         }
        if(dpbutton== midtermQuestion.Right){ 
             midtermQuestion.RightClicked=true;
            midtermQuestion.Rbox[midtermQuestion.clickedCTR-1]=midtermQuestion.Qbox[midtermQuestion.clickedCTR-1].right;
         
        }
        
        if( midtermQuestion.clickedCTR==4)
        { 
          midtermQuestion.LeftClicked=true;
          midtermQuestion.RightClicked=true;
         midtermQuestion.Left.setEnabled(false);
         midtermQuestion.Right.setEnabled(false);
         String Answer = "";
            for (int J = 0; J<midtermQuestion.Rbox.length; ++J) 
            {
               Answer +=  midtermQuestion.Rbox[J] + ",";
            }
            midtermQuestion.txt.setText(Answer);  
          
        }
    
         ++midtermQuestion.clickedCTR;
         midtermQuestion.txt.setText(midtermQuestion.Qbox[midtermQuestion.clickedCTR-1].question);
         midtermQuestion.Left.setText(midtermQuestion.Qbox[midtermQuestion.clickedCTR-1].left);
         midtermQuestion.Right.setText(midtermQuestion.Qbox[midtermQuestion.clickedCTR-1].right);
         midtermQuestion.mytimer = new Timer();
         midtermQuestion.mytimer.start();
      
 }
    
   }
































public class midtermQuestion{
    static JLabel txt;
     static Question[] Qbox;
     static String[] Rbox;
     static JButton Left;
      static JButton Right;
      static boolean LeftClicked;
      static boolean RightClicked;
      static Timer mytimer;
      static int clickedCTR;
     
public static void main(String[] args)
{
    clickedCTR=1;
    Qbox=new Question[5];
    Rbox=new String[]{"NO RESPONCE","NO RESPONCE","NO RESPONCE","NO RESPONCE","NO RESPONCE"};
    Qbox[0]= new Question("Favorite Ice Cream","Vanilla","Chocolate");
    Qbox[1]= new Question("Which season is better","Winter","Summer");
    Qbox[2]= new Question("Which pet is better","Cat","Dog");
    Qbox[3]= new Question("Unicorns are real","True","False");
    Qbox[4]= new Question("Text or Call","Text","Call");
    LeftClicked=false;
    RightClicked=false;
    Left= new JButton(Qbox[0].left);
    Right=new JButton(Qbox[0].right);
    ButtonListener lst=new ButtonListener(); 
    Left.addActionListener(lst);
    Right.addActionListener(lst);
    for(int i=0;i<5;i++){Qbox[i].Display();}
    txt=new JLabel(Qbox[0].getQuestion(),SwingConstants.LEFT);
    JFrame QuestionFrame=new JFrame("Midterm question");
    QuestionFrame.setDefaultCloseOperation(QuestionFrame.EXIT_ON_CLOSE);
    JPanel Dapanel=new JPanel();
    Dapanel.setLayout(new GridLayout(2,2,50,50));
    Dapanel.add(txt,SwingConstants.CENTER);
    Dapanel.add(new JLabel("", SwingConstants.CENTER ));
    Dapanel.add(Left);
    Dapanel.add(Right);
    QuestionFrame.setSize(400,400);
    QuestionFrame.add(Dapanel);
    QuestionFrame.setVisible(true);
    midtermQuestion.mytimer = new Timer(); 
    midtermQuestion.mytimer.start();
  
}
   
}

