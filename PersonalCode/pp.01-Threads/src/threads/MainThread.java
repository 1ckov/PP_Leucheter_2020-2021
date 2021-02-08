package src.threads;

public class MainThread {
    public static void main(String[] args) {
        var nr = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of Processors : " + nr);
        var self = Thread.currentThread();
        System.out.println("Name : "+ self.getName());
        System.out.println("Priority : " + self.getPriority());
        System.out.println("ID : " + self.getId());
    }    
}