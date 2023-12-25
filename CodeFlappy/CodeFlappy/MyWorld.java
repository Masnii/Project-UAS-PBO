import greenfoot.*; 

public class MyWorld extends World
{
    public static int KECEPATAN=2,KONDISI=0,HIGHSCORE=0;
    
    private Message message;
    
    private int NumCelah=6,PointerCelah=0,IndexCelah=0,FINISH=100,deltacelah=150;
    public int mscore=0;
    private int[] PosCelah=new int[NumCelah];
    private Tiang[] tiang=new Tiang[2*NumCelah];
    private Score score=new Score();
    private TutupBawah tutupbawah;
    private TutupAtas tutupatas;
    
    private int[] DataCelah=new int[]{
          202,59,157,67,209,60,162,61,111,57,174,47,119,59,127,74,145,54,120,55,146,69,195,72,115,48,201,59,288,47,296,60,274,58,202,75,253,54,249,75,149,72,146,41,106,41,128,74,226,52,187,68,194,46,126,40,183,51,261,77,231,73,273,55,275,44,288,62,219,44,181,58,130,58,142,66,145,42,113,66,142,72,145,51,83,51,110,51,152,41,62,66,97,77,119,41,216,75,197,59
    };
    
    public MyWorld()
    {    
        super(600,512,1,false); 
        Greenfoot.setSpeed(50);
        
        KONDISI=-15;
        PointerCelah=getWidth();
        mscore=0;
        FINISH=(int)(0.5*getWidth())+deltacelah*((int)(1+DataCelah.length/2));
        
        GreenfootImage img=new GreenfootImage("background.png");
        GreenfootImage image=new GreenfootImage(getWidth(),getHeight());
        int i=0;
        while(i<getWidth()){
            image.drawImage(img,i,0);
            i+=img.getWidth();
        };
        setBackground(image);
        
        message=new Message();
        addObject(message,(int)(0.5*getWidth()),(int)(0.5*getHeight()));
        
        for(int j=0;j<NumCelah;j++){
            setNewCelah(j);
        }
        
        int d=200;
        i=0;
        while(i<=getWidth()+2*d){
            addObject(new Lantai(),i,getHeight()-20);
            i+=d;
        };  
        Lantai.plg=i-d;
                
        addObject(new Bird(),(int)(0.5*getWidth()),(int)(0.5*getHeight()));
        addObject(score,(int)(0.5*getWidth()),50);
        score.setScore(mscore);
        
        tutupbawah=new TutupBawah();
        tutupatas=new TutupAtas();
        addObject(tutupatas,-1000,(int)(0.5*getHeight()-50));
        addObject(tutupbawah,-1000,(int)(0.5*getHeight()+50));
    }
    
    public void addScore()
    {
        mscore++;
        score.setScore(mscore);
        Greenfoot.playSound("point.wav");
    }
    
    private void setNewCelah(int id)
    {
        if(IndexCelah>=(int)(1.0*DataCelah.length/2)){
            return;
        }
        PointerCelah+=deltacelah;
        PosCelah[id]=PointerCelah;
        
        int h=320;
        int pydown=DataCelah[2*IndexCelah+0]+(int)(0.5*h+DataCelah[2*IndexCelah+1]);
        int pyup=DataCelah[2*IndexCelah+0]-(int)(0.5*h+DataCelah[2*IndexCelah+1]);
        
        if(tiang[2*id+0]==null){
            tiang[2*id+0]=new Tiang();
            addObject(tiang[2*id+0],PosCelah[id],pydown);
        }else tiang[2*id+0].setLocation(PosCelah[id],pydown);
        tiang[2*id+0].SETNILAI=false;
        
        if(tiang[2*id+1]==null){
            tiang[2*id+1]=new Tiang();
            addObject(tiang[2*id+1],PosCelah[id],pyup);
            tiang[2*id+1].setRotation(180);
        }else tiang[2*id+1].setLocation(PosCelah[id],pyup);
        
        IndexCelah++;
    }
    
    public void setKemenangan()
    {
        tutupatas.setMode(1);
        gameover();KONDISI=5;
    }
    
    public void gameover()
    {
        score.setLocation(-1000,score.getY());
        tutupatas.setLocation((int)(0.5*getWidth()),tutupatas.getY());
        tutupbawah.setLocation((int)(0.5*getWidth()),tutupbawah.getY());
        if(HIGHSCORE<mscore)HIGHSCORE=mscore;
        KONDISI=2;
    }
    
    public void generateCelah()
    {
        int po=(int)(0.5*getHeight());
        for(int i=0;i<50;i++){
            int px=60+Greenfoot.getRandomNumber(240);
            while(Math.abs(px-po)>100){
                px=60+Greenfoot.getRandomNumber(240);
            }
            System.out.print(px+",");
            System.out.print(40+Greenfoot.getRandomNumber(40)+",");
            po=px;
        }
    }
    
    public void act()
    {
        if(KONDISI<0)KONDISI++;
        if(KONDISI==0 && Greenfoot.mouseClicked(null)){
            removeObject(message);
            KONDISI++;
        }
        if(KONDISI==1){
            for(int j=0;j<NumCelah;j++){
                PosCelah[j]-=KECEPATAN;
                if(PosCelah[j]<0.5*getWidth() && tiang[2*j+0]!=null && !tiang[2*j+0].SETNILAI){
                    tiang[2*j+0].SETNILAI=true;
                    addScore();
                }
                if(PosCelah[j]<-100)setNewCelah(j);
            }
            PointerCelah-=KECEPATAN;
            FINISH-=KECEPATAN;
            if(FINISH<0)setKemenangan();
        }
        if(KONDISI==4 && Greenfoot.mouseClicked(null)){
            KONDISI=2;
        }
    }
}
