package Main;


import java.util.List;

/**
 *
 * @author Jorre Vedder S4379101
 * 
 * Main, some demonstration is created and executed here.
 */
public class Main
{
    public static void main(String[] args) {
        int [] game = {1,2,3,4,  5,6,7,8, 9,16,11,12, 13,14,15,10};

        Configuration s = new SlidingGame (game);
        System.out.println("Initial Configuration: ");
        System.out.println(s);
        Solver solver = new Solver(s);
        String result = solver.solve();
        System.out.println(result);
        if(result == "Success!"){
            System.out.println("Path to succes:");
            for(Configuration c:solver.getRoute()){
                System.out.println(c);
            }
        }
        
        
        int[] game2 = {16,2,3,4,  5,6,7,8, 9,10,11,12, 13,14,15,1};
        
        s = new SlidingGame (game2);
        System.out.println("Initial Configuration: ");
        System.out.println(s);
        solver = new Solver(s);
        result = solver.solve();
        System.out.println(result);
        if(result == "Success!"){
            System.out.println("Path to succes:");
            for(Configuration c:solver.getRoute()){
                System.out.println(c);
            }
        }
    }
}
