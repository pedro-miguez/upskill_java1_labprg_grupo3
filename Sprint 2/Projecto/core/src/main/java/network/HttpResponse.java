package network;

/**
 * Current class has te tools to create the webservice response (as HttpResponse).
 */
public class HttpResponse {
    private int status;
    private String body;

    /**
     * Instantiates a new webservice response with set parameters.
     *
     * @param status the status of the response
     * @param body   the body of the response
     */
    public HttpResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    /**
     * Gets status of the response.
     *
     * @return the status of the response
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status of the response.
     *
     * @param status the status of the response
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets body of the response.
     *
     * @return the body of the response
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body of the response.
     *
     * @param body the body of the response
     */
    public void setBody(String body) {
        this.body = body;
    }
}
