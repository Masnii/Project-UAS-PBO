import greenfoot.*; 

public class Tiang extends Actor
{
    public boolean SETNILAI=false;
    public void act() 
    {
        if(MyWorld.KONDISI>0){
            int px=getX()-MyWorld.KECEPATAN;
            setLocation(px,getY());            
        }
    }    
}
