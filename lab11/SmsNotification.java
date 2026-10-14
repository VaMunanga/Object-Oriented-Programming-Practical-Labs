public class SmsNotification implements Notification {
    public String send(String m) {
        return "SMS: " + m;
    }
}
