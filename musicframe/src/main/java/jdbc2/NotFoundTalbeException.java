package jdbc2;

public class NotFoundTalbeException extends RuntimeException {

    public NotFoundTalbeException() {
        super("对不起，请先使用@Table标记Pojo类！");
    }
}
