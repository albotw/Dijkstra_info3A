/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author yt646712
 */
public class Dijkstra extends JFrame{
    
    public static final int MAX_RAYON = 50;
    public static final int MIN_RAYON = 9;

    public static final int POINTS = 1000;
    public static final int OBSTACLES = 10;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int MARGIN = 20;
    public static final int INFINI = 9999;
    public static final int R = 50;        //rayon pour prendre en compte un point dans les calculs
    public static final double SAVE_THRESOLD = 1.15;   //seuil de sauvegarde
    
    public static ArrayList<Obstacle> obstacles;
    public static ArrayList<Sommet> graphe;
    public static ArrayList<Sommet> graphe_origine;
    public static ArrayList<Sommet> graphe_arrivee;
    public static ArrayList<Sommet> PCC;
    Sommet origine;
    Sommet arrivee;

    //public static Heap graphe;
    RenderPanel UI;

    
    public Dijkstra(){
        this.setTitle("Dijkstra");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        init();
        graphe_origine = ALCopy(graphe);
        graphe_arrivee = ALCopy(graphe);
        
        System.out.println(graphe_origine.get(0));
        System.out.println(graphe_arrivee.get(0));
        graphe_origine = dijkstra(origine, arrivee, graphe_origine);
        System.out.println(graphe_origine.size());
        //graphe_arrivee = dijkstra(arrivee, origine, graphe_arrivee);
        System.out.println(graphe_arrivee.size());
        
        for(int i = 0; i < graphe.size() - 1; i++){
            double d = (graphe_origine.get(i).distance + graphe_arrivee.get(i).distance) / graphe_arrivee.get(i).distance;
            if (d < SAVE_THRESOLD){
                graphe.remove(graphe.get(i));
            }
            System.out.println(graphe_origine.get(i).distance + "  " + graphe_arrivee.get(i).distance);
        }
        
        
    }
    
    public ArrayList<Sommet> dijkstra(Sommet origine, Sommet arrivee, ArrayList<Sommet> graphe){
        ArrayList<Sommet> output = new ArrayList<Sommet>();
        Sommet s1 = origine;
        do{
            s1 = find_min(graphe);
            if (s1 != null){
                for (int i = 0; i <= s1.voisins.size() - 1; i++){
                    double d = s1.getArc(i);
                    Sommet s = s1.getVoisin(i);
                    if (s.distance > s1.distance + d){
                        s.distance = s1.getDistance() + d;
                        s.pred = s1;
                    }
                }
                output.add(s1);
                graphe.remove(s1);
            }
        }while(s1 != arrivee && s1 != null);

        Sommet s = arrivee;
        while (s != null){
            System.out.println(s);
            PCC.add(s);
            s = s.pred;
        }
        return output;
    }
    
    public void init(){
        //graphe = new Heap(MAX_POINTS);
        graphe = new ArrayList<Sommet>();
        PCC = new ArrayList<Sommet>();
        obstacles = new ArrayList<Obstacle>();
        graphe_origine = new ArrayList<Sommet>();
        graphe_arrivee = new ArrayList<Sommet>();
        generateObstacles();
        

        Point p_origine = new Point(5, 5);
        origine = new Sommet(p_origine);
        graphe.add(origine);
        for (int i = 0; i < POINTS - 2; i++){
            Point p = generatePoint(2);
            //graphe.addObject(s, i);
            Sommet s = new Sommet(p);
            s.distance = INFINI;
            graphe.add(s);
        }
        //generateStaticPoints();
        
        //arrivee = graphe.get(MAX_POINTS - 1);
        Point p_arrivee = new Point(1019, 763);
        arrivee = new Sommet(p_arrivee);
        arrivee.distance = INFINI;
        graphe.add(arrivee);
        
        for (Sommet s : graphe){
            for (Sommet s2 : graphe){
                double d = Sommet.Distance(s, s2);
                if (d <= R){
                    s.addVoisin(s2, d);
                    s2.addVoisin(s, d);
                    
                }
            }
        }
        UI = new RenderPanel();
        this.add(UI);
    }
    

    public Sommet find_min(ArrayList<Sommet> graphe){
        /*double mini = INFINI;
        Sommet output = null;
        for (Sommet s : Q){
            double distance = Point.calcDistance(origine.pos, s.pos);
            if (distance <= mini && distance <= R){
                output = s;
                mini = s.pos.getDistance();
            }
        }*/
        
        Sommet output = null;
        double mini = INFINI;
        for (int i = 0; i <= graphe.size() -1; i++){
            if (graphe.get(i).getDistance() < mini){
                output = graphe.get(i);
                mini = output.getDistance();

            }
        }
        return output;
    }
    
    public void generateObstacles(){
        for (int i = 0; i < OBSTACLES; i++){
            int type = (int)(2*Math.random());
            if (type == 0){ //disque
                int r = (int)(MIN_RAYON + (MAX_RAYON - MIN_RAYON) * Math.random());
                Point c = generatePoint(0);
                Disque d = new Disque(c, r);
                obstacles.add(d);
            }
            else{   //rectangle
                Point ex1  = generatePoint(0);
                Point ex2 = null;
                
                //pour faciliter les calculs. implique que ext1 = HG et ext2 = BD
                do{
                    ex2 = generatePoint(0);
                }while (ex2.getX() <= ex1.getX() || ex2.getY() <= ex1.getY());
                
                Rectangle r = new Rectangle(ex1, ex2);
                obstacles.add(r);
            }
        }
    }
    
    //mode == 0:    pas de vérification de collision.
    //mode == 1:    vérification de collision
    //mode == 2:    vérification de collision ET inclusion dans le rayon.
    public Point generatePoint(int mode){
        if (mode == 0){
            int x = (int)(1+(WIDTH - 1) * Math.random());
            int y = (int)(1+(HEIGHT -1) * Math.random());
            Point p = new Point(x, y);
            return p;
        }
        else{
            boolean collide = false;
            Point p = null;
            do{
                collide = false;
                p = null;
                int x = (int)(1+(WIDTH - MARGIN) * Math.random());
                int y = (int)(1+(HEIGHT - MARGIN) * Math.random());
                p = new Point(x, y);
                for (int i = 0; i < obstacles.size(); i++){
                    if (obstacles.get(i).collision(new Sommet(p)) == true){
                        collide = true;
                    }
                    else if (mode == 3){    //vérification d'inclusion. A MODIFIER
                        for (int j = 0; j < graphe.size() -1; j++){
                            if (0> R){
                                collide = true;
                            }
                        }
                    }
                }
            }while (collide == true);
            return p;
        }
    }
    public ArrayList<Sommet> ALCopy (ArrayList<Sommet> al_origin){
        ArrayList<Sommet> output = new ArrayList<Sommet>();
        for (int i = 0; i <= al_origin.size() - 1; i++){
            output.add(new Sommet(al_origin.get(i)));
        }
        return output;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        System.setProperty("file.encoding", "UTF-8");
        EventQueue.invokeLater(() -> {
            Dijkstra app = new Dijkstra();
        });
    }   
}
