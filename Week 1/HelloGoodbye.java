public class HelloGoodbye {
    public static void main(String[] args) {
        String res = "Hello ";
        for (String arg : args){
            res += arg + " and ";
        }
        System.out.println(res);
        res = "Goodbye ";
        for (int i = args.length - 1; i >= 0; i--){
            res += args[i] + " and ";
        }
        System.out.println(res);
    }
}
