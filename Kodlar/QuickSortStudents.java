import java.util.ArrayList;
import java.util.Collections;

public class QuickSortStudents {

    public static void sort(ArrayList<Student> table){
        quickSort(table,0,table.size()-1);
    }

    private static void quickSort(ArrayList<Student> table, int first,int last){
        if(first < last){
            int pivIndex = partition(table,first,last);
            quickSort(table,first,pivIndex-1);
            quickSort(table,pivIndex+1,last);
        }
    }

    private static int partition(ArrayList<Student> table, int first,int last){
        sort3(table,first,last);
        Collections.swap(table,first,(first+last) / 2);
        Student pivot = table.get(first);
        int up = first;
        int down = last;

        do{
            while ((up < last) && (pivot.compareTo(table.get(up)) >= 0)){
                up++;
            }
            while ((pivot.compareTo(table.get(down)) < 0)){
                down--;
            }
            if(up < down){
                Collections.swap(table,up,down);
            }
        }while(up < down );
        Collections.swap(table,first,down);
        return down;
    }

    private static void sort3(ArrayList<Student> table,int first,int last){
        int middle = (first + last) / 2;

        if(table.get(middle).compareTo(table.get(first)) < 0)
            Collections.swap(table,first,middle);
        if(table.get(last).compareTo(table.get(middle)) < 0)
            Collections.swap(table,middle,last);
        if(table.get(middle).compareTo(table.get(first)) < 0)
            Collections.swap(table,first,middle);
    }
}