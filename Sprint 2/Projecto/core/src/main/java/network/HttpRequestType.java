package network;

/**
 * Current Class is an Enum Class that sets up the types of the request we can do to the webservice.
 */
public enum HttpRequestType {
    /**
     * GET, to obtain data.
     */
    GET,
    /**
     * POST, to publish data.
     */
    POST,
    /**
     * PUT, to update data.
     */
    PUT,
    /**
     * DELETE, to delete data.
     */
    DELETE
}
