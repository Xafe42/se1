public class HelloWorld {

    public static void main(String[] args) {
        var msg = "Hello World!";
        if(args.length > 0) {
            msg = String.format("Hello World, %s!", args[0]);
        }
        System.out.println(msg);
    }
}