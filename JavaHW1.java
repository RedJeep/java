/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahw1;

/**
 *
 * @author Jeff Garip
 */
 
class Ingredient
{
 Ingredient(double measure,String item){ Measurement=measure; Item=item;}
 public String getItem(){return Item;}
 void setItem(String ItemAlterd){Item=ItemAlterd;}
 public double getMeasurement(){return Measurement;}
 double Measurement;
 String Item;
 }

class Recipe{
 Ingredient[] ibox;
 String[] CSteps;
 // add method to be implemented in future
 Recipe(Ingredient[] Ibox,String[] cbox){
     this.ibox=Ibox;
     this.CSteps=cbox;
 }
  @Override
    public String toString()
    {
        String ingredientNsteps="";
        String ingredient="";
        String Step="";
        for(int i=0;i<this.ibox.length;i++)
        {
         ingredient+= this.ibox[i].Item+ "." +"\r\n\r\n";
        
        }
       
        
      for(int j=0; j<CSteps.length;j++)
      {
          Step+=CSteps[j]+"\r\n";
      }
   
      ingredientNsteps=ingredient+Step;
      return ingredientNsteps;
    }
     
     

 }







public class JavaHW1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println(" Igredients and Recipe ");
        System.out.println("");
        Ingredient[] myIbox={ 
            new Ingredient(1.0,"Salted butter"), 
            new Ingredient(1.0,"White sugar"),
            new Ingredient(1.0,"Light brown sugar"),
            new Ingredient(2.0,"vanila extract"),
            new Ingredient(2.0,"large eggs"),
            new Ingredient(3.0,"flour"),
            new Ingredient(1.0,"baking soda"),
            new Ingredient(0.5,"baking powder"),
            new Ingredient(1.0,"sea salt"),
            new Ingredient(2.0,"choclate chip")};
        
        
        
        String[] Steps={"Preheat oven to 375 degrees F.", 
            "Line a baking pan with parchment paper and set aside",
            "Set aside In a separate bowl mix flour, baking soda, salt, baking powder.",
            "Set aside Cream together butter and sugars until combined. ",
            "Beat in eggs and vanilla until fluffy.",
            "Mix in the dry ingredients until combined.",
            "Add 12 oz package of chocolate chips and mix well",
            "Roll 2-3 ",
            "Bake in preheated oven for approximately 8-10 minutes.",
            "Let them sit on the baking pan for 2 minutes before removing to cooling rack."};
        
         Recipe myRecipe=new Recipe(myIbox,Steps);

         String val=myRecipe.toString();
         System.out.println(val);
     
    }
}
