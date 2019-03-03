package br.com.summa.zxed.ex;

public class RootCauseException extends Exception {
    private static final long serialVersionUID = 1L;

    public static Exception getRootCause(Exception ex) {
        Exception root = ex;
        while (root.getCause() instanceof Exception) {
            root = (Exception)root.getCause();
        }
        return root;
    }

    public RootCauseException(Exception cause) {
        super(getRootCause(cause));
    }

    public String getMessage() {
        return getCause().getMessage().split("\\n")[0];
    }

    public String getLocalizedMessage() {
        return getCause().getLocalizedMessage().split("\\n")[0];
    }
}
