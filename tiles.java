import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.*;


public class tiles extends JPanel {

    public static void draw(double x1, double y1,double x2, double y2,Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Shape l = new Line2D.Double(x1, y1, x2, y2);
        g2.draw(l);
    }
    public static void circle(double x, double y, double r, Graphics g,String colr) {
        Graphics2D g2d = (Graphics2D) g;

        if(colr.equals("red")){
            g2d.setPaint(Color.RED);
        }
        else if(colr.equals("green")){
            g2d.setPaint(Color.green);

        }
        else if(colr.equals("purp")){
            g2d.setPaint(Color.magenta);
        }
        else if(colr.equals("blue")){
            g2d.setPaint(Color.blue);
        }
        else if(colr.equals("or")){
            g2d.setPaint(Color.ORANGE);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.draw(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
        g2d.setPaint(Color.black);
    }


    public static void drawtri1(double angle, double length, double x1, double y1,boolean left,Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        double phi=(1+Math.sqrt(5))/2;
        double x0 =x1+(Math.cos(angle) * length / phi);
        double y0 =y1+(Math.sin(angle) * length / phi);

        circle(x1,y1,10,g,"blue");


        g2d.setPaint(Color.RED);
        draw(x1, y1, x0, y0, g);
        x1 = x0;
        y1 = y0;
        g2d.setPaint(Color.black);

        circle(x1, y1, 10, g, "green");

        angle += (Math.PI / 180) * 108;
        x0 =x1+ (Math.cos(angle) * length);
        y0 =y1+ (Math.sin(angle) * length);
        draw(x1, y1, x0, y0, g);
        x1 = x0;
        y1 = y0;

        circle(x1,y1,10,g,"red");

        angle += (Math.PI / 180) * 144;
        x0 =x1+(Math.cos(angle) * length);
        y0 =y1+(Math.sin(angle) * length);
        draw(x1, y1, x0, y0, g);
        x1 = x0;
        y1 = y0;

        angle  += (Math.PI / 180) * 108;

        if(left) {
            x0 =x1+((Math.cos(angle+(Math.PI/180)*36)*length)/phi);
            y0 =y1+((Math.sin(angle+(Math.PI/180)*36)*length)/phi);
            draw(x1, y1, x0, y0, g);

            circle(x0, y0, 10, g, "or");
        }
        else{
            x0 =x1+((Math.cos(angle+(Math.PI /180)*72)*length)/(phi*phi))  ;
            y0 =y1+((Math.sin(angle+(Math.PI /180)*72)*length)/(phi*phi))  ;
            draw(x1 + (Math.cos(angle) * length / (phi)), y1 + (Math.sin(angle) * length / (phi)), x0, y0, g);

            circle(x0, y0, 10, g, "or");
        }
    }
    public static void drawtri2(double angle, double length, double x1, double y1,boolean left,Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        circle(x1,y1,10,g,"blue");

        double phi=(1+Math.sqrt(5))/2;
        double x0 =x1+(Math.cos(angle) * length*phi) ;
        double y0 = (Math.sin(angle) * length*phi) + y1;

        g2d.setPaint(Color.RED);
        draw(x1, y1, x0, y0, g);
        x1 = x0;
        y1 = y0;
        g2d.setPaint(Color.black);

        circle(x1,y1,10,g,"green");

        angle += (Math.PI /180)*144;
        x0 = x1+(Math.cos(angle) * length);
        y0 = (Math.sin(angle) * length) + y1;
        draw(x1, y1, x0, y0, g);
        x1 = x0;
        y1 = y0;

        circle(x1,y1,10,g,"red");


        angle += (Math.PI/180)*72;
        x0 = x1+(Math.cos(angle) * length) ;
        y0 = (Math.sin(angle) * length) + y1;
        draw(x1, y1, x0, y0, g);
        x1 = x0;
        y1 = y0;
        angle +=(Math.PI /180)*144;



        if(left) {//L2
            x0 = x1+(Math.cos(angle) * length);
            y0 =y1+ (Math.sin(angle) * length);


            circle(x0, y0, 10, g, "or");

            draw(x1+(Math.cos(angle+(Math.PI /180)*36)*length/phi),y1+(Math.sin(angle+(Math.PI /180)*36)*length/phi),x0,y0,g);
            draw(x1 + (Math.cos(angle + (Math.PI / 180) * 36) * length), y1 + (Math.sin(angle + (Math.PI / 180) * 36) * length), x0, y0, g);
        }
        else{//R2
            x0 =x1+(Math.cos(angle+(Math.PI /180)*36) * length / phi)+(Math.cos(angle)*length/phi);
            y0 =y1+ (Math.sin(angle+(Math.PI /180)*36) * length / phi)+(Math.sin(angle) * length / phi);


            circle(x0, y0, 10, g, "or");

            draw(x1+(Math.cos(angle)*length/phi),y1+(Math.sin(angle) * length / phi), x0, y0, g);
            draw(x1 + (Math.cos(angle) * length / phi), y1 + (Math.sin(angle) * length / phi), x1 + (Math.cos(angle + (Math.PI / 180) * 36) * length), y1 + (Math.sin(angle + (Math.PI / 180) * 36) * length), g);
        }
    }


    public static void drawtile(double angle, double length, double x1, double y1,boolean left,boolean tri,int deg, Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        double phi=(1+Math.sqrt(5))/2;
        if(deg>0){
            if(left && tri){//L1
                drawtri1(angle,length,x1,y1,left,g);

                /*
                L1
                L2
                 */


                drawtile(angle- (Math.PI/180)*108,length/phi,x1+(Math.cos(angle+(Math.PI/180)*72)*length/(phi*phi)),y1+(Math.cos(angle+(Math.PI/180)*72)*length/(phi*phi)),true,true,deg-1,g);//L1
                drawtile(angle+ (Math.PI/180)*108,length/phi,x1+(Math.cos(angle)*length/phi),y1+(Math.sin(angle)*length/phi),true,false,deg-1,g);//L2
            }
            if((!left) && tri){//R1
                drawtri1(angle,length,x1,y1,left,g);

               /*
                R1
                R2
                 */

                drawtile(angle+(Math.PI/180)*108,length/phi,x1+(Math.cos(angle+(Math.PI/180)*72)*length/(phi*phi)),y1+(Math.sin(angle + (Math.PI / 180) * 72)*length/(phi*phi)),false,true,deg-1,g);//R1
                drawtile(angle-(Math.PI/180)*108,length/phi,x1+((Math.cos(angle)*length)/phi),y1+((Math.sin(angle)*length)/phi),false,false,deg-1,g);//R2
            }
            if(left && (!tri)){//L2
                drawtri2(angle, length, x1, y1, left, g);

                /*
                R1
                R2
                L2
                 */

                drawtile(angle- (Math.PI/180)*144,length/phi,x1+(Math.cos(angle+(Math.PI/180)*36)*length),y1+(Math.sin(angle+(Math.PI/180)*36)*length),false,true,deg-1,g);//R1
                drawtile(angle,length/phi,x1,y1,false,false,deg-1,g);//R2
                drawtile(angle+ (Math.PI/180)*108,length/phi,x1+(Math.cos(angle)*length*phi),y1+(Math.sin(angle)*length*phi),true,false,deg-1,g);//L2
            }
            if((!left) && (!tri)){//R2
                drawtri2(angle,length,x1,y1,left,g);

                 /*
                L1
                L2
                R2
                 */

                drawtile(angle +(Math.PI/180)*144,length/phi,x1+(Math.cos(angle)*(length/phi))+(Math.cos(angle+(Math.PI/180)*36)*(length/phi)),y1+(Math.sin(angle)*(length/phi))+(Math.sin(angle+(Math.PI/180)*36)*(length/phi)),true,true,deg-1,g);//L1
                drawtile(angle, length / phi, x1 + (Math.cos(angle) * length / phi), y1 + (Math.sin(angle) * length / phi), true, false, deg -1,g);//L2
                drawtile(angle - (Math.PI/180)*144,length/phi,x1+(Math.cos(angle+(Math.PI/180)*36)*length),y1+(Math.sin(angle+(Math.PI/180)*36)*length),false,false,deg-1,g);//R2
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("s");
        tiles a = new tiles();
        frame.add(a);
        frame.setSize(1200, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double w=getWidth();


        double h=getHeight();
        double length=300;
        double x1=(w-length)/2;
        double y1=(h/2);


        drawtile(0, length, x1+100, y1, true, true, 2, g);




    }

}
