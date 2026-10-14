public class MemberLimitExceededException extends Exception {
    public MemberLimitExceededException(String id) {
        super("Member loan limit exceeded: " + id);
    }
}
