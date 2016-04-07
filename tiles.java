import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;




public class Ptiling extends JPanel {


    public static int size=4000;//PNG legnth
    
    public static  ArrayList<ArrayList<Double>> makespace(ArrayList<ArrayList<Double>> a,int num){
        if(a.size()>0) {
            System.out.println("makespace(): list size > 0");
            return a;}
        else {
            for (int i = 0; i < num; i++) {
                ArrayList<Double> t = new ArrayList<Double>();
                for (int j = 0; j < 2; j++) {
                    t.add(0.0);
                }
                a.add(t);
            }
        }
        return a;
    }

    public static void draw(double x1, double y1,double x2, double y2,String color,Graphics2D g2d){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if(color.equals("red")){
            g2d.setPaint(Color.RED);
        }
        else if(color.equals("green")){
            g2d.setPaint(Color.green);
        }
        else if(color.equals("purple")){
            g2d.setPaint(Color.magenta);
        }
        else if(color.equals("blue")){
            g2d.setPaint(Color.blue);
        }
        else if(color.equals("orange")){
            g2d.setPaint(Color.ORANGE);
        }
        else if(color.equals("black")){
            g2d.setPaint(Color.black);
        }
        else if(color.equals("white")){
            g2d.setPaint(Color.white);
        }

        Shape l = new Line2D.Double(x1, y1, x2, y2);
        g2d.draw(l);
    }
    public static double dist(double x1,double y1,double x2,double y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    public static void drawTriangle(ArrayList<ArrayList<Double>> points,String color,Graphics2D g2d){
        if(points.size()!=3){
            System.out.println("not a triangle # points = "+ points.size());
            return;
        }
        if(  ( (points.get(0).get(1)-points.get(1).get(1))/(points.get(0).get(0)-points.get(1).get(0))  )==( (points.get(2).get(1)-points.get(1).get(1))/(points.get(2).get(0)-points.get(1).get(0) )  ) ){
            System.out.println("not a triangle points are colinear --> "+ ( (points.get(0).get(1)-points.get(1).get(1))/(points.get(0).get(0)-points.get(1).get(0) )+" = "+( (points.get(2).get(1)-points.get(1).get(1))/(points.get(2).get(0)-points.get(1).get(0) )  ) ));
            return;
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);



        int x[]={(int)Math.round(points.get(0).get(0)),(int)Math.round(points.get(1).get(0)),(int)Math.round(points.get(2).get(0))};
        int y[]={(int)Math.round(points.get(0).get(1)),(int)Math.round(points.get(1).get(1)),(int)Math.round(points.get(2).get(1))};



        if(color.equals("red")){
            g2d.setPaint(Color.RED);
        }
        else if(color.equals("green")){
            g2d.setPaint(Color.green);
        }
        else if(color.equals("purple")){
            g2d.setPaint(Color.magenta);
        }
        else if(color.equals("blue")){
            g2d.setPaint(Color.blue);
        }
        else if(color.equals("orange")){
            g2d.setPaint(Color.ORANGE);
        }
        else if(color.equals("cyan")){
            g2d.setPaint(Color.CYAN);
        }
        else if(color.equals("black")){
            g2d.setPaint(Color.black);
        }

        draw(points.get(0).get(0),points.get(0).get(1),points.get(1).get(0),points.get(1).get(1),color,g2d);
        draw(points.get(0).get(0),points.get(0).get(1),points.get(2).get(0),points.get(2).get(1),color,g2d);
        draw(points.get(1).get(0),points.get(1).get(1),points.get(2).get(0),points.get(2).get(1),color,g2d);
        g2d.fillPolygon(x,y,3);
    }

    /**
     * thin
     * @param angle draw triangle at this angle
     * @param length length of short side
     * @param x1
     * @param y1
     * @return 3 points for thin tri
     */
    public static ArrayList<ArrayList<Double>> thin(double angle, double length, double x1, double y1) {
        ArrayList<ArrayList<Double>> result =new ArrayList<ArrayList<Double>>();

        for(int i=0;i<3;i++){
            ArrayList<Double> temp =new ArrayList<Double>();
            for(int j=0;j<2;j++){
                temp.add(0.0);
            }
            result.add(i,temp);
        }


        double phi = (1 + Math.sqrt(5)) / 2;

        double x0 = x1 + (Math.cos(angle) * length / phi);
        double y0 = y1 + (Math.sin(angle) * length / phi);
        result.get(0).set(0, x0);
        result.get(0).set(1, y0);

        x1 = x0;
        y1 = y0;

        angle += 3 * Math.PI / 5;
        x0 = x1 + (Math.cos(angle) * length);
        y0 = y1 + (Math.sin(angle) * length);
        result.get(1).set(0, x0);
        result.get(1).set(1, y0);

        x1=x0;
        y1=y0;


        angle += 4 * Math.PI / 5;
        x0 = x1 + (Math.cos(angle) * length);
        y0 = y1 + (Math.sin(angle) * length);
        result.get(2).set(0, x0);
        result.get(2).set(1, y0);



        return result;

    }

    /**
     * fat
     * @param angle draw triangle at this angle
     * @param length length of short side
     * @param x1
     * @param y1
     * @return 3 points for fat tri
     */
    public static ArrayList<ArrayList<Double>> fat(double angle, double length, double x1, double y1) {
        ArrayList<ArrayList<Double>> result =new ArrayList<ArrayList<Double>>();

        for(int i=0;i<3;i++){
            ArrayList<Double> temp =new ArrayList<Double>();
            for(int j=0;j<2;j++){
                temp.add(0.0);
            }
            result.add(i,temp);
        }

        double phi = (1 + Math.sqrt(5)) / 2;

        double x0 = x1 + (Math.cos(angle) * length * phi);
        double y0 = y1 + (Math.sin(angle) * length * phi);
        result.get(0).set(0, x0);
        result.get(0).set(1, y0);

        x1=x0;
        y1=y0;

        angle += 4 * Math.PI / 5;
        x0 = x1 + (Math.cos(angle) * length);
        y0 = y1 + (Math.sin(angle) * length);
        result.get(1).set(0, x0);
        result.get(1).set(1, y0);

        x1=x0;
        y1=y0;


        angle += 2 * Math.PI / 5;
        x0 = x1 + (Math.cos(angle) * length);
        y0 = y1 + (Math.sin(angle) * length);
        result.get(2).set(0, x0);
        result.get(2).set(1, y0);



        return result;
    }

    /**
     * rotate
     * @param a rotate points by
     * @param x1 rotate at this xcord
     * @param y1 rotate at this ycord
     * @param points list of points to be rotated
     * @return list of points rotated
     */
    public static  ArrayList<ArrayList<Double>> rotate(double a,double x1,double y1,ArrayList<ArrayList<Double>> points){

        /************************************
         * |cos(a)  -sin(a)|    |x1  x2...|
         * |sin(a)  cos(a) |  X |y1  y2...|
         ************************************/


        //make empty array to store stuff in
        ArrayList<ArrayList<Double>> r =new ArrayList<ArrayList<Double>>();
        for(int i=0;i<2;i++){
            ArrayList<Double> temp =new ArrayList<Double>();
            for(int j=0;j<2;j++){
                temp.add(0.0);
            }
            r.add(temp);
        }
        r.get(0).set(0, Math.cos(a));
        r.get(0).set(1, Math.sin(a));
        r.get(1).set(0, -Math.sin(a));
        r.get(1).set(1, Math.cos(a));


        ArrayList<ArrayList<Double>> result =new ArrayList<ArrayList<Double>>();
        for(int i=0;i<points.size();i++){
            ArrayList<Double> temp =new ArrayList<Double>();
            for(int j=0;j<2;j++){
                temp.add(0.0);
            }
            result.add(temp);
        }

        //problem w/ func call changing vars outside func copy stuff from param to new list
        ArrayList<ArrayList<Double>> pointss=new ArrayList<ArrayList<Double>>();
        for(ArrayList<Double> i: points){
            ArrayList<Double> temp =new ArrayList<Double>();
            for(Double j:i){
                temp.add(j);
            }
            pointss.add(temp);
        }

        for (ArrayList<Double> i:pointss) {
            i.set(0, i.get(0) - x1);
            i.set(1, i.get(1) - y1);
        }


        for (int i = 0; i<pointss.size(); i++) {
            for (int j = 0; j<r.size(); j++) {
                double sum=0;
                for(int n = 0; n<pointss.get(0).size(); n++){
                    sum+=r.get(j).get(n) * pointss.get(i).get(n);
                }
                result.get(i).set(j,sum);
            }
        }


        for (ArrayList<Double> i:result) {
            i.set(0, i.get(0) + x1);
            i.set(1, i.get(1) + y1);
        }

        return result;
    }

    /**
     * subdivide
     * @param points list of 3 points for drawing a triangle
     * @param left consider the triangle being drawn where the unequal side
     *             wasnt drawn at an angle, is the subdivision on left or right
     * @param thin is the triangle thin or not
     * @param deg  angle at which triangle is drawn
     * @param g2d  necessary (as far as i know) graphics object
     */
    public static  void subdivide (ArrayList<ArrayList<Double>> points,boolean left,boolean thin,int deg,Graphics2D g2d ){
        if(deg>0) {
            String s="";
            if(thin) s = "white";
            else s = "white";

            drawTriangle(points,s,g2d);

            double phi = (1 + Math.sqrt(5)) / 2;
            double length = 0;
            double angle = 0;
            int v = 0;
            int a = 0;
            int b = 0;

            ArrayList<ArrayList<Double>> temp0 = new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Double>> temp1 = new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Double>> temp2 = new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Double>> temp4 = new ArrayList<ArrayList<Double>>();

            ArrayList<Double> p = new ArrayList<Double>();
            ArrayList<Double> q = new ArrayList<Double>();

            p.add(0.0);
            p.add(0.0);
            q.add(0.0);
            q.add(0.0);

            makespace(temp0, points.size());
            makespace(temp2,2);
            makespace(temp4,3);

            double d10=Math.round(dist(points.get(0).get(0), points.get(0).get(1), points.get(1).get(0), points.get(1).get(1)));
            double d20= Math.round(dist(points.get(0).get(0), points.get(0).get(1), points.get(2).get(0), points.get(2).get(1)));

            double d10a=dist(points.get(0).get(0), points.get(0).get(1), points.get(1).get(0), points.get(1).get(1));
            double d20a=dist(points.get(0).get(0), points.get(0).get(1), points.get(2).get(0), points.get(2).get(1));


            if(thin) {//get smallest side find angle for thin triangle
                //[0][1]
                //[0][1]<[0][2]
                if (d10<d20){
                    angle = Math.atan((points.get(0).get(1) - points.get(1).get(1)) / (points.get(0).get(0) - points.get(1).get(0)));
                    length = d20a;
                    a = 0;
                    b = 1;
                    v = 2;
                }
                //[2][0]
                //[0][1]>[0][2]
                else if (d10>d20){
                    angle = Math.atan((points.get(2).get(1) - points.get(0).get(1)) / (points.get(2).get(0) - points.get(0).get(0)));
                    length = d10a;
                    a = 2;
                    b = 0;
                    v = 1;
                }
                //[1][2]
                //[0][1]==[0][2]
                else {
                    angle = Math.atan((points.get(2).get(1) - points.get(1).get(1)) / (points.get(2).get(0) - points.get(1).get(0)));
                    length = d20a;
                    a = 1;
                    b = 2;
                    v = 0;
                }
            }


            else{//get largest side find angle for fat triangle
                //[0][2]
                //[0][1]<[0][2]
                if (d10<d20) {
                    angle = Math.atan((points.get(0).get(1) - points.get(2).get(1)) / (points.get(0).get(0) - points.get(2).get(0)));
                    length = d10a;
                    a = 0;
                    b = 2;
                    v = 1;
                }
                //[1][0]
                //[0][1]>[0][2]
                else if (d10>d20) {
                    angle = Math.atan((points.get(0).get(1) - points.get(1).get(1)) / (points.get(0).get(0) - points.get(1).get(0)));
                    length = d20a;
                    a = 1;
                    b = 0;
                    v = 2;
                }
                //[2][1]
                //[0][1]==[0][2]
                else {
                    angle = Math.atan((points.get(2).get(1) - points.get(1).get(1)) / (points.get(2).get(0) - points.get(1).get(0)));
                    length =d10a;
                    a = 2;
                    b = 1;
                    v = 0;
                }
            }

            temp1=rotate(angle,points.get(a).get(0),points.get(a).get(1),points);//rotate triangle so that base parellel to x-axis


            if(deg==1){
                draw(points.get(a).get(0),points.get(a).get(1),points.get(b).get(0),points.get(b).get(1),"white",g2d);
            }

            //rotate 180 deg if upside down
            if (temp1.get(a).get(1) > temp1.get(v).get(1)) {
                angle = angle + Math.PI;
                temp1=rotate(Math.PI,points.get(a).get(0),points.get(a).get(1),temp1);
            }

            //get left + right point
            int l = 0;
            int r = 0;
            for (int i = 0;i<temp1.size();i++){
                if (temp1.get(i).get(0)>temp1.get(l).get(0)) {
                    l = i;
                }
            }
            int [] aa ={0,1,2};
            for(int i:aa){
                if((l!=i)&&(v!=i)){
                    r=i;
                }
            }


            if (thin) {//thin triangle
                if (left) {//L1
                    //define point p where to split triangle
                    p.set(0,temp1.get(l).get(0) - (Math.cos(2 * Math.PI / 5) * length / (phi * phi)));
                    p.set(1,temp1.get(l).get(1) + (Math.sin(2 * Math.PI / 5) * length / (phi * phi)));

                    temp2.set(0,p);
                    temp2=rotate(-angle, points.get(a).get(0), points.get(a).get(1), temp2);
                    p = temp2.get(0);

                    for(int i=0;i<2;i++){
                        temp0.set(0,p);
                        temp0.set(1,points.get(r));
                        if(i==0){//L1
                            temp0.set(2,points.get(l));
                            subdivide(temp0, true, true,deg-1,g2d);
                        }
                        else{//L2
                            temp0.set(2,points.get(v));
                            subdivide(temp0, true, false,deg-1,g2d);
                        }
                    }
                }

                else {//R1
                    //define point p where to split triangle
                    p.set(0,temp1.get(l).get(0) - (Math.cos(Math.PI / 5) * length / phi));
                    p.set(1,temp1.get(l).get(1) + (Math.sin(Math.PI / 5) * length / phi));

                    temp2.set(0,p);
                    temp2=rotate(-angle, points.get(a).get(0), points.get(a).get(1), temp2);
                    p = temp2.get(0);


                    for(int i=0;i<2;i++){
                        temp0.set(0,p);
                        temp0.set(1,points.get(l));
                        if(i==0){//R1
                            temp0.set(2,points.get(r));
                            subdivide(temp0, false, true,deg-1,g2d);
                        }
                        else{//R2
                            temp0.set(2,points.get(v));
                            subdivide(temp0, false, false,deg-1,g2d);
                        }
                    }

                }
            }
            else {//fat triangle

                if (left) {//L2
                    //define point p and q where triangle is split
                    p.set(0,temp1.get(l).get(0) - length);
                    p.set(1,temp1.get(l).get(1));

                    q.set(0,temp1.get(l).get(0) - (Math.cos(Math.PI / 5) * length / phi));
                    q.set(1,temp1.get(l).get(1) + (Math.sin(Math.PI / 5) * length / phi));

                    temp2.set(0,p);
                    temp2.set(1,q);
                    temp2=rotate(-angle, points.get(a).get(0), points.get(a).get(1), temp2);

                    p = temp2.get(0);
                    q = temp2.get(1);


                    temp0.set(0,p);
                    for(int i=0;i<3;i++){
                        if(i==0) {//R1
                            temp0.set(1,points.get(v));
                            temp0.set(2,q);
                            subdivide(temp0, false,true,deg-1 ,g2d);
                        }
                        else if(i==1) {//R2
                            temp0.set(1,points.get(l));
                            temp0.set(2,q);
                            subdivide(temp0, false, false,deg-1 ,g2d);
                        }
                        else if(i==2) {//L2
                            temp0.set(1,points.get(v));
                            temp0.set(2,points.get(r));
                            subdivide(temp0, true, false,deg-1 ,g2d);
                        }
                    }
                }

                else {//R2

                    //define point p and q where triangle is split
                    p.set(0,temp1.get(l).get(0) - (length / phi));
                    p.set(1,temp1.get(l).get(1));

                    q.set(0,temp1.get(r).get(0) + (Math.cos(Math.PI / 5) * length / phi));
                    q.set(1,temp1.get(r).get(1)+(Math.sin(Math.PI/5)*length/phi) );

                    temp2.set(0, p);
                    temp2.set(1, q);
                    temp2=rotate(-angle, points.get(a).get(0), points.get(a).get(1), temp2);

                    p = temp2.get(0);
                    q = temp2.get(1);


                    temp0.set(0, p);
                    for(int i=0;i<3;i++){
                        if(i==0) {//L1
                            temp0.set(1,points.get(v));
                            temp0.set(2,q);
                            subdivide(temp0, true, true,deg-1 ,g2d);
                        }
                        else if(i==2) {//L2
                            temp0.set(1,q);
                            temp0.set(2,points.get(r));
                            subdivide(temp0, true,false,deg-1 ,g2d);
                        }
                        else if(i==1) {//R2
                            temp0.set(1,points.get(l));
                            temp0.set(2,points.get(v));
                            subdivide(temp0, false, false,deg-1 ,g2d);
                        }
                    }
                }
            }
        }
    }
    





    public void paintComponent(Graphics g) {

        BufferedImage bi = new BufferedImage(size,size, BufferedImage.TYPE_INT_ARGB);
        File image = new File("D:\\yourImageName.PNG");
        Graphics2D g2d = bi.createGraphics();


        double w = getWidth();
        double h = getHeight();
        double length = 1300;
        double phi = (1 + Math.sqrt(5)) / 2;

        boolean thin=false;

        if(thin){
            double l=(w-(length/phi))/2;
            double hh=(h-length*Math.sin(2*Math.PI/5))/2;
            subdivide(thin(0,length,l,hh),true,true,2,g2d);
        }
        else {
            double l=(w-(length*phi))/2;
            double hh=(h-length*Math.sin(Math.PI/5))/2;
            subdivide(fat(0,length,l,hh),true,false,2,g2d);
        }


        try{
            ImageIO.write(bi,"PNG",image);
        }
        catch (Exception e){
            System.out.println("not working");
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Ptiling a = new Ptiling();
        frame.add(a);
        frame.setSize(size,size);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
    }

}
