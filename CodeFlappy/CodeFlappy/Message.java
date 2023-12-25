import greenfoot.*;  

public class Message extends Actor
{
    private int arah=-1,hitung=0,panjang=15,jeda=0;
    public void act() 
    {
        if(hitung==panjang){
            hitung=0;
            arah*=-1;
        }
        if(jeda>0){
            jeda--;
        }else{
            hitung++;
            jeda=5;
            setLocation(getX(),getY()+arah);
        }
    }    
}
