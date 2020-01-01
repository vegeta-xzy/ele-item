package com.ele.item.common.config.response;


/**
 * 
 * @author nec
 * @DESC 异常处理，一般为内部异常处理，方便定位问题
 *
 */
public class GlobalException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Status status;

    private GlobalException() {
        super();
    }

    public GlobalException(Status status) {
        super(status.toString());
        this.status = status;
    }

    public GlobalException(Status status, Throwable cause) {
        super(status.toString(), cause);
        this.status = status;
    }

    public GlobalException(Status status, String message, Throwable cause) {
        super(String.format("%s\t%s", status.toString(), message), cause);
        this.status = status;
    }
}
