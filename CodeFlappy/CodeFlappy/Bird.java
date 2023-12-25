import greenfoot.*; 

public class Bird extends Actor
{
    private GreenfootImage img0,img1,img2;
    private int arah=1,posisi=0,jeda=0,rotate=0;
    private double py=0,vy=0,gy=10,dt=0.2;
    
    public boolean DIE=false;
    
    public void addedToWorld(World latar)
    {
        img0=new GreenfootImage("downflap.png");
        img1=new GreenfootImage("midflap.png");
        img2=new GreenfootImage("upflap.png");
        setImage(img0);
        
        py=getY();
    }
    
    public void act() 
    {        
        int minv=23;
        if(!DIE && getOneIntersectingObject(Tiang.class)!=null){
            vy=-Greenfoot.getRandomNumber(50);
            DIE=true;
            Greenfoot.playSound("hit.wav");
        }
        
        if(jeda==0){
            if(!DIE){
                posisi=(arah>0)?(posisi+1):(posisi-1);
                if(posisi==0){
                    setImage(img0);
                    arah*=-1;
                }else if(posisi==1){
                    setImage(img1);
                }else if(posisi==2){
                    setImage(img2);
                    arah*=-1;
                }
            }
            jeda=7;
        }else jeda--;
        
        if(MyWorld.KONDISI<=0 || MyWorld.KONDISI>=5)return;
        
        if(!DIE && Greenfoot.mouseClicked(null)){
            vy=-minv;
        }
                
        py+=vy*dt+0.5*gy*dt*dt;
        vy+=gy*dt;
        int bts=getWorld().getHeight()-75;
        if(py>bts){
            py=bts;
            setLocation(getX()-MyWorld.KECEPATAN,getY());
            DIE=true;
            if(((MyWorld)getWorld()).KONDISI==1){
                ((MyWorld)getWorld()).gameover();
                Greenfoot.playSound("die.wav");
            }
            return;
        }
        int minrot=-20;
        rotate=minrot+(int)(135.0*(vy-minv)/(1.5*minv));
        if(rotate<minrot)rotate=minrot;
        if(rotate>90)rotate=90;
        setLocation(getX(),(int)py);
        setRotation(rotate);
    }    
}
