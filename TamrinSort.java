package tamrinsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.MAX_VALUE;
import java.util.Arrays;
import java.util.Scanner;

public class TamrinSort {

    public static void main(String[] args) throws FileNotFoundException, IOException {
       long time=System.currentTimeMillis();
        Scanner in = new Scanner(System.in);
        System.out.println("please Enter input file location:");
        String inputfile= in.nextLine();
        System.out.println("please Enter output file location:");
        String outputfile=in.nextLine();
        FileWriter out =new FileWriter(outputfile);
        BufferedWriter bout =new  BufferedWriter(out);
         System.out.println(""+LineNumber(inputfile));
        int linenumber = LineNumber(inputfile);
        int divided =128*1024*1024;
        long finish = linenumber;    //shart khatemeh
        
       FileReader fr = new FileReader(inputfile);
       BufferedReader br = new BufferedReader(fr);
        
       FileWriter fw = null ;
       BufferedWriter bw = null;
        
        int chunk = linenumber/divided;       //tedad chunk
        if(chunk >= 1){          //chunk agar bishtar az 1 bod yani niaz has ke chan bar arraye ejad she
         
            for(int i =0 ; i<chunk ; i++){
                long sort=System.currentTimeMillis();
                 //agar tedad line bishtar az tedad khone
                if(linenumber>=divided){              //array bood yani dobare bayad ye arraye kamel dashte bashim
                    long []a=new long [divided];
                    for(int j=0 ; j<divided; j++)
                    {
                        String t = br.readLine();
                        if(t!=null)
                        a[j]=Integer.parseInt(t);
                    }
                    Arrays.sort(a);
                    //neveshtan array sort shode dar file makhsos sort anha 
                   fw = new  FileWriter("E:\\New folder\\"+i+".txt");
                   bw = new BufferedWriter(fw);
                    for(int h=0;h<a.length;h++)
                    {
                        String f= ""+a[h];
                        bw.write(f+"\n");
                        //bw.newLine();
                    }
                  
                    linenumber=linenumber-divided;                //har seri az tedad line kam mishe ta zamani ke tedad
                                                           //line ma kamtar az hafeze ma bashe
                     bw.close();                           //zamani ke inja close nmikardam dakhel file neveshte nemishod
                }
                long so=System.currentTimeMillis();
                System.out.println("sort"+(so-sort));
            }
        }
            //chunk hayee ke megdareshon kamel nis
            //baghyee mande ha 
                    if(linenumber>=1){
                    long ez=System.currentTimeMillis();

                    long a[]=new long [linenumber];
                    for(int k=0;k<linenumber;k++)
                    {
                        String t = br.readLine();
                        if(t!=null)
                        a[k]=Long.parseLong(t);
                    }
                     Arrays.sort(a);
                     fw = new  FileWriter("E:\\New folder\\"+chunk+".txt");
                     bw = new BufferedWriter(fw);
                    //neveshtan array sort shode dar file makhsos sort anha 
                    for(int h=0;h<a.length;h++)
                    {
                        String f= ""+a[h];
                        bw.write(f);
                        bw.newLine();
                 
                  }
                    chunk=chunk+1;
                    br.close();
                    fr.close();
                    bw.close();
                    fw.close();
                    }
               //**jahat khandan az file haye chunk shode va moratab shode
                
                BufferedReader []chunkhayeman=new BufferedReader[chunk];
                for(int i=0; i<chunk ; i++){
                chunkhayeman[i]=new BufferedReader(new FileReader("E:\\New folder\\"+i+".txt"));
                }
                    long findmin[] = new long [chunk];
                    //por kardan avalyee arraye 
                    for(int i=0 ; i<chunk;i++)
                    {
                        String t =chunkhayeman[i].readLine();
                        if(t!=null)
                            findmin[i]=Long.parseLong(t);
                    }
                        //peyda karadan min az arraye va 
                        //neveshtan dar file asli
                    //be tedad khat haye mojod tekrar shode va min ra peyda mikond
                    while(finish>0){
                        
                     int index = Indexmin(findmin);
                     long min= findmin[index];
                     bout.write(""+min);
                     bout.newLine();
                     //shomarandeye marbot be min ra dar count gharar midahad
                     //chon har seri az count kam mikonim ye motaghayere joda barayash dar nazar migirim
                    //har file khat chandom gharar darad
                
                     String t=chunkhayeman[index].readLine();
                     
                         if(t==null)
                             t=""+MAX_VALUE;
                    
                     findmin[index]=Long.parseLong(t);
                       finish--;
                }
                for(int i=0; i<chunk ; i++){
                chunkhayeman[i].close();
                }
                    long time1=System.currentTimeMillis();
                    System.out.println("final="+(time1-time));
                    bout.close();
        
    }
        //** indexmin arraye ra barmigardanad
    public static int Indexmin(long a[])
    {
        int min =0;
        for (int i=0 ;i< a.length ;i++)
            if (a[i]<a[min])
                min=i;
        return min;
    }
   
    public static int LineNumber(String s) throws FileNotFoundException, IOException{
        long number=System.currentTimeMillis();
          FileReader fr = new FileReader(s);
        BufferedReader br =new BufferedReader(fr);
        String t = br.readLine();
       int counter=0;
        while(t!=null)
        {
            t=br.readLine();
             counter++;
        }
               return counter;
    }
}
        
    
    

