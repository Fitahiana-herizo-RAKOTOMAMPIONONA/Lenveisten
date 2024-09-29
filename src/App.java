import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class App {
    static ArrayList<String> bibliotheque =new ArrayList<String>();

    public static int resolveLenveisten(String str ,String str2){
        int [][] matrice = new int[str.length()+1][str2.length()+1];
        for (int i=0 ; i<= str.length(); i++){
            for (int j=0 ; j<= str2.length(); j++){
                if (i==0) {
                    matrice[i][j]= j;
                }else if(j==0){
                    matrice[i][j] = i;
                }else{
                    matrice[i][j] = minimun_edits(matrice[i-1][j-1] + test_caractere(str.charAt(i-1) , str2.charAt(j-1) ) , matrice[i-1][j] + 1 , matrice[i][j-1]+1);
                };
            }
        }
        return matrice[str.length()][str2.length()];
    }   


    public static int minimun_edits(int  ...nums){
        return Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
    }


    public static int test_caractere(char c1 ,char c2){
        return c1 == c2? 0 : 1;
    }

    public static void insertToBibliotheaque(String pathname){
        File fichier = new File(pathname);
        try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
            String ligne;
            while ((ligne = br.readLine()) != null) {
                bibliotheque.add(ligne);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String readline(){
        Scanner scanner = new Scanner(System.in);
        String moString = scanner.nextLine();
        scanner.close();
        return moString;
    }

    public static boolean isExist(){
        return bibliotheque.contains(readline());
    }
    public static boolean isExist(String motString){
        return bibliotheque.contains(motString);
    }

    public static void liste(){
        String mot= readline();
        ArrayList<String> x =new ArrayList<String>();
        if (isExist(mot)) {
            System.out.println("Le mot " + mot + "existe vraiment ! pas de faute");
        }else{
            for(int i= 0 ; i<bibliotheque.size(); i++){
                if (resolveLenveisten(mot, bibliotheque.get(i))==1){
                    x.add(bibliotheque.get(i));
                    System.out.println(bibliotheque.get(i));
                }
            }
        }
    }
    public static void main(String[] args) {
        insertToBibliotheaque("src/liste_francais.txt");
        
        liste();
    }
}
