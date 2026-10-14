public class EmailNotification implements Notification {
    public String send(String m) {
        return "EMAIL: " + m;
    }
}
