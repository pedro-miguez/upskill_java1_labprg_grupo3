package network;

import utils.Constants;

/**
 * Current class has the tools to create a request to the webservice (as HttpRequest).
 */
public class HttpRequest {
    private HttpRequestType type;
    private String url;

    /**
     * Instantiates a new request to the webservice with set parameters.
     *
     * @param type the type of the request
     * @param url  the url for the request
     */
    public HttpRequest(HttpRequestType type, String url) {
        this.type = type;
        this.url = Constants.HOST + url;
    }

    /**
     * Gets type of the request.
     *
     * @return the type of the request
     */
    public HttpRequestType getType() {
        return type;
    }

    /**
     * Sets type of the request.
     *
     * @param type the type of the request
     */
    public void setType(HttpRequestType type) {
        this.type = type;
    }

    /**
     * Gets url for the request.
     *
     * @return the url for the request.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url for the request.
     *
     * @param url the url for the request.
     */
    public void setUrl(String url) {
        this.url = url;
    }


}
