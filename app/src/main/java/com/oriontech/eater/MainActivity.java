package com.oriontech.eater;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oriontech.eater.Model.Fruit;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tvScore,tvStart;
    private ImageView ivEater,ivOrange,ivApple,ivAnahtar,ivPapaya,ivPeach,ivPomegranetes,ivApricot,ivBanana,ivCherry,ivManderin;
    FrameLayout flFrame;
    //Positon
    private int eaterY;
    private int appleX,appleY,orangeX,orangeY,anahtarX,anahtarY,papayaX,papayaY,peachX,peachY,apricotX,apricotY,bananaX,bananaY,cherryX,cherryY,mandarinX,mandarinY;
    //size
    private int frameHeigth;
    private int eaterSize;
    private int screenWidth;
    private int screenHaight;
    //Classes
    private Handler handler;
    private Timer timer;
    //Status Check
    private boolean startFlag=false;
    private boolean actionFlag=false;

    AnimationDrawable eatingEater;

    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        flFrame=findViewById(R.id.flFrame);
        tvScore=findViewById(R.id.tvScore);
        tvStart= findViewById(R.id.tvStart);
        ivEater=findViewById(R.id.ivEater);
        ivApple=findViewById(R.id.ivApple);
        ivOrange=findViewById(R.id.ivOrange);
        ivAnahtar=findViewById(R.id.ivAnahtar);
        ivApricot=findViewById(R.id.ivApricot);
        ivBanana=findViewById(R.id.ivBanana);
        ivCherry=findViewById(R.id.ivCherry);
        ivManderin=findViewById(R.id.ivManderin);
        ivPapaya=findViewById(R.id.ivPapaya);
        ivPeach=findViewById(R.id.ivPeach);
        ivPomegranetes=findViewById(R.id.ivPomegranetes);

        //Animation for  Eater
        ivEater.setImageResource(R.drawable.pacman);
        eatingEater =  (AnimationDrawable) ivEater.getDrawable();
        eatingEater.start();

        //Initialize Class
        handler = new Handler();
        timer = new Timer();

        //get screen size
        screenHaight= getScreenSize().y;
        screenWidth=getScreenSize().x;
        //Ekran dışına taşıyoruz
        setStartPositionToFruits();

        tvScore.setText("Score : 0");
    }
    //meyvelerin ilk pozisyonun kran dışına taşır
    private void setStartPositionToFruits(){
        ivApple.setX(-80);
        ivApple.setY(-80);
        ivOrange.setX(-80);
        ivOrange.setY(-80);
        ivAnahtar.setX(-80);
        ivAnahtar.setY(-80);
        ivPapaya.setX(-80);
        ivPapaya.setY(-80);
        ivPeach.setX(-80);
        ivPeach.setY(-80);
        ivPomegranetes.setX(-80);
        ivPomegranetes.setY(-80);
        ivApricot.setX(-80);
        ivApricot.setY(-80);
        ivBanana.setX(-80);
        ivBanana.setY(-80);
        ivCherry.setX(-80);
        ivCherry.setY(-80);
        ivManderin.setX(-80);
        ivManderin.setY(-80);
    }
    //ekran boyutlarını getirmek için. Her telefon farklı boyutta ve bunun için ihtiyaç var
    private Point getScreenSize() {
        WindowManager wm = getWindowManager();
        Display display=wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(startFlag==false){
            startFlag=true;
            tvStart.setVisibility(View.GONE);

            frameHeigth=flFrame.getHeight();    //y ekseni boyutu frame'in yüksekliği kadardır
            eaterY=(int) ivEater.getY();    //eater'ın o anki pozisyonu
            eaterSize=ivEater.getHeight();  //eater'in yükseliği, kare olduğu için aynı zamanda gemişliği

            //timer'ı başlatıyoruz,bekleme=0, period=20. her 20 ms'de changePos çalışır.
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            },0,20);

        }else {//startflag= true olduğundan if sonrası else çalışır
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                actionFlag=true;
            }else if(event.getAction()==MotionEvent.ACTION_UP){
                actionFlag=false;
            }
        }
        return true;

    }
    public void changePos(){
        hitCheck();
        //respawn apple
        appleX-=10;
        if(appleX<0){
            appleX=screenWidth+20;
            appleY=(int) Math.floor(Math.random()*(frameHeigth-ivApple.getHeight()));
        }
        ivApple.setX(appleX);
        ivApple.setY(appleY);
        //respawn orange
        orangeX-=16;
        if(orangeX<0){
            orangeX=screenWidth+10;
            orangeY=(int) Math.floor(Math.random()*(frameHeigth-ivOrange.getHeight()));
        }
        ivOrange.setX(orangeX);
        ivOrange.setY(orangeY);
        //respawn papaya
        papayaX-=16;
        if(papayaX<0){
            papayaX=screenWidth+10;
            papayaY=(int) Math.floor(Math.random()*(frameHeigth-ivPapaya.getHeight()));
        }
        ivPapaya.setX(papayaX);
        ivPapaya.setY(papayaY);
        //respawn peach
        peachX-=16;
        if(peachX<0){
            peachX=screenWidth+10;
            peachY=(int) Math.floor(Math.random()*(frameHeigth-ivPeach.getHeight()));
        }
        ivPeach.setX(peachX);
        ivPeach.setY(peachY);
        //respawn orange
        orangeX-=16;
        if(orangeX<0){
            orangeX=screenWidth+10;
            orangeY=(int) Math.floor(Math.random()*(frameHeigth-ivOrange.getHeight()));
        }
        ivOrange.setX(orangeX);
        ivOrange.setY(orangeY);
        //respawn orange
        orangeX-=16;
        if(orangeX<0){
            orangeX=screenWidth+10;
            orangeY=(int) Math.floor(Math.random()*(frameHeigth-ivOrange.getHeight()));
        }
        ivOrange.setX(orangeX);
        ivOrange.setY(orangeY);
        //respawn orange
        orangeX-=16;
        if(orangeX<0){
            orangeX=screenWidth+10;
            orangeY=(int) Math.floor(Math.random()*(frameHeigth-ivOrange.getHeight()));
        }
        ivOrange.setX(orangeX);
        ivOrange.setY(orangeY);
        //respawn anahtar
        anahtarX-=20;
        if(anahtarX<0){
            anahtarX=screenWidth+500;
            anahtarY=(int) Math.floor(Math.random()*(frameHeigth-ivAnahtar.getHeight()));
        }
        ivAnahtar.setX(anahtarX);
        ivAnahtar.setY(anahtarY);
        //MovePos
        if(actionFlag){
            //Touching
            eaterY-=20;
        }else {
            //Relasing
            eaterY+=20;
        }
        //check eater position
        //yukarı sınırı geçmesin
        if(eaterY<0)
            eaterY=0;
        //aşağı sınırı geçmesin
        if(eaterY>frameHeigth-eaterSize)
            eaterY=frameHeigth-eaterSize;

        ivEater.setY(eaterY);
    }
    //Eater ile meyve çarpışmasını kontrol eder
    private void hitCheck() {
        //eğer eater meyvenin ortasıve daha fazlasına değerse puan alır. Her meyve için farklı puan tanımlanabilir
        //meyvenin x ve y'de orta noktaları
        int appleCenterX = appleX + ivApple.getWidth()/2;
        int appleCenterY = appleY + ivApple.getHeight()/2;
        int orangeCenterX = orangeX + ivOrange.getWidth()/2;
        int orangeCenterY = orangeY + ivOrange.getHeight()/2;
        int anahtarCenterX = anahtarX + ivAnahtar.getWidth()/2;
        int anahtarCenterY = anahtarY + ivAnahtar.getHeight()/2;
        //0<=appleCenterX<=eatherWidth
        //eaterY<=appleCenterY<=eaterY+eaterHeight
        //-->  Eğer meyvenin merkez noktasının x koordinatı 0 ile eatertın size'ı arasında ise  ve
        // meyvenin merkez noktasısnı Y koordinat 0 ile  arasında ise (eatar size + eater Y pozisyonu) arasında ise
        //puan alır.
        if(0<=appleCenterX && appleCenterX<=eaterSize && eaterY<=appleCenterY && appleCenterY<=(eaterY+eaterSize)){
            score+=10;
            appleX=-20;
            tvScore.setText("Score : " + score);
        }else if(0<=orangeCenterX && orangeCenterX<=eaterSize && eaterY<=orangeCenterY && orangeCenterY<=(eaterY+eaterSize)){//Orange
            score+=15;
            orangeX=-10;
            tvScore.setText("Score : " + score);
        }else if(0<=anahtarCenterX && anahtarCenterX<=eaterSize && eaterY<=anahtarCenterY && anahtarCenterY<=(eaterY+eaterSize)){ //Anahtar
            score-=20;
            anahtarX=-500;
            tvScore.setText("Score : " + score);
        }

    }


    public void showFragment(Fragment fragment, String tag){
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.flFrame,fragment,tag);
        fTransaction.addToBackStack(tag);
        fTransaction.commit();
    }

}
